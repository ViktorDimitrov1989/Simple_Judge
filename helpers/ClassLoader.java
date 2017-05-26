package helpers;

import java.io.File;
import java.io.IOException;

public interface ClassLoader {

    Class<?> loadClass(File file, String type) throws IOException, ClassNotFoundException, Exception;

}
