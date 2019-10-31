package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.InhabitantInfoCorrectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询所有信息修正订单
     * @return
     */
    public List<InhabitantInfoCorrectDao> findInhabitantAll(){
        String sql = "select correct_id,user_open_id,house_id,correct_name,correct_idcard,correct_tel," +
                "correct_idcard_positive_img,correct_idcard_back_img,correct_new_name,correct_new_address," +
                "correct_new_tel,correct_submit_date from b_info_correct_inhabitant";
        return jdbcTemplate.query(sql,new InfoCorrectRowMapper());
    }



    /**
     * 新增信息修正订单
     * @param dao
     * @return
     */
    public int addInhabitantInfoCorrectOrder(InhabitantInfoCorrectDao dao){
        String sql = "insert into b_info_correct_inhabitant(correct_id, user_open_id, house_id, correct_name, " +
                "correct_idcard, correct_tel, correct_idcard_positive_img, correct_idcard_back_img, correct_new_name, " +
                "correct_new_address, correct_new_tel, correct_submit_date) values('"+dao.getCorrectId()+"'," +
                "'"+dao.getUserOpenId()+"','"+dao.getHouseId()+"','"+dao.getCorrectName()+"','"+dao.getCorrectIdcard()+"'," +
                "'"+dao.getCorrectTel()+"','"+dao.getCorrectIdcardPositiveImg()+"','"+dao.getCorrectIdcardBackImg()+"'," +
                "'"+dao.getCorrectNewName()+"','"+dao.getCorrectNewAddress()+"','"+dao.getCorrectNewTel()+"'," +
                "'"+ Utils.GetTime(dao.getCorrectSubmitDate()) +"')";

        return jdbcTemplate.update(sql);

    }



    public int updateInhabitantInfoCorrect(InhabitantInfoCorrectDao dao){
        String sql = "update b_info_correct_inhabitant set house_id= '"+dao.getHouseId()+"'," +
                "correct_name='"+dao.getCorrectName()+"',correct_idcard='"+dao.getCorrectIdcard()+"'," +
                "correct_tel='"+dao.getCorrectTel()+"',correct_idcard_positive_img='"+dao.getCorrectIdcardPositiveImg()+"'," +
                "correct_idcard_back_img='"+dao.getCorrectIdcardBackImg()+"',correct_new_name='"+dao.getCorrectNewName()+"'," +
                "correct_new_address='"+dao.getCorrectNewAddress()+"',correct_new_tel='"+dao.getCorrectNewTel()+"'" +
                " where correct_id ='"+dao.getCorrectId()+"'";

        return jdbcTemplate.update(sql);
    }


    /**
     * 删除一个或多个信息修正订单
     * @param ids
     * @return
     */
    public int delInhabitantCorrectIds(List<String> ids){
        String sql = "delete from b_info_correct_inhabitant where correct_id in('"+Utils.joinStrings(ids,"','")+"')";

        return jdbcTemplate.update(sql);
    }



    class InfoCorrectRowMapper implements RowMapper<InhabitantInfoCorrectDao>{
        @Override
        public InhabitantInfoCorrectDao mapRow(ResultSet rs, int i) throws SQLException {
            return new InhabitantInfoCorrectDao(
                    rs.getString("correct_id"),
                    rs.getString("user_open_id"),
                    rs.getString("house_id"),
                    rs.getString("correct_name"),
                    rs.getString("correct_idcard"),
                    rs.getString("correct_tel"),
                    rs.getString("correct_idcard_positive_img"),
                    rs.getString("correct_idcard_back_img"),
                    rs.getString("correct_new_name"),
                    rs.getString("correct_new_address"),
                    rs.getString("correct_new_tel"),
                    Utils.GetDate(rs.getString("correct_submit_date"))
            );
        }
    }



}
