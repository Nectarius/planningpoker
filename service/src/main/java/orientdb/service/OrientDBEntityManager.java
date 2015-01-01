package orientdb.service;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.orientechnologies.orient.object.iterator.OObjectIteratorClass;
import org.springframework.stereotype.Service;
import orientdb.entity.PlanningGame;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by adelfiri on 1/1/15.
 */
@Service
public class OrientDBEntityManager {

    @PersistenceContext
    private EntityManager entityManager;

    private OObjectDatabaseTx databaseTx;

    public OObjectDatabaseTx getDatabaseTx() {
        return (OObjectDatabaseTx) entityManager.getDelegate();
    }

    public <RET> OObjectIteratorClass<RET> browseClass(final Class<RET> iClusterClass) {
        return getDatabaseTx().browseClass(iClusterClass, true);
    }

    public void persist(Object entity) {
        entityManager.persist(entity);
    }

    public <T> T find(Class<T> entityClass, Object primaryKey)  {
        return entityManager.find(entityClass, primaryKey);
    }
}
