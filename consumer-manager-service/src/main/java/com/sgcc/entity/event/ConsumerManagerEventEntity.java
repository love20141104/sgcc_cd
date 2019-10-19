package com.sgcc.entity.event;

import com.sgcc.dao.ConsumerManagerDao;
import com.sgcc.repository.ConsumerManagerRedisRepository;
import com.sgcc.repository.ConsumerManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class ConsumerManagerEventEntity {
    @Autowired
    private ConsumerManagerRepository consumerManagerRepository;
    @Autowired
    private ConsumerManagerRedisRepository consumerManagerRedisRepository;

    /**
     * 新增客户经理信息存入mysql
     * @param consumerManagerDao
     */
    public void insertConsumerManager(ConsumerManagerDao consumerManagerDao) throws SQLException {
        consumerManagerRepository.insertConsumerManager(consumerManagerDao);
    }

    /**
     * 新增客户经理信息存入redis
     * @param consumerManagerDao
     */
    public void insertRedisConsumerManager(ConsumerManagerDao consumerManagerDao){
        consumerManagerRedisRepository.save(consumerManagerDao);
    }

    /**
     * 删除Mysql客户经理
     * @param consumerManagerId
     * @throws SQLException
     */
    public void deleteConsumerManager(String consumerManagerId) throws SQLException{
        consumerManagerRepository.deleteConsumerManagerDao(consumerManagerId);
    }

    /**
     * 删除Redis客户经理
     * @param consumerManagerId
     */
    public void deleteRedisConsumerManager(String consumerManagerId) {
        consumerManagerRedisRepository.deleteById(consumerManagerId);
    }

    /**
     * 批量删除客户经理信息
     * @param consumerManagerIds
     */
    public void deleteConsumerManagers(List<String> consumerManagerIds) throws SQLException{
        consumerManagerRepository.deleteConsumerManagerDaos(consumerManagerIds);
    }

    /**
     * 修改Mysql客户经理
     * @param consumerManagerDao
     */
    public void updateConsumerManager(ConsumerManagerDao consumerManagerDao) throws SQLException{
        consumerManagerRepository.updateConsumerManagerDao(consumerManagerDao);
    }

    /**
     * 修改Redis客户经理
     * @param consumerManagerDao
     */
    public void updateRedisConsumerManager(ConsumerManagerDao consumerManagerDao) {
        consumerManagerRedisRepository.save(consumerManagerDao);
    }


    /**
     * 将客户经理信息存入redis
     * @param consumerManagerDaos
     */
    public void saveAllInRedis(List<ConsumerManagerDao> consumerManagerDaos) {
        consumerManagerRedisRepository.saveAll(consumerManagerDaos);
    }
}
