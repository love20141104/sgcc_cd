package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.BannerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BannerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<BannerDao> findAll()
    {
        String sql = "select id,banner_img,banner_url from d_banner_setting";

        try {
            return jdbcTemplate.query(sql, new BannerRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }
    }
    public BannerDao findById(String id)
    {
        String sql = "select id,banner_img,banner_url from d_banner_setting where id ='" + id + "'";

        try {
            return jdbcTemplate.queryForObject(sql, new BannerRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }
    }
    public void update(BannerDao dao )
    {
        String sql = "update d_banner_setting set banner_img = '"+ dao.getBanner_img()+ "',banner_url = '" + dao.getBanner_url()
                + "' where id = '" + dao.getId() + "'";
        try {
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }
    }

    public void save(BannerDao dao )
    {
        String sql = "insert into  d_banner_setting (id,banner_img,banner_url) values(" +dao.getId()+"','"+
                dao.getBanner_img() +"','"+ dao.getBanner_url() + "')";
        try {
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }
    }

    public void deletes(List<String> ids){
        String strIds = Utils.joinStrings(ids,"','");
        String sql = "delete from d_banner_setting where id in('"+ strIds +"')";
        jdbcTemplate.execute(sql);
    }

    class BannerRowMapper implements RowMapper<BannerDao> {
        @Override
        public BannerDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BannerDao(
                    rs.getString("id"),
                    rs.getString("banner_img"),
                    rs.getString("banner_url")
            );
        }
    }
}
