package base.core;

import base.controller.ContactsAPI;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import helpers.ContactService;

/**
 * Created by @v.matviichenko
 */
public class GuiceBinderModule extends AbstractModule {

    protected void configure() {
        bind(TestProperties.class).toInstance(TestProperties.getInstance());
        Names.bindProperties(binder(), TestProperties.getInstance().getServerProperties());
        Names.bindProperties(binder(), TestProperties.getInstance().getCommonProperties());
        bind(ContactsAPI.class).in(Singleton.class);
        bind(ContactService.class).in(Singleton.class);
    }
}
