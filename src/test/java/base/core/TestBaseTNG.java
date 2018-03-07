package base.core;

import base.controller.ContactAPI;
import com.google.inject.Inject;
import org.testng.annotations.Guice;

/**
 * Created by @v.matviichenko
 */
@Guice(modules = GuiceBinderModule.class)
public abstract class TestBaseTNG {
    protected TestProperties properties = TestProperties.getInstance();

    @Inject
    protected ContactAPI apiEndpoints;
}
