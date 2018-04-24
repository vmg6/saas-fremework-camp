package base.core;

import base.controller.ContactsAPI;
import com.google.inject.Inject;
import helpers.ContactService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;

/**
 * Created by @v.matviichenko
 */
@Guice(modules = GuiceBinderModule.class)
public abstract class TestBaseTNG {
    protected TestProperties properties = TestProperties.getInstance();
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @Inject
    protected ContactsAPI apiEndpoints;

    @Inject
    protected ContactService contactService;

    @BeforeMethod
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; }));
    }
}
