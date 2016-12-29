package com.demo.dao;

import com.demo.BaseTest;
import com.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cumt_ on 2016/12/17.
 */
public class UserDaoTest extends BaseTest {

    private final  static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    private UserDao userDao;

    @Test
    public void loginTest() {
        userDao.login("admin","admin");
    }

    @Test
    public void findByName(){
        String userName = "user1";
        User user = userDao.findByName(userName);
        logger.info("userName = ["+userName+"]"+"userEmail = ["+user.getEmail()+"]");
        Assert.assertTrue("user1@163.com".equals(user.getEmail()));
    }
}
