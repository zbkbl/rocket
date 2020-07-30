package thread;

/**
 * @author liuyang
 * @description
 * @date 2020/07/11 15:06
 **/
public class PrintDemo {

    private Integer count = 0;

    static class Task1 implements Runnable {
        final PrintDemo printDemo;

        public Task1(PrintDemo printDemo) {
            this.printDemo = printDemo;
        }

        @Override
        public void run() {
            synchronized (printDemo){
                while(printDemo.count<=100){
                    System.out.println(Thread.currentThread().getName() + " print : " + printDemo.count++);
                    printDemo.notifyAll();
                    try{
                        if(printDemo.count<=100)
                            printDemo.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            }
        }
    }


    public static void main(String[] args) {
        PrintDemo printDemo = new PrintDemo();
        Task1 task1 = new Task1(printDemo);
        Thread thread1 = new Thread(task1);
        thread1.setName("print 偶数");

        Task1 task2 = new Task1(printDemo);
        Thread thread2 = new Thread(task2);
        thread2.setName("print 奇数");

        thread1.start();
        thread2.start();
    }
}
