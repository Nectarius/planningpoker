import com.orientechnologies.orient.core.entity.OEntityManager;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * Created by nectarius on 18.05.14.
 */

public class SchemeCreate {

    private String login = "root";

    private String password = "somethingPassword";

    @Test
    public void test() {

        OObjectDatabaseTx database = new OObjectDatabaseTx("remote:localhost/planningpoker-document").open(login, password );

        database.setAutomaticSchemaGeneration(true);

        database.getEntityManager().registerEntityClasses("entity");

        OEntityManager entityManager = database.getEntityManager();

        if(entityManager  instanceof OEntityManager ){
            OEntityManager oEntityManager = entityManager;
            Collection<Class<?>> entities = oEntityManager .getRegisteredEntities();

            for(Class cl : entities){
                System.out.println(cl.getName());
            }

        }

    }

}
