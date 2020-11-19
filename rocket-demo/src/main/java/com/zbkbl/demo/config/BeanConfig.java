package com.zbkbl.demo.config;

import com.zbkbl.demo.config.bean.MyFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuyang
 * @description
 * @date 2020/11/19 15:42
 **/
@Configuration
@Slf4j
public class BeanConfig {

    @Bean
    public MyFactoryBean studentByFactoryBean() {
        MyFactoryBean stuFactoryBean = new MyFactoryBean();
        stuFactoryBean.setStudentInfo("Student[name=lily, age=15]");
        return stuFactoryBean;
    }
}
