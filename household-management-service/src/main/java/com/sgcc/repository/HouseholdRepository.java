package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.*;
import com.sgcc.dto.HouseholdNumsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class HouseholdRepository {
    private Logger logger = Logger.getLogger(HouseholdRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${precompile}")
    private Boolean precompile;



    /**
     * 查询缴费记录中其他非绑定用户户号
     * @param openId
     * @return
     */
    public List<HouseholdNumsDTO> getNoBindList(String openId){
        if (precompile) {
            String sql = "select distinct pay_household_number from b_pay_info where user_open_id='" + openId + "' and pay_household_number not in ("
                    + "select hi.household_number from b_pay_info p,b_user u,r_user_household uh,b_household_info hi"
                    + " where p.user_open_id = u.user_open_id and uh.user_id = u.user_id and hi.household_id = uh.household_id"
                    + " and p.user_open_id = ?)";
            return jdbcTemplate.query(sql,new Object[]{openId}, new NoBindRowMapper());
        }else {
            String sql = "select distinct pay_household_number from b_pay_info where user_open_id='" + openId + "' and pay_household_number not in ("
                    + "select hi.household_number from b_pay_info p,b_user u,r_user_household uh,b_household_info hi"
                    + " where p.user_open_id = u.user_open_id and uh.user_id = u.user_id and hi.household_id = uh.household_id"
                    + " and p.user_open_id = '" + openId + "')";
            return jdbcTemplate.query(sql, new NoBindRowMapper());
        }
    }



    public Boolean userExceed5(String userOpenId){
        if (precompile) {
            String sql = "select count(id) from b_user b left join r_user_household r "
                    + " on b.user_id = r.user_id where  user_open_id = ?";
            logger.info("selectSQL:" + sql);
            Integer integer = jdbcTemplate.queryForObject(sql,new Object[]{userOpenId}, Integer.class);
            if (integer >= 5) {
                return true;
            } else {
                return false;
            }
        }else {
            String sql = "select count(id) from b_user b left join r_user_household r "
                    + " on b.user_id = r.user_id where  user_open_id = '"
                    + userOpenId + "'";
            logger.info("selectSQL:" + sql);
            Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
            if (integer >= 5) {
                return true;
            } else {
                return false;
            }
        }
    }

    public Boolean householdExceed5(String householdNumber){
        if (precompile) {
            String sql = "select count(id) from b_household_info b left join r_user_household r "
                    + " on b.household_id = r.household_id where household_number =  ? ";
            logger.info("selectSQL:" + sql);
            Integer integer = jdbcTemplate.queryForObject(sql,new Object[]{householdNumber}, Integer.class);
            if (integer >= 5) {
                return true;
            } else {
                return false;
            }
        }else {
            String sql = "select count(id) from b_household_info b left join r_user_household r "
                    + " on b.household_id = r.household_id where household_number =  '"
                    + householdNumber + "'";
            logger.info("selectSQL:" + sql);
            Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
            if (integer >= 5) {
                return true;
            } else {
                return false;
            }
        }
    }
    //保存householdInfoDao
    public void insertHouseholdInfo(HouseholdInfoDao householdInfoDao){
        if (precompile) {
            String sql = "INSERT INTO b_household_info (household_id,household_householder,"
                    + "household_number,household_address,household_default,household_type,"
                    + "household_password,is_available) VALUES (?,?,?,?,?, ?,?,?)";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    householdInfoDao.getHouseholdId()
                    ,householdInfoDao.getHouseholdHouseholder()
                    ,householdInfoDao.getHouseholdNumber()
                    ,householdInfoDao.getHouseholdAddress()
                    ,householdInfoDao.getHouseholdDefault()

                    ,householdInfoDao.getHouseholdType()
                    ,householdInfoDao.getHouseholdPassword()
                    ,householdInfoDao.getIsAvailable()
            });
        }else {
            String sql = "INSERT INTO b_household_info (household_id,household_householder,"
                    + "household_number,household_address,household_default,household_type,"
                    + "household_password,is_available) VALUES ('"
                    + householdInfoDao.getHouseholdId() + "', '"
                    + householdInfoDao.getHouseholdHouseholder() + "', '"
                    + householdInfoDao.getHouseholdNumber() + "', '"
                    + householdInfoDao.getHouseholdAddress() + "', "
                    + householdInfoDao.getHouseholdDefault() + ", '"

                    + householdInfoDao.getHouseholdType() + "', '"
                    + householdInfoDao.getHouseholdPassword() + "', "
                    + householdInfoDao.getIsAvailable() + ");";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.execute(sql);
        }
    }
    //保存userDao
    public void insertUser(UserDao userDao){
        if (precompile) {
            String sql = "INSERT INTO b_user(user_id,user_open_id,user_tel,is_available) VALUES (?,?,?,?)";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    userDao.getUserId()
                    ,userDao.getUserOpenId()
                    ,userDao.getUserTel()
                    ,userDao.getIsAvailable()
            });
        }else {
            String sql = "INSERT INTO b_user(user_id,user_open_id,user_tel,is_available) VALUES ('"
                    + userDao.getUserId() + "', '"
                    + userDao.getUserOpenId() + "', '"
                    + userDao.getUserTel() + "', "
                    + userDao.getIsAvailable() + ");";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.execute(sql);
        }
    }
