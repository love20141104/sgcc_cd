package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.CommerceInfoCorrectDao;
import com.sgcc.dao.InhabitantInfoCorrectDao;
import com.sgcc.dao.SubscribeDao;
import com.sgcc.dto.HouseholdInfosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${precompile}")
    private Boolean precompile;




    public SubscribeDao findSubscribe(String openId){

        String sql = "select id,user_open_id,nick_name,sex,city,head_img_url,is_sub_bill,is_sub_pay,is_sub_notice_pay,is_sub_analysis " +
                "from t_wechat_users where user_open_id = '"+openId+"'";
        return jdbcTemplate.queryForObject(sql,new SubscribeRowMapper());

    }

    class SubscribeRowMapper implements RowMapper<SubscribeDao>{

        @Override
        public SubscribeDao mapRow(ResultSet rs, int i) throws SQLException {
            return new SubscribeDao(
                   rs.getString("id"),
                    rs.getString("user_open_id"),
                    rs.getString("nick_name"),
                    rs.getInt("sex"),
                    rs.getString("city"),
                    rs.getString("head_img_url"),
                    rs.getInt("is_sub_bill"),
                    rs.getInt("is_sub_pay"),
                    rs.getInt("is_sub_notice_pay"),
                    rs.getInt("is_sub_analysis")
            );
        }
    }


    public int updateSubscribe(SubscribeDao dao){

        String sql = "update t_wechat_users set ";
        String sqlWhere = " where user_open_id='"+dao.getUser_open_id()+"'";

        StringBuffer stringBuffer = new StringBuffer();
        if(dao.getIs_sub_bill() != null)
            stringBuffer.append("is_sub_bill='"+dao.getIs_sub_bill()+"',");
        if(dao.getIs_sub_pay() != null)
            stringBuffer.append("is_sub_pay='"+dao.getIs_sub_pay()+"',");
        if(dao.getIs_sub_notice_pay() != null)
            stringBuffer.append("is_sub_notice_pay='"+dao.getIs_sub_notice_pay()+"',");
        if(dao.getIs_sub_analysis() != null)
            stringBuffer.append("is_sub_analysis='"+dao.getIs_sub_analysis()+"',");

        if (!Strings.isNullOrEmpty(stringBuffer.toString())){
            sql += stringBuffer.substring(0,stringBuffer.lastIndexOf(",")) +sqlWhere;
        }

        return jdbcTemplate.update(sql);

    }




    /**
     * 查询默认户号信息
     * @param openId
     * @return
     */
    public HouseholdInfosDTO getDefaultHousehold(String openId){
        if (precompile) {
            try {
                String sql = "select hi.household_householder,hi.household_number,hi.household_address "
                        + "from b_user u,r_user_household uh,b_household_info hi"
                        + " where uh.user_id = u.user_id and hi.household_id = uh.household_id"
                        + " and u.user_open_id = ? and hi.household_default = true";
                return jdbcTemplate.queryForObject(sql,new Object[]{openId}, new DefaultHouseholdRowMapper());
            } catch (Exception e) {
                return null;
            }
        }else {
            try {
                String sql = "select hi.household_householder,hi.household_number,hi.household_address "
                        + "from b_user u,r_user_household uh,b_household_info hi"
                        + " where uh.user_id = u.user_id and hi.household_id = uh.household_id"
                        + " and u.user_open_id = '" + openId + "' and hi.household_default = true";
                return jdbcTemplate.queryForObject(sql, new DefaultHouseholdRowMapper());
            } catch (Exception e) {
                return null;
            }
        }
    }



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
        if (precompile) {
            String sql = "insert into b_info_correct_commerce(correct_id,user_open_id,house_id,correct_name,correct_idcard," +
                    "correct_tel,correct_idcard_positive_img,correct_idcard_back_img,correct_license_img,correct_new_name," +
                    "correct_new_address,correct_new_tel,propertyRight_img1,propertyRight_img2,propertyRight_img3," +
                    "propertyRight_img4,propertyRight_img5,propertyRight_img6,correct_submit_date) " +
                    "values(?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?)";
            return jdbcTemplate.update(sql,new Object[]{
                    dao.getCorrectId()
                    ,dao.getUserOpenId()
                    ,dao.getHouseId()
                    ,dao.getCorrectName()
                    ,dao.getCorrectIdcard()

                    ,dao.getCorrectTel()
                    , dao.getCorrectIdcardPositiveImg()
                    ,dao.getCorrectIdcardBackImg()
                    , dao.getCorrectLicenseImg()
                    ,dao.getCorrectNewName()

                    , dao.getCorrectNewAddress()
                    , dao.getCorrectNewTel()
                    , dao.getPropertyRightImg1()
                    , dao.getPropertyRightImg2()
                    , dao.getPropertyRightImg3()

                    , dao.getPropertyRightImg4()
                    , dao.getPropertyRightImg5()
                    ,dao.getPropertyRightImg6()
                    ,Utils.GetTime(dao.getCorrectSubmitDate())
            });
        }else {
            String sql = "insert into b_info_correct_commerce(correct_id,user_open_id,house_id,correct_name,correct_idcard," +
                    "correct_tel,correct_idcard_positive_img,correct_idcard_back_img,correct_license_img,correct_new_name," +
                    "correct_new_address,correct_new_tel,propertyRight_img1,propertyRight_img2,propertyRight_img3," +
                    "propertyRight_img4,propertyRight_img5,propertyRight_img6,correct_submit_date) values('" + dao.getCorrectId() + "'," +
                    "'" + dao.getUserOpenId() + "','" + dao.getHouseId() + "','" + dao.getCorrectName() + "','" + dao.getCorrectIdcard() + "'," +
                    "'" + dao.getCorrectTel() + "','" + dao.getCorrectIdcardPositiveImg() + "','" + dao.getCorrectIdcardBackImg() + "'," +
                    "'" + dao.getCorrectLicenseImg() + "','" + dao.getCorrectNewName() + "','" + dao.getCorrectNewAddress() + "'," +
                    "'" + dao.getCorrectNewTel() + "','" + dao.getPropertyRightImg1() + "','" + dao.getPropertyRightImg2() + "'," +
                    "'" + dao.getPropertyRightImg3() + "','" + dao.getPropertyRightImg4() + "','" + dao.getPropertyRightImg5() + "'," +
                    "'" + dao.getPropertyRightImg6() + "','" + Utils.GetTime(dao.getCorrectSubmitDate()) + "')";
            return jdbcTemplate.update(sql);
        }
    }


    /**
     * 修改个体工商业信息修正订单
     * @param dao
     * @return
     */
    public int updateCommerceInfoCorrectOrder(CommerceInfoCorrectDao dao){
        if (precompile) {
            String sql = "update b_info_correct_commerce set " +
                    "house_id = ? ," +
                    "correct_name = ? ," +
                    "correct_idcard = ? ," +
                    "correct_tel = ? ," +
                    "correct_idcard_positive_img = ? ," +
                    "correct_idcard_back_img = ? ," +
                    "correct_license_img = ? ," +
                    "correct_new_name = ? ," +
                    "correct_new_address = ? ," +
                    "correct_new_tel = ? ," +
                    "propertyRight_img1 = ? ," +
                    "propertyRight_img2 = ? ," +
                    "propertyRight_img3 = ? ," +
                    "propertyRight_img4 = ? ," +
                    "propertyRight_img5 = ? ," +
                    "propertyRight_img6 = ? " +
                    " where correct_id = ? ";
            return jdbcTemplate.update(sql,new Object[]{
                    dao.getHouseId()
                    ,dao.getCorrectName()
                    , dao.getCorrectIdcard()
                    , dao.getCorrectTel()
                    , dao.getCorrectIdcardPositiveImg()
                    , dao.getCorrectIdcardBackImg()
                    , dao.getCorrectLicenseImg()
                    , dao.getCorrectNewName()
                    , dao.getCorrectNewAddress()
                    , dao.getCorrectNewTel()
                    , dao.getPropertyRightImg1()
                    , dao.getPropertyRightImg2()
                    , dao.getPropertyRightImg3()
                    , dao.getPropertyRightImg4()
                    , dao.getPropertyRightImg5()
                    , dao.getPropertyRightImg6()
                    , dao.getCorrectId()
            });
        }else {
            String sql = "update b_info_correct_commerce set " +
                    "house_id = '" + dao.getHouseId() + "'," +
                    "correct_name = '" + dao.getCorrectName() + "'," +
                    "correct_idcard = '" + dao.getCorrectIdcard() + "'," +
                    "correct_tel = '" + dao.getCorrectTel() + "'," +
                    "correct_idcard_positive_img = '" + dao.getCorrectIdcardPositiveImg() + "'," +
                    "correct_idcard_back_img = '" + dao.getCorrectIdcardBackImg() + "'," +
                    "correct_license_img = '" + dao.getCorrectLicenseImg() + "'," +
                    "correct_new_name = '" + dao.getCorrectNewName() + "'," +
                    "correct_new_address = '" + dao.getCorrectNewAddress() + "'," +
                    "correct_new_tel = '" + dao.getCorrectNewTel() + "'," +
                    "propertyRight_img1 = '" + dao.getPropertyRightImg1() + "'," +
                    "propertyRight_img2 = '" + dao.getPropertyRightImg2() + "'," +
                    "propertyRight_img3 = '" + dao.getPropertyRightImg3() + "'," +
                    "propertyRight_img4 = '" + dao.getPropertyRightImg4() + "'," +
                    "propertyRight_img5 = '" + dao.getPropertyRightImg5() + "'," +
                    "propertyRight_img6 = '" + dao.getPropertyRightImg6() + "'" +
                    " where correct_id = '" + dao.getCorrectId() + "'";
            return jdbcTemplate.update(sql);
        }
    }


    /**
     * 删除个体工商业信息修正订单
     * @return
     */
    public int delCommerceInfoCorrectOrder(List<String> ids){
        if (precompile) {
            String sql = "delete from b_info_correct_commerce where correct_id = ? ";
             jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
                public int getBatchSize() {
                    return ids.size();
                }
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    ps.setString(1,ids.get(i));
                }
            });
             return ids.size();
        }else {
            String sql = "delete from b_info_correct_commerce where correct_id in('" + Utils.joinStrings(ids, "','") + "')";
            return jdbcTemplate.update(sql);
        }
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
        if (precompile) {
            String sql = "insert into b_info_correct_inhabitant(correct_id, user_open_id, house_id, correct_name, " +
                    "correct_idcard, correct_tel, correct_idcard_positive_img, correct_idcard_back_img, correct_new_name, " +
                    "correct_new_address, correct_new_tel, correct_submit_date)" +
                    " values(?,?,?,?,?,?,?,?,?,?,?,?)";

            return jdbcTemplate.update(sql,new Object[]{
                    dao.getCorrectId()
                    ,dao.getUserOpenId()
                    ,dao.getHouseId()
                    ,dao.getCorrectName()
                    , dao.getCorrectIdcard()
                    , dao.getCorrectTel()
                    , dao.getCorrectIdcardPositiveImg()
                    , dao.getCorrectIdcardBackImg()
                    , dao.getCorrectNewName()
                    ,dao.getCorrectNewAddress()
                    , dao.getCorrectNewTel()
                    ,Utils.GetTime(dao.getCorrectSubmitDate())
            });
        }else {
            String sql = "insert into b_info_correct_inhabitant(correct_id, user_open_id, house_id, correct_name, " +
                    "correct_idcard, correct_tel, correct_idcard_positive_img, correct_idcard_back_img, correct_new_name, " +
                    "correct_new_address, correct_new_tel, correct_submit_date) values('" + dao.getCorrectId() + "'," +
                    "'" + dao.getUserOpenId() + "','" + dao.getHouseId() + "','" + dao.getCorrectName() + "','" + dao.getCorrectIdcard() + "'," +
                    "'" + dao.getCorrectTel() + "','" + dao.getCorrectIdcardPositiveImg() + "','" + dao.getCorrectIdcardBackImg() + "'," +
                    "'" + dao.getCorrectNewName() + "','" + dao.getCorrectNewAddress() + "','" + dao.getCorrectNewTel() + "'," +
                    "'" + Utils.GetTime(dao.getCorrectSubmitDate()) + "')";

            return jdbcTemplate.update(sql);
        }

    }


    /**
     * 修改居民信息修正订单
     * @param dao
     * @return
     */
    public int updateInhabitantInfoCorrect(InhabitantInfoCorrectDao dao){
        if (precompile) {
            String sql = "update b_info_correct_inhabitant set house_id= ? ," +
                    "correct_name=? ,correct_idcard=? ," +
                    "correct_tel=? ,correct_idcard_positive_img=? ," +
                    "correct_idcard_back_img=? ,correct_new_name=? ," +
                    "correct_new_address=? ,correct_new_tel=? " +
                    " where correct_id =? ";

            return jdbcTemplate.update(sql,new Object[]{
                    dao.getHouseId()
                    ,dao.getCorrectName()
                    , dao.getCorrectIdcard()
                    , dao.getCorrectTel()
                    , dao.getCorrectIdcardPositiveImg()
                    , dao.getCorrectIdcardBackImg()
                    , dao.getCorrectNewName()
                    , dao.getCorrectNewAddress()
                    , dao.getCorrectNewTel()
                    , dao.getCorrectId()
            });
        }else {
            String sql = "update b_info_correct_inhabitant set house_id= '" + dao.getHouseId() + "'," +
                    "correct_name='" + dao.getCorrectName() + "',correct_idcard='" + dao.getCorrectIdcard() + "'," +
                    "correct_tel='" + dao.getCorrectTel() + "',correct_idcard_positive_img='" + dao.getCorrectIdcardPositiveImg() + "'," +
                    "correct_idcard_back_img='" + dao.getCorrectIdcardBackImg() + "',correct_new_name='" + dao.getCorrectNewName() + "'," +
                    "correct_new_address='" + dao.getCorrectNewAddress() + "',correct_new_tel='" + dao.getCorrectNewTel() + "'" +
                    " where correct_id ='" + dao.getCorrectId() + "'";

            return jdbcTemplate.update(sql);
        }
    }


    /**
     * 删除一个或多个居民信息修正订单
     * @param ids
     * @return
     */
    public int delInhabitantCorrectIds(List<String> ids){
        if (precompile) {
            String sql = "delete from b_info_correct_inhabitant where correct_id  =? ";

             jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
                 public int getBatchSize() {
                     return ids.size();
                 }
                 public void setValues(PreparedStatement ps, int i)
                         throws SQLException {
                     ps.setString(1,ids.get(i));
                 }
             });
             return ids.size();
        }else {
            String sql = "delete from b_info_correct_inhabitant where correct_id in('" + Utils.joinStrings(ids, "','") + "')";

            return jdbcTemplate.update(sql);
        }
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


    class DefaultHouseholdRowMapper implements RowMapper<HouseholdInfosDTO> {
        @Override
        public HouseholdInfosDTO mapRow(ResultSet rs, int i) throws SQLException {
            return new HouseholdInfosDTO(
                    rs.getString("household_householder"),
                    rs.getString("household_number"),
                    rs.getString("household_address")
            );
        }
    }



}
