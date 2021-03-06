package com.canal.kafka.consumer;

import com.alibaba.fastjson.JSONObject;
import com.canal.dao.UserDao;
import com.canal.redis.RedisClient;
import com.canal.data.UserData;
import com.canal.entity.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ThinkPad on 2021/2/28.
 */
@Component
public class UserCanalConsumer {
    //日志记录
    private static Logger log = LoggerFactory.getLogger(UserCanalConsumer.class);
    //redis操作工具类
    @Resource
    private UserDao userDao;
    //监听的队列名称为：canaltopic
    @KafkaListener(topics = "user_canal_topic")
    public void receive(ConsumerRecord<?, ?> consumer) {
        String value = (String) consumer.value();
        log.info("topic名称:{},key:{},分区位置:{},下标:{},value:{}", consumer.topic(), consumer.key(),consumer.partition(), consumer.offset(), value);
        //转换为javaBean
        UserData canalBean = JSONObject.parseObject(value, UserData.class);
        //获取是否是DDL语句
        boolean isDdl = canalBean.isDdl();
        //获取类型
        String type = canalBean.getType();
        //不是DDL语句
        if (!isDdl) {
            List<User> users = canalBean.getData();
            //过期时间
            long TIME_OUT = 600L;
            if ("INSERT".equals(type)) {
                //新增语句
                for (User user : users) {
                    Long id = user.getId();
                    //新增到redis中,过期时间是10分钟
                    userDao.save(user);
                }
            } else if ("UPDATE".equals(type)) {
                //更新语句
                for (User user : users) {
                    Long id = user.getId();
                    //更新到redis中,过期时间是10分钟
                    userDao.save(user);
                }
            } else {
                //删除语句
                for (User user : users) {
                    Long id = user.getId();
                    //从redis中删除
                    userDao.delete(user);
                }
            }
        }
    }
}