//保存SubscribeDao
    public void insertSubscribe(SubscribeDao subscribeDao){
        if (precompile) {
            String sql = "INSERT INTO b_subscribe(sub_id,user_id,sub_bill,sub_pay,sub_arrears,sub_coulometric_analysis,sub_power,is_available) " +
                    "VALUES (?,?,?,?,?, ?,?,?)";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    subscribeDao.getSubId()
                    ,subscribeDao.getUserId()
                    ,subscribeDao.getSubBill()
                    ,subscribeDao.getSubPay()
                    ,subscribeDao.getSubArrears()

                    ,subscribeDao.getSubCoulometricAnalysis()
                    ,subscribeDao.getSubPower()
                    ,subscribeDao.getIsAvailable()
            });
        }else {
            String sql = "INSERT INTO b_subscribe(sub_id,user_id,sub_bill,sub_pay,sub_arrears,sub_coulometric_analysis,sub_power,is_available) VALUES ('"
                    + subscribeDao.getSubId() + "', '"
                    + subscribeDao.getUserId() + "', "
                    + subscribeDao.getSubBill() + ", "
                    + subscribeDao.getSubPay() + ", "
                    + subscribeDao.getSubArrears() + ", "

                    + subscribeDao.getSubCoulometricAnalysis() + ", "
                    + subscribeDao.getSubPower() + ", "
                    + subscribeDao.getIsAvailable() + ");";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.execute(sql);
        }
    }
//保存UserHouseholdDao
    public void insertUserHousehold(UserHouseholdDao userHouseholdDao){
        if (precompile) {
            String sql = "INSERT INTO r_user_household(id,user_id,household_id,is_available) VALUES (?,?,?,?);";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    userHouseholdDao.getId()
                    ,userHouseholdDao.getUserId()
                    ,userHouseholdDao.getHouseholdId()
                    ,userHouseholdDao.getIsAvailable()
            });
        }else {
            String sql = "INSERT INTO r_user_household(id,user_id,household_id,is_available) VALUES ('"
                    + userHouseholdDao.getId() + "', '"
                    + userHouseholdDao.getUserId() + "', '"
                    + userHouseholdDao.getHouseholdId() + "', "
                    + userHouseholdDao.getIsAvailable() + ");";
            logger.info("insertSQL:" + sql);
            jdbcTemplate.execute(sql);
        }
    }
