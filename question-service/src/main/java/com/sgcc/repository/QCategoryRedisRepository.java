package com.sgcc.repository;

import com.sgcc.dao.QuestionCategoryDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QCategoryRedisRepository extends CrudRepository<QuestionCategoryDao, String> {
    List<QuestionCategoryDao> findAllByCategoryAvailable(boolean categoryAvailable);
}