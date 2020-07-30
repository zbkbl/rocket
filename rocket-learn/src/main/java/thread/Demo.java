package thread;

/**
 * @author liuyang
 * @description thread demo
 * @date 2020/07/10 17:33
 **/
public class Demo {

    static class Task1 implements Runnable{
        final Object lock1;
        final Object lock2;

        public Task1(Object lock1, Object lock2){
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            synchronized(lock1){
                System.out.println(Thread.currentThread().getName() + " get lock 1");
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + " get lock 2");
                }
            }
        }
    }

    static class Task2 implements Runnable{
        final Object lock1;
        final Object lock2;
        public Task2(Object lock1, Object lock2){
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + " get lock 2");
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName() + " get lock 1");
                }
            }
        }
    }

    public static void main(String[] args) {
        final Object lock1 = new Object();
        final Object lock2 = new Object();
        Task1 task1 = new Task1(lock1, lock2);
        Thread t1 = new Thread(task1);
        t1.setName("thread-task-1");

        Task2 task2 = new Task2(lock1, lock2);
        Thread t2 = new Thread(task2);
        t2.setName("thread-task-2");

        t1.start();
        t2.start();
    }
}
