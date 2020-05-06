package com.zbkbl.demo.controller;

import com.zbkbl.demo.dao.phoenix.UserDao;
import com.zbkbl.demo.dao.pixiu.StudentDao;
import com.zbkbl.demo.service.UserService;
import com.zbkbl.demo.vo.StudentVo;
import com.zbkbl.demo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuyang
 * @description
 * @date 2020/05/06 17:45
 **/
@Controller
@RequestMapping(value = "/api/v1/test")
public class TestController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private StudentDao studentDao;

    @RequestMapping(value = "/getUser")
    @ResponseBody
    public UserVo getUser(@RequestParam(value = "id") Long id) {
        return userDao.findUserById(id);
    }

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "success";
    }

    @RequestMapping(value = "/getStudent")
    @ResponseBody
    public StudentVo getStudent(@RequestParam(value = "id") Long id) {
        return studentDao.findStudentById(id);
    }
}
