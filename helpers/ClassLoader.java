package helpers;

import java.io.IOException;

public interface ClassLoader {

    Class<?> findClass(String name) throws IOException, ClassNotFoundException;
}
