package com.zbkbl;

import static org.junit.Assert.assertTrue;

import abstractFactory.*;
import abstractFactory.singleton.Singleton;
import abstractFactory.staticFactory.Sender;
import builder.Meal;
import builder.MealBuilder;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test(){
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
    public void test2(){
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
    public void test4(){
        System.out.println(Singleton.getInstance());
    }


    @Test
    public void test5(){
        Set<Integer>  set = new HashSet<>();
        set.add(1);
        set.add(2);
        Set set1 = new HashSet();
        for (Integer i:set){
            set1.add(i);
        }

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            Integer a = iterator.next();
            if(a == 1){
                iterator.remove();
            }
        }

        System.out.println(set);
        System.out.println(set1);
    }
}
