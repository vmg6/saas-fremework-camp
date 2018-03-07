package base.core;

import base.controller.ContactAPI;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

/**
 * Created by @v.matviichenko
 */
public class GuiceBinderModule extends AbstractModule {

    protected void configure() {
        bind(TestProperties.class).toInstance(TestProperties.getInstance());
        Names.bindProperties(binder(), TestProperties.getInstance().getServerProperties());
        Names.bindProperties(binder(), TestProperties.getInstance().getCommonProperties());
        bind(ContactAPI.class).in(Singleton.class);
    }
}
