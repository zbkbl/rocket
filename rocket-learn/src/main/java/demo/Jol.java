package demo;

import org.openjdk.jol.info.ClassLayout;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author liuyang
 * @description
 * @date 2020/11/20 10:48
 **/
public class Jol {

    public static void main(String[] args) {
        test3();
    }

    public static void test1() {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        o.hashCode();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    private static void test2() {
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);
    }

    private static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            return unsafe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void test3(){
        boolean isCalc = Boolean.getBoolean("true");
        System.out.println(isCalc);
    }
}
