package helpers;

import javax.tools.*;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;

public class CustomClassLoader implements ClassLoader{
    private JavaCompiler compiler;
    private StandardJavaFileManager fileManager;

    public CustomClassLoader() {
    }

    private void init(){
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.fileManager = this.compiler.getStandardFileManager(null,null,null);
    }

    @Override
    public Class<?> loadClass(File file, String type) throws Exception {
        this.init();

        File parentDirectory = file.getParentFile();

        this.fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(parentDirectory));

        Iterable<? extends JavaFileObject> compilationUnits = this.fileManager.getJavaFileObjectsFromFiles(Collections.singletonList(file));
        this.compiler.getTask(null, this.fileManager, null,null,null, compilationUnits).call();
        this.fileManager.close();

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{parentDirectory.toURI().toURL()});

        if(type.equals("test")){
            return classLoader.loadClass("tests." + file.getName().replace(".java", ""));
        } else{
            return classLoader.loadClass("models." + file.getName().replace(".java", ""));
        }
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
