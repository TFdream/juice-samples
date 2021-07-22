package juice.samples.web.controller;

import juice.contracts.ResultDTO;
import juice.lock.DistributedLock;
import juice.lock.DistributedLockManager;
import juice.lock.RedLock;
import juice.util.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁示例
 * @author Ricky Fung
 */
@RestController
@RequestMapping("/api/dist-lock")
public class DistributedLockController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private int maxWaitTime = 20;

    private int leaseTime = 45;

    @Resource
    private DistributedLockManager distributedLockManager;

    @GetMapping("/lock")
    public ResultDTO lock(@RequestParam(value = "productId", required = false) Integer productId) {
        if (productId == null) {
            return ResultDTO.invalidParam("productId必传");
        }
        String key = String.format("lock:%s", productId);
        DistributedLock lock = distributedLockManager.getLock(key);
        boolean success = lock.tryLock(0, leaseTime, TimeUnit.SECONDS);
        if (!success) {
            LOG.info("商品秒杀-提交请求, productId={} 加锁失败, key={}", productId, key);
            return ResultDTO.invalidParam("加锁失败");
        }
        try {
            //模拟业务处理逻辑
            long time = RandomUtils.nextLong(1, maxWaitTime);
            TimeUnit.SECONDS.sleep(time);

            LOG.info("商品秒杀-提交请求, productId={} 加锁成功", productId);
            return ResultDTO.ok();
        } catch (Exception e) {
            LOG.error("商品秒杀-提交请求异常", e);
        } finally {
            lock.unlock();
        }
        return ResultDTO.systemError();
    }

    @GetMapping("/multi-lock")
    public ResultDTO multiLock(@RequestParam(value = "productId", required = false) Integer productId) {
        if (productId == null) {
            return ResultDTO.invalidParam("productId必传");
        }

        DistributedLock lock1 = distributedLockManager.getLock(String.format("multi_lock:%s_%s", productId, 1));
        DistributedLock lock2 = distributedLockManager.getLock(String.format("multi_lock:%s_%s", productId, 2));
        DistributedLock lock3 = distributedLockManager.getLock(String.format("multi_lock:%s_%s", productId, 3));

        DistributedLock multiLock = distributedLockManager.getMultiLock(lock1, lock2, lock3);
        boolean success = multiLock.tryLock(0, leaseTime, TimeUnit.SECONDS);
        if (!success) {
            LOG.info("分布式锁-多锁-提交请求, productId={} 加锁失败", productId);
            return ResultDTO.invalidParam("加锁失败");
        }
        try {
            //模拟业务处理逻辑
            long time = RandomUtils.nextLong(1, maxWaitTime);
            TimeUnit.SECONDS.sleep(time);

            LOG.info("分布式锁-多锁-提交请求, productId={} 加锁成功", productId);
            return ResultDTO.ok();
        } catch (Exception e) {
            LOG.error("分布式锁-多锁-提交请求异常", e);
        } finally {
            multiLock.unlock();
        }
        return ResultDTO.systemError();
    }

    @GetMapping("/red-lock")
    public ResultDTO redLock(@RequestParam(value = "productId", required = false) Integer productId) {
        if (productId == null) {
            return ResultDTO.invalidParam("productId必传");
        }

        DistributedLock lock1 = distributedLockManager.getLock(String.format("red_lock:%s_%s", productId, 1));
        DistributedLock lock2 = distributedLockManager.getLock(String.format("red_lock:%s_%s", productId, 2));
        DistributedLock lock3 = distributedLockManager.getLock(String.format("red_lock:%s_%s", productId, 3));

        RedLock redLock = new RedLock(lock1, lock2, lock3);
        boolean success = redLock.tryLock(0, leaseTime, TimeUnit.SECONDS);
        if (!success) {
            LOG.info("分布式锁-红锁-提交请求, productId={} 加锁失败", productId);
            return ResultDTO.invalidParam("加锁失败");
        }
        try {
            //模拟业务处理逻辑
            long time = RandomUtils.nextLong(1, maxWaitTime);
            TimeUnit.SECONDS.sleep(time);

            LOG.info("分布式锁-红锁-提交请求, productId={} 加锁成功", productId);
            return ResultDTO.ok();
        } catch (Exception e) {
            LOG.error("分布式锁-红锁-提交请求异常", e);
        } finally {
            redLock.unlock();
        }
        return ResultDTO.systemError();
    }
}
