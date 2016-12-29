package com.demo.dao.redis;

import com.demo.BaseTest;
import com.demo.dao.UserDao;
import com.demo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cumt_ on 2016/12/18.
 */
public class RedisDaoTest extends BaseTest{

    private Logger logger = LoggerFactory.getLogger(RedisDaoTest.class);

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void  getUser(){
        String  userNameId = "1";
        User user = redisDao.getUSer(userNameId);
        if(user==null){
            user = userDao.findByName("user"+userNameId);
            redisDao.putUser(user);
            logger.debug("user从数据库中取出");
        }else{
            logger.debug("user从redis缓存中取出的");
        }
        logger.info("userName = ["+user.getUserName()+"]"+"userEmail = ["+user.getEmail()+"]");
        Assert.assertTrue("user1@163.com".equals(user.getEmail()));
    }

    @Test
    public void putUser(){
        User user = userDao.findByName("user1");
        String result = redisDao.putUser(user);
        logger.info("putUser:"+result);
    }



}
