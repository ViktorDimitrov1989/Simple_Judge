package helpers;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomClassLoader implements ClassLoader{


    public CustomClassLoader() {
    }

    @Override
    public Class<?> loadClass(File file) throws Exception {
        URL[] url = new URL[0];

        try {
            url = new URL[]{file.toURI().toURL()};
        }catch (MalformedURLException e){
            System.out.println(e.getMessage());
        }

        int substringLength = file.getName().indexOf(".");

        if(substringLength != -1){
            String className = this.removeExtension(file.getName());


            URLClassLoader classLoader = new URLClassLoader(url);

            Class currentClass = null;

            try {
                currentClass = classLoader.loadClass("tests." + className);

                return currentClass;
            }catch (ClassNotFoundException e){
                System.out.println("Class " + className + " not found");
            }
        }

        return null;
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
