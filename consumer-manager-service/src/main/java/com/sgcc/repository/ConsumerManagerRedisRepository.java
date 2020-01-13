package com.sgcc.repository;

import com.sgcc.dao.ConsumerManagerDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerManagerRedisRepository extends CrudRepository<ConsumerManagerDao, String> {


}
