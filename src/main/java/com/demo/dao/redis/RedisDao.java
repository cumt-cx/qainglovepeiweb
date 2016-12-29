package com.demo.dao.redis;

import com.demo.entity.User;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by cumt_ on 2016/12/17.
 */
public class RedisDao {

    private final static  Logger logger = LoggerFactory.getLogger(RedisDao.class);

    private final static String USER_KEY_PREFIX = "user:";

    private JedisPool jedisPool;

    private RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);

    public RedisDao(String ip,int port ){
        jedisPool = new JedisPool(ip,port);
    }

    public User getUSer(String  userNameId){
        try {
            Jedis jedis= jedisPool.getResource();
            try {
                String key = USER_KEY_PREFIX+userNameId;
                byte[] bytes = jedis.get(key.getBytes());
                User user = schema.newMessage();
                ProtobufIOUtil.mergeFrom(bytes,user,schema);
                return user;
            }finally {
                jedis.close();
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    public String putUser(User user){
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String userName = user.getUserName();
                String key = USER_KEY_PREFIX+userName.charAt(userName.length()-1)+"";
                byte[] bytes = ProtobufIOUtil.toByteArray(user,schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存
                int  timeout = 2*60;
                String result = jedis.setex(key.getBytes(),timeout,bytes);
                return result;
            }catch (Exception e){
                logger.error(e.getMessage());
            }finally {
                jedis.close();
            }
        }catch (Exception e){

        }
        return null;
    }



}
