package com.example.entity;

import com.example.dao.TestRedisDao;
import com.example.repository.TestRedisRepository;
import com.example.test.TestRedisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestCacheEntity {
    @Autowired
    private TestRedisRepository testRedisRepository;

    /**
     * 测试redis保存TestRedisDao
     *
     */
    public void save(List<TestRedisDao> testRedisDaos){

        testRedisRepository.saveAll(testRedisDaos);
    }
    /**
     * 测试redis查询TestRedisDao
     *
     */
    public List<TestRedisDTO> find(List<String> ids){
        return new ArrayList<TestRedisDTO>(){{
            testRedisRepository.findAllById(ids).forEach( t->{
                add(t.build());
            });
        }};
    }
}
