package com.sgcc.repository;

import com.example.Utils;
import com.sgcc.dao.RepairProgressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
                ",'"+progressDao.getProgressStatus().name()+"'"+

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

    }
    public List<RepairProgressDao> selectRepairProgressList(String jobId){
        return null;
    }
}
