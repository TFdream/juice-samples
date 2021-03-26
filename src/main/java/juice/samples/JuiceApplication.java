package juice.samples;

import juice.datasource.annotation.EnableDynamicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ricky Fung
 */
//开启动态数据源
@EnableDynamicDataSource(basePackages = {"juice.samples.storage.mapper", "juice.samples.storage.manager"})
@SpringBootApplication
public class JuiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JuiceApplication.class, args);
    }
}
