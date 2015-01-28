package orientdb.springintegration;

import com.orientechnologies.orient.core.entity.OEntityManager;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.orientechnologies.orient.object.jpa.OJPAPersistenceProvider;
import com.orientechnologies.orient.object.jpa.OJPAPersistenceUnitInfo;
import com.orientechnologies.orient.object.jpa.parsing.PersistenceXmlUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.persistence.spi.PersistenceUnitInfo;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Logger;

import static com.orientechnologies.orient.object.jpa.parsing.PersistenceXmlUtil.PERSISTENCE_XML;

/**
 * Created by adelfiri on 12/25/14.
 */
public class OrientDBPersistenceProvider extends OJPAPersistenceProvider {
    /**
     * the log used by this class.
     */
    private static Logger logger = Logger.getLogger(OrientDBPersistenceProvider.class.getName());

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private void applyAdvanceOptions(Properties properties){

        String password = properties.getProperty("javax.persistence.jdbc.password");

        String login = properties.getProperty("javax.persistence.jdbc.user");

        String url = properties.getProperty("javax.persistence.jdbc.url");

        String ddl = properties.getProperty("orientdb.ddl-generation");

        String entitiesLocation = properties.getProperty("orientdb.entity");

        if(ddl.equals("create-tables")){

            createTables(url, login, password, entitiesLocation);

        }

    }

    private void createTables(String url, String login, String password, String entitiesLocation) {

        OObjectDatabaseTx database = new OObjectDatabaseTx(url).open(login, password );

        //String curDir = new File("").getAbsolutePath();

        //System.out.println(curDir);

        database.setAutomaticSchemaGeneration(true);

        database.getEntityManager().registerEntityClasses(entitiesLocation);
 /*
        OEntityManager entityManager = database.getEntityManager();

        if(entityManager  instanceof OEntityManager ){
            OEntityManager oEntityManager = entityManager;
            Collection<Class<?>> entities = oEntityManager .getRegisteredEntities();

            for(Class cl : entities){
                System.out.println(cl.getName());
            }

        }*/

    }

    private void setPersistenceUnits( URL persistenceXml) throws IllegalAccessException{
        Field[] fields = this.getClass().getSuperclass().getDeclaredFields();

        for(Field field : fields){
            if(field.getName().equals("persistenceUnits")){
                field.setAccessible(true);
                final Object oldValue = field.get(this);
                Collection info = PersistenceXmlUtil.parse(persistenceXml);

                if(info.size()<1){
                    throw new RuntimeException("Persistence units not found");
                }
                field.set(this, info);

                Object properties = info.toArray()[0];

                if(properties instanceof PersistenceUnitInfo){
                    applyAdvanceOptions(((PersistenceUnitInfo)properties).getProperties());
                }
            }
        }

    }

    public OrientDBPersistenceProvider(String persistenceXmlLocation) {
        try {

            Resource[] resources = resourcePatternResolver.getResources(persistenceXmlLocation);
            URL persistenceXml = resources[0].getURL();
            setPersistenceUnits(persistenceXml);

        } catch (Exception e) {
            logger.info("Can't parse '" + PERSISTENCE_XML + "' :" + e.getMessage());
        }
    }


}
