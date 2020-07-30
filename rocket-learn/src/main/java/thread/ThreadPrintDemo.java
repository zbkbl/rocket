package thread;

/**
 * @author liuyang
 * @description
 * @date 2020/07/11 15:48
 **/
public class ThreadPrintDemo {

    private static int count = 0;
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    private final Object lock3 = new Object();

    public void multiTurning(){
        Thread t1 = new Thread(()->{
            while (count <= 100) {
                synchronized (lock2) {
                    synchronized (lock1) {
                        System.out.println("thread-1 : 1");
                        count++;
                        lock1.notifyAll();
                    }
                    try {
                        if (count <= 100) {
                            lock2.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
           while (count<=100){
               synchronized (lock3){
                   synchronized (lock2){
                       System.out.println("thread-2 : 2");
                       count++;
                       lock2.notifyAll();
                   }
                   try{
                       if (count <=100){
                           lock3.wait();
                       }
                   }catch (InterruptedException e){
                       e.printStackTrace();
                   }
               }
           }
        });

        Thread t3 = new Thread(() -> {
            while (count<=100){
                synchronized (lock1){
                    synchronized (lock3){
                        System.out.println("thread-3 : 3");
                        count++;
                        lock3.notifyAll();
                    }
                    try{
                        if(count <=100){
                            lock1.wait();
                        }
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    public static void main(String[] args) {
        ThreadPrintDemo t = new ThreadPrintDemo();
        t.multiTurning();
    }
}
