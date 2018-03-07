package base.core;

import java.io.InputStream;

/**
 * Created by @v.matviichenko
 */
public class ResourceUtil {

    public static InputStream getResourceAsStream(String resourceName) {

        if (ResourceUtil.class.getClassLoader().getResourceAsStream(resourceName) == null)
            throw new RuntimeException("Unable to get '" + resourceName + "' resource file!!!");

        return ResourceUtil.class.getClassLoader().getResourceAsStream(resourceName);
    }
}
