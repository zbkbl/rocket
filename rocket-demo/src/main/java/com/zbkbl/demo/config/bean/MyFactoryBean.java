package com.zbkbl.demo.config.bean;

import com.zbkbl.demo.vo.StudentVo;
import org.springframework.beans.factory.FactoryBean;

import java.sql.Timestamp;

/**
 * @author liuyang
 * @description
 * @date 2020/11/19 15:37
 **/
public class MyFactoryBean implements FactoryBean<StudentVo> {

    String studentInfo;


    @Override
    public StudentVo getObject() throws Exception {
        System.out.println(studentInfo);
        StudentVo s = new StudentVo();
        s.setName("Bob");
        s.setAge(25);
        s.setId(13);
        s.setPassword("!@#$%");
        s.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return s;
    }

    @Override
    public Class<?> getObjectType() {
        return StudentVo.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(String studentInfo) {
        this.studentInfo = studentInfo;
    }
}
