package com.sgcc.repository;

import com.example.Utils;

import com.sgcc.dao.JobAndRepairProgressDao;
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
public class RepairProgressRepository {
    private Logger logger = Logger.getLogger(RepairProgressRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${precompile}")
    private Boolean precompile;
    //保存抢修进度
    public void insertRepairProgress(RepairProgressDao progressDao){
        if (precompile) {
            String sql = "INSERT INTO b_repair_progress(id" +
                    ",user_open_id" +
                    ",job_id" +
                    ",progress_status" +
                    ",progress_date" +
                    ",progress_img1" +
                    ",progress_img2" +
                    ",progress_img3) VALUES (?,?,?,?,? ,?,?,? )";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    progressDao.getId()
                    ,progressDao.getUserOpenId()
                    ,progressDao.getJobId()
                    ,progressDao.getProgressStatus()

                    , Utils.GetTime(progressDao.getProgressDate())
                    ,progressDao.getProgressImg1()
                    ,progressDao.getProgressImg2()
                    ,progressDao.getProgressImg3()
            });
        }else {
            String sql = "INSERT INTO b_repair_progress(id" +
                    ",user_open_id" +
                    ",job_id" +
                    ",progress_status" +
                    ",progress_date" +
                    ",progress_img1" +
                    ",progress_img2" +
                    ",progress_img3) VALUES ( '" +
                    progressDao.getId() + "'" +
                    ",'" + progressDao.getUserOpenId() + "'" +
                    ",'" + progressDao.getJobId() + "'" +
                    ",'" + progressDao.getProgressStatus() + "'" +

                    ",'" + Utils.GetTime(progressDao.getProgressDate()) + "'" +
                    ",'" + progressDao.getProgressImg1() + "'" +
                    ",'" + progressDao.getProgressImg2() + "'" +
                    ",'" + progressDao.getProgressImg3() + "')";
            logger.info("SQL:" + sql);
            jdbcTemplate.execute(sql);
        }

    }
    public void updateRepairProgress(RepairProgressDao progressDao){

    }
    //删除抢修进度
    @Transactional
    public void deleteRepairProgress(List<String> ids){
        if (precompile) {
            String sql = "delete from b_repair_progress where id = ? ";
            jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
                public int getBatchSize() {
                    return ids.size();
                }
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    ps.setString(1,ids.get(i));
                }
            });
            logger.info("deleteSQL:" + sql);
        }else {
            String sql = "delete from b_repair_progress where id in('" + Utils.joinStrings(ids, "','") + "')";
            jdbcTemplate.execute(sql);
            logger.info("deleteSQL:" + sql);
        }
    }
    //通过工单id查询抢修进度
    public List<RepairProgressDao> selectRepairProgressList(String jobId){
        if (precompile) {
            String sql = "select id,user_open_id,job_id,progress_status,progress_date"
                    + ",progress_img1,progress_img2,progress_img3 "
                    + "from b_repair_progress where job_id = ? ";
            try {
                logger.info("SQL:" + sql);
                return jdbcTemplate.query(sql,new Object[]{jobId}, new RepairProgressDaoRowMapper());
            } catch (Exception e) {
                return null;
            }
        }else {
            String sql = "select id,user_open_id,job_id,progress_status,progress_date"
                    + ",progress_img1,progress_img2,progress_img3 "
                    + "from b_repair_progress where job_id = '" + jobId + " '";
            try {
                logger.info("SQL:" + sql);
                return jdbcTemplate.query(sql, new RepairProgressDaoRowMapper());
            } catch (Exception e) {
                return null;
            }
        }
    }
    //通过停电公告id查询抢修进度
    public List<JobAndRepairProgressDao> selectRepairProgressListByNoticeId(String noticeId) {
        if (precompile) {
            String sql = "select job_repair_personnel,job_reason, id,rp.user_open_id user_open_id,j.job_id,progress_status,progress_date,progress_img1,progress_img2,progress_img3 " +
                    "from b_repair_progress rp left join b_job j on rp.job_id=j.job_id where  j.notice_id = ? order by progress_date asc";
            try {
                logger.info("SQL:" + sql);
                return jdbcTemplate.query(sql,new Object[]{noticeId}, new JobAndRepairProgressDaoRowMapper());
            } catch (Exception e) {
                return null;
            }
        }else {
            String sql = "select job_repair_personnel,job_reason, id,rp.user_open_id user_open_id,j.job_id,progress_status,progress_date,progress_img1,progress_img2,progress_img3 " +
                    "from b_repair_progress rp left join b_job j on rp.job_id=j.job_id where  j.notice_id =" + noticeId + " order by progress_date asc";
            try {
                logger.info("SQL:" + sql);
                return jdbcTemplate.query(sql, new JobAndRepairProgressDaoRowMapper());
            } catch (Exception e) {
                return null;
            }
        }

    }

    class RepairProgressDaoRowMapper implements RowMapper<RepairProgressDao> {

        @Override
        public RepairProgressDao mapRow(ResultSet rs, int i) throws SQLException {
            RepairProgressDao repairProgressDao = new RepairProgressDao(

                    rs.getString("id"),
                    rs.getString("user_open_id"),
                    rs.getString("job_id"),
                    rs.getString("progress_status"),

                    Utils.GetDate( rs.getString("progress_date")),
                    rs.getString("progress_img1"),
                    rs.getString("progress_img2"),
                    rs.getString("progress_img3")
            );
            return repairProgressDao;
        }
    }
    class JobAndRepairProgressDaoRowMapper implements RowMapper<JobAndRepairProgressDao> {

        @Override
        public JobAndRepairProgressDao mapRow(ResultSet rs, int i) throws SQLException {
            JobAndRepairProgressDao repairProgressDao = new JobAndRepairProgressDao(
                    rs.getString("job_repair_personnel"),
                    rs.getString("job_reason"),
                    rs.getString("id"),
                    rs.getString("user_open_id"),
                    rs.getString("job_id"),
                    rs.getString("progress_status"),

                    Utils.GetDate( rs.getString("progress_date")),
                    rs.getString("progress_img1"),
                    rs.getString("progress_img2"),
                    rs.getString("progress_img3")
            );
            return repairProgressDao;
        }
    }
}
