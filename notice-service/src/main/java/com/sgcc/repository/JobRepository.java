package com.sgcc.repository;

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

    }
    public void updatejobStatus(String  jobId,String jobStatus){

    }
    public void deleteJob(List<String> ids){

    }
    public List<RepairProgressDao> selectJob(){
        return null;
    }
}
