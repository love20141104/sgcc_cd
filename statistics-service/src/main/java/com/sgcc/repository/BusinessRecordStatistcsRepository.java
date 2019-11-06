package com.sgcc.repository;

import com.sgcc.dao.BusinessRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
/**
 * 业扩报装记录查询
 */
public class BusinessRecordStatistcsRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<BusinessRecordDao> getBusinessRecord() {
        String sql = "select type,sum(num) num from (\n"
                + " select '增容申请' as type,count(*) num from b_increase_capacity_commerce\n"
                + " UNION all select '增容申请' as type , count(*) num from b_increase_capacity_inhabitant\n"
                + " union all select '新装申请' as type , count(*) num from b_new_install_commerce\n"
                + " union all select '新装申请' as type , count(*) num from b_new_install_inhabitant\n"
                + " union all select '更名过户' as type , count(*) num from b_rename_transfer\n"
                + " union all select '税票变更' as type , count(*) num from b_change_taxticket\n"
                + " union all select '信息修正' as type , count(*) num from b_info_correct_commerce\n"
                + " union all select '信息修正' as type , count(*) num from b_info_correct_inhabitant\n"
                + ") as n group by type";
        return jdbcTemplate.query(sql, new BusinessRecordMapper());
    }

    class BusinessRecordMapper implements RowMapper<BusinessRecordDao> {


        @Override
        public BusinessRecordDao mapRow(ResultSet rs, int i) throws SQLException {
            return new BusinessRecordDao(
                    rs.getString("type"),
                    rs.getInt("num")
            );
        }
    }
}
