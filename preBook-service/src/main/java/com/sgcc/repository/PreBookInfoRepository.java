package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.BlacklistDao;
import com.sgcc.dao.CheckerInfoDao;
import com.sgcc.dao.PrebookInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PreBookInfoRepository {

    private Logger logger = Logger.getLogger(PreBookInfoRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;








    public int updateLineUp(PrebookInfoDao dao){
        String sql = "update b_prebook_detail set lineup_no=?,lineup_time=? where id=?";

        return jdbcTemplate.update(sql,new Object[]{
                dao.getLineupNo(),
                dao.getLineupTime(),
                dao.getId()
        });
    }


    public PrebookInfoDao updateCheckPrebook(PrebookInfoDao dao){

        String sql = "update b_prebook_detail set status=?,reject_reason=?,checker_id=? where id=?";

        jdbcTemplate.update(sql,new Object[]{
                dao.getStatus(),
                dao.getRejectReason(),
                dao.getCheckerId(),
                dao.getId()
        });

        String sql2 = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id from b_prebook_detail " +
                "where id = ? ";

        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql2,new Object[]{dao.getId()},new PreBookRowMapper());

        return prebookInfoDaos.get(0);
    }



    public PrebookInfoDao addPrebook(PrebookInfoDao dao){

        String sql = "insert into b_prebook_detail(id,user_open_id,business_type_id,business_type_name,service_hall_id," +
                "service_hall_name,household_no,contact,contact_tel,submit_date,status) " +
                "values (?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{
                dao.getId(),
                dao.getUserOpenId(),
                dao.getBusinessTypeId(),
                dao.getBusinessTypeName(),
                dao.getServiceHallId(),
                dao.getServiceHallName(),
                dao.getHouseholdNo(),
                dao.getContact(),
                dao.getContactTel(),
                Utils.GetTime(dao.getSubmitDate()),
                dao.getStatus()
        });

        String sql2 = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id from b_prebook_detail " +
                "where id = ? ";

        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql2,new Object[]{dao.getId()},new PreBookRowMapper());

        return prebookInfoDaos.get(0);
    }


    public List<PrebookInfoDao> getPrebookInfo(String openId,int status){
        String sql = "";
        if (status == 1){
            sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                    "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id from b_prebook_detail " +
                    "where user_open_id = ? and status=1 order by submit_date desc ";
        }else if (status == 2){
            sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                    "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id from b_prebook_detail " +
                    "where user_open_id = ? and status=2 order by submit_date desc ";
        }else if (status == 3){
            sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                    "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id from b_prebook_detail " +
                    "where user_open_id = ? and status=3 order by submit_date desc ";
        }else {
            return null;
        }


        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql,new Object[]{openId},new PreBookRowMapper());

        return prebookInfoDaos;
    }


    public List<PrebookInfoDao> getCheckList(String hallName,int status){
        String sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                    "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id from b_prebook_detail " +
                    "where service_hall_name = ? and status=? order by submit_date desc ";

        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql,new Object[]{hallName,status},new PreBookRowMapper());

        return prebookInfoDaos;
    }

    public CheckerInfoDao getCheckerByOpenId(String openId){
        String sql = "select id,checker_name,checker_tel,user_open_id,service_hall_id,service_hall_name " +
                "from b_prebook_checker where user_open_id = ?";
        return jdbcTemplate.query(sql,new Object[]{openId},new CheckerInfoRowMapper()).get(0);
    }




    public PrebookInfoDao getCheckDetailList(String id){
        String sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id from b_prebook_detail " +
                "where id = ? order by submit_date desc ";

        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql,new Object[]{id},new PreBookRowMapper());

        return prebookInfoDaos.get(0);
    }



    public PrebookInfoDao getPrebookInfoDetail(String id){
        String sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                    "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id from b_prebook_detail " +
                    "where id = ?";
        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql,new Object[]{id},new PreBookRowMapper());

        return prebookInfoDaos.get(0);
    }


    public int addChecker(CheckerInfoDao dao){
        String sql = "insert into b_prebook_checker(id,checker_name,checker_tel,user_open_id,service_hall_id,service_hall_name) " +
                "values(?,?,?,?,?,?)";

        return jdbcTemplate.update(sql,new Object[]{
                dao.getId(),
                dao.getCheckerName(),
                dao.getCheckTel(),
                dao.getUserOpenId(),
                dao.getServiceHallId(),
                dao.getServiceHallName()
        });
    }

    public int updateChecker(CheckerInfoDao dao){
        String sql = "update b_prebook_checker set checker_name=?,checker_tel=?,user_open_id=? " +
                "where id=?";

        return jdbcTemplate.update(sql,new Object[]{
                dao.getCheckerName(),
                dao.getCheckTel(),
                dao.getUserOpenId(),
                dao.getId()
        });
    }

    public void delChecker(List<String> ids){
        String sql = "delete from b_prebook_checker where id in ('" + Utils.joinStrings(ids, "','")+"')";
        jdbcTemplate.update(sql);
    }


    public CheckerInfoDao getCheckerInfo(String hallId){
        String sql = "select id,checker_name,checker_tel,user_open_id,service_hall_id,service_hall_name " +
                "from b_prebook_checker where service_hall_id = ?";
        return jdbcTemplate.query(sql,new Object[]{hallId},new CheckerInfoRowMapper()).get(0);
    }

    public CheckerInfoDao getCheckerInfoById(String id){
        String sql = "select id,checker_name,checker_tel,user_open_id,service_hall_id,service_hall_name " +
                "from b_prebook_checker where id = ?";
        return jdbcTemplate.query(sql,new Object[]{id},new CheckerInfoRowMapper()).get(0);
    }


    public int getBlacklistByOpenId(String openId){

        String sql = "select id,user_open_id,create_date from b_prebook_blacklist where user_open_id = ?";
        List<BlacklistDao> blacklistDaos = jdbcTemplate.query(sql,new Object[]{openId},new BlacklistRowMapper());
        return blacklistDaos.size();
    }


    class CheckerInfoRowMapper implements RowMapper<CheckerInfoDao>{

        @Override
        public CheckerInfoDao mapRow(ResultSet rs, int i) throws SQLException {
            return new CheckerInfoDao(
                    rs.getString("id"),
                    rs.getString("checker_name"),
                    rs.getString("checker_tel"),
                    rs.getString("user_open_id"),
                    rs.getString("service_hall_id"),
                    rs.getString("service_hall_name")
            );
        }
    }



    class PreBookRowMapper implements RowMapper<PrebookInfoDao>{

        @Override
        public PrebookInfoDao mapRow(ResultSet rs, int i) throws SQLException {
            return new PrebookInfoDao(
                    rs.getString("id"),
                    rs.getString("user_open_id"),
                    rs.getString("business_type_id"),
                    rs.getString("business_type_name"),
                    rs.getString("service_hall_id"),
                    rs.getString("service_hall_name"),
                    rs.getString("household_no"),
                    rs.getString("lineup_no"),
                    !Strings.isNullOrEmpty(rs.getString("lineup_time")) ?Utils.GetDate(rs.getString("lineup_time")):null,
                    rs.getString("contact"),
                    rs.getString("contact_tel"),
                    Utils.GetDate(rs.getString("submit_date")),
                    rs.getInt("status"),
                    rs.getString("reject_reason"),
                    rs.getString("checker_id")
            );
        }
    }



    class BlacklistRowMapper implements RowMapper<BlacklistDao>{

        @Override
        public BlacklistDao mapRow(ResultSet rs, int i) throws SQLException {
            return new BlacklistDao(
                    rs.getString("id"),
                    rs.getString("user_open_id"),
                    Utils.GetDate(rs.getString("submit_time"))
            );
        }
    }


}
