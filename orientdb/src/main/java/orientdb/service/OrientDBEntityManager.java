package orientdb.service;

import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.query.OQuery;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.orientechnologies.orient.object.iterator.OObjectIteratorClass;
import org.springframework.stereotype.Service;
import orientdb.entity.PlanningGame;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public <RET extends List<?>> RET query(OQuery<?> iCommand, Object... iArgs){
        return getDatabaseTx().query(iCommand, iArgs);
    }

    public <RET> OObjectIteratorClass<RET> browseClass(final Class<RET> iClusterClass) {
        return getDatabaseTx().browseClass(iClusterClass, true);
    }

    public <T> T persist(T entity) {
        return getDatabaseTx().save(entity);
    }

    public <T> T find(Class<T> entityClass, Object primaryKey)  {
        return entityManager.find(entityClass, primaryKey);
    }

    public void delete(String key){
        ORecordId oRecordId = new ORecordId(key);
        getDatabaseTx().delete(oRecordId);
    }

}
