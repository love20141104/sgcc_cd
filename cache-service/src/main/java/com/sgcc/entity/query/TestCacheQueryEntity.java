package com.sgcc.entity.query;

import com.sgcc.dao.TestRedisDao;
import com.sgcc.repository.TestRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestCacheQueryEntity {
    @Autowired
    private TestRedisRepository testRedisRepository;

    /**
     * 测试redis查询TestRedisDao
     *
     */
    public List<TestRedisDao> findTestRedisDaosByAge(int age){
        return testRedisRepository.findAllByAge(age);
    }

    /**
     * 测试redis查询TestRedisDao
     *
     */
    public TestRedisDao findTestRedisDaoById(String id){
        return testRedisRepository.findById(id).get();
    }
}
