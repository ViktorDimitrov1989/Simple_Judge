package helpers;

import java.io.File;
import java.io.IOException;

public interface ClassLoader {

    Class<?> loadClass(File file) throws IOException, ClassNotFoundException, Exception;

}
