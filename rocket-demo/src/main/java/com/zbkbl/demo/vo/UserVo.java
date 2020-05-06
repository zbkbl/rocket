package com.zbkbl.demo.vo;

import lombok.*;

/**
 * @author liuyang
 * @description
 * @date 2020/05/06 17:39
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private long id;

    private String userName;

    private String description;
}
