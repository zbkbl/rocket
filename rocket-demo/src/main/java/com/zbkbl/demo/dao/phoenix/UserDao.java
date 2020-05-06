package com.zbkbl.demo.dao.phoenix;


import com.zbkbl.demo.vo.UserVo;

public interface UserDao {

    UserVo findUserById(long id);
}
