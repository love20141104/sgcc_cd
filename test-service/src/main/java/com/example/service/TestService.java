package com.example.service;

import com.example.dao.TestRedisDao;
import com.example.entity.TestEntity;
import com.example.entity.TestCacheEntity;
import com.example.entity.query.TestCacheQueryEntity;
import com.example.exception.TopErrorCode;
import com.example.result.Result;
import com.example.test.TestRedisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TestService {
    @Autowired
    private TestEntity testEntity;
    @Autowired
    private TestCacheEntity testCacheEntity;
    @Autowired
    private TestCacheQueryEntity testCacheQueryEntity;

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

}
