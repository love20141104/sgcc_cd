package com.sgcc.repository;

import com.sgcc.dao.PreBookDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;

import java.util.List;

@Repository
public interface PrebookRedisRepository extends CrudRepository<PreBookDao, String> {

    List<PreBookDao> findAllByUserId(String userId);

    List<PreBookDao> findAllByPrebookCode(String prebookCode);

    List<PreBookDao> findAllByUserIdAndPrebookDate(String userId, String date);

    List<PreBookDao> findAllByServiceHallId(String serviceHallId);

    List<PreBookDao> findAllByServiceHallIdAndPrebookDate(String serviceHallId, String prebookDate);

    List<PreBookDao> findAllByServiceHallIdAndPrebookDateAndPrebookStartTime(String serviceHallId, String prebookDate, String prebookStartTime);


}
