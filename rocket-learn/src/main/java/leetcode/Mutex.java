package leetcode;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author liuyang
 * @description lock
 * @date 2021/03/03 11:50
 **/
public class Mutex implements Lock, Serializable {


    private static class Sync extends AbstractQueuedSynchronizer {
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        protected boolean tryAcquire(int acquires) {
            assert acquires == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int release){
            assert release == 1;
            if(getState() == 0){
                throw new IllegalMonitorStateException();
            }
            if(compareAndSetState(1,0)){
                setExclusiveOwnerThread(null);
                return true;
            }
            return false;
        }

        Condition newCondition(){
            return new ConditionObject();
        }
    }


    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.tryAcquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
