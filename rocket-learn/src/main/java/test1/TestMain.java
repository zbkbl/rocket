package test1;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Arrays.copyOf 测试
 * @author: Liuyang
 * @date: 2019-10-14 10:31
 **/
public class TestMain {

    public static void main(String[] args) {
        List<Student> a = new ArrayList<>();
        Student s = new Student("小明",10);
        a.add(s);
        a.add(new Student("小红",11));
        a.add(new Student("小昂",12));
        System.out.println(a);
        List<Student> b = new ArrayList<>(a);
        System.out.println(b);
        b.add(new Student("小浪",13));
        System.out.println(a);
        System.out.println(b);
        s.name = "小明明";
        System.out.println(a);
        System.out.println(b);

    }

    static class Student{
        String name;
        int age;

        public Student(){}

        public Student(String name,int age){
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
