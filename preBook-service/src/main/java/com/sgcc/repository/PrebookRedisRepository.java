package com.sgcc.repository;

import com.sgcc.dao.PreBookDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;

import java.util.List;

@Repository
public interface PrebookRedisRepository extends CrudRepository<PreBookDao,String> {

    List<PreBookDao> findAllByUserId(String userId);

    List<PreBookDao> findAllByServiceHallId(String serviceHallId);

    List<PreBookDao> findAllByServiceHallIdAndPrebookDateAndPrebookStartTime(String serviceHallId, Date prebookDate, Date prebookStartTime);


}
