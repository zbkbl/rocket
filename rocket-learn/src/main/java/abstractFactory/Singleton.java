package abstractFactory;

/**
 * @author liuyang
 * @description
 * @date 2021/03/19 10:00
 **/
public class Singleton {

    private static class SingletonHolder{
        private static final Singleton instance = new Singleton();
    }

    private Singleton(){

    }
    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }
}
