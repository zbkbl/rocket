package com.zbkbl.demo.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author liuyang
 * @description
 * @date 2020/05/06 16:59
 **/
@Configuration
@MapperScan(basePackages = PhoenixDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "phoenixSqlSessionFactory")
public class PhoenixDataSourceConfig {

    static final String PACKAGE = "com.zbkbl.demo.dao.phoenix";

    private static final String MAPPER_LOCATION = "classpath:mapper/rcd_phoenix/*.xml";

    @Value("${phoenix.datasource.url}")
    private String url;

    @Value("${phoenix.datasource.username}")
    private String user;

    @Value("${phoenix.datasource.password}")
    private String password;

    @Value("${phoenix.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "phoenixDataSource")
    public DataSource phoenixDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "phoenixTransactionManager")
    public DataSourceTransactionManager phoenixTransactionManager() {
        return new DataSourceTransactionManager(phoenixDataSource());
    }

    @Bean(name = "phoenixSqlSessionFactory")
    public SqlSessionFactory phoenixSqlSessionFactory(@Qualifier("phoenixDataSource") DataSource phoenixDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(phoenixDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PhoenixDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();

    }
}
