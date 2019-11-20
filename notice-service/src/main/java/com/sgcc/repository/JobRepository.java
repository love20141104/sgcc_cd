package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.JobDao;
import com.sgcc.dao.RepairProgressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;
@Repository
public class JobRepository {
    private Logger logger = Logger.getLogger(JobRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${precompile}")
    private Boolean precompile;
    public void insertJob(JobDao jobDao){
        String sql = "INSERT INTO b_job(job_id" +
                ",job_no" +
                ",user_open_id" +
                ",notice_id" +
                ",job_status" +
                ",job_repair_personnel" +
                ",job_reason" +
                ",submit_date) VALUES ( '" +
                jobDao.getJobId()+"'" +
                ",'"+jobDao.getJobNo()+"'"+
                ",'"+jobDao.getUserOpenId()+"'"+
                ",'"+jobDao.getNoticeId()+"'"+

                ",'"+jobDao.getJobStatus()+"'"+
                ",'"+jobDao.getJobRepairPersonnel()+"'"+
                ",'"+jobDao.getJobReason()+"'"+
                ",'"+Utils.GetTime(jobDao.getSubmitDate())+"')";
        logger.info("SQL:" + sql);
        jdbcTemplate.execute(sql);
    }
    public void updatejobStatus(String  jobId, String jobStatus){
        String sql = "update b_job set job_status = '"+jobStatus+"' where job_id ='"+jobId+"'";
        logger.info("SQL:" + sql);
        jdbcTemplate.execute(sql);
    }
    public void deleteJob(List<String> ids){

    }
    public List<RepairProgressDao> selectJob(){
        return null;
    }
}
