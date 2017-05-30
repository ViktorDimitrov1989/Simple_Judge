package helpers;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;

public class CustomClassLoader implements ClassLoader{
    private JavaCompiler compiler;
    private StandardJavaFileManager fileManager;
    private URLClassLoader classLoader;

    public CustomClassLoader() {
        this.init();
    }

    private void init(){
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.fileManager = this.compiler.getStandardFileManager(null,null,null);
    }

    @Override
    public synchronized Class<?> loadClass(File file, String type) throws Exception {
        File parentDirectory = file.getParentFile();

        this.fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(parentDirectory));

        Iterable<? extends JavaFileObject> compilationUnits = this.fileManager.getJavaFileObjectsFromFiles(Collections.singletonList(file));
        this.compiler.getTask(null, this.fileManager, null,null,null, compilationUnits).call();
        this.fileManager.close();

        this.classLoader = URLClassLoader.newInstance(new URL[]{parentDirectory.toURI().toURL()});

        String className = "";

        if(type.equals("test")){
            className = "tests." + file.getName().replace(".java", "");
            Class<?> result = this.classLoader.loadClass(className);
            this.classLoader.close();
            return result;
        } else{
            className = "models." + file.getName().replace(".java", "");
            Class<?> result = this.classLoader.loadClass(className);
            this.classLoader.close();
            return result;
        }
    }


    @SuppressWarnings("unchecked")
    private void addPath(String s) throws Exception {
        File f = new File(s);
        Class urlClass = URLClassLoader.class;
        Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
        method.setAccessible(true);
        method.invoke(this.classLoader, new URL[]{f.toURI().toURL()});
    }

    private String removeExtension(String file){
        return file.replaceFirst("[.][^.]+$", "");
    }

    private Class<?> defineClass(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject().getClass();
    }


    private byte[] loadClassData(String className) {
        //read class
        InputStream is = getClass().getClassLoader().getResourceAsStream(className.replace(".", "/")+".class");
        ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
        //write into byte
        int len =0;
        try {
            while((len=is.read())!=-1){
                byteSt.write(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //convert into byte array
        return byteSt.toByteArray();
    }
}