//通过userOpenId查询UserDao
    public UserDao selectUserByUserOpenId(String userOpenId){
        if (precompile) {
            String sql = "select user_id,user_open_id,user_tel,is_available from b_user where  user_open_id = ?";
            try {
                UserDao userDao = jdbcTemplate.queryForObject(sql, new Object[]{userOpenId},new UserRowMapper());
                return userDao;
            } catch (Exception e) {
                return null;
            }
        }else {
            String sql = "select user_id,user_open_id,user_tel,is_available from b_user where  user_open_id = '"
                    + userOpenId + " '";
            try {
                UserDao userDao = jdbcTemplate.queryForObject(sql, new UserRowMapper());
                return userDao;
            } catch (Exception e) {
                return null;
            }
        }
    }
    //通过userOpenId查询subscribe
    public SubscribeDao selectSubscribeByUserOpenId(String userOpenId){
        if (precompile) {
            String sql = "select sub_id,b_subscribe.user_id user_id,sub_bill,sub_pay,sub_arrears"
                    + ",sub_coulometric_analysis,sub_power,b_subscribe.is_available is_available "
                    + "from b_subscribe left join b_user on b_subscribe.user_id=b_user.user_id  "
                    + "where user_open_id = ?";
            try {
                return jdbcTemplate.queryForObject(sql,new Object[]{userOpenId}, new SubscribeRowMapper());
            } catch (Exception e) {
                return null;
            }
        }else {
            String sql = "select sub_id,b_subscribe.user_id user_id,sub_bill,sub_pay,sub_arrears"
                    + ",sub_coulometric_analysis,sub_power,b_subscribe.is_available is_available "
                    + "from b_subscribe left join b_user on b_subscribe.user_id=b_user.user_id  "
                    + "where user_open_id = '" + userOpenId + " '";
            try {
                return jdbcTemplate.queryForObject(sql, new SubscribeRowMapper());
            } catch (Exception e) {
                return null;
            }
        }
    }
    //解绑删除关系表，户号表
    @Transactional
    public void deleteUserHouseHoldAndHouseholdInfo(String userOpenId,String householdId ){
        if (precompile) {
            if (!Strings.isNullOrEmpty(householdId)) {
                String sql = "delete from b_household_info where household_id =?";
                String userId = getUserIdByUserOpenId(userOpenId);
                logger.info("deleteSQL:" + sql);
                jdbcTemplate.update(sql,new Object[]{householdId});

                String sql2 = "delete from r_user_household where user_id =? and household_id =?";
                logger.info("deleteSQL:" + sql2);
                jdbcTemplate.update(sql2,new Object[]{userId,householdId});
            }
        }else {
            if (!Strings.isNullOrEmpty(householdId)) {
                String sql = "delete from b_household_info where household_id ='" + householdId + "'";
                String userId = getUserIdByUserOpenId(userOpenId);
                logger.info("deleteSQL:" + sql);
                jdbcTemplate.execute(sql);

                String sql2 = "delete from r_user_household where user_id ='" + userId + "' and household_id ='" + householdId + "'";
                logger.info("deleteSQL:" + sql2);
                jdbcTemplate.execute(sql2);
            }
        }
    }
    @Transactional
    public void unavailableUserHouseHold(String userOpenId ,Boolean available){
        if (precompile) {
            //通过userOpenId获得用户id
            String userIdByUserOpenId = getUserIdByUserOpenId(userOpenId);
            //通过userOpenId获得HouseholdId列表
            List<String> householdIdByUserOpenId = getHouseholdIdByUserOpenId(userOpenId);
            if (!Strings.isNullOrEmpty(userIdByUserOpenId) && null != householdIdByUserOpenId && householdIdByUserOpenId.size() > 0) {
                //作废b_user
                String sql1 = "update b_user set  is_available =?  where user_open_id = ?";
                logger.info("updateSQL:" + sql1);
                jdbcTemplate.update(sql1,new Object[]{available,userOpenId});
                //作废r_user_household
                String sql2 = "update r_user_household set  is_available = ? where user_id = ? ";
                logger.info("updateSQL:" + sql2);
                jdbcTemplate.update(sql2,new Object[]{available,userIdByUserOpenId});
                //作废b_household_info
                String sql3 = "update b_household_info set  is_available = ? where household_id = ?";
                logger.info("updateSQL:" + sql3);
                jdbcTemplate.batchUpdate(sql3,new BatchPreparedStatementSetter() {
                    public int getBatchSize() {
                        return householdIdByUserOpenId.size();
                    }
                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        ps.setBoolean(1,available);
                        ps.setString(2,householdIdByUserOpenId.get(i));
                    }
                });
                //作废b_subscribe
                String sql4 = "update b_subscribe set  is_available = ? where user_id =  ?";
                logger.info("updateSQL:" + sql4);
                jdbcTemplate.update(sql4,new Object[]{available,userIdByUserOpenId});
            }
        }else {
            //通过userOpenId获得用户id
            String userIdByUserOpenId = getUserIdByUserOpenId(userOpenId);
            //通过userOpenId获得HouseholdId列表
            List<String> householdIdByUserOpenId = getHouseholdIdByUserOpenId(userOpenId);
            if (!Strings.isNullOrEmpty(userIdByUserOpenId) && null != householdIdByUserOpenId && householdIdByUserOpenId.size() > 0) {
                //作废b_user
                String sql1 = "update b_user set  is_available = "
                        + available
                        + " where user_open_id = '"
                        + userOpenId + "'";
                logger.info("updateSQL:" + sql1);
                jdbcTemplate.execute(sql1);
                //作废r_user_household
                String sql2 = "update r_user_household set  is_available = "
                        + available
                        + " where user_id = '"
                        + userIdByUserOpenId + "'";
                logger.info("updateSQL:" + sql2);
                jdbcTemplate.execute(sql2);
                //作废b_household_info
                String sql3 = "update b_household_info set  is_available = "
                        + available
                        + " where household_id in ('" + Utils.joinStrings(householdIdByUserOpenId, "','") + "')";
                logger.info("updateSQL:" + sql3);
                jdbcTemplate.execute(sql3);
                //作废b_subscribe
                String sql4 = "update b_subscribe set  is_available = "
                        + available
                        + " where user_id =  '"
                        + userIdByUserOpenId + "'";
                logger.info("updateSQL:" + sql4);
                jdbcTemplate.execute(sql4);
            }
        }
    }

    /**
     * 获取用户绑定的户号列表
     * @param userOpenId
     * @return
     */
    public List<HouseholdInfoDao> getBindList(String userOpenId) {
        if (precompile) {
            String sql = "select hi.household_id household_id" +
                    ",household_householder" +
                    ",household_number" +
                    ",household_address" +
                    ",household_default" +
                    ",household_type" +
                    ",household_password" +
                    ",hi.is_available is_available from b_household_info hi " +
                    "LEFT JOIN r_user_household r ON hi.household_id = r.household_id " +
                    "LEFT JOIN b_user u on r.user_id=u.user_id " +
                    " where u.user_open_id = ?";

            return jdbcTemplate.query(sql,new Object[]{userOpenId}, new HouseholdInfoRowMapper());
        }else {
            String sql = "select hi.household_id household_id" +
                    ",household_householder" +
                    ",household_number" +
                    ",household_address" +
                    ",household_default" +
                    ",household_type" +
                    ",household_password" +
                    ",hi.is_available is_available from b_household_info hi " +
                    "LEFT JOIN r_user_household r ON hi.household_id = r.household_id " +
                    "LEFT JOIN b_user u on r.user_id=u.user_id " +
                    " where u.user_open_id = '" + userOpenId + "'";

            return jdbcTemplate.query(sql, new HouseholdInfoRowMapper());
        }
    }
    public HouseholdInfoDao getHouseholdInfo(String householdId) {
        if (precompile) {
            String sql = "select hi.household_id household_id" +
                    ",household_householder" +
                    ",household_number" +
                    ",household_address" +
                    ",household_default" +
                    ",household_type" +
                    ",household_password" +
                    ",hi.is_available is_available from b_household_info  hi" +
                    " where household_id = ?";
            try {
                return jdbcTemplate.queryForObject(sql,new Object[]{householdId}, new HouseholdInfoRowMapper());
            } catch (Exception e) {
                return null;
            }
        }else {
            String sql = "select hi.household_id household_id" +
                    ",household_householder" +
                    ",household_number" +
                    ",household_address" +
                    ",household_default" +
                    ",household_type" +
                    ",household_password" +
                    ",hi.is_available is_available from b_household_info  hi" +
                    " where household_id = '" + householdId + "'";
            try {
                return jdbcTemplate.queryForObject(sql, new HouseholdInfoRowMapper());
            } catch (Exception e) {
                return null;
            }
        }

    }

    /**
     * 修改户号密码
     * @param openId
     * @param householdNum
     * @param pwd
     */
    public void updatePwd(String openId, String householdNum, String pwd){
        if (precompile) {
            String householdId = getHouseholdIdByUserOpenIdAndHouseholdNum(openId, householdNum);
            if (!Strings.isNullOrEmpty(householdId)) {
                String sql = "update b_household_info set household_password = ? where household_id = ? ";
                jdbcTemplate.update(sql,new Object[]{pwd,householdId});
            }
        }else {
            String householdId = getHouseholdIdByUserOpenIdAndHouseholdNum(openId, householdNum);
            if (!Strings.isNullOrEmpty(householdId)) {
                String sql = "update b_household_info set household_password = '" + pwd + "' " +
                        "where household_id = '" + householdId + "'";
                jdbcTemplate.execute(sql);
            }
        }

    }

    /**
     * 设置默认户号
     * @param openId
     * @param householdId
     */
    @Transactional
    public void setDefaultHouseholdNum(String openId, String householdId){
        if (precompile) {
            //通过userOpenId获得HouseholdId列表
            List<String> householdIdByUserOpenId = getHouseholdIdByUserOpenId(openId);
            if (null != householdIdByUserOpenId && householdIdByUserOpenId.size() > 0 && !Strings.isNullOrEmpty(householdId)) {
                String sql0 = "update b_household_info set household_default = false " +
                        "where household_id =?";

                String sql = "update b_household_info set household_default = true " +
                        "where household_id = ?";
                jdbcTemplate.batchUpdate(sql0,new BatchPreparedStatementSetter() {
                    public int getBatchSize() {
                        return householdIdByUserOpenId.size();
                    }
                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        ps.setString(1,householdIdByUserOpenId.get(i));
                    }
                });
                jdbcTemplate.update(sql,new Object[]{householdId});
            }
        }else {
            //通过userOpenId获得HouseholdId列表
            List<String> householdIdByUserOpenId = getHouseholdIdByUserOpenId(openId);
            if (null != householdIdByUserOpenId && householdIdByUserOpenId.size() > 0 && !Strings.isNullOrEmpty(householdId)) {
                String sql0 = "update b_household_info set household_default = false " +
                        "where household_id in ('" + Utils.joinStrings(householdIdByUserOpenId, "','") + "')";

                String sql = "update b_household_info set household_default = true " +
                        "where household_id = '" + householdId + "'";
                jdbcTemplate.execute(sql0);
                jdbcTemplate.execute(sql);
            }
        }
    }

    /**
     * 通过userOpenId获得用户id
     * @param userOpenId
     * @return userId
     */
    public String getUserIdByUserOpenId(String userOpenId) {
        if (precompile) {
            String sql0 = "  select  user_id "
                    + " from  b_user bu "
                    + "  where bu.user_open_id = ? ";
            logger.info("selectSQL:" + sql0);
            try {
                return jdbcTemplate.queryForObject(sql0,new Object[]{userOpenId}, String.class);
            } catch (Exception e) {
                return null;
            }
        }else {
            String sql0 = "  select  user_id "
                    + " from  b_user bu "
                    + "  where bu.user_open_id ='"
                    + userOpenId + "'";
            logger.info("selectSQL:" + sql0);
            try {
                return jdbcTemplate.queryForObject(sql0, String.class);
            } catch (Exception e) {
                return null;
            }
        }
    }

    /**
     * 通过userOpenId获得HouseholdId列表
     * @param userOpenId
     * @return HouseholdId
     */
    public List<String> getHouseholdIdByUserOpenId(String userOpenId){
        if (precompile) {
            String sql0= "select distinct(ruh.household_id) household_id "
                    + " from r_user_household ruh left join b_user bu "
                    + " on ruh.user_id=bu.user_id where bu.user_open_id = ? ";
            logger.info("selectSQL:" + sql0);
            try {
                return jdbcTemplate.query(sql0,new Object[]{userOpenId} ,new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet rs, int i) throws SQLException {
                        return rs.getString("household_id");
                    }
                });
            }catch (Exception e){
                return null;
            }
        }else {
            String sql0 = "select distinct(ruh.household_id) household_id "
                    + " from r_user_household ruh left join b_user bu "
                    + " on ruh.user_id=bu.user_id where bu.user_open_id ='"
                    + userOpenId + "'";
            logger.info("selectSQL:" + sql0);
            try {
                return jdbcTemplate.query(sql0, new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet rs, int i) throws SQLException {
                        return rs.getString("household_id");
                    }
                });
            } catch (Exception e) {
                return null;
            }
        }

    }

    /**
     * 通过userOpenId and HouseholdNum获得HouseholdId
     * @param userOpenId
     * @param householdNum
     * @return
     */
    public String getHouseholdIdByUserOpenIdAndHouseholdNum(String userOpenId,String householdNum){
        if (precompile) {
            String sql0 = "select hi.household_id from b_household_info hi " +
                    "left join r_user_household r on r.household_id = hi.household_id " +
                    "left join b_user u on u.user_id = r.user_id " +
                    "where u.user_open_id = ? and hi.household_number = ?";
            logger.info("selectSQL:" + sql0);
            try {
                return jdbcTemplate.queryForObject(sql0,new Object[]{userOpenId,householdNum}, String.class);
            } catch (Exception e) {
                return null;
            }
        }else {
            String sql0 = "select hi.household_id from b_household_info hi " +
                    "left join r_user_household r on r.household_id = hi.household_id " +
                    "left join b_user u on u.user_id = r.user_id " +
                    "where u.user_open_id = '" + userOpenId + "' and hi.household_number = '" + householdNum + "'";
            logger.info("selectSQL:" + sql0);
            try {
                return jdbcTemplate.queryForObject(sql0, String.class);
            } catch (Exception e) {
                return null;
            }
        }

    }

    /**
     * 修改订阅信息
     * @param openId
     * @param columnName
     * @param is_subscribe
     */
    public void updateSubscribe(String openId,String columnName,boolean is_subscribe){
        if (precompile) {
            String sql = "update b_subscribe set ? = ? where user_id = (" +
                    "select u.user_id from b_user u " +
                    "where u.user_open_id = ? )";
            jdbcTemplate.update(sql,new Object[]{columnName,is_subscribe,openId});
        }else {
            String sql = "update b_subscribe set " + columnName + " = " + is_subscribe +
                    " where user_id = (" +
                    "select u.user_id from b_user u " +
                    "where u.user_open_id = '" + openId + "'" +
                    ")";
            jdbcTemplate.execute(sql);
        }
    }

//获得UserSubscribeDao列表
    public List<UserSubscribeDao> getUserSubscribeList(Boolean isAvailable) {
        if (precompile) {
            String sql = "select b_user.user_id user_id,user_open_id,user_tel,sub_id,sub_bill,sub_pay,sub_arrears"
                    + ",sub_coulometric_analysis,sub_power,b_user.is_available is_available "
                    + "from b_subscribe left join b_user on b_subscribe.user_id=b_user.user_id "
                    + " where b_user.is_available = ? " ;
            try {
                return jdbcTemplate.query(sql,new Object[]{isAvailable}, new UserSubscribeRowMapper());
            } catch (Exception e) {
                return null;
            }
        }else {
            String sql = "select b_user.user_id user_id,user_open_id,user_tel,sub_id,sub_bill,sub_pay,sub_arrears"
                    + ",sub_coulometric_analysis,sub_power,b_user.is_available is_available "
                    + "from b_subscribe left join b_user on b_subscribe.user_id=b_user.user_id  ";
            if (null != isAvailable) {
                String wheresql = " where b_user.is_available = " + isAvailable;
                sql += wheresql;
            }
            try {
                return jdbcTemplate.query(sql, new UserSubscribeRowMapper());
            } catch (Exception e) {
                return null;
            }
        }
    }
    //修改UserSubscribeDao
    @Transactional
    public void updateUserSubscribe(UserSubscribeDao userSubscribeDao) {
        if (precompile) {
            String sql1 = "update b_user set user_tel =? "
                    + " where user_open_id = ? ";

            String userId = getUserIdByUserOpenId(userSubscribeDao.getUserOpenId());

            String sql2 = "update b_subscribe set sub_bill = ? "
                    + ",sub_pay = ? "
                    + ",sub_arrears = ? "
                    + ",sub_coulometric_analysis = ? "
                    + ",sub_power = ? "
                    + " where user_id= ? ";
            jdbcTemplate.update(sql1,new Object[]{userSubscribeDao.getUserTel(),userSubscribeDao.getUserOpenId()});
            jdbcTemplate.update(sql2,new Object[]{
                    userSubscribeDao.getSubBill()
                    ,userSubscribeDao.getSubPay()
                    ,userSubscribeDao.getSubArrears()
                    ,userSubscribeDao.getSubCoulometricAnalysis()
                    ,userSubscribeDao.getSubPower()

                    ,userId
            });
        }else {
            String sql1 = "update b_user set user_tel ='" + userSubscribeDao.getUserTel() + "'"
                    + " where user_open_id = '" + userSubscribeDao.getUserOpenId() + "'";
            String userId = getUserIdByUserOpenId(userSubscribeDao.getUserOpenId());
            String sql2 = "update b_subscribe set sub_bill = " + userSubscribeDao.getSubBill()
                    + ",sub_pay = " + userSubscribeDao.getSubPay()
                    + ",sub_arrears = " + userSubscribeDao.getSubArrears()
                    + ",sub_coulometric_analysis = " + userSubscribeDao.getSubCoulometricAnalysis()
                    + ",sub_power = " + userSubscribeDao.getSubPower()
                    + " where user_id='" + userId + "'";
            jdbcTemplate.execute(sql1);
            jdbcTemplate.execute(sql2);
        }
    }




    class NoBindRowMapper implements RowMapper<HouseholdNumsDTO> {
        @Override
        public HouseholdNumsDTO mapRow(ResultSet rs, int i) throws SQLException {
            return new HouseholdNumsDTO(
                    rs.getString("pay_household_number")
            );
        }
    }


    class HouseholdInfoRowMapper implements RowMapper<HouseholdInfoDao> {
        @Override
        public HouseholdInfoDao mapRow(ResultSet rs, int i) throws SQLException {
            return new HouseholdInfoDao(
                    rs.getString("household_id"),
                    rs.getString("household_householder"),
                    rs.getString("household_number"),
                    rs.getString("household_address"),
                    rs.getBoolean("household_default"),
                    rs.getString("household_type"),
                    rs.getString("household_password"),
                    rs.getBoolean("is_available")
                    );
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
    class UserSubscribeRowMapper implements RowMapper<UserSubscribeDao> {
        @Override
        public UserSubscribeDao mapRow(ResultSet rs, int i) throws SQLException {
            return new UserSubscribeDao(
                    rs.getString("user_id"),
                    rs.getString("user_open_id"),
                    rs.getString("user_tel"),
                    rs.getString("sub_id"),

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
