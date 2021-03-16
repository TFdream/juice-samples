package juice.samples.web.controller;

import juice.contracts.ResultDTO;
import juice.lock.DistributedLock;
import juice.lock.DistributedLockClient;
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

    @Resource
    private DistributedLockClient distributedLockClient;

    @GetMapping("/submit")
    public ResultDTO submit(@RequestParam("productId") Integer productId) {
        String key = String.format("lock:%s", productId);
        DistributedLock lock = distributedLockClient.getLock(key);
        boolean success = lock.tryLock(0, 10, TimeUnit.SECONDS);
        if (!success) {
            LOG.info("商品秒杀-提交请求, productId={} 加锁失败, key={}", productId, key);
            return ResultDTO.invalidParam("加锁失败");
        }
        try {
            //模拟业务处理逻辑
            long time = RandomUtils.nextLong(1, 10);
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
}
