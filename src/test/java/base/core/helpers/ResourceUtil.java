package base.core.helpers;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by @v.matviichenko
 */
public class ResourceUtil {
    public static String getResourcePath(String resourceName) {

        return getResourceUrl(resourceName).getPath();
    }

    public static File getResourceFile(String resourceName) {

        return new File(getResourceUrl(resourceName).getPath());
    }

    public static InputStream getResourceAsStream(String resourceName) {

        if (ResourceUtil.class.getClassLoader().getResourceAsStream(resourceName) == null)
            throw new RuntimeException("Unable to get '" + resourceName + "' resource file!!!");

        return ResourceUtil.class.getClassLoader().getResourceAsStream(resourceName);

    }

    private static URL getResourceUrl(String resourceName) {

        if (ResourceUtil.class.getClassLoader().getResource(resourceName) == null)
            throw new RuntimeException("Unable to get '" + resourceName + "' resource file!!!");

        return ResourceUtil.class.getClassLoader().getResource(resourceName);
    }
}
