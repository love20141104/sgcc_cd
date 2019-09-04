package com.sgcc.entity;

import com.sgcc.repository.TestRepository;
import com.sgcc.test.TestRedisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestEntity {
    @Autowired
    private TestRepository testRepository;

    /**
     *
     */
    public List<TestRedisDTO> findAllUesrs(){
        List<TestRedisDTO> redisDTOS = testRepository.findAllUesrs();
        return redisDTOS;
    }

    public void saveUesr(TestRedisDTO testRedisDTO){
        testRepository.saveUser(testRedisDTO);
    }
}
