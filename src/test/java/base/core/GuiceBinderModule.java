package base.core;

import base.controller.APIEndpoints;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Created by @v.matviichenko
 */
public class GuiceBinderModule extends AbstractModule {

    protected void configure() {
        bind(APIEndpoints.class).in(Singleton.class);
    }
}
