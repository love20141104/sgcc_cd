package com.sgcc.inhabitant.Repository;

import com.example.Utils;
import com.sgcc.inhabitant.dao.InhabitantNewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InhabitantNewRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save( InhabitantNewDao dao )
    {
        String sql = "insert into b_new_install_inhabitant ( id,user_open_id,new_install_district,new_install_address," +
                "new_install_capacity,propertyRight_img1,propertyRight_img2," +
                "propertyRight_img3,propertyRight_img4,propertyRight_img5,propertyRight_img6," +
                "cq_idcard_positive_img,cq_idcard_back_img,new_install_name,new_install_idcard,new_install_telphone," +
                "new_install_apply_person,new_install_transactor,new_install_transactor_idcard,sq_arttorney_img,sq_idcard_positive_img," +
                "sq_idcard_back_img,new_install_transactor_tel,submit_date) values('" +
                dao.getId()+"','" +
                dao.getUser_open_id() + "','" +
                dao.getNew_install_district()+ "','" +
                dao.getNew_install_address()+ "','" +
                dao.getNew_install_capacity() + "','" +
                dao.getPropertyRight_img1() + "','" +
                dao.getPropertyRight_img2() + "','" +
                dao.getPropertyRight_img3() + "','" +
                dao.getPropertyRight_img4() + "','" +
                dao.getPropertyRight_img5() + "','" +
                dao.getPropertyRight_img6() + "','" +
                dao.getCq_idcard_positive_img()+ "','" +
                dao.getCq_idcard_back_img() + "','" +
                dao.getNew_install_name() + "','" +
                dao.getNew_install_idcard() + "','" +
                dao.getNew_install_telphone() + "','" +
                dao.getNew_install_apply_person() + "','" +
                dao.getNew_install_transactor()+ "','" +
                dao.getNew_install_transactor_idcard() + "','"+
                dao.getSq_arttorney_img()+ "','"+
                dao.getSq_idcard_positive_img() + "','" +
                dao.getSq_idcard_back_img()+ "','" +
                dao.getNew_install_transactor_tel()+ "','" +
                Utils.GetTime(dao.getSubmit_date()) +"')";
        jdbcTemplate.execute(sql);
    }

    public void update( InhabitantNewDao dao )
    {
        String sql = "update b_new_install_inhabitant set id ='"+ dao.getId()+"',"+
                "user_open_id = '" + dao.getUser_open_id() +"',"+
                "new_install_district= '" + dao.getNew_install_district() +"',"+
                "new_install_address= '" + dao.getNew_install_address() +"',"+
                "new_install_capacity= '" + dao.getNew_install_capacity() +"',"+
                "propertyRight_img1= '" + dao.getPropertyRight_img1() +"',"+
                "propertyRight_img2= '" + dao.getPropertyRight_img2() +"',"+
                "propertyRight_img3= '" + dao.getPropertyRight_img3() +"',"+
                "propertyRight_img4= '" + dao.getPropertyRight_img4() +"',"+
                "propertyRight_img5= '" + dao.getPropertyRight_img5() +"',"+
                "propertyRight_img6= '" + dao.getPropertyRight_img6() +"',"+
                "cq_idcard_positive_img= '" + dao.getCq_idcard_positive_img() +"',"+
                "cq_idcard_back_img= '" + dao.getCq_idcard_back_img() +"',"+
                "new_install_name= '" + dao.getNew_install_name() +"',"+
                "new_install_idcard= '" + dao.getNew_install_idcard() +"',"+
                "new_install_telphone= '" + dao.getNew_install_telphone() +"',"+
                "new_install_apply_person= '" + dao.getNew_install_apply_person() +"',"+
                "new_install_transactor= '" + dao.getNew_install_transactor() +"',"+
                "sq_arttorney_img= '" + dao.getSq_arttorney_img() +"',"+
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
        String sql = "delete from b_new_install_inhabitant where id in('"+ strIds +"')";
        jdbcTemplate.execute(sql);
    }

    public List<InhabitantNewDao> findAll()
    {
        String sql = "select id,user_open_id,new_install_district,new_install_address," +
                "new_install_capacity,propertyRight_img1,propertyRight_img2," +
                "propertyRight_img3,propertyRight_img4,propertyRight_img5,propertyRight_img6," +
                "cq_idcard_positive_img,cq_idcard_back_img,new_install_name,new_install_idcard,new_install_telphone," +
                "new_install_apply_person,new_install_transactor,sq_arttorney_img,sq_idcard_positive_img," +
                "sq_idcard_back_img,new_install_transactor_idcard,new_install_transactor_tel,submit_date from b_new_install_inhabitant";

        try {
            return jdbcTemplate.query(sql, new InhabitantNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }
    }

    public InhabitantNewDao findById(String id )
    {
        String sql = "select id,user_open_id,new_install_district,new_install_address," +
                "new_install_capacity,propertyRight_img1,propertyRight_img2," +
                "propertyRight_img3,propertyRight_img4,propertyRight_img5,propertyRight_img6," +
                "cq_idcard_positive_img,cq_idcard_back_img,new_install_name,new_install_idcard,new_install_telphone," +
                "new_install_apply_person,new_install_transactor,sq_arttorney_img,sq_idcard_positive_img," +
                "sq_idcard_back_img,new_install_transactor_idcard,new_install_transactor_tel,submit_date from b_new_install_inhabitant where id ='" + id + "'";

        try {
            return jdbcTemplate.queryForObject(sql, new InhabitantNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }
    }


    public List<InhabitantNewDao> findByOpenId(String id )
    {
        String sql = "select id,user_open_id,new_install_district,new_install_address," +
                "new_install_capacity,propertyRight_img1,propertyRight_img2," +
                "propertyRight_img3,propertyRight_img4,propertyRight_img5,propertyRight_img6," +
                "cq_idcard_positive_img,cq_idcard_back_img,new_install_name,new_install_idcard,new_install_telphone," +
                "new_install_apply_person,new_install_transactor,sq_arttorney_img,sq_idcard_positive_img," +
                "sq_idcard_back_img,new_install_transactor_idcard,new_install_transactor_tel,submit_date " +
                "from b_new_install_inhabitant where user_open_id ='" + id + "'";

        try {
            return jdbcTemplate.query(sql, new InhabitantNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }
    }


    class InhabitantNewRowMapper implements RowMapper<InhabitantNewDao> {
        @Override
        public InhabitantNewDao mapRow(ResultSet rs, int i) throws SQLException {
            InhabitantNewDao dao = new InhabitantNewDao(
                    rs.getString("id"),
                    rs.getString("user_open_id"),
                    rs.getString("new_install_district"),
                    rs.getString("new_install_address"),
                    rs.getString("new_install_capacity"),
                    rs.getString("propertyRight_img1"),
                    rs.getString("propertyRight_img2"),
                    rs.getString("propertyRight_img3"),
                    rs.getString("propertyRight_img4"),
                    rs.getString("propertyRight_img5"),
                    rs.getString("propertyRight_img6"),
                    rs.getString("cq_idcard_positive_img"),
                    rs.getString("cq_idcard_back_img"),
                    rs.getString("new_install_name"),
                    rs.getString("new_install_idcard"),
                    rs.getString("new_install_telphone"),
                    rs.getString("new_install_apply_person"),
                    rs.getString("new_install_transactor"),
                    rs.getString("sq_arttorney_img"),
                    rs.getString("sq_idcard_positive_img"),
                    rs.getString("sq_idcard_back_img"),
                    rs.getString("new_install_transactor_idcard"),
                    rs.getString("new_install_transactor_tel"),
                    null
            );
            if( rs.getDate("submit_date") != null )
            {
                dao.setSubmit_date( Utils.GetDate( rs.getString("submit_date")) );
            }

            return dao;
        }
    }
}
