package com.sgcc.repository;

import com.example.Utils;

import com.sgcc.dao.RepairProgressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
    public void insertRepairProgress(RepairProgressDao progressDao){
        String sql = "INSERT INTO b_repair_progress(id" +
                ",user_open_id" +
                ",job_id" +
                ",progress_status" +
                ",progress_date" +
                ",progress_img1" +
                ",progress_img2" +
                ",progress_img3) VALUES ( '" +
                progressDao.getId()+"'" +
                ",'"+progressDao.getUserOpenId()+"'"+
                ",'"+progressDao.getJobId()+"'"+
                ",'"+progressDao.getProgressStatus()+"'"+

                ",'"+Utils.GetTime(progressDao.getProgressDate())+"'"+
                ",'"+progressDao.getProgressImg1()+"'"+
                ",'"+progressDao.getProgressImg2()+"'"+
                ",'"+progressDao.getProgressImg3()+"')";
        logger.info("SQL:" + sql);
        jdbcTemplate.execute(sql);

    }
    public void updateRepairProgress(RepairProgressDao progressDao){

    }
    public void deleteRepairProgress(List<String> ids){
        String sql = "delete from b_repair_progress where id in('" + Utils.joinStrings(ids, "','") + "')";
        jdbcTemplate.execute(sql);
        logger.info("deleteSQL:" + sql);
    }
    public List<RepairProgressDao> selectRepairProgressList(String jobId){
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

    public List<RepairProgressDao> selectRepairProgressListByNoticeId(String noticeId) {
        String sql = "select id,user_open_id,job_id,progress_status,progress_date"
                + ",progress_img1,progress_img2,progress_img3 "
                + "from b_repair_progress where job_id = (select notice_id from b_job where  notice_id ='" + noticeId + " ')";
        try {
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql, new RepairProgressDaoRowMapper());
        } catch (Exception e) {
            return null;
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
}
