package com.zbkbl.demo.controller;

import com.zbkbl.demo.dao.phoenix.UserDao;
import com.zbkbl.demo.dao.pixiu.StudentDao;
import com.zbkbl.demo.event.DemoEvent;
import com.zbkbl.demo.po.IDResp;
import com.zbkbl.demo.service.AopTestService;
import com.zbkbl.demo.service.AopTestServiceImpl;
import com.zbkbl.demo.service.SpringEventService;
import com.zbkbl.demo.service.UserService;
import com.zbkbl.demo.vo.StudentVo;
import com.zbkbl.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liuyang
 * @description
 * @date 2020/05/06 17:45
 **/
@Controller
@RequestMapping(value = "/api/v1/test")
@Slf4j
public class TestController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private UserService userService;

    @Resource
    private SpringEventService springEventService;

    @Resource(name = "studentByFactoryBean")
    private StudentVo studentVo;

    @RequestMapping(value = "/getUser")
    @ResponseBody
    public UserVo getUser(@RequestParam(value = "id") Long id) {
        return userDao.findUserById(id);
    }

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        springEventService.publishEventAsync(new DemoEvent("liuyang",26));
        log.info("student:{}", studentVo);
        return "success";
    }

    @RequestMapping(value = "/getStudent")
    @ResponseBody
    public StudentVo getStudent(@RequestParam(value = "id") Long id) {
        return studentDao.findStudentById(id);
    }

    @RequestMapping(value = "/testRawType")
    @ResponseBody
    public String rawType(){
        Set<StudentVo> studentVoSet = new HashSet<>();
        StudentVo s1 = new StudentVo(222,"ly1","123456",25, new Timestamp(System.currentTimeMillis()), new UserVo(111, "spel", "test") );
        StudentVo s2 = new StudentVo(333,"ly2","123456",26, new Timestamp(System.currentTimeMillis()), null);
        StudentVo s3 = new StudentVo(444,"ly3","123456",27, new Timestamp(System.currentTimeMillis()), null);
        studentVoSet.add(s1);
        studentVoSet.add(s2);
        studentVoSet.add(s3);
        IDResp<StudentVo> idResp = new IDResp<>(studentVoSet);
        userService.test(s1);
        return "success";
    }
}
