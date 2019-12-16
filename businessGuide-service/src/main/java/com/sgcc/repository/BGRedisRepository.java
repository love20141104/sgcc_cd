package com.sgcc.repository;

import com.sgcc.dao.BusinessGuideRedisDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BGRedisRepository extends CrudRepository<BusinessGuideRedisDao,String> {

    List<BusinessGuideRedisDao> findAllByCategoryId(String cid);
    List<BusinessGuideRedisDao> findAllByTitle(String title);

}
