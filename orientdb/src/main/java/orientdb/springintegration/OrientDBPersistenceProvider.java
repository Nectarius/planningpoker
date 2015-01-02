package orientdb.springintegration;

import com.orientechnologies.orient.object.jpa.OJPAPersistenceProvider;
import com.orientechnologies.orient.object.jpa.parsing.PersistenceXmlUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collection;
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

    private void setPersistenceUnits( URL persistenceXml) throws IllegalAccessException{
        Field[] fields = this.getClass().getSuperclass().getDeclaredFields();

        for(Field field : fields){
            if(field.getName().equals("persistenceUnits")){
                field.setAccessible(true);
                final Object oldValue = field.get(this);
                Collection info = PersistenceXmlUtil.parse(persistenceXml);
                field.set(this, info);
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
