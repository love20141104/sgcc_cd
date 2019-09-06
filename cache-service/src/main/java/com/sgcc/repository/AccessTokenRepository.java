package com.sgcc.repository;

import com.sgcc.dao.AccessTokenDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessTokenRepository extends CrudRepository<AccessTokenDao,String> {
}
