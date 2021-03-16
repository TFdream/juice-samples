package juice.samples.config;

import juice.ratelimiter.RateLimiterConfig;
import juice.ratelimiter.internal.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

/**
 * @author Ricky Fung
 */
@Configuration
public class DemoRateLimiterConfig {

    @Bean
    public RedisRateLimiter redisRateLimiter(StringRedisTemplate stringRedisTemplate) {
        // Create a custom RateLimiter configuration
        RateLimiterConfig config = RateLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(1000))
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(5)
                .build();
        // Create a RateLimiter
        return new RedisRateLimiter("redis", config, stringRedisTemplate);
    }
}
