package com.sgcc.commerce.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.commerce.dao.CommerceIncreaseCapacityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CommerceIncreaseCapacityRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 新增增容订单
     * @param dao
     * @return
     */
    public int addIncreaseCapacityOrder(CommerceIncreaseCapacityDao dao){

        String sql = "insert into b_increase_capacity_commerce(id,user_open_id,in_company_name," +
                "in_current_capacity,in_name,in_idcard,in_telphone,cq_idcard_positive_img,cq_idcard_back_img," +
                "in_license_img,propertyRight_img1,propertyRight_img2,propertyRight_img3,propertyRight_img4," +
                "propertyRight_img5,propertyRight_img6,in_apply_person,in_transactor,in_transactor_idcard," +
                "sq_idcard_positive_img,sq_idcard_back_img,in_invoice,invoice_company,invoice_number,invoice_bank," +
                "invoice_bank_account,invoice_regist_addr,invoice_phone,invoice_date,invoice_img,in_submit_date) values(" +
                "'"+dao.getId()+"','"+dao.getOpenId()+"','"+dao.getCompanyName()+"'," +
                ""+dao.getCurrentCapacity()+",'"+dao.getName()+"','"+dao.getIdcard()+"','"+dao.getContactTel()+"'," +
                "'"+dao.getCqIdcardPositiveImg()+"','"+dao.getCqIdcardBackImg()+"','"+dao.getLicenseImg()+"'," +
                "'"+dao.getSecuritiesImg1()+"','"+dao.getSecuritiesImg2()+"','"+dao.getSecuritiesImg3()+"'," +
                "'"+dao.getSecuritiesImg4()+"','"+dao.getSecuritiesImg5()+"','"+dao.getSecuritiesImg6()+"'," +
                "'"+dao.getAplicant()+"','"+dao.getTransactor()+"','"+dao.getTransactorIdcard()+"'," +
                "'"+dao.getSqIdcardPositiveImg()+"','"+dao.getSqIdcardBackImg()+"',"+dao.getInvoiceFlag()+"," +
                "'"+dao.getCompanyName()+"','"+dao.getInvoiceNum()+"','"+dao.getInvoiceBank()+"'," +
                "'"+dao.getInvoiceBankAccount()+"','"+dao.getInvoiceRegistAddr()+"','"+dao.getInvoiceContactTel()+"'," +
                "'"+dao.getInvoiceDate()+"','"+dao.getInvoiceImg()+"','"+Utils.GetTime(dao.getSubmitDate())+"')";
        return jdbcTemplate.update(sql);

    }

    /**
     *根据订单号查询增容详情
     * @param openId
     * @return
     */
    public List<CommerceIncreaseCapacityDao> findIncreaseCapacityOrderList(String openId){

        String sql = "select id,user_open_id,in_company_name,in_current_capacity,in_name,in_idcard,in_telphone," +
                "cq_idcard_positive_img,cq_idcard_back_img,in_license_img,propertyRight_img1,propertyRight_img2," +
                "propertyRight_img3,propertyRight_img4,propertyRight_img5,propertyRight_img6,in_apply_person," +
                "in_transactor,in_transactor_idcard,sq_idcard_positive_img,sq_idcard_back_img,in_invoice," +
                "invoice_company,invoice_number,invoice_bank,invoice_bank_account,invoice_regist_addr,invoice_phone," +
                "invoice_date,invoice_img,in_submit_date from b_increase_capacity_commerce " +
                "where user_open_id='"+openId+"'";
        return jdbcTemplate.query(sql,new IncreaseCapacityRowMapper());

    }

    /**
     *查询所有增容详情
     * @return
     */
    public List<CommerceIncreaseCapacityDao> findIncreaseCapacityAll(){

        String sql = "select id,user_open_id,in_company_name," +
                "in_current_capacity,in_name,in_idcard,in_telphone,cq_idcard_positive_img,cq_idcard_back_img," +
                "in_license_img,propertyRight_img1,propertyRight_img2,propertyRight_img3,propertyRight_img4," +
                "propertyRight_img5,propertyRight_img6,in_apply_person,in_transactor,in_transactor_idcard," +
                "sq_idcard_positive_img,sq_idcard_back_img,invo,invoice_company,invoice_number,invoice_bank," +
                "invoice_bank_account,invoice_regist_addr,invoice_phone,invoice_date,invoice_img,in_submit_date from " +
                "b_increase_capacity_commerce";
        return jdbcTemplate.query(sql,new IncreaseCapacityRowMapper());

    }



    /**
     * 修改增容详情
     * @param dao
     * @return
     */
    public int updateIncreaseCapacity(CommerceIncreaseCapacityDao dao){

        String sql = "update b_increase_capacity_commerce set user_open_id='"+dao.getOpenId()+"'," +
                "in_company_name='"+dao.getCompanyName()+"'," +
                "in_current_capacity="+dao.getCurrentCapacity()+"," +
                "in_name='"+dao.getName()+"'," +
                "in_idcard='"+dao.getIdcard()+"'," +
                "in_telphone='"+dao.getContactTel()+"'," +
                "cq_idcard_positive_img='"+dao.getCqIdcardPositiveImg()+"'," +
                "cq_idcard_back_img='"+dao.getCqIdcardBackImg()+"'," +
                "in_license_img='"+dao.getLicenseImg()+"'," +
                "propertyRight_img1='"+dao.getSecuritiesImg1()+"'," +
                "propertyRight_img2='"+dao.getSecuritiesImg2()+"'," +
                "propertyRight_img3='"+dao.getSecuritiesImg3()+"'," +
                "propertyRight_img4='"+dao.getSecuritiesImg4()+"'," +
                "propertyRight_img5='"+dao.getSecuritiesImg5()+"'," +
                "propertyRight_img6='"+dao.getSecuritiesImg6()+"'," +
                "in_apply_person='"+dao.getAplicant()+"'," +
                "in_transactor='"+dao.getTransactor()+"'," +
                "in_transactor_idcard='"+dao.getTransactorIdcard()+"'," +
                "sq_idcard_positive_img='"+dao.getSqIdcardPositiveImg()+"'," +
                "sq_idcard_back_img='"+dao.getSqIdcardBackImg()+"'," +
                "in_invoice="+dao.getInvoiceFlag()+"," +
                "invoice_company='"+dao.getCompanyName()+"'," +
                "invoice_number='"+dao.getInvoiceNum()+"'," +
                "invoice_bank='"+dao.getInvoiceBank()+"'," +
                "invoice_bank_account='"+dao.getInvoiceBankAccount()+"'," +
                "invoice_regist_addr='"+dao.getInvoiceRegistAddr()+"'," +
                "invoice_phone='"+dao.getInvoiceContactTel()+"'," +
                "invoice_date='"+Utils.GetTime(dao.getInvoiceDate())+"'," +
                "invoice_img='"+dao.getInvoiceImg()+"'," +
                "in_submit_date='"+Utils.GetTime(dao.getSubmitDate())+"' " +
                "where id='"+dao.getId()+"'";

        return jdbcTemplate.update(sql);

    }

    /**
     * 删除增容详情
     * @param ids
     */
    public void delIncreaseCapacity(List<String> ids){

        String sql = "delete from b_increase_capacity_commerce where id in('"+
                Utils.joinStrings(ids,"','") +"') ";
        jdbcTemplate.execute(sql);

    }


    class IncreaseCapacityRowMapper implements RowMapper<CommerceIncreaseCapacityDao>{

        @Override
        public CommerceIncreaseCapacityDao mapRow(ResultSet rs, int i) throws SQLException {
            return new CommerceIncreaseCapacityDao(
                rs.getString("id"),
                rs.getString("user_open_id"),
                rs.getString("in_company_name"),
                rs.getDouble("in_current_capacity"),
                rs.getString("in_name"),
                rs.getString("in_idcard"),
                rs.getString("in_telphone"),
                rs.getString("in_license_img"),
                rs.getString("in_apply_person"),
                rs.getString("in_transactor"),
                rs.getString("in_transactor_idcard"),
                rs.getBoolean("in_invoice"),
                rs.getString("invoice_number"),
                rs.getString("invoice_bank"),
                rs.getString("invoice_bank_account"),
                rs.getString("invoice_regist_addr"),
                rs.getString("invoice_phone"),
                rs.getDate("invoice_date"),
                rs.getString("propertyRight_img1"),
                rs.getString("propertyRight_img2"),
                rs.getString("propertyRight_img3"),
                rs.getString("propertyRight_img4"),
                rs.getString("propertyRight_img5"),
                rs.getString("propertyRight_img6"),
                rs.getString("cq_idcard_positive_img"),
                rs.getString("cq_idcard_back_img"),
                rs.getString("sq_idcard_positive_img"),
                rs.getString("sq_idcard_back_img"),
                rs.getString("invoice_img"),
                rs.getDate("in_submit_date")

            );
        }
    }


}
