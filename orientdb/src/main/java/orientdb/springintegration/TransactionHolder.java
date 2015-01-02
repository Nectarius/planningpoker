package orientdb.springintegration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.jpa.EntityManagerHolder;

/**
 * Created by adelfiri on 12/25/14.
 */
public class TransactionHolder {

    protected transient Log logger = LogFactory.getLog(getClass());

    private EntityManagerHolder entityManagerHolder;

    public TransactionHolder(EntityManagerHolder entityManagerHolder){
        this.entityManagerHolder = entityManagerHolder;
    }

    public EntityManagerHolder getEntityManagerHolder() {
        return entityManagerHolder;
    }

    public void setEntityManagerHolder(EntityManagerHolder entityManagerHolder) {
        this.entityManagerHolder = entityManagerHolder;
    }

}
