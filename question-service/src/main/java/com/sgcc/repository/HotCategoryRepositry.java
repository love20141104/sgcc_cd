package com.sgcc.repository;

import com.sgcc.dao.HotQuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HotCategoryRepositry {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getHotQuuestion(){
        String sql = "select\n" +
                "  d.category_id category_id "+//,count(*) count\n" +
                " from d_question_category d\n" +
                "left join b_page_statistics b on b.page_url =concat('https://sgcc.link/commonProblemDetails?categoryId=',d.category_id)\n" +
                "where d.category_available = true\n" +
                "group by d.category_id\n" +
                "order by count(*) desc\n" +
                "limit 2 offset 0";
        List<String> categoryIds = jdbcTemplate.queryForList(sql,String.class);
        return categoryIds;
    }

    class HotQuestionRowMapper implements RowMapper<HotQuestionDao> {
        @Override
        public HotQuestionDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new HotQuestionDao(
                    rs.getString("category_id"),
                    rs.getInt("count")
            );
        }

    }
}
