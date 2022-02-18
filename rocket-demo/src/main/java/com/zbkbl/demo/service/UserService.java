package com.zbkbl.demo.service;

import com.zbkbl.demo.annotation.Activity;
import com.zbkbl.demo.annotation.FilterLogPoint;
import com.zbkbl.demo.po.IDResp;
import com.zbkbl.demo.vo.StudentVo;
import com.zbkbl.demo.vo.UserVo;

/**
 * @author liuyang
 * @description
 * @date 2020/05/06 17:45
 **/
public interface UserService {


    void testRawType(IDResp<StudentVo> idResp);


    void testRawType2(IDResp<UserVo> idResp);

    @FilterLogPoint(name = "geek")
    @Activity(name = "pinhaofan", activityEnum = "zhuli", logEventEnum = "start", userId = "#{#studentVo.userVo.id}", bizId = "#{#studentVo.name}")
    void test(StudentVo studentVo);
}
