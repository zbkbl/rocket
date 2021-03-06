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
 * @date 2020/05/06 16:04
 **/
@Configuration
@MapperScan(basePackages = PixiuDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "pixiuSqlSessionFactory")
public class PixiuDataSourceConfig {

    static final String PACKAGE = "com.zbkbl.demo.dao.pixiu";

    private static final String MAPPER_LOCATION = "classpath:mapper/rcd_pixiu/*.xml";

    @Value("${pixiu.datasource.url}")
    private String url;

    @Value("${pixiu.datasource.username}")
    private String user;

    @Value("${pixiu.datasource.password}")
    private String password;

    @Value("${pixiu.datasource.driverClassName}")
    private String driverClass;

    @Bean (name = "pixiuDataSource")
    @Primary
    public DataSource pixiuDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "pixiuTransactionManager")
    @Primary
    public DataSourceTransactionManager pixiuTransactionManager(){
        return new DataSourceTransactionManager(pixiuDataSource());
    }

    @Bean(name = "pixiuSqlSessionFactory")
    @Primary
    public SqlSessionFactory pixiuSqlSessionFactory(@Qualifier("pixiuDataSource") DataSource pixiuDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory  = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(pixiuDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PixiuDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
