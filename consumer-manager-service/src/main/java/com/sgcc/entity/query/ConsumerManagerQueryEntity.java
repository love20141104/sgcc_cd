package com.sgcc.entity.query;

import com.google.common.collect.Lists;
import com.sgcc.dao.ConsumerManagerDao;
import com.sgcc.repository.ConsumerManagerRedisRepository;
import com.sgcc.repository.ConsumerManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumerManagerQueryEntity {
    @Autowired
    private ConsumerManagerRepository consumerManagerRepository;
    @Autowired
    private ConsumerManagerRedisRepository consumerManagerRedisRepository;

    /**
     * 根据id在redis中查询客户经理信息
     * @param consumerManagerId
     * @return
     */
    public ConsumerManagerDao findByIdInRedis(String consumerManagerId) {
        return consumerManagerRedisRepository.findById(consumerManagerId).orElse(null);
    }
    /**
     * 根据id在mySQL中查询客户经理信息
     * @param consumerManagerId
     * @return
     */
    public ConsumerManagerDao findById(String consumerManagerId){
        try {
            return consumerManagerRepository.selectConsumerManagerDaoByUserId(consumerManagerId);
        }
        catch (SQLException e )
        {
            return null;
        }
    }

    /**
     * 从redis中查所有的客户经理信息
     */
    public List<ConsumerManagerDao> findAllConsumerManagerInRedis() {
        List<ConsumerManagerDao> consumerManagerDaos = Lists.newArrayList(consumerManagerRedisRepository.findAll());
        return consumerManagerDaos;
    }

    /**
     * 从mysql中查所有的客户经理信息
     * @return
     */
    public List<ConsumerManagerDao> findAllConsumerManager() throws SQLException {
        List<ConsumerManagerDao> consumerManagerDaos = consumerManagerRepository.selectConsumerManagerDaos();
        return consumerManagerDaos;
    }


    public List<ConsumerManagerDao> findFiveConsumerManagerDaos() throws SQLException {
        List<ConsumerManagerDao> consumerManagerDaos = consumerManagerRepository.selectFiveConsumerManagerDaos();
        return consumerManagerDaos;
    }


}
