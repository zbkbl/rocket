package clazz;

import com.zbkbl.BlockingQueue;

/**
 * @author liuyang
 * @description
 * @date 2020/10/28 17:15
 **/
public class ClassLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException,
            SecurityException, IllegalArgumentException {
        MyClassLoader myClassLoader = new MyClassLoader("/Users/liuyang/workspace/java");
        Object c = myClassLoader.loadClass("com.zbkbl.BlockingQueue");
        if (c != null) {
            System.out.println(c);
            System.out.println(c instanceof BlockingQueue);
        }
    }
}
