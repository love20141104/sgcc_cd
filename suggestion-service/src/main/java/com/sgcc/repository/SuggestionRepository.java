package com.sgcc.repository;

import com.sgcc.dao.SuggestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SuggestionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


}
