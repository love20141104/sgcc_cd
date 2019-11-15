package com.sgcc.inhabitant.Repository;

import com.example.Utils;
import com.sgcc.inhabitant.dao.InhabitantIncreaseCapacityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InhabitantIncreaseCapacityRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${precompile}")
    private Boolean precompile;
    /**
     * 根据订单号查询增容订单详细
     * @param orderNo
     * @return
     */
    public List<InhabitantIncreaseCapacityDao> findOrderDetail(String orderNo){
        if (precompile) {
            String sql = "select id,in_order_no,user_open_id,in_current_capacity," +
                    "in_name,in_idcard,in_telphone,in_apply_person,in_transactor,in_transactor_idcard,cq_idcard_positive_img," +
                    "cq_idcard_back_img,sq_arttorney_img,sq_idcard_positive_img,sq_idcard_back_img,in_submit_date " +
                    "from b_increase_capacity_inhabitant where in_order_no=? ";
            return jdbcTemplate.query(sql,new Object[]{orderNo}, new IncreaseCapacityRowMapper());
        }else {
            String sql = "select id,in_order_no,user_open_id,in_current_capacity," +
                    "in_name,in_idcard,in_telphone,in_apply_person,in_transactor,in_transactor_idcard,cq_idcard_positive_img," +
                    "cq_idcard_back_img,sq_arttorney_img,sq_idcard_positive_img,sq_idcard_back_img,in_submit_date " +
                    "from b_increase_capacity_inhabitant where in_order_no='" + orderNo + "'";
            return jdbcTemplate.query(sql, new IncreaseCapacityRowMapper());
        }
    }

    /**
     * 新增增容订单
     * @param dao
     * @return
     */
    public int addIncreaseCapacityOrder(InhabitantIncreaseCapacityDao dao){
        if (precompile) {
            String sql = "insert into b_increase_capacity_inhabitant(id,in_order_no,user_open_id,in_current_capacity," +
                    "in_name,in_idcard,in_telphone,in_apply_person,in_transactor,in_transactor_idcard,cq_idcard_positive_img," +
                    "cq_idcard_back_img,sq_arttorney_img,sq_idcard_positive_img,sq_idcard_back_img,in_submit_date) " +
                    " values(?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?)";
            return jdbcTemplate.update(sql,new Object[]{
                    dao.getId()
                    ,dao.getOrderNo()
                    ,dao.getOpenId()
                    ,dao.getCurrentCapacity()
                    ,dao.getName()

                    ,dao.getIdcard()
                    ,dao.getContactTel()
                    ,dao.getAplicant()
                    ,dao.getTransactor()
                    ,dao.getTransactorIdcard()

                    ,dao.getCqIdcardPositiveImg()
                    ,dao.getCqIdcardBackImg()
                    ,dao.getSq_arttorney_img()
                    ,dao.getSqIdcardPositiveImg()
                    ,dao.getSqIdcardBackImg()

                    ,Utils.GetTime(dao.getSubmitDate())
            });
        }else {
            String sql = "insert into b_increase_capacity_inhabitant(id,in_order_no,user_open_id,in_current_capacity," +
                    "in_name,in_idcard,in_telphone,in_apply_person,in_transactor,in_transactor_idcard,cq_idcard_positive_img," +
                    "cq_idcard_back_img,sq_arttorney_img,sq_idcard_positive_img,sq_idcard_back_img,in_submit_date) values(" +
                    "'" + dao.getId() + "','" + dao.getOrderNo() + "','" + dao.getOpenId() + "'," + dao.getCurrentCapacity() + "," +
                    "'" + dao.getName() + "','" + dao.getIdcard() + "','" + dao.getContactTel() + "','" + dao.getAplicant() + "'," +
                    "'" + dao.getTransactor() + "','" + dao.getTransactorIdcard() + "','" + dao.getCqIdcardPositiveImg() + "'," +
                    "'" + dao.getCqIdcardBackImg() + "','" + dao.getSq_arttorney_img() + "','" + dao.getSqIdcardPositiveImg() + "','" + dao.getSqIdcardBackImg() + "'," +
                    "'" + Utils.GetTime(dao.getSubmitDate()) + "')";
            return jdbcTemplate.update(sql);
        }
    }


    class IncreaseCapacityRowMapper implements RowMapper<InhabitantIncreaseCapacityDao>{

        @Override
        public InhabitantIncreaseCapacityDao mapRow(ResultSet rs, int i) throws SQLException {
            return new InhabitantIncreaseCapacityDao(
                    rs.getString("id"),
                    rs.getString("in_order_no"),
                    rs.getString("user_open_id"),
                    rs.getDouble("in_current_capacity"),
                    rs.getString("in_name"),
                    rs.getString("in_idcard"),
                    rs.getString("in_telphone"),
                    rs.getString("in_apply_person"),
                    rs.getString("in_transactor"),
                    rs.getString("in_transactor_idcard"),
                    rs.getString("cq_idcard_positive_img"),
                    rs.getString("cq_idcard_back_img"),
                    rs.getString("sq_arttorney_img;"),
                    rs.getString("sq_idcard_positive_img"),
                    rs.getString("sq_idcard_back_img"),
                    rs.getDate("in_submit_date")
            );
        }
    }


}
