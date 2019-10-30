package com.sgcc.inhabitant.Repository;

import com.example.Utils;
import com.sgcc.inhabitant.dao.InhabitantRenameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InhabitantRenameRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 查询所有更名过户订单
     * @return
     */
    public List<InhabitantRenameDao> findAll(){
        String sql = "select id,info_id,house_id,user_open_id,info_change,info_name,info_idcard," +
                "info_telphone,cq_idcard_img_positive,cq_idcard_img_back,info_apply_person,info_transactor," +
                "sq_arttorney_img,sq_idcard_positive_img,sq_idcard_back_img,info_transactor_idcard,info_transactor_tel," +
                "info_submit_date from b_rename_transfer";

        return jdbcTemplate.query(sql,new RenameRowMapper());
    }

    /**
     * 根据当前户号信息查询更名过户订单
     * @param infoId
     * @return
     */
    public List<InhabitantRenameDao> findRenameOrderList(String infoId){
        String sql = "select id,info_id,house_id,user_open_id,info_change,info_name,info_idcard," +
                "info_telphone,cq_idcard_img_positive,cq_idcard_img_back,info_apply_person,info_transactor," +
                "sq_arttorney_img,sq_idcard_positive_img,sq_idcard_back_img,info_transactor_idcard,info_transactor_tel," +
                "info_submit_date from b_rename_transfer" +
                " where info_id='"+infoId+"'";

        return jdbcTemplate.query(sql,new RenameRowMapper());
    }


    public List<InhabitantRenameDao> findRenameById(String openId){
        String sql = "select id,info_id,house_id,user_open_id,info_change,info_name,info_idcard," +
                "info_telphone,cq_idcard_img_positive,cq_idcard_img_back,info_apply_person,info_transactor," +
                "sq_arttorney_img,sq_idcard_positive_img,sq_idcard_back_img,info_transactor_idcard,info_transactor_tel," +
                "info_submit_date from b_rename_transfer" +
                " where user_open_id='"+openId+"'";

        return jdbcTemplate.query(sql,new RenameRowMapper());
    }



    /**
     * 删除
     * @param ids
     */
    public void delRenameOrderList(List<String> ids){
        String sql = "delete from b_rename_transfer where info_id in('"+Utils.joinStrings(ids,"','")+"')";
        jdbcTemplate.execute(sql);
    }



    /**
     *修改
     */
    public int updateRenameOrderList(InhabitantRenameDao dao){
        String sql = "update b_rename_transfer set house_id='"+dao.getHouseId()+"'," +
                "info_change = "+dao.getChange()+"," +
                "info_name = '"+dao.getInfoName()+"'," +
                "info_idcard = '"+dao.getInfoIdCard()+"'," +
                "info_telphone = '"+dao.getInfoTel()+"'," +
                "cq_idcard_img_positive = '"+dao.getCqIdCardPositiveImg()+"'," +
                "cq_idcard_img_back = '"+dao.getCqIdCardBackImg()+"'," +
                "info_apply_person = '"+dao.getApplicant()+"'," +
                "info_transactor = '"+dao.getApplicant()+"'," +
                "sq_arttorney_img = '"+dao.getSqArttorneyImg()+"'," +
                "sq_idcard_positive_img = '"+dao.getSqIdCardPositiveImg()+"'," +
                "sq_idcard_back_img = '"+dao.getSqIdCardBackImg()+"'," +
                "info_transactor_idcard = '"+dao.getTransactorIdCard()+"'," +
                "info_transactor_tel = '"+dao.getTransactorTel()+"'"+
                "where info_id='"+dao.getInfoId()+"'";
        return jdbcTemplate.update(sql);
    }

    /**
     * 新增更名过户订单
     * @param dao
     * @return
     */
    public int addRenameOrder(InhabitantRenameDao dao){
        String sql = "insert into b_rename_transfer(id,info_id,house_id,user_open_id,info_change,info_name,info_idcard," +
                "info_telphone,cq_idcard_img_positive,cq_idcard_img_back,info_apply_person,info_transactor," +
                "sq_arttorney_img,sq_idcard_positive_img,sq_idcard_back_img,info_transactor_idcard,info_transactor_tel," +
                "info_submit_date) values('"+dao.getId()+"'," +
                "'"+dao.getInfoId()+"','"+dao.getHouseId()+"','"+dao.getOpenId()+"',"+dao.getChange()+"," +
                "'"+dao.getInfoName()+"','"+dao.getInfoIdCard()+"','"+dao.getInfoTel()+"','"+dao.getCqIdCardPositiveImg()+"'," +
                "'"+dao.getCqIdCardBackImg()+"','"+dao.getApplicant()+"','"+dao.getTransactorName()+"','"+dao.getSqArttorneyImg()+"'," +
                "'"+dao.getSqIdCardPositiveImg()+"','"+dao.getSqIdCardBackImg()+"','"+dao.getTransactorIdCard()+"','"+dao.getTransactorTel()+"'," +
                "'"+ Utils.GetTime(dao.getSubmitDate())+"')";

        return jdbcTemplate.update(sql);
    }

    class RenameRowMapper implements RowMapper<InhabitantRenameDao>{

        @Override
        public InhabitantRenameDao mapRow(ResultSet rs, int i) throws SQLException {
            return new InhabitantRenameDao(
                   rs.getString("id"),
                    rs.getString("info_id"),
                    rs.getString("house_id"),
                    rs.getString("user_open_id"),
                    rs.getBoolean("info_change"),
                    rs.getString("info_name"),
                    rs.getString("info_idcard"),
                    rs.getString("info_telphone"),
                    rs.getString("cq_idcard_img_positive"),
                    rs.getString("cq_idcard_img_back"),
                    rs.getString("info_apply_person"),
                    rs.getString("info_transactor"),
                    rs.getString("sq_arttorney_img"),
                    rs.getString("sq_idcard_positive_img"),
                    rs.getString("sq_idcard_back_img"),
                    rs.getString("info_transactor_idcard"),
                    rs.getString("info_transactor_tel"),
                    rs.getDate("info_submit_date")
            );
        }
    }


}
