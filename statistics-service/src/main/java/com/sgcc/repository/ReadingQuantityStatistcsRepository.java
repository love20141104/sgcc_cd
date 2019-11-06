package com.sgcc.repository;

import com.sgcc.dao.ReadingQuantityStatistcsDao;
import com.sgcc.sgccenum.DateRangeEnum;
import com.sgcc.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
/**
 * 文章阅读量
 */
public class ReadingQuantityStatistcsRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ReadingQuantityStatistcsDao> getReadingQuantityStatistcsDTOs(DateRangeEnum dateRangeEnum) {
        String deteLimit;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (dateRangeEnum.equals(DateRangeEnum.WEEK)) {
            deteLimit = "and page.visit_date >= '" + sdf.format(DateUtil.getnweekFirst(1)) + "'";
        } else if (dateRangeEnum.equals(DateRangeEnum.MONTH)) {
            deteLimit = "and page.visit_date >= '" + sdf.format(DateUtil.getnMonthFirst(0)) + "'";
        } else {
            deteLimit = "and page.visit_date >= '" + new Date().getYear() + "-01-01'";
        }
        String sql = "select article.article_title title,article.article_url url,count(page.page_url) readNum from b_article article "
                + " left join b_page_statistics page on page.page_url = article.article_url "
                + deteLimit
                + " group by page.page_url,article.article_title,article.article_url "
                + " order by readNum DESC limit 10 ";


        return jdbcTemplate.query(sql, new ReadingQuantityMapper());


    }

    class ReadingQuantityMapper implements RowMapper<ReadingQuantityStatistcsDao> {


        @Override
        public ReadingQuantityStatistcsDao mapRow(ResultSet rs, int i) throws SQLException {
            return new ReadingQuantityStatistcsDao(
                    rs.getString("title"),
                    rs.getString("url"),
                    rs.getInt("readNum")
            );
        }
    }

}
