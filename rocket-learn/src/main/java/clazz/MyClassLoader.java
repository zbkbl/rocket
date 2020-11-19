package clazz;

import java.io.*;

/**
 * @author liuyang
 * @description
 * @date 2020/10/28 17:16
 **/
public class MyClassLoader extends ClassLoader{

    private String classpath;

    public MyClassLoader(String classpath) {
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            byte[] classData = getClassBinaryData(name);
            if(classData == null){

            }else {
                return defineClass(name, classData, 0, classData.length);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return super.findClass(name);
    }


    private byte[] getClassBinaryData(String className) throws IOException {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        String path = classpath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try{
            in = new FileInputStream(path);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int len = 0;
            while ((len = in.read(buffer)) != -1){
                out.write(buffer, 0, len);
            }
            return out.toByteArray();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            in.close();
            out.close();
        }
        return null;
    }
}
