package configuration;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by adelfiri on 1/2/15.
 */
@Service
public class MapperFacadeFactory implements FactoryBean<MapperFacade> {

    public MapperFacade getObject() throws Exception {
        return new DefaultMapperFactory.Builder().build().getMapperFacade();
    }

    public Class<?> getObjectType() {
        return MapperFacade.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
