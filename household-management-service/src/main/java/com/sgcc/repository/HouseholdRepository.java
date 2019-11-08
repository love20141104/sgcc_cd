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
    //保存householdInfoDao
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
    //保存userDao
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
//保存UserHouseholdDao
    public void insertUserHousehold(UserHouseholdDao userHouseholdDao){
        String sql ="INSERT INTO r_user_household(id,user_id,household_id,is_available) VALUES ('"
                + userHouseholdDao.getId()+"', '"
                + userHouseholdDao.getUserId()+"', '"
                + userHouseholdDao.getHouseholdId()+"', "
                + userHouseholdDao.getIsAvailable()+");";
        logger.info("insertSQL:"+sql);
        jdbcTemplate.execute(sql);
    }
//保存UserDao
    public UserDao selectUserByUserOpenId(String userOpenId){
        String sql="select user_id,user_open_id,user_tel,is_available from b_user  ";
        StringBuffer sql_where = new StringBuffer();
        if(!Strings.isNullOrEmpty(userOpenId)){
            sql_where.append("user_open_id = '").append(userOpenId).append("'  ");
        }
        if(!Strings.isNullOrEmpty(sql_where.toString())){
            sql +=" where " + sql_where.toString();
        }
        try {
            UserDao userDao = jdbcTemplate.queryForObject(sql, new UserRowMapper());
            return userDao;
        }catch (Exception e){
            return null;
        }
    }
    //保存b_subscribe
    public SubscribeDao selectSubscribeByUserOpenId(String userOpenId){
        String sql="select sub_id,b_subscribe.user_id user_id,sub_bill,sub_pay,sub_arrears"
                + ",sub_coulometric_analysis,sub_power,b_subscribe.is_available is_available "
                + "from b_subscribe left join b_user on b_subscribe.user_id=b_user.user_id  ";
        StringBuffer sql_where = new StringBuffer();
        if(!Strings.isNullOrEmpty(userOpenId)){
            sql_where.append("user_open_id = '").append(userOpenId).append("'  ");
        }
        if(!Strings.isNullOrEmpty(sql_where.toString())){
            sql +=" where " + sql_where.toString();
        }
        try {
            return jdbcTemplate.queryForObject(sql, new SubscribeRowMapper());
        }catch (Exception e){
            return null;
        }
    }
    //解绑删除关系表，户号表
    @Transactional
    public void deleteUserHouseHoldAndHouseholdInfo(String householdNumber,String userOpenId){
        if(!Strings.isNullOrEmpty(householdNumber)&&!Strings.isNullOrEmpty(userOpenId)) {
            String sql = "delete from b_household_info where household_id =( select ru.household_id from (select household_id from b_user bu left  join r_user_household ruh "
                    + " on bu.user_id=ruh.user_id "
                    + " and bu.user_open_id='" + userOpenId + "') ru left join b_household_info bhi "
                    + " on ru.household_id=bhi.household_id "
                    + " where bhi.household_number='" + householdNumber + "');";
            logger.info("deleteSQL:" + sql);
            jdbcTemplate.execute(sql);
            String sql2 = "delete from r_user_household where id =( select ruid from (select id,household_id from b_user bu left  join r_user_household ruh "
                    + " on bu.user_id=ruh.user_id "
                    + " and bu.user_open_id='" + userOpenId + "') ru left join b_household_info bhi "
                    + " on ru.household_id=bhi.household_id "
                    + " where bhi.household_number='" + householdNumber + "');";
            logger.info("deleteSQL:" + sql2);
            jdbcTemplate.execute(sql2);
        }
    }
    @Transactional
    public void unavailableUserHouseHold(String userOpenId ,Boolean available){
        if(Strings.isNullOrEmpty(userOpenId)){
            //作废b_user
            String sql1="update b_user set ( is_available = "
                    + available
                    +" where user_open_id = '"
                    +userOpenId+"'";
            logger.info("updateSQL:" + sql1);
            jdbcTemplate.execute(sql1);
            //作废r_user_household
            String sql2="update r_user_household set ( is_available = "
                    + available
                    +" where user_id in ( select ruh.user_id user_id "
                    + " from r_user_household ruh left join b_user bu "
                    + " on ruh.user_id=bu.user_id and bu.user_open_id ='"
                    +userOpenId+"')";
            logger.info("updateSQL:" + sql2);
            jdbcTemplate.execute(sql2);
            //作废b_user
            String sql3="update b_user set ( is_available = "
                    + available
                    +" where user_open_id = '"
                    +userOpenId+"'";
            logger.info("updateSQL:" + sql3);
            jdbcTemplate.execute(sql3);
            //作废b_user
            String sql4="update b_user set ( is_available = "
                    + available
                    +" where user_open_id = '"
                    +userOpenId+"'";
            logger.info("updateSQL:" + sql4);
            jdbcTemplate.execute(sql4);

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
    class SubscribeRowMapper implements RowMapper<SubscribeDao> {
        @Override
        public SubscribeDao mapRow(ResultSet rs, int i) throws SQLException {
            return new SubscribeDao(
                    rs.getString("sub_id"),
                    rs.getString("user_id"),
                    rs.getBoolean("sub_bill"),
                    rs.getBoolean("sub_pay"),
                    rs.getBoolean("sub_arrears"),
                    rs.getBoolean("sub_coulometric_analysis"),
                    rs.getBoolean("sub_power"),
                    rs.getBoolean("is_available")
            );
        }
    }

}
