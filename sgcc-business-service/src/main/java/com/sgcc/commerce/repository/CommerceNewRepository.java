package com.sgcc.commerce.repository;

import com.example.Utils;
import com.sgcc.commerce.dao.CommerceNewDao;
import com.sgcc.commerce.dao.CommerceRenameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CommerceNewRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save( CommerceNewDao dao )
    {
        String sql = "insert into b_new_install_commerce ( id,user_open_id,new_install_company_name,new_install_district,new_install_address," +
        "new_install_capacity,new_install_license_img,propertyRight_img1,propertyRight_img2," +
                "propertyRight_img3,propertyRight_img4,propertyRight_img5,propertyRight_img6,new_install_name," +
                "cq_idcard_positive_img,cq_idcard_back_img,new_install_idcard,new_install_telphone,new_install_invoice," +
                "invoice_company,invoice_number,invoice_bank,invoice_bank_account,invoice_regist_addr,invoice_phone," +
                "invoice_date,invoice_img,new_install_apply_person,new_install_transactor,new_install_transactor_idcard,sq_idcard_positive_img," +
                "sq_idcard_back_img,new_install_transactor_tel,submit_date) values('" +
                dao.getId()+"','" + dao.getUser_open_id() + "','" + dao.getNew_install_company_name() + "','" +
                dao.getNew_install_district()+ "','" + dao.getNew_install_address() + "','" + dao.getNew_install_capacity() + "','" + dao.getNew_install_license_img() + "','"+
                dao.getPropertyRight_img1() + "','" + dao.getPropertyRight_img2() + "','" + dao.getPropertyRight_img3() + "','" +
                dao.getPropertyRight_img4() + "','" + dao.getPropertyRight_img5() + "','" + dao.getPropertyRight_img6() + "','" +
                dao.getNew_install_name() + "','" + dao.getCq_idcard_positive_img()+ "','" + dao.getCq_idcard_back_img() + "','" +
                dao.getNew_install_idcard() + "','" + dao.getNew_install_telphone() + "','" + Utils.Boolean2Int(dao.isNew_install_invoice()) + "','" +
                dao.getInvoice_company()+ "','" +dao.getInvoice_number() + "','" + dao.getInvoice_bank() + "','" + dao.getInvoice_bank_account()
                + "','" + dao.getInvoice_regist_addr() + "','" + dao.getInvoice_phone() + "','" + Utils.GetTime(dao.getInvoice_date()) + "','" +
                dao.getInvoice_img() + "','" + dao.getNew_install_apply_person() + "','" + dao.getNew_install_transactor()+ "','" +
                dao.getNew_install_transactor_idcard() + "','" + dao.getSq_idcard_positive_img() + "','" + dao.getSq_idcard_back_img()+ "','" +
                dao.getNew_install_transactor_tel()+ "','" + Utils.GetTime(dao.getSubmit_date()) +"')";
        jdbcTemplate.execute(sql);
    }

    public void update( CommerceNewDao dao )
    {
        String sql = "update b_new_install_commerce set id ='"+ dao.getId()+"',"+
                "user_open_id = '" + dao.getUser_open_id() +"',"+
                "new_install_company_name= '" + dao.getNew_install_company_name() +"',"+
                "new_install_district= '" + dao.getNew_install_district() +"',"+
                "new_install_address= '" + dao.getNew_install_address() +"',"+
                "new_install_capacity= '" + dao.getNew_install_capacity() +"',"+
                "new_install_license_img= '" + dao.getNew_install_license_img() +"',"+
                "propertyRight_img1= '" + dao.getPropertyRight_img1() +"',"+
                "propertyRight_img2= '" + dao.getPropertyRight_img2() +"',"+
                "propertyRight_img3= '" + dao.getPropertyRight_img3() +"',"+
                "propertyRight_img4= '" + dao.getPropertyRight_img4() +"',"+
                "propertyRight_img5= '" + dao.getPropertyRight_img5() +"',"+
                "propertyRight_img6= '" + dao.getPropertyRight_img6() +"',"+
                "new_install_name= '" + dao.getNew_install_name() +"',"+
                "cq_idcard_positive_img= '" + dao.getCq_idcard_positive_img() +"',"+
                "cq_idcard_back_img= '" + dao.getCq_idcard_back_img() +"',"+
                "new_install_idcard= '" + dao.getNew_install_idcard() +"',"+
                "new_install_telphone= '" + dao.getNew_install_telphone() +"',"+
                "new_install_invoice= '" + Utils.Boolean2Int(dao.isNew_install_invoice()) +"',"+
                "invoice_company= '" + dao.getInvoice_company() +"',"+
                "invoice_number= '" + dao.getInvoice_number() +"',"+
                "invoice_bank= '" + dao.getInvoice_bank() +"',"+
                "invoice_bank_account= '" + dao.getInvoice_bank_account() +"',"+
                "invoice_regist_addr= '" + dao.getInvoice_regist_addr() +"',"+
                "invoice_phone= '" + dao.getInvoice_phone() +"',"+
                "invoice_date= '" + Utils.GetTime(dao.getInvoice_date()) +"',"+
                "invoice_img= '" + dao.getInvoice_img() +"',"+
                "new_install_apply_person= '" + dao.getNew_install_apply_person() +"',"+
                "new_install_transactor= '" + dao.getNew_install_transactor() +"',"+
                "sq_idcard_positive_img= '" + dao.getSq_idcard_positive_img() +"',"+
                "sq_idcard_back_img= '" + dao.getSq_idcard_back_img() +"',"+
                "new_install_transactor_idcard= '" + dao.getNew_install_transactor_idcard() +"',"+
                "new_install_transactor_tel= '" + dao.getNew_install_transactor_tel() +"',"+
                "submit_date= '" + Utils.GetTime(dao.getSubmit_date()) +"' where id='" + dao.getId() + "'";
        jdbcTemplate.execute(sql);
    }

    public void deletes( List<String> ids )
    {
        String strIds = Utils.joinStrings(ids,"','");
        String sql = "delete from b_new_install_commerce where id in('"+ strIds +"')";
        jdbcTemplate.execute(sql);
    }

    public List<CommerceNewDao> findAll()
    {
        String sql = "select id,user_open_id,new_install_company_name,new_install_district,new_install_address," +
                "new_install_capacity,new_install_license_img,propertyRight_img1,propertyRight_img2," +
                "propertyRight_img3,propertyRight_img4,propertyRight_img5,propertyRight_img6,new_install_name," +
                "cq_idcard_positive_img,cq_idcard_back_img,new_install_idcard,new_install_telphone,new_install_invoice," +
                "invoice_company,invoice_number,invoice_bank,invoice_bank_account,invoice_regist_addr,invoice_phone," +
                "invoice_date,invoice_img,new_install_apply_person,new_install_transactor,sq_idcard_positive_img," +
                "sq_idcard_back_img,new_install_transactor_idcard,new_install_transactor_tel,submit_date from b_new_install_commerce";

        try {
            return jdbcTemplate.query(sql, new CommerceNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }
    }

    public CommerceNewDao findById(String id )
    {
        String sql = "select id,user_open_id,new_install_company_name,new_install_district,new_install_address," +
                "new_install_capacity,new_install_license_img,propertyRight_img1,propertyRight_img2," +
                "propertyRight_img3,propertyRight_img4,propertyRight_img5,propertyRight_img6,new_install_name," +
                "cq_idcard_positive_img,cq_idcard_back_img,new_install_idcard,new_install_telphone,new_install_invoice," +
                "invoice_company,invoice_number,invoice_bank,invoice_bank_account,invoice_regist_addr,invoice_phone," +
                "invoice_date,invoice_img,new_install_apply_person,new_install_transactor,sq_idcard_positive_img," +
                "sq_idcard_back_img,new_install_transactor_idcard,new_install_transactor_tel,submit_date from b_new_install_commerce where id ='" + id + "'";

        try {
            return jdbcTemplate.queryForObject(sql, new CommerceNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }
    }


    public List<CommerceNewDao> findByOpenId(String id )
    {
        String sql = "select id,user_open_id,new_install_company_name,new_install_district,new_install_address," +
                "new_install_capacity,new_install_license_img,propertyRight_img1,propertyRight_img2," +
                "propertyRight_img3,propertyRight_img4,propertyRight_img5,propertyRight_img6,new_install_name," +
                "cq_idcard_positive_img,cq_idcard_back_img,new_install_idcard,new_install_telphone,new_install_invoice," +
                "invoice_company,invoice_number,invoice_bank,invoice_bank_account,invoice_regist_addr,invoice_phone," +
                "invoice_date,invoice_img,new_install_apply_person,new_install_transactor,sq_idcard_positive_img," +
                "sq_idcard_back_img,new_install_transactor_idcard,new_install_transactor_tel,submit_date from b_new_install_commerce where user_open_id ='" + id + "'";

        try {
            return jdbcTemplate.query(sql, new CommerceNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }
    }



    public CommerceNewDao findByUserOpenId(String user_open_id )
    {
        String sql = "select id,user_open_id,new_install_company_name,new_install_district,new_install_address," +
                "new_install_capacity,new_install_license_img,propertyRight_img1,propertyRight_img2," +
                "propertyRight_img3,propertyRight_img4,propertyRight_img5,propertyRight_img6,new_install_name," +
                "cq_idcard_positive_img,cq_idcard_back_img,new_install_idcard,new_install_telphone,new_install_invoice," +
                "invoice_company,invoice_number,invoice_bank,invoice_bank_account,invoice_regist_addr,invoice_phone," +
                "invoice_date,invoice_img,new_install_apply_person,new_install_transactor,sq_idcard_positive_img," +
                "sq_idcard_back_img,new_install_transactor_idcard,new_install_transactor_tel,submit_date from b_new_install_commerce where user_open_id ='" + user_open_id + "'";

        try {
            return jdbcTemplate.queryForObject(sql, new CommerceNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }
    }

    class CommerceNewRowMapper implements RowMapper<CommerceNewDao> {
        @Override
        public CommerceNewDao mapRow(ResultSet rs, int i) throws SQLException {
            CommerceNewDao dao = new CommerceNewDao(
                    rs.getString("id"),
                    rs.getString("user_open_id"),
                    rs.getString("new_install_company_name"),
                    rs.getString("new_install_district"),
                    rs.getString("new_install_address"),
                    rs.getString("new_install_capacity"),
                    rs.getString("new_install_license_img"),
                    rs.getString("propertyRight_img1"),
                    rs.getString("propertyRight_img2"),
                    rs.getString("propertyRight_img3"),
                    rs.getString("propertyRight_img4"),
                    rs.getString("propertyRight_img5"),
                    rs.getString("propertyRight_img6"),
                    rs.getString("new_install_name"),
                    rs.getString("cq_idcard_positive_img"),
                    rs.getString("cq_idcard_back_img"),
                    rs.getString("new_install_idcard"),
                    rs.getString("new_install_telphone"),
                    Utils.Int2Boolean(rs.getInt("new_install_invoice")),
                    rs.getString("invoice_company"),
                    rs.getString("invoice_number"),
                    rs.getString("invoice_bank"),
                    rs.getString("invoice_bank_account"),
                    rs.getString("invoice_regist_addr"),
                    rs.getString("invoice_phone"),
                    null,//rs.getDate("invoice_date"),
                    rs.getString("invoice_img"),
                    rs.getString("new_install_apply_person"),
                    rs.getString("new_install_transactor"),
                    rs.getString("sq_idcard_positive_img"),
                    rs.getString("sq_idcard_back_img"),
                    rs.getString("new_install_transactor_idcard"),
                    rs.getString("new_install_transactor_tel"),
                    null
            );
            if( rs.getDate("invoice_date") != null )
            {
                dao.setInvoice_date( Utils.GetDate( rs.getString("invoice_date")) );
            }
            if( rs.getDate("submit_date") != null )
            {
                dao.setSubmit_date( Utils.GetDate( rs.getString("submit_date")) );
            }

            return dao;
        }
    }
}
