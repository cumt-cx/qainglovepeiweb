package com.demo.dao;

import com.demo.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by cumt_ on 2016/12/17.
 */
public interface UserDao {
    Integer login(@Param("userName") String userName, @Param("passWord") String passWord);

    User findByName(@Param("userName") String userName);
}

