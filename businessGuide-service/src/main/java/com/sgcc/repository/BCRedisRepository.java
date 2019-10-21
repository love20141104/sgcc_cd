package com.sgcc.repository;

import com.sgcc.dao.BusinessCategoryDao;
import com.sgcc.dao.BusinessCategoryRedisDao;
import com.sgcc.dto.BusinessCategoryDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BCRedisRepository extends CrudRepository<BusinessCategoryRedisDao,String> {


}
