package juice.samples;

import juice.config.springsupport.annotation.EnableDistributedLock;
import juice.config.springsupport.annotation.EnableDynamicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Ricky Fung
 */
@EnableDistributedLock
//开启动态数据源
@EnableDynamicDataSource
@SpringBootApplication
public class JuiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JuiceApplication.class, args);
        System.out.println(context);
    }
}
