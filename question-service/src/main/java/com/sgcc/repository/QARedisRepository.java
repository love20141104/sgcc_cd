package com.sgcc.repository;

import com.sgcc.dao.QuestionAnswerDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QARedisRepository extends CrudRepository<QuestionAnswerDao, String> {
    List<QuestionAnswerDao> findAllByCategoryIdAndAndCategoryAvailable(String categoryId,boolean CategoryAvailable);
}
