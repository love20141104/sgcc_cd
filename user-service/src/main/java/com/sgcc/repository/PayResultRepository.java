package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.PayResultDao;
import com.sgcc.dto.PayQueryStatisticsDTO;
import com.sgcc.dto.PayStatisticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 新增支付结果信息
     * @param payResultDao
     * @return
     */
    public int insertPayResult(PayResultDao payResultDao){
        String sql = "insert into b_pay_info(id,pay_id,order_number,pay_household_number," +
                "user_open_id,pay_totalFee,pay_date,payment_channel) values('"+payResultDao.getId()+"'," +
                "'"+payResultDao.getPayId()+"','"+payResultDao.getOrderNo()+"'," +
                "'"+payResultDao.getUserNo()+"','"+payResultDao.getOpenId()+"',"+payResultDao.getMoney()+"," +
                "'"+ Utils.GetTime(payResultDao.getOrderSubmitTime()) +"'," +
                "'"+payResultDao.getPaymentChannel()+"')";

        return jdbcTemplate.update(sql);
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
     * 根据月份查询今年缴费结果
     * @return
     */
    public List<PayQueryStatisticsDTO> findPayResultByMonth(){

        String sql = "select COUNT(id) AS count,sum(pay_totalFee) as pay_totalFee,DATE_FORMAT(pay_date,'%m') as conditions " +
                "from b_pay_info where YEAR(pay_date)=YEAR(NOW()) group by DATE_FORMAT(pay_date ,'%m')";

        return jdbcTemplate.query(sql,new PayStatisticsRowMapper());
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





    class PayStatisticsRowMapper implements RowMapper<PayQueryStatisticsDTO>{
        @Override
        public PayQueryStatisticsDTO mapRow(ResultSet rs, int i) throws SQLException {
            return new PayQueryStatisticsDTO(
                    rs.getInt("count"),
                    rs.getDouble("pay_totalFee"),
                    rs.getString("conditions")
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
