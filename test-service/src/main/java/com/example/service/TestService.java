package com.example.service;

import com.example.dao.TestRedisDao;
import com.example.entity.TestEntity;
import com.example.entity.TestCacheEntity;
import com.example.entity.query.TestCacheQueryEntity;
import com.example.exception.TopErrorCode;
import com.example.producer.TestProducer;
import com.example.result.Result;
import com.example.test.TestRedisDTO;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class TestService {
    @Autowired
    private TestEntity testEntity;
    @Autowired
    private TestCacheEntity testCacheEntity;
    @Autowired
    private TestCacheQueryEntity testCacheQueryEntity;
    @Autowired
    private TestProducer testProducer;

    /**
     * 从mysql中查数据
     * @return
     */
    public Result findAllUesrs(){
        return Result.success(testEntity.findAllUesrs());
    }

    /**
     *
     * @param testRedisDTOS
     * @return
     */
    public Result saveInRedis(List<TestRedisDTO> testRedisDTOS){
        try{
            testCacheEntity.save(new ArrayList<TestRedisDao>(){{
                testRedisDTOS.forEach(testRedisDTO -> {
                    add(new TestRedisDao(
                            testRedisDTO.getId()
                            ,testRedisDTO.getName()
                            ,testRedisDTO.getAge()
                    ));
                });
            }});
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     *
     * @param ids
     * @return
     */
    public Result findInRedis(List<String> ids){
        try{
            return Result.success(testCacheEntity.find(ids));
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     *
     */
    public  Result saveUser(TestRedisDTO testRedisDTO){
        try{
            if(  Strings.isNullOrEmpty(testRedisDTO.getId())){
                testRedisDTO.setId(UUID.randomUUID().toString());
            }

            testCacheEntity.save(new ArrayList<TestRedisDao>() {{
                add(new TestRedisDao(
                        testRedisDTO.getId()
                        ,testRedisDTO.getName()
                        ,testRedisDTO.getAge()
                ));
            }});
            testProducer.testMQ(testRedisDTO.getId());
            return Result.success(testRedisDTO.getId());
        }catch (Exception e){
            e.printStackTrace();
            throw new  RuntimeException("发送消息队列失败");
        }

    }

}
