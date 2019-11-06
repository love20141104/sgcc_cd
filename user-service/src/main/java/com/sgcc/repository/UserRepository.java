package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.CommerceInfoCorrectDao;
import com.sgcc.dao.InhabitantInfoCorrectDao;
import com.sgcc.dto.commerce.CommerceInfoCorrectSubmitDTO;
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

    /**************************************************个体工商业************************************************************/
    /**
     * 查询所有个体工商业信息修正订单
     * @return
     */
    public List<CommerceInfoCorrectDao> findCommerceAll(){
        String sql = "select correct_id,user_open_id,house_id,correct_name,correct_idcard,correct_tel," +
                "correct_idcard_positive_img,correct_idcard_back_img,correct_license_img,correct_new_name," +
                "correct_new_address,correct_new_tel,propertyRight_img1,propertyRight_img2,propertyRight_img3," +
                "propertyRight_img4,propertyRight_img5,propertyRight_img6,correct_submit_date " +
                "from b_info_correct_commerce";
        return jdbcTemplate.query(sql,new CommerceInfoCorrectRowMapper());
    }

    /**
     * 新增个体工商业信息修正订单
     * @param dao
     * @return
     */
    public int addCommerceInfoCorrectOrder(CommerceInfoCorrectDao dao){
        String sql = "insert into b_info_correct_commerce(correct_id,user_open_id,house_id,correct_name,correct_idcard," +
                "correct_tel,correct_idcard_positive_img,correct_idcard_back_img,correct_license_img,correct_new_name," +
                "correct_new_address,correct_new_tel,propertyRight_img1,propertyRight_img2,propertyRight_img3," +
                "propertyRight_img4,propertyRight_img5,propertyRight_img6,correct_submit_date) values('"+dao.getCorrectId()+"'," +
                "'"+dao.getUserOpenId()+"','"+dao.getHouseId()+"','"+dao.getCorrectName()+"','"+dao.getCorrectIdcard()+"'," +
                "'"+dao.getCorrectTel()+"','"+dao.getCorrectIdcardPositiveImg()+"','"+dao.getCorrectIdcardBackImg()+"'," +
                "'"+dao.getCorrectLicenseImg()+"','"+dao.getCorrectNewName()+"','"+dao.getCorrectNewAddress()+"'," +
                "'"+dao.getCorrectNewTel()+"','"+dao.getPropertyRightImg1()+"','"+dao.getPropertyRightImg2()+"'," +
                "'"+dao.getPropertyRightImg3()+"','"+dao.getPropertyRightImg4()+"','"+dao.getPropertyRightImg5()+"'," +
                "'"+dao.getPropertyRightImg6()+"','"+Utils.GetTime(dao.getCorrectSubmitDate())+"')";
        return jdbcTemplate.update(sql);
    }


    /**
     * 修改个体工商业信息修正订单
     * @param dao
     * @return
     */
    public int updateCommerceInfoCorrectOrder(CommerceInfoCorrectDao dao){
        String sql = "update b_info_correct_commerce set " +
                "house_id = '"+dao.getHouseId()+"'," +
                "correct_name = '"+dao.getCorrectName()+"'," +
                "correct_idcard = '"+dao.getCorrectIdcard()+"'," +
                "correct_tel = '"+dao.getCorrectTel()+"'," +
                "correct_idcard_positive_img = '"+dao.getCorrectIdcardPositiveImg()+"'," +
                "correct_idcard_back_img = '"+dao.getCorrectIdcardBackImg()+"'," +
                "correct_license_img = '"+dao.getCorrectLicenseImg()+"'," +
                "correct_new_name = '"+dao.getCorrectNewName()+"'," +
                "correct_new_address = '"+dao.getCorrectNewAddress()+"'," +
                "correct_new_tel = '"+dao.getCorrectNewTel()+"'," +
                "propertyRight_img1 = '"+dao.getPropertyRightImg1()+"'," +
                "propertyRight_img2 = '"+dao.getPropertyRightImg2()+"'," +
                "propertyRight_img3 = '"+dao.getPropertyRightImg3()+"'," +
                "propertyRight_img4 = '"+dao.getPropertyRightImg4()+"'," +
                "propertyRight_img5 = '"+dao.getPropertyRightImg5()+"'," +
                "propertyRight_img6 = '"+dao.getPropertyRightImg6()+"'" +
                " where correct_id = '"+dao.getCorrectId()+"'";
        return jdbcTemplate.update(sql);
    }


    /**
     * 删除个体工商业信息修正订单
     * @return
     */
    public int delCommerceInfoCorrectOrder(List<String> ids){
        String sql = "delete from b_info_correct_commerce where correct_id in('"+Utils.joinStrings(ids,"','")+"')";
        return jdbcTemplate.update(sql);
    }


    class CommerceInfoCorrectRowMapper implements RowMapper<CommerceInfoCorrectDao>{
        @Override
        public CommerceInfoCorrectDao mapRow(ResultSet rs, int i) throws SQLException {
            return new CommerceInfoCorrectDao(
                    rs.getString("correct_id"),
                    rs.getString("user_open_id"),
                    rs.getString("house_id"),
                    rs.getString("correct_name"),
                    rs.getString("correct_idcard"),
                    rs.getString("correct_tel"),
                    rs.getString("correct_idcard_positive_img"),
                    rs.getString("correct_idcard_back_img"),
                    rs.getString("correct_license_img"),
                    rs.getString("correct_new_name"),
                    rs.getString("correct_new_address"),
                    rs.getString("correct_new_tel"),
                    rs.getString("propertyRight_img1"),
                    rs.getString("propertyRight_img2"),
                    rs.getString("propertyRight_img3"),
                    rs.getString("propertyRight_img4"),
                    rs.getString("propertyRight_img5"),
                    rs.getString("propertyRight_img6"),
                    Utils.GetDate(rs.getString("correct_submit_date"))
            );
        }
    }


    /**************************************************居民************************************************************/

    /**
     * 查询所有居民信息修正订单
     * @return
     */
    public List<InhabitantInfoCorrectDao> findInhabitantAll(){
        String sql = "select correct_id,user_open_id,house_id,correct_name,correct_idcard,correct_tel," +
                "correct_idcard_positive_img,correct_idcard_back_img,correct_new_name,correct_new_address," +
                "correct_new_tel,correct_submit_date from b_info_correct_inhabitant";
        return jdbcTemplate.query(sql,new InhabitantInfoCorrectRowMapper());
    }



    /**
     * 新增居民信息修正订单
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


    /**
     * 修改居民信息修正订单
     * @param dao
     * @return
     */
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
     * 删除一个或多个居民信息修正订单
     * @param ids
     * @return
     */
    public int delInhabitantCorrectIds(List<String> ids){
        String sql = "delete from b_info_correct_inhabitant where correct_id in('"+Utils.joinStrings(ids,"','")+"')";

        return jdbcTemplate.update(sql);
    }



    class InhabitantInfoCorrectRowMapper implements RowMapper<InhabitantInfoCorrectDao>{
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