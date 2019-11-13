package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.PayResultDao;
import com.sgcc.dto.PaymentAmountChartDTO;
import com.sgcc.dto.PaymentTimesDTO;
import com.sgcc.dto.TotalFeesAvgChartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class ChartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 一年每月缴费金额
     * @return
     */
    public List<PaymentAmountChartDTO> findPaymentAmountChart(){
//        String sql = "select sum(pay_totalFee) as total,date_format(pay_date ,'%Y-%m') as pay_date from b_pay_info " +
//                "WHERE date_sub(curdate(), interval 12 month ) < date(pay_date) group by DATE_FORMAT(pay_date ,'%Y-%m') " +
//                "ORDER BY DATE_FORMAT(pay_date ,'%Y-%m') asc";

        String sql = "select ifnull(sum(pay_totalFee),0) as total,d.date as pay_date from b_pay_info p " +
                "RIGHT JOIN (select DATE_FORMAT(date_sub(curdate(), interval 11 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 10 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 9 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 8 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 7 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 6 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 5 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 4 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 3 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 2 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 1 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 0 month ),'%Y-%m') as date) d " +
                "ON date_format(p.pay_date ,'%Y-%m')=d.date " +
                "group by d.date " +
                "order by d.date asc;";



        return jdbcTemplate.query(sql,new PaymentAmountChartRowMapper());
    }

    class PaymentAmountChartRowMapper implements RowMapper<PaymentAmountChartDTO>{
        @Override
        public PaymentAmountChartDTO mapRow(ResultSet rs, int i) throws SQLException {
            return new PaymentAmountChartDTO(
                    rs.getDouble("total"),
                    rs.getString("pay_date")
            );
        }
    }



    /**
     * 查询十天总支付笔数
     * @return
     */
    public PaymentTimesDTO findAllPaymentTimesChart(){

        String sql = "select COUNT(id) AS total from b_pay_info "
                +"WHERE date_sub(curdate(), interval 10 day ) < date(pay_date)";
        return jdbcTemplate.queryForObject(sql,new PaymentTimesChartAllRowMapper());
    }

    /**
     * 查询最近十天每天支付笔数
     * @return
     */
    public List<PaymentTimesDTO> findPaymentTimesChart(){
//        String sql = "select COUNT(id) AS total,DATE_FORMAT(pay_date ,'%Y-%m-%d') as pay_date "
//                +"from b_pay_info WHERE date_sub(curdate(), interval 10 day ) < date(pay_date) "
//                +"group by DATE_FORMAT(pay_date ,'%Y-%m-%d') ORDER BY DATE_FORMAT(pay_date ,'%Y-%m-%d') asc";

        String sql = "select COUNT(id) AS total,d.date  as pay_date from b_pay_info p " +
                "right join (select date_sub(curdate(), interval 9 day ) as date " +
                "union select date_sub(curdate(), interval 8 day ) as date " +
                "union select date_sub(curdate(), interval 7 day ) as date " +
                "union select date_sub(curdate(), interval 6 day ) as date " +
                "union select date_sub(curdate(), interval 5 day ) as date " +
                "union select date_sub(curdate(), interval 4 day ) as date " +
                "union select date_sub(curdate(), interval 3 day ) as date " +
                "union select date_sub(curdate(), interval 2 day ) as date " +
                "union select date_sub(curdate(), interval 1 day ) as date " +
                "union select date_sub(curdate(), interval 0 day ) as date) d " +
                "on DATE_FORMAT(p.pay_date ,'%Y-%m-%d') = d.date group by d.date order by d.date asc ";

        List<PaymentTimesDTO> paymentTimesDTOS = jdbcTemplate.query(sql,new PaymentTimesChartRowMapper());
        return paymentTimesDTOS;
    }

    class PaymentTimesChartAllRowMapper implements RowMapper<PaymentTimesDTO>{
        @Override
        public PaymentTimesDTO mapRow(ResultSet rs, int i) throws SQLException {
            return new PaymentTimesDTO(
                    rs.getDouble("total"),
                    null
            );
        }
    }




    class PaymentTimesChartRowMapper implements RowMapper<PaymentTimesDTO>{
        @Override
        public PaymentTimesDTO mapRow(ResultSet rs, int i) throws SQLException {
            return new PaymentTimesDTO(
                    rs.getDouble("total"),
                    rs.getString("pay_date")
            );
        }
    }



    /**
     * 查询最近10天缴费总额和日均销售额
     * @return
     */
    public TotalFeesAvgChartDTO findTotalFeesAvgChart(){
        String sql = "select (select sum(pay_totalFee) as total from b_pay_info WHERE date_sub(curdate(), interval 10 day ) < date(pay_date)) as total,\n" +
                "       (select sum(pay_totalFee)/10 as average from b_pay_info WHERE date_sub(curdate(), interval 10 day ) < date(pay_date)) as average,\n" +
                "       sum(pay_totalFee) thisWeekTotal,\n" +
                "       (select sum(pay_totalFee) from b_pay_info WHERE yearweek(date_format(pay_date,'%Y-%m-%d'),1) = yearweek(now(),1)-1) as ratio\n" +
                "from b_pay_info WHERE yearweek(date_format(pay_date,'%Y-%m-%d'),1) = yearweek(now(),1) ";
        TotalFeesAvgChartDTO totalFeesAvgChartDTO = jdbcTemplate.queryForObject(sql,new TotalFeesAvgChartRowMapper());
        // 计算同比增长率


        if (totalFeesAvgChartDTO.getRatio() != 0){
            double ratio = (totalFeesAvgChartDTO.getThisWeekTotal()-totalFeesAvgChartDTO.getRatio())/totalFeesAvgChartDTO.getRatio();
            totalFeesAvgChartDTO.setRatio(ratio);
        }else {
            totalFeesAvgChartDTO.setRatio(0);
        }
        return totalFeesAvgChartDTO;
    }


    class TotalFeesAvgChartRowMapper implements RowMapper<TotalFeesAvgChartDTO>{
        @Override
        public TotalFeesAvgChartDTO mapRow(ResultSet rs, int i) throws SQLException {
            return new TotalFeesAvgChartDTO(
                    rs.getDouble("total"),
                    rs.getDouble("thisWeekTotal"),
                    rs.getDouble("ratio"),
                    rs.getDouble("average")
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
