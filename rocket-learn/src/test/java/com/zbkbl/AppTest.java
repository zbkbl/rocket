package com.zbkbl;

import static org.junit.Assert.assertTrue;

import abstractFactory.*;
import abstractFactory.singleton.Singleton;
import abstractFactory.staticFactory.Sender;
import builder.Meal;
import builder.MealBuilder;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void test() {
        AbstractFactory shapeFactory = FactoryProducer.getFactory("shape");
        Shape shape1 = shapeFactory.getShape("circle");
        shape1.draw();

        AbstractFactory colorFactory = FactoryProducer.getFactory("color");
        Color color = colorFactory.getColor("red");
        color.fill();
    }

    @Test
    public void test1() {
        Provider p = new SendMailFactory();
        Sender s = p.produce();
        s.send();
    }

    @Test
    public void test2() {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " + vegMeal.getCost());


        Meal nonVegMeal = mealBuilder.prepareNonvegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " + nonVegMeal.getCost());
    }

    @Test
    public void test4() {
        System.out.println(Singleton.getInstance());
    }


    @Test
    public void test5() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        Set set1 = new HashSet();
        for (Integer i : set) {
            set1.add(i);
        }

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer a = iterator.next();
            if (a == 1) {
                iterator.remove();
            }
        }

        System.out.println(set);
        System.out.println(set1);
    }


    @Test
    public void test6() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "liuyang");
        jsonObject.put("filterInfo", "test");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "liuyang1");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", "liuyang2");
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("name", "liuyang3");
        List<JSONObject> filterInfos = new ArrayList<>();
        filterInfos.add(jsonObject);
        filterInfos.add(jsonObject1);
        filterInfos.add(jsonObject2);
        filterInfos.add(jsonObject3);
        for (JSONObject json : filterInfos) {
            json.remove("filterInfo");
        }
        System.out.println(filterInfos);
    }

    @Test
    public void test7() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++){
                if(j ==5){
                    break;
                }
                System.out.println(i*j);
            }
        }
    }
}
