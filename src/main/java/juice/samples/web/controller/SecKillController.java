package juice.samples.web.controller;

import juice.contracts.ResultDTO;
import juice.ratelimiter.internal.RedisRateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品秒杀
 * @author Ricky Fung
 */
@RestController
@RequestMapping("/api/sec-kill")
public class SecKillController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisRateLimiter redisRateLimiter;

    @GetMapping("/submit")
    public ResultDTO submit(@RequestParam("productId") Integer productId) {
        boolean success = redisRateLimiter.tryAcquire();
        if (!success) {
            LOG.info("商品秒杀-提交请求, productId:{} 超出最大处理能力", productId);
            return ResultDTO.invalidParam("服务忙......");
        }
        LOG.info("商品秒杀-提交请求, productId:{} 秒杀成功", productId);
        return ResultDTO.ok();
    }
}
