package test1;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-07-19 16:57
 **/

public class Test {
    public static void main(String[] args) {

        Class<Demo> clazz = Demo.class;
        try {
            Field field = clazz.getField("name");
            Seven sevenField = field.getAnnotation(Seven.class);
            System.out.println(sevenField.value());
            System.out.println(sevenField.Property());

            Method method =clazz.getMethod("hello");
            Seven sevenMethod = method.getAnnotation(Seven.class);
            System.out.println(sevenMethod.value());
            System.out.println(sevenMethod.Property());

            Field field1 = clazz.getField("age");
            Seven ageFiled = field1.getAnnotation(Seven.class);
            System.out.println(ageFiled.value());
            System.out.println(ageFiled.Property());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
