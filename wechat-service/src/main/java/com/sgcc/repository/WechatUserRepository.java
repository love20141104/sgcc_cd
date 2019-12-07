package com.sgcc.repository;

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
