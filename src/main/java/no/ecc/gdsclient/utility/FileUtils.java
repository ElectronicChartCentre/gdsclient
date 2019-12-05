package no.ecc.gdsclient.utility;

import java.io.File;

public class FileUtils {

    /**
     * Convert path to native form
     * <p>
     * On windows: path/to/check will be converted to path\to\check
     * <p>
     * On unix: path\to\check will be converted to path/to/check
     * 
     * @param path
     * @return
     */
    public static String convertToNativePath(String path) {
        path = path.replace('\\', File.separatorChar);
        path = path.replace('/', File.separatorChar);
        return path;
    }

}