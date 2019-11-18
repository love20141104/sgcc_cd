package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.PayResultDao;
import com.sgcc.dto.PayQueryStatisticsDTO;
import com.sgcc.dto.PayResultViewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PayResultRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${precompile}")
    private Boolean precompile;

    /**
     * 查询最近一次缴费记录
     * @param openId
     * @return
     */
    public PayResultViewsDTO findMoneyByRecently(String openId){
        if (precompile) {
            try {
                String sql = "select pay_totalFee from b_pay_info where pay_household_number in("
                        + "select hi.household_number from b_user u,r_user_household uh,b_household_info hi"
                        + " where u.user_id=uh.user_id and uh.household_id=hi.household_id and hi.household_default=true and u.user_open_id=?)"
                        + "and pay_date in (select max(pay_date) from b_pay_info where pay_household_number in("
                        + "select hi.household_number from b_user u,r_user_household uh,b_household_info hi"
                        + " where u.user_id=uh.user_id and uh.household_id=hi.household_id and hi.household_default=true and u.user_open_id=?));";

                return jdbcTemplate.queryForObject(sql,new Object[]{openId,openId}, new findMoneyByRecentlyRowMapper());
            } catch (Exception e) {
                return null;
            }
        }else {
            try {
                String sql = "select pay_totalFee from b_pay_info where pay_household_number in("
                        + "select hi.household_number from b_user u,r_user_household uh,b_household_info hi"
                        + " where u.user_id=uh.user_id and uh.household_id=hi.household_id and hi.household_default=true and u.user_open_id='" + openId + "')"
                        + "and pay_date in (select max(pay_date) from b_pay_info where pay_household_number in("
                        + "select hi.household_number from b_user u,r_user_household uh,b_household_info hi"
                        + " where u.user_id=uh.user_id and uh.household_id=hi.household_id and hi.household_default=true and u.user_open_id='" + openId + "'));";

                return jdbcTemplate.queryForObject(sql, new findMoneyByRecentlyRowMapper());
            } catch (Exception e) {
                return null;
            }
        }
    }




    /**
     * 新增支付结果信息
     * @param payResultDao
     * @return
     */
    public int insertPayResult(PayResultDao payResultDao){
        if (precompile) {
            String sql = "insert into b_pay_info(id,pay_id,order_number,pay_household_number," +
                    "user_open_id,pay_totalFee,pay_date,payment_channel) values(?,?,?,?,? ,?,?,?)";

            return jdbcTemplate.update(sql,new Object[]{
                    payResultDao.getId()
                    ,payResultDao.getPayId()
                    , payResultDao.getOrderNo()
                    ,payResultDao.getUserNo()
                    , payResultDao.getOpenId()

                    , payResultDao.getMoney()
                    ,Utils.GetTime(payResultDao.getOrderSubmitTime())
                    ,payResultDao.getPaymentChannel()});
        }else {
            String sql = "insert into b_pay_info(id,pay_id,order_number,pay_household_number," +
                    "user_open_id,pay_totalFee,pay_date,payment_channel) values('" + payResultDao.getId() + "'," +
                    "'" + payResultDao.getPayId() + "','" + payResultDao.getOrderNo() + "'," +
                    "'" + payResultDao.getUserNo() + "','" + payResultDao.getOpenId() + "'," + payResultDao.getMoney() + "," +
                    "'" + Utils.GetTime(payResultDao.getOrderSubmitTime()) + "'," +
                    "'" + payResultDao.getPaymentChannel() + "')";

            return jdbcTemplate.update(sql);
        }
    }

    /**
     * 查询所有支付结果信息
     */
    public List<PayResultDao> findPayResult(){
        String sql = "select id,pay_id,order_number,pay_household_number," +
                "user_open_id,pay_totalFee,pay_date,payment_channel from b_pay_info";

        return jdbcTemplate.query(sql,new PayResultRowMapper());
    }



    /**
     * 根据当前时间查询最近30天缴费结果
     * @return
     */
    public PayQueryStatisticsDTO findPayResultByMonth(String date){
        if (precompile) {
            String sql = "select COUNT(id) AS count,sum(pay_totalFee) as pay_totalFee" +
                    " from b_pay_info WHERE date_sub( ? , interval 30 day ) <= pay_date;";

            return jdbcTemplate.queryForObject(sql,new Object[]{date}, new PayStatisticsRowMapper());
        }else {
            String sql = "select COUNT(id) AS count,sum(pay_totalFee) as pay_totalFee" +
                    " from b_pay_info WHERE date_sub('" + date + "', interval 30 day ) <= pay_date;";

            return jdbcTemplate.queryForObject(sql, new PayStatisticsRowMapper());
        }
    }


    /**
     * 根据当前时间查询每月缴费结果
     * @return
     */
    public List<PayQueryStatisticsDTO> findPayResultByCurrentMonth(String date){
        if (precompile) {
            String sql = "select COUNT(id) AS count,sum(pay_totalFee) as pay_totalFee " +
                    "from b_pay_info where YEAR(pay_date)=YEAR( ? ) group by DATE_FORMAT(pay_date ,'%m')";

            return jdbcTemplate.query(sql,new Object[]{date}, new PayStatisticsRowMapper());
        }else {
            String sql = "select COUNT(id) AS count,sum(pay_totalFee) as pay_totalFee " +
                    "from b_pay_info where YEAR(pay_date)=YEAR('" + date + "') group by DATE_FORMAT(pay_date ,'%m')";

            return jdbcTemplate.query(sql, new PayStatisticsRowMapper());
        }
    }

    /**
     * 根据年份查询缴费结果
     * @return
     */
    public List<PayQueryStatisticsDTO> findPayResultByYear(){

        String sql = "select COUNT(id) AS count,sum(pay_totalFee) as pay_totalFee,DATE_FORMAT(pay_date,'%Y') as conditions" +
                " from b_pay_info group by DATE_FORMAT(pay_date ,'%Y');";

        return jdbcTemplate.query(sql,new PayStatisticsRowMapper());
    }


    /**
     * 根据当前时间查询当前年份的缴费结果
     * @param date
     * @return
     */
    public PayQueryStatisticsDTO findPayResultByCurrentYear(String date){
        if (precompile) {
            String sql = "select COUNT(id) AS count,sum(pay_totalFee) as pay_totalFee" +
                    " from b_pay_info where YEAR(pay_date) = year( ? ) group by DATE_FORMAT(pay_date ,'%Y');";

            return jdbcTemplate.queryForObject(sql,new Object[]{date}, new PayStatisticsRowMapper());
        }else {
            String sql = "select COUNT(id) AS count,sum(pay_totalFee) as pay_totalFee" +
                    " from b_pay_info where YEAR(pay_date) = year('" + date + "') group by DATE_FORMAT(pay_date ,'%Y');";

            return jdbcTemplate.queryForObject(sql, new PayStatisticsRowMapper());
        }
    }




    class PayStatisticsRowMapper implements RowMapper<PayQueryStatisticsDTO>{
        @Override
        public PayQueryStatisticsDTO mapRow(ResultSet rs, int i) throws SQLException {
            return new PayQueryStatisticsDTO(
                    rs.getInt("count"),
                    rs.getDouble("pay_totalFee")
            );
        }
    }


    class findMoneyByRecentlyRowMapper implements RowMapper<PayResultViewsDTO>{

        @Override
        public PayResultViewsDTO mapRow(ResultSet rs, int i) throws SQLException {
            return new PayResultViewsDTO(
                    rs.getDouble("pay_totalFee"),
                    null
            );
        }
    }



    class PayResultRowMapper implements RowMapper<PayResultDao>{

        @Override
        public PayResultDao mapRow(ResultSet rs, int i) throws SQLException {
            return new PayResultDao(
                    rs.getString("id"),
                    rs.getString("pay_id"),
                    rs.getString("order_number"),
                    rs.getString("pay_household_number"),
                    rs.getString("user_open_id"),
                    rs.getDouble("pay_totalFee"),
                    rs.getString("payment_channel"),
                    Utils.GetDate(rs.getString("pay_date"))
            );
        }
    }



}
