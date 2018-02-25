package base.core;

import base.controller.APIEndpoints;
import com.google.inject.Inject;
import org.testng.annotations.Guice;

/**
 * Created by @v.matviichenko
 */
@Guice(modules = GuiceBinderModule.class)
public abstract class TestBaseTNG {

    @Inject
    protected APIEndpoints apiEndpoints;
}
