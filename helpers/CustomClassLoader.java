package helpers;

import java.io.*;

public class CustomClassLoader implements ClassLoader{


    public CustomClassLoader() {
    }

    @Override
    public Class<?> findClass(String name) throws IOException, ClassNotFoundException {
        byte[] bt =  this.loadClassData(name);

        return this.defineClass(bt);
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
