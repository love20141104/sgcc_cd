package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.JobDao;
import com.sgcc.dao.NoticeAndJobDao;
import com.sgcc.dao.NoticeDao;
import com.sgcc.dao.RepairProgressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
@Repository
public class JobRepository {
    private Logger logger = Logger.getLogger(JobRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${precompile}")
    private Boolean precompile;
    //保存工单
    public void insertJob(JobDao jobDao){
        if (precompile) {
            String sql = "INSERT INTO b_job(job_id" +
                    ",job_no" +
                    ",user_open_id" +
                    ",notice_id" +
                    ",job_status" +
                    ",job_repair_personnel" +
                    ",job_reason" +
                    ",submit_date) VALUES (?,?,?,?,? ,?,?,? )";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    jobDao.getJobId()
                    ,jobDao.getJobNo()
                    ,jobDao.getUserOpenId()
                    ,jobDao.getNoticeId()

                    ,jobDao.getJobStatus()
                    ,jobDao.getJobRepairPersonnel()
                    ,jobDao.getJobReason()
                    ,Utils.GetTime(jobDao.getSubmitDate())
            });
        }else {
            String sql = "INSERT INTO b_job(job_id" +
                    ",job_no" +
                    ",user_open_id" +
                    ",notice_id" +
                    ",job_status" +
                    ",job_repair_personnel" +
                    ",job_reason" +
                    ",submit_date) VALUES ( '" +
                    jobDao.getJobId() + "'" +
                    ",'" + jobDao.getJobNo() + "'" +
                    ",'" + jobDao.getUserOpenId() + "'" +
                    ",'" + jobDao.getNoticeId() + "'" +

                    ",'" + jobDao.getJobStatus() + "'" +
                    ",'" + jobDao.getJobRepairPersonnel() + "'" +
                    ",'" + jobDao.getJobReason() + "'" +
                    ",'" + Utils.GetTime(jobDao.getSubmitDate()) + "')";
            logger.info("SQL:" + sql);
            jdbcTemplate.execute(sql);
        }
    }
    //更新工单状态
    public void updatejobStatus(String  jobId, String jobStatus){
        if (precompile) {
            String sql = "update b_job set job_status = ? where job_id =? ";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{jobStatus,jobId});
        }else {
            String sql = "update b_job set job_status = '" + jobStatus + "' where job_id ='" + jobId + "'";
            logger.info("SQL:" + sql);
            jdbcTemplate.execute(sql);
        }
    }
    @Transactional
    public void deleteJob(List<String> ids){
        if (precompile) {
            String sql0 = "delete from b_repair_progress where job_id = ?";
            jdbcTemplate.batchUpdate(sql0,new BatchPreparedStatementSetter() {
                public int getBatchSize() {
                    return ids.size();
                }
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    ps.setString(1,ids.get(i));
                }
            });
            String sql = "delete from b_job where job_id = ?";
            jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
                public int getBatchSize() {
                    return ids.size();
                }
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    ps.setString(1,ids.get(i));
                }
            });
            logger.info("deleteSQL:" + sql0);
            logger.info("deleteSQL:" + sql);
        }else {
            String sql0 = "delete from b_repair_progress where job_id in('" + Utils.joinStrings(ids, "','") + "')";
            jdbcTemplate.execute(sql0);
            String sql = "delete from b_job where job_id in('" + Utils.joinStrings(ids, "','") + "')";
            jdbcTemplate.execute(sql);
            logger.info("deleteSQL:" + sql0);
            logger.info("deleteSQL:" + sql);
        }
    }
    public List<NoticeAndJobDao> selectNoticeAndJob(){
        String sql = "select id,bn.notice_id notice_id,notice_district,notice_type,notice_range,notice_date " +
                " ,job_id ,job_no ,user_open_id,job_status,job_repair_personnel,job_reason,submit_date "
                +" from b_blackout_notice bn right join  b_job j on bn.notice_id=j.notice_id   ";
        logger.info("SQL:" + sql);
        return jdbcTemplate.query(sql, new NoticeAndJobDaoRowMapper());
    }
    public String selectJobIdByNoticeId(String noticeId){
        if (precompile) {
            String sql = "select job_id  from b_job where notice_id= ? ";
            logger.info("SQL:" + sql);
            return jdbcTemplate.queryForObject(sql,new Object[]{noticeId}, String.class);
        }else {
            String sql = "select job_id  from b_job where notice_id= '" + noticeId + "'";
            logger.info("SQL:" + sql);
            return jdbcTemplate.queryForObject(sql, String.class);
        }
    }

    class NoticeAndJobDaoRowMapper implements RowMapper<NoticeAndJobDao> {
        @Override
        public NoticeAndJobDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new NoticeAndJobDao(
                    rs.getString("id"),
                    rs.getString("notice_id"),
                    rs.getString("notice_district"),
                    rs.getString("notice_type"),
                    rs.getString("notice_range"),
                    rs.getString("notice_date"),

                    rs.getString("job_id"),
                    rs.getString("job_no"),
                    rs.getString("user_open_id"),
                    rs.getString("job_status"),
                    rs.getString("job_repair_personnel"),
                    rs.getString("job_reason"),
                    Utils.GetDate(rs.getString("submit_date"))
            );
        }

    }
}
