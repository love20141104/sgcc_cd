package com.sgcc.repository;

import com.sgcc.dao.ReplierAndCheckerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReplierAndCheckerRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ReplierAndCheckerDao> findAllByRegion(String region ){
        String sql = "select id,major_region,replier_openid,replier_name,checker_openid,checker_name from d_customer_service_staff " +
                " where major_region like ?";
        return jdbcTemplate.query(sql,new Object[]{"%"+region+"%"},new ReplierAndCheckerRowMapper());
    }

    public List<ReplierAndCheckerDao> findAll(   ){
        String sql = "select id,major_region,replier_openid,replier_name,checker_openid,checker_name from d_customer_service_staff";
        return jdbcTemplate.query(sql,new Object[]{},new ReplierAndCheckerRowMapper());
    }

    @Transactional
    public void save( ReplierAndCheckerDao dao  ){
        String sql = "INSERT INTO d_customer_service_staff(id,major_region,replier_openid,replier_name,checker_openid,checker_name) " +
                "values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{ dao.getId(),dao.getMajor_region(),
        dao.getReplier_openid(),dao.getReplier_name(),dao.getChecker_openid(),dao.getChecker_name()});
    }

    @Transactional
    public void update( ReplierAndCheckerDao dao  ){
        String sql = "UPDATE d_customer_service_staff SET major_region = ?,replier_openid = ?,replier_name = ?,checker_openid = ?," +
                "checker_name = ? where id = ?";
        jdbcTemplate.update(sql,new Object[]{ dao.getMajor_region(),
                dao.getReplier_openid(),dao.getReplier_name(),dao.getChecker_openid(),dao.getChecker_name(),dao.getId()});
    }

    public Boolean replierByOpenId( String openId ){
        String sql = "select count(replier_openid) from d_customer_service_staff where replier_openid = ? ";
        Integer integer = jdbcTemplate.queryForObject(sql,new Object[]{openId }, Integer.class);
        if(integer>0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkerByOpenId( String openId ){
        String sql = "select count(checker_openid) from d_customer_service_staff where checker_openid = ? ";
        Integer integer = jdbcTemplate.queryForObject(sql,new Object[]{ openId}, Integer.class);
        if(integer>0){
            return true;
        }else {
            return false;
        }
    }

    public String getReplyOpenId(String userLocation) {
        String sql = "select replier_openid from d_customer_service_staff where major_region like ? ";
        String s = jdbcTemplate.queryForObject(sql, new Object[]{"%" + userLocation + "%"}, String.class);
        return s;
    }

    public Integer getCountByOpenId(String openId, Integer role) {
        if(2==role){
            String sql = "select count(id) from (" +
                    "select s.id,s.suggestion_content,s.suggestion_contact,s.suggestion_tel" +
                    ",s.submit_date,s.img_1,s.img_2,s.img_3,s.suggestion_id,r.reply_content" +
                    ",r.reply_openid,r.reply_date,r.check_openid,r.check_state,r.check_reject,r.check_date " +
                    "from b_suggestion s LEFT JOIN b_suggestion_reply r on s.id=r.suggestion_id   " +
                    " where  s.user_location in(select major_region from d_customer_service_staff " +
                    " where replier_openid = ? )) a where a.check_state = 0 or a.check_state is null";
            Integer s = jdbcTemplate.queryForObject(sql, new Object[]{openId}, Integer.class);
            return s;
        }
        if(3==role){
            String sql = "select count(s.id) from b_suggestion_reply sr left join b_suggestion s on sr.suggestion_id=s.id " +
                    "  where check_state is null and sr.reply_content is not null  and  sr.reply_openid in( " +
                    " select distinct(replier_openid) from d_customer_service_staff where checker_openid =? )  ";
            Integer s = jdbcTemplate.queryForObject(sql, new Object[]{openId}, Integer.class);
            return s;
        }else {
            return null;
        }

    }

    public ReplierAndCheckerDao getReplyOpenIdByCheckOpenId(String check_openid) {
        String sql = "select id,major_region,replier_openid,replier_name,checker_openid,checker_name " +
                "from d_customer_service_staff where checker_openid = ? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{check_openid}, new ReplierAndCheckerRowMapper());

    }

    public ReplierAndCheckerDao getReplyOpenIdByReplyOpenId(String reply_openid) {
        String sql = "select  id,major_region,replier_openid,replier_name,checker_openid,checker_name " +
                "from d_customer_service_staff where replier_openid = ? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{reply_openid}, new ReplierAndCheckerRowMapper());
    }


    class ReplierAndCheckerRowMapper implements RowMapper<ReplierAndCheckerDao> {
        @Override
        public ReplierAndCheckerDao mapRow(ResultSet rs, int i) throws SQLException {
            return new ReplierAndCheckerDao(
                    rs.getString("id"),
                    rs.getString("major_region"),
                    rs.getString("replier_openid"),
                    rs.getString("replier_name"),
                    rs.getString("checker_openid"),
                    rs.getString("checker_name"));
        }
    }
}
