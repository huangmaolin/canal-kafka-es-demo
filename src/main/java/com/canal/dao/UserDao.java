package com.canal.dao;

import com.canal.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by ThinkPad on 2021/3/2.
 */
public interface UserDao extends ElasticsearchRepository<User,Long>{
    public List<User> findAll();
}
