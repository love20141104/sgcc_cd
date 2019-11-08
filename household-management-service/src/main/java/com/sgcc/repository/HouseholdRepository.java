package com.sgcc.repository;

import com.google.common.base.Strings;
import com.sgcc.dao.HouseholdInfoDao;
import com.sgcc.dao.SubscribeDao;
import com.sgcc.dao.UserDao;
import com.sgcc.dao.UserHouseholdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
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
            return true;
        }else {
            return false;
        }
    }

    public Boolean householdExceed5(String householdNumber){
        String sql="select count(id) from b_household_info b left join r_user_household r "
                + " on b.household_id = r.household_id where household_number =  "
                + householdNumber;
        logger.info("selectSQL:"+sql);
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        if(integer>=5){
            return true;
        }else {
            return false;
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
        String sql ="INSERT INTO b_user(user_id,user_open_id,user_tel,is_available) VALUES ('"
                + userDao.getUserId()+"', '"
                + userDao.getUserOpenId()+"', '"
                + userDao.getUserTel()+"', "
                + userDao.getIsAvailable()+");";
        logger.info("insertSQL:"+sql);
        jdbcTemplate.execute(sql);
    }

    public void insertSubscribe(SubscribeDao subscribeDao){
        String sql ="INSERT INTO b_subscribe(sub_id,user_id,sub_bill,sub_pay,sub_arrears,sub_coulometric_analysis,sub_power,is_available) VALUES ('"
                + subscribeDao.getSubId()+"', '"
                + subscribeDao.getUserId()+"', "
                + subscribeDao.getSubBill()+", "
                + subscribeDao.getSubPay()+", "
                + subscribeDao.getSubArrears()+", "
                + subscribeDao.getSubCoulometricAnalysis()+", "
                + subscribeDao.getSubPower()+", "
                + subscribeDao.getIsAvailable()+");";
        logger.info("insertSQL:"+sql);
        jdbcTemplate.execute(sql);
    }

    public void insertUserHousehold(UserHouseholdDao userHouseholdDao){
        String sql ="INSERT INTO r_user_household(id,user_id,household_id,is_available) VALUES ('"
                + userHouseholdDao.getId()+"', '"
                + userHouseholdDao.getUserId()+"', '"
                + userHouseholdDao.getHouseholdId()+"', "
                + userHouseholdDao.getIsAvailable()+");";
        logger.info("insertSQL:"+sql);
        jdbcTemplate.execute(sql);
    }

    public Boolean ifUserByUserOpenId(String userOpenId){
        String sql="select count(user_id) from b_user  ";
        StringBuffer sql_where = new StringBuffer();
        if(!Strings.isNullOrEmpty(userOpenId)){
            sql_where.append("user_open_id = '").append(userOpenId).append("'  ");
        }
        if(!Strings.isNullOrEmpty(sql_where.toString())){
            sql +=" where " + sql_where.toString();
        }
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        if(integer>0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean ifSubscribeByUserOpenId(String userOpenId){
        String sql="select count(sub_id) from b_subscribe  ";
        StringBuffer sql_where = new StringBuffer();
        if(!Strings.isNullOrEmpty(userOpenId)){
            sql_where.append("user_open_id = '").append(userOpenId).append("'  ");
        }
        if(!Strings.isNullOrEmpty(sql_where.toString())){
            sql +=" where " + sql_where.toString();
        }
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        if(integer>0){
            return true;
        }else {
            return false;
        }
    }

    class UserRowMapper implements RowMapper<UserDao> {
        @Override
        public UserDao mapRow(ResultSet rs, int i) throws SQLException {
            return new UserDao(
                    rs.getString("user_id"),
                    rs.getString("user_open_id"),
                    rs.getString("user_tel"),
                    rs.getBoolean("is_available")
            );
        }
    }

}
