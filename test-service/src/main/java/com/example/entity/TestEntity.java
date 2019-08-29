package com.example.entity;

import com.example.repository.TestRepository;
import com.example.test.TestRedisDTO;
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
