package com.zbkbl.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuyang
 * @description
 * @date 2020/12/16 10:55
 **/
@Controller
@RequestMapping
@Slf4j
public class LoginController {

    @RequestMapping(value = "/login")
    public String getLogin(){
       return "/login";
    }
}
