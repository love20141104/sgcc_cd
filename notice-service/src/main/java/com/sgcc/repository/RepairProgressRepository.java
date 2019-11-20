package com.sgcc.repository;

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


    }
    public void updateRepairProgress(RepairProgressDao progressDao){

    }
    public void deleteRepairProgress(List<String> ids){

    }
    public List<RepairProgressDao> selectRepairProgress(String jobId){
        return null;
    }
}
