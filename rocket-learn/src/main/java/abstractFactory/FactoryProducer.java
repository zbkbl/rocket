package abstractFactory;

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

}
