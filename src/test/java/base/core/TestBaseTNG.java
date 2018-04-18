package base.core;

import base.controller.ContactsAPI;
import com.google.inject.Inject;
import helpers.ContactService;
import org.testng.annotations.Guice;

/**
 * Created by @v.matviichenko
 */
@Guice(modules = GuiceBinderModule.class)
public abstract class TestBaseTNG {
    protected TestProperties properties = TestProperties.getInstance();

    @Inject
    protected ContactsAPI apiEndpoints;

    @Inject
    protected ContactService contactService;
}
