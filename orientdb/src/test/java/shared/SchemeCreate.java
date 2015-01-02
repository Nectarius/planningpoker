package shared;

import com.orientechnologies.orient.core.entity.OEntityManager;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import org.junit.Test;


import java.util.Collection;

/**
 * temporary solutions
 */

public class SchemeCreate {

    private String login = "root";

    private String password = "somethingPassword";

    @Test
    public void test() {

        OObjectDatabaseTx database = new OObjectDatabaseTx("remote:localhost/planningpoker-document").open(login, password );

        database.setAutomaticSchemaGeneration(true);

        database.getEntityManager().registerEntityClasses("orientdb/entity");

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
