package abstractFactory;

import org.junit.Test;

/**
 * @description: 工厂生成器
 * @author: Liuyang
 * @date: 2018-11-07 14:37
 **/
public class FactoryProducer {

    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
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
}
