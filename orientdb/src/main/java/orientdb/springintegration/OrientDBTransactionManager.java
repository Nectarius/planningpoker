package orientdb.springintegration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.ResourceTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.*;

/**
 * Created by adelfiri on 12/25/14.
 */
public class OrientDBTransactionManager extends AbstractPlatformTransactionManager
        implements ResourceTransactionManager, InitializingBean {

    private EntityManagerFactory entityManagerFactory;

    /**
     * Create a new JpaTransactionManager instance.
     * A EntityManagerFactory has to be set to be able to use it.
     *
     * @see #setEntityManagerFactory
     */
    public OrientDBTransactionManager() {
        setNestedTransactionAllowed(true);
    }

    /**
     * Create a JPA EntityManager to be used for a transaction.
     * <p>The default implementation checks whether the EntityManagerFactory
     * is a Spring proxy and unwraps it first.
     *
     * @see javax.persistence.EntityManagerFactory#createEntityManager()
     * @see EntityManagerFactoryInfo#getNativeEntityManagerFactory()
     */
    protected EntityManager createEntityManagerForTransaction() {
        EntityManagerFactory emf = getEntityManagerFactory();
        return emf.createEntityManager();
    }

    @Override
    protected Object doGetTransaction() throws TransactionException {
        EntityManagerHolder emHolder = (EntityManagerHolder)
                TransactionSynchronizationManager.getResource(getEntityManagerFactory());
        TransactionHolder transactionHolder = new TransactionHolder(emHolder);
        return transactionHolder;
    }


    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {

        TransactionHolder transactionHolder = (TransactionHolder) transaction;

        try {
            if (transactionHolder.getEntityManagerHolder() == null ||
                    transactionHolder.getEntityManagerHolder().isSynchronizedWithTransaction()) {
                EntityManager newEm = createEntityManagerForTransaction();
                if (logger.isDebugEnabled()) {
                    logger.debug("Opened new EntityManager [" + newEm + "] for Orient DB transaction");
                }
                transactionHolder.setEntityManagerHolder(new EntityManagerHolder(newEm));
            }

            EntityManager em = transactionHolder.getEntityManagerHolder().getEntityManager();

            // Delegate to JpaDialect for actual transaction begin.
            final int timeoutToUse = determineTimeout(definition);

            em.getTransaction().begin();

            // Register transaction timeout.
            if (timeoutToUse != TransactionDefinition.TIMEOUT_DEFAULT) {
                transactionHolder.getEntityManagerHolder().setTimeoutInSeconds(timeoutToUse);
            }

            //
            // it's something to think about ... {Bind the orientdb.entity manager holder to the thread.}
            /*if (transactionHolder.isNewEntityManagerHolder()) {
                TransactionSynchronizationManager.bindResource(
                        getEntityManagerFactory(), txObject.getEntityManagerHolder());
            }*/
            transactionHolder.getEntityManagerHolder().setSynchronizedWithTransaction(true);
        } catch (TransactionException ex) {
            //closeEntityManagerAfterFailedBegin(transactionHolder);
            throw ex;
        } catch (Throwable ex) {
            //closeEntityManagerAfterFailedBegin(transactionHolder);
            throw new CannotCreateTransactionException("Could not open Orient DB EntityManager for transaction", ex);
        }

    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) throws TransactionException {

        TransactionHolder transactionHolder = (TransactionHolder) status.getTransaction();

        try {
            EntityTransaction tx = transactionHolder.getEntityManagerHolder().getEntityManager().getTransaction();
            tx.commit();
        } catch (RollbackException ex) {
            if (ex.getCause() instanceof RuntimeException) {


            }
            throw new TransactionSystemException("Could not commit JPA transaction", ex);
        } catch (RuntimeException ex) {
            throw new TransactionSystemException("Couldn't resist query ...  ", ex);
            // Assumably failed to flush changes to database.

        }

    }

    @Override
    protected void doRollback(DefaultTransactionStatus status) throws TransactionException {

        TransactionHolder transactionHolder = (TransactionHolder) status.getTransaction();

        try {
            EntityTransaction tx = transactionHolder.getEntityManagerHolder().getEntityManager().getTransaction();

            tx.rollback();

        } catch (PersistenceException ex) {
            throw new TransactionSystemException("Could not roll back JPA transaction", ex);
        } finally {

            // Clear all pending inserts/updates/deletes in the EntityManager.
            // Necessary for pre-bound EntityManagers, to avoid inconsistent state.
            transactionHolder.getEntityManagerHolder().getEntityManager().clear();

        }

    }

    /**
     * Create a new JpaTransactionManager instance.
     *
     * @param emf EntityManagerFactory to manage transactions for
     */
    public OrientDBTransactionManager(EntityManagerFactory emf) {
        this();
        this.entityManagerFactory = emf;
        afterPropertiesSet();
    }


    /**
     * Set the EntityManagerFactory that this instance should manage transactions for.
     * <p>Alternatively, specify the orientdb.springintegration unit name of the target EntityManagerFactory.
     * By default, a default EntityManagerFactory will be retrieved through finding a
     * single unique bean of type EntityManagerFactory in the containing BeanFactory.
     */
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.entityManagerFactory = emf;
    }

    /**
     * Return the EntityManagerFactory that this instance should manage transactions for.
     */
    public EntityManagerFactory getEntityManagerFactory() {
        return this.entityManagerFactory;
    }


    @Override
    public void afterPropertiesSet() {


    }

    @Override
    public Object getResourceFactory() {
        return getEntityManagerFactory();
    }
}

