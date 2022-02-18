package com.zbkbl.demo.service.impl;

import com.zbkbl.demo.annotation.Activity;
import com.zbkbl.demo.annotation.FilterLogPoint;
import com.zbkbl.demo.po.IDResp;
import com.zbkbl.demo.service.UserService;
import com.zbkbl.demo.vo.StudentVo;
import com.zbkbl.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author liuyang
 * @description
 * @date 2020/05/19 10:13
 **/
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    @FilterLogPoint(name = "geek")
    public void testRawType(IDResp<StudentVo> idResp) {
        log.info("execute testRawType ....");
        try {
            Thread.sleep(new Random().nextInt(200));
        }catch (InterruptedException e){
            log.error("execute testRawType has been interrupted ",e);
        }
    }

    @Override
    @FilterLogPoint(name = "boss")
    public void testRawType2(IDResp<UserVo> idResp) {
        log.info("execute testRawType2 ....");
    }

    @Override
    @Activity(name = "pinhaofan", activityEnum = "zhuli", logEventEnum = "start", userId = "#{#studentVo.userVo.id}", bizId = "#{#studentVo.name}")
//    @Activity(name = "tuanhaohuo")
    public void test(StudentVo studentVo) {
        log.info("execute test ....");
    }
}
