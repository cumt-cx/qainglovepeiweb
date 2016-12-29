package com.demo.service;

import com.demo.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cumt_ on 2016/12/17.
 */
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void loginTest(){
        userService.login("admin","admin");
    }

}
