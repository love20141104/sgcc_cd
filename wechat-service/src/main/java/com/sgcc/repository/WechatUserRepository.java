package com.sgcc.repository;

import com.example.CurrentPage;
import com.example.PaginationHelper;
import com.sgcc.dao.UserDao;
import com.sgcc.dto.UserInfoViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WechatUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<UserDao> findUsers(){
        String sql = "select id,user_open_id,nick_name,sex,city,head_img_url from t_wechat_users";
        return jdbcTemplate.query(sql,new WechatUsersRowMapper());

    }


    public CurrentPage<UserDao> findPageList(int getPageNo, int getPageSize) {
        PaginationHelper<UserDao> ph = new PaginationHelper<>();
        // 持久化
        String sql = "select id,user_open_id,nick_name,sex,city,head_img_url from t_wechat_users";
        String countSql = "SELECT Count(id) FROM t_wechat_users;";
        try {
            return ph.fetchPage(jdbcTemplate, countSql, sql, new Object[]{}, getPageNo,getPageSize, new WechatUsersRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CurrentPage<UserDao> findUsersByNickName(String nickName,int getPageNo, int getPageSize) {
        PaginationHelper<UserDao> ph = new PaginationHelper<>();
        // 持久化
        String sql = "select id,user_open_id,nick_name,sex,city,head_img_url from t_wechat_users " +
                " where nick_name like '%"+nickName+"%'";
        String countSql = "SELECT Count(id) FROM t_wechat_users" + " where nick_name like '%"+nickName+"%'";
        try {
            return ph.fetchPage(jdbcTemplate, countSql, sql, new Object[]{}, getPageNo,getPageSize, new WechatUsersRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<UserDao> findUsersByFullNickName(String fullNickName) {
        String sql = "select id,user_open_id,nick_name,sex,city,head_img_url from t_wechat_users " +
                "where nick_name = '"+fullNickName+"'";
        return jdbcTemplate.query(sql,new WechatUsersRowMapper());
    }


    class WechatUsersRowMapper implements RowMapper<UserDao> {

        @Override
        public UserDao mapRow(ResultSet rs, int i) throws SQLException {
            return new UserDao(
                rs.getString("id"),
                    rs.getString("user_open_id"),
                    rs.getString("nick_name"),
                    rs.getInt("sex"),
                    rs.getString("city"),
                    rs.getString("head_img_url")

            );
        }
    }

    @Transactional
    public void saveUsers(List<UserDao> userDaos){
        String sql = "insert into t_wechat_users(id,user_open_id,nick_name,sex,city,head_img_url) " +
                "values (?,?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,userDaos.get(i).getId());
                ps.setString(2, userDaos.get(i).getOpenid());
                ps.setString(3, userDaos.get(i).getNickname());
                ps.setInt(4, userDaos.get(i).getSex());
                ps.setString(5, userDaos.get(i).getCity());
                ps.setString(6, userDaos.get(i).getHeadimgurl());
            }

            @Override
            public int getBatchSize() {
                return userDaos.size();
            }
        });

    }




}
