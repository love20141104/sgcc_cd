package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.BlacklistDao;
import com.sgcc.dao.CheckerInfoDao;
import com.sgcc.dao.PrebookInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PreBookInfoRepository {

    private Logger logger = Logger.getLogger(PreBookInfoRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int[] updateTicketStatus(List<String> ids){
        String sql = "update b_prebook_detail set ticket_status=1 where id=? and status=2 and is_printed=true ";

        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,ids.get(i));
            }

            @Override
            public int getBatchSize() {
                return ids.size();
            }
        });
    }



    public int[] updatePrintStatus(List<String> ids){
        String sql = "update b_prebook_detail set is_printed=true where id=? and status=2";

        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,ids.get(i));
            }

            @Override
            public int getBatchSize() {
                return ids.size();
            }
        });
    }



    public List<PrebookInfoDao> getAllPrebook(){
        String sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id," +
                "start_date,end_date,ticket_status,prebook_no,is_printed,check_date from b_prebook_detail ";

        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql,new PreBookRowMapper());
        return prebookInfoDaos;
    }


    public List<PrebookInfoDao> getPrebookList(){
        String sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id," +
                "start_date,end_date,ticket_status,prebook_no,is_printed,check_date from b_prebook_detail " +
                "where ticket_status=0 and status = 1";

        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql,new PreBookRowMapper());
        return prebookInfoDaos;
    }

    public List<PrebookInfoDao> getPrebookCount(String startDate,String endDate){
        String sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id," +
                "start_date,end_date,ticket_status,prebook_no,is_printed,check_date from b_prebook_detail " +
                "where start_date = ? and end_date = ? ";

        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql,new Object[]{startDate,endDate},new PreBookRowMapper());
        return prebookInfoDaos;
    }


    public List<PrebookInfoDao> getNotTakeTicketList(){
        String sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id," +
                "start_date,end_date,ticket_status,prebook_no,is_printed,check_date from b_prebook_detail " +
                "where ticket_status = 0 ";

        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql,new PreBookRowMapper());
        return prebookInfoDaos;
    }



    public int updateLineUp(PrebookInfoDao dao){
        String sql = "update b_prebook_detail set lineup_no=?,lineup_time=? where id=?";

        return jdbcTemplate.update(sql,new Object[]{
                dao.getLineupNo(),
                dao.getLineupTime(),
                dao.getId()
        });
    }


    public PrebookInfoDao updateCheckPrebook(PrebookInfoDao dao){

        String sql = "update b_prebook_detail set status=?,reject_reason=?,checker_id=?,check_date=? where id=?";

        jdbcTemplate.update(sql,new Object[]{
                dao.getStatus(),
                dao.getRejectReason(),
                dao.getCheckerId(),
                Utils.GetTime(dao.getCheckDate()),
                dao.getId()
        });

        String sql2 = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id," +
                "start_date,end_date,ticket_status,prebook_no,is_printed,check_date from b_prebook_detail where id = ? ";

        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql2,new Object[]{dao.getId()},new PreBookRowMapper());

        return prebookInfoDaos.get(0);
    }



    public PrebookInfoDao addPrebook(PrebookInfoDao dao){

        String sql = "insert into b_prebook_detail(id,user_open_id,business_type_id,business_type_name,service_hall_id," +
                "service_hall_name,household_no,contact,contact_tel,submit_date,status,start_date,end_date,ticket_status" +
                ",prebook_no,is_printed) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                dao.getStatus(),
                Utils.GetTime(dao.getStartDate()),
                Utils.GetTime(dao.getEndDate()),
                dao.getTicketStatus(),
                dao.getPrebookNo(),
                dao.getIsPrinted()
        });

        String sql2 = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id," +
                "start_date,end_date,ticket_status,prebook_no,is_printed,check_date from b_prebook_detail where id = ? ";

        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql2,new Object[]{dao.getId()},new PreBookRowMapper());

        return prebookInfoDaos.get(0);
    }


    public List<PrebookInfoDao> getPrebookInfo(String openId,int status){
        String sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id," +
                "start_date,end_date,ticket_status,prebook_no,is_printed,check_date from b_prebook_detail where user_open_id = ? and status=? " +
                "order by submit_date desc ";

        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql,new Object[]{openId,status},new PreBookRowMapper());

        return prebookInfoDaos;
    }


    public List<PrebookInfoDao> getPrebookSize(String openId){
        String sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id," +
                "start_date,end_date,ticket_status,prebook_no,is_printed,check_date from b_prebook_detail " +
                "where user_open_id = ? and status <> 3";

        List<PrebookInfoDao> prebookInfoDaos =
                jdbcTemplate.query(sql,new Object[]{openId},new PreBookRowMapper());

        return prebookInfoDaos;
    }



    public List<PrebookInfoDao> getCheckList(String hallId,int status,Boolean isPrinted){

        String sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id," +
                "start_date,end_date,ticket_status,prebook_no,is_printed,check_date from b_prebook_detail";


        List<PrebookInfoDao> prebookInfoDaos = new ArrayList<>();
        if (isPrinted != null){
            sql += " where service_hall_id = ? and status=? and is_printed=? and ticket_status = 0 order by submit_date desc ";
            prebookInfoDaos = jdbcTemplate.query(sql,new Object[]{hallId,status,isPrinted},new PreBookInfoRepository.PreBookRowMapper());
        }else {
            sql += " where service_hall_id = ? and status=? and ticket_status = 0 order by submit_date desc ";
            prebookInfoDaos = jdbcTemplate.query(sql,new Object[]{hallId,status},new PreBookInfoRepository.PreBookRowMapper());
        }


//        String sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
//                    "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id," +
//                    "start_date,end_date,ticket_status,prebook_no,is_printed from b_prebook_detail where service_hall_id = ? and status=? " +
//                    "order by submit_date desc ";
//
//        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql,new Object[]{hallId,status},new PreBookRowMapper());

        return prebookInfoDaos;
    }

    public CheckerInfoDao getCheckerByOpenId(String openId){
        String sql = "select id,checker_name,checker_tel,user_open_id,service_hall_id,service_hall_name " +
                "from b_prebook_checker where user_open_id = ?";
        return jdbcTemplate.query(sql,new Object[]{openId},new CheckerInfoRowMapper()).get(0);
    }



    public CheckerInfoDao getCheckerById(String id){
        String sql = "select id,checker_name,checker_tel,user_open_id,service_hall_id,service_hall_name " +
                "from b_prebook_checker where id = ?";
        return jdbcTemplate.query(sql,new Object[]{id},new CheckerInfoRowMapper()).get(0);
    }


    public PrebookInfoDao getCheckDetailList(String id){
        String sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id," +
                "start_date,end_date,ticket_status,prebook_no,is_printed,check_date from b_prebook_detail " +
                "where id = ? order by submit_date desc ";

        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql,new Object[]{id},new PreBookRowMapper());

        return prebookInfoDaos.get(0);
    }



    public PrebookInfoDao getPrebookInfoDetail(String id){
        String sql = "select id,user_open_id,business_type_id,business_type_name,service_hall_id,service_hall_name," +
                    "household_no,lineup_no,lineup_time,contact,contact_tel,submit_date,status,reject_reason,checker_id," +
                    "start_date,end_date,ticket_status,prebook_no,is_printed,check_date from b_prebook_detail where id = ?";
        List<PrebookInfoDao> prebookInfoDaos = jdbcTemplate.query(sql,new Object[]{id},new PreBookRowMapper());

        return prebookInfoDaos.get(0);
    }


    public List<CheckerInfoDao> getCheckerByServcieHallId(String hallId){
        String sql = "select id,checker_name,checker_tel,user_open_id,service_hall_id,service_hall_name " +
                "from b_prebook_checker where service_hall_id = ?";
        return jdbcTemplate.query(sql,new Object[]{hallId},new CheckerInfoRowMapper());
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

    public List<CheckerInfoDao> getAllCheckers(){
        String sql = "select id,checker_name,checker_tel,user_open_id,service_hall_id,service_hall_name " +
                "from b_prebook_checker ";
        return jdbcTemplate.query(sql,new CheckerInfoRowMapper());
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

        String sql = "select id,user_open_id,household_no,contact,contact_tel,create_date " +
                "from b_prebook_blacklist where user_open_id = ?";
        List<BlacklistDao> blacklistDaos = jdbcTemplate.query(sql,new Object[]{openId},new BlacklistRowMapper());
        return blacklistDaos.size();
    }


    public void addBlacklist(List<BlacklistDao> blacklistDaos){
        String sql = "insert into b_prebook_blacklist(user_open_id,household_no,contact,contact_tel,create_date) " +
                "values (?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,blacklistDaos.get(i).getUserOpenId());
                ps.setString(2,blacklistDaos.get(i).getHouseholdNo());
                ps.setString(3,blacklistDaos.get(i).getContact());
                ps.setString(4,blacklistDaos.get(i).getContactTel());
                ps.setString(5,Utils.GetTime(blacklistDaos.get(i).getCreateDate()));
            }

            @Override
            public int getBatchSize() {
                return blacklistDaos.size();
            }
        });
    }

    public Integer getRoleByOpenId(String openId) {
        String sql = "select count(id) " +
                "from b_prebook_checker where user_open_id = ?";
        Integer integer = jdbcTemplate.queryForObject(sql, new Object[]{openId}, Integer.class);
        if(integer>0){
            return 1;
        }else {
            return 0;
        }
    }

    public Integer getCountByOpenId(String openId) {
        String sql = "select count(id) from b_prebook_detail where status =1 and service_hall_id in( select service_hall_id " +
                "from b_prebook_checker where user_open_id = ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{openId}, Integer.class);

    }

    public List<BlacklistDao> getBlacklist() {
        String sql = "select id,user_open_id,household_no,contact,contact_tel,create_date " +
                "from b_prebook_blacklist";
        List<BlacklistDao> blacklistDaos = jdbcTemplate.query(sql,new BlacklistRowMapper());
        return blacklistDaos;
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
                    rs.getString("checker_id"),
                    Utils.GetDate(rs.getString("start_date")),
                    Utils.GetDate(rs.getString("end_date")),
                    rs.getInt("ticket_status"),
                    rs.getString("prebook_no"),
                    rs.getBoolean("is_printed"),
                    !Strings.isNullOrEmpty(rs.getString("check_date")) ? Utils.GetDate(rs.getString("check_date")) : null
            );
        }
    }



    class BlacklistRowMapper implements RowMapper<BlacklistDao>{

        @Override
        public BlacklistDao mapRow(ResultSet rs, int i) throws SQLException {
            return new BlacklistDao(
                    rs.getInt("id"),
                    rs.getString("user_open_id"),
                    rs.getString("household_no"),
                    rs.getString("contact"),
                    rs.getString("contact_tel"),
                    Utils.GetDate(rs.getString("create_date"))
            );
        }
    }


}
