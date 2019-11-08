package com.sgcc.repository;

import com.sgcc.dao.HouseholdInfoDao;
import com.sgcc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Repository
public class HouseholdRepository {
    private Logger logger = Logger.getLogger(HouseholdRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Boolean userExceed5(String userOpenId){
        String sql="select count(id) from b_user b left join r_user_household r "
                + " on b.user_id = r.user_id where user_open_id = "
                + userOpenId;
        logger.info("selectSQL:"+sql);
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        if(integer>=5){
            return false;
        }else {
            return true;
        }
    }

    public Boolean householdExceed5(String householdNumber){
        String sql="select count(id) from b_household_info b left join r_user_household r "
                + " on b.household_id = r.household_id where household_number =  "
                + householdNumber;
        logger.info("selectSQL:"+sql);
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        if(integer>=5){
            return false;
        }else {
            return true;
        }
    }

    public void insertHouseholdInfo(HouseholdInfoDao householdInfoDao){
        String sql ="INSERT INTO b_household_info (household_id,household_householder,"
                + "household_number,household_address,household_default,household_type,"
                + "household_password,is_available) VALUES ('"
                + householdInfoDao.getHouseholdId()+"', '"
                + householdInfoDao.getHouseholdHouseholder()+"', '"
                + householdInfoDao.getHouseholdNumber()+"', '"
                + householdInfoDao.getHouseholdAddress()+"', "
                + householdInfoDao.getHouseholdDefault()+", '"
                + householdInfoDao.getHouseholdType()+"', '"
                + householdInfoDao.getHouseholdPassword()+"', "
                + householdInfoDao.getIsAvailable()+");";
        logger.info("insertSQL:"+sql);
        jdbcTemplate.execute(sql);
    }
    public void insertUser(UserDao userDao){
        String sql ="INSERT INTO b_household_info (";
    }

}
