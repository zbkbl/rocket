package abstractFactory.singleton;

/**
 * @description: 单例模式
 * @author: Liuyang
 * @date: 2018-11-07 15:36
 **/
public class Singleton {

    private static volatile Singleton instance;

    private Singleton(){

    }

    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
