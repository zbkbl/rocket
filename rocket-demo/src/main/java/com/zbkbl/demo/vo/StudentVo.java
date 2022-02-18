package com.zbkbl.demo.vo;

import lombok.*;

import java.sql.Timestamp;

/**
 * @author liuyang
 * @description
 * @date 2020/05/06 17:38
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentVo {

    private long id;

    private String name;

    private String password;

    private int age;

    private Timestamp updateTime;

    private UserVo userVo;
}
