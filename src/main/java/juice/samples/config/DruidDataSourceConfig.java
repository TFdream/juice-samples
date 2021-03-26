package juice.samples.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import juice.datasource.DynamicDataSource;
import juice.samples.constants.DataSourceKey;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ricky Fung
 */
@Configuration
@MapperScan(value = "juice.samples.storage.**.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class DruidDataSourceConfig {

    @Value("${mybatis.mapper-locations:}")
    private String mapperLocations;

    @Value("${mybatis.type-aliases-package:}")
    private String typeAliasesPackage;

    @Value("${mybatis.configLocation:}")
    private String configLocation;

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        bean.setTypeAliasesPackage(typeAliasesPackage);
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(configLocation));
        return bean.getObject();
    }

    //===========
    @Bean
    public DynamicDataSource dynamicDataSource() {
        Map<String,String> pkgDefaultDsKeyMap = new HashMap<>(4);
        pkgDefaultDsKeyMap.put("juice.samples.storage.mapper.member", DataSourceKey.MASTER_MEMBER);
        pkgDefaultDsKeyMap.put("juice.samples.storage.mapper.product", DataSourceKey.MASTER_PRODUCT);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> tarDsMap = new HashMap<>(8);
        tarDsMap.put(DataSourceKey.MASTER_MEMBER, memberMasterDS());
        tarDsMap.put(DataSourceKey.SLAVE_MEMBER, memberSlaveDS());
        tarDsMap.put(DataSourceKey.MASTER_PRODUCT, productMasterDS());
        tarDsMap.put(DataSourceKey.SLAVE_PRODUCT, productSlaveDS());
        dynamicDataSource.setTargetDataSources(tarDsMap);

        dynamicDataSource.setDefaultTargetDataSource(tarDsMap.get(DataSourceKey.MASTER_MEMBER));

        dynamicDataSource.setPkgDefaultDsKeyMap(pkgDefaultDsKeyMap);

        return dynamicDataSource;
    }

    //==========
    @Bean
    @ConfigurationProperties("spring.datasource.member.master")
    public DataSource memberMasterDS(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.member.slave")
    public DataSource memberSlaveDS(){
        return DruidDataSourceBuilder.create().build();
    }

    //==========
    @Bean
    @ConfigurationProperties("spring.datasource.product.master")
    public DataSource productMasterDS(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.product.slave")
    public DataSource productSlaveDS(){
        return DruidDataSourceBuilder.create().build();
    }

}
