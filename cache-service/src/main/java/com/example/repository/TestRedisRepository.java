package com.example.repository;

import com.example.dao.TestRedisDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRedisRepository extends CrudRepository<TestRedisDao,String> {
    List<TestRedisDao> findAllByAge(int age);
}
