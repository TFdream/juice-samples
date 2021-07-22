package juice.samples.lock;

import juice.contracts.ResultDTO;
import juice.lock.DistributedLock;
import juice.lock.DistributedLockManager;
import juice.samples.BaseSpringBootJUnitTest;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Ricky Fung
 */
public class LockTest extends BaseSpringBootJUnitTest {

    @Resource
    private DistributedLockManager distributedLockManager;

    private int leaseTime = 60;

    @Test
    @Ignore
    public void testMultiLock() {
        for (int i=0; i<20; i++) {
            Integer productId = Integer.valueOf(i+1);
            DistributedLock lock1 = distributedLockManager.getLock(String.format("multi_lock:%s_%s", productId, 1));
            DistributedLock lock2 = distributedLockManager.getLock(String.format("multi_lock:%s_%s", productId, 2));
            DistributedLock lock3 = distributedLockManager.getLock(String.format("multi_lock:%s_%s", productId, 3));

            DistributedLock multiLock = distributedLockManager.getMultiLock(lock1, lock2, lock3);
            boolean success = multiLock.tryLock(0, leaseTime, TimeUnit.SECONDS);
            if (success) {
                System.out.println(String.format("多锁-加锁成功, productId=%s", productId));
            } else {
                System.out.println(String.format("多锁-加锁失败, productId=%s", productId));
            }
        }
    }

    @Test
    @Ignore
    public void testLock() {
        for (int i=0; i<20; i++) {
            Integer productId = Integer.valueOf(i+1);
            String key = String.format("lock:%s", productId);
            DistributedLock lock = distributedLockManager.getLock(key);
            boolean success = lock.tryLock(0, leaseTime, TimeUnit.SECONDS);
            if (success) {
                System.out.println(String.format("商品秒杀-提交请求, productId=%s 加锁成功, key=%s", productId, key));
            } else {
                System.out.println(String.format("商品秒杀-提交请求, productId=%s 加锁失败, key=%s", productId, key));
            }
        }
    }
}
