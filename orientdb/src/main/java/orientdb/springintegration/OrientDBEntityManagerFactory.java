package orientdb.springintegration;

import org.springframework.beans.factory.FactoryBean;

import javax.persistence.EntityManagerFactory;
import java.util.Map;

/**
 * Created by adelfiri on 12/25/14.
 */
public class OrientDBEntityManagerFactory implements FactoryBean<EntityManagerFactory> {

    private String persistenceXmlLocation = "classpath*:/orientdb.springintegration.xml";

    private String persistenceUnitName = "defaultPersistenceName";

    private OrientDBPersistenceProvider provider = null;

    private EntityManagerFactory entityManagerFactory = null;

    private Map map = null;

    public EntityManagerFactory getEntityManagerFactory(){
        if(entityManagerFactory == null){
            entityManagerFactory = getProvider().createEntityManagerFactory(persistenceUnitName, map);
        }
        return entityManagerFactory ;
    }

    public OrientDBPersistenceProvider getProvider() {
        if(provider == null){
            provider = new OrientDBPersistenceProvider(persistenceXmlLocation);
        }
        return provider;
    }

    public String getPersistenceXmlLocation() {
        return persistenceXmlLocation;
    }

    public void setPersistenceXmlLocation(String persistenceXmlLocation) {
        this.persistenceXmlLocation = persistenceXmlLocation;
    }

    public String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    public void setPersistenceUnitName(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    @Override
    public EntityManagerFactory getObject() throws Exception {
        return getEntityManagerFactory();
    }

    @Override
    public Class<? extends EntityManagerFactory> getObjectType() {
        return getEntityManagerFactory().getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
