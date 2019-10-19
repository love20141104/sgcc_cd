package com.sgcc.repository;

import com.google.common.base.Joiner;
import com.sgcc.dao.ConsumerManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ConsumerManagerRepository {

    private Logger logger = Logger.getLogger(ConsumerManagerRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 新增客户经理
     */
    public void insertConsumerManager (ConsumerManagerDao consumerManagerDao) throws SQLException{
        String sql = "insert into d_consumer_manager("
                +"id"
                +", consumer_manager_id"
                +", consumer_manager_name"
                +", consumer_manager_tel"
                +", consumer_manager_service_area"
                +", consumer_manager_administrative_region"
                +", consumer_manager_duty"
                +", consumer_manager_work_time"
                +", consumer_manager_emergency_tel"
                +", consumer_manager_work_unit"
                +", consumer_manager_category"
                +", consumer_manager_img) value ('"
                + consumerManagerDao.getId() +"','"
                + consumerManagerDao.getConsumerManagerId() +"','"
                + consumerManagerDao.getConsumerManagerName() + "','"
                + consumerManagerDao.getConsumerManagerTel() + "','"
                + consumerManagerDao.getConsumerManagerServiceArea() +"','"
                + consumerManagerDao.getConsumerManagerAdministrativeRegion() + "','"
                + consumerManagerDao.getConsumerManagerDuty()+"','"
                + consumerManagerDao.getConsumerManagerWorkTime()+"','"
                + consumerManagerDao.getConsumerManagerEmergencyTel()+"','"
                + consumerManagerDao.getConsumerManagerWorkUnit()+"','"
                + consumerManagerDao.getConsumerManagerCategory()+"','"
                + consumerManagerDao.getConsumerManagerImg()+"')";
        logger.info("新增客户经理信息："+sql);
        jdbcTemplate.execute(sql);

    }

    /**
     * 删除客户经理
     */

    public void deleteConsumerManagerDao(String consumerManagerId) throws SQLException{
        String sql = "delete from d_consumer_manager where consumer_manager_id = '"+consumerManagerId+"'";
        logger.info("删除客户经理信息："+sql);
        jdbcTemplate.execute(sql);
    }
    /**
     * 批量删除客户经理
     */
    public void deleteConsumerManagerDaos(List<String> consumerManagerIds) throws SQLException{
        String sql = "delete from d_consumer_manager where consumer_manager_id in ('"+Joiner.on("','").join(consumerManagerIds)+"')";
        logger.info("批量删除客户经理信息："+sql);
        jdbcTemplate.execute(sql);
    }
    /**
     * 修改客户经理
     */

    public void updateConsumerManagerDao(ConsumerManagerDao consumerManagerDao) throws SQLException{
        String sql = "update d_consumer_manager set "
                +" consumer_manager_name = '" + consumerManagerDao.getConsumerManagerName()+"'"
                +", consumer_manager_tel = '" + consumerManagerDao.getConsumerManagerTel() +"'"
                +", consumer_manager_service_area = '" + consumerManagerDao.getConsumerManagerServiceArea() +"'"
                +", consumer_manager_administrative_region = '" + consumerManagerDao.getConsumerManagerAdministrativeRegion() +"'"
                +", consumer_manager_duty = '" + consumerManagerDao.getConsumerManagerDuty() +"'"
                +", consumer_manager_work_time = '" + consumerManagerDao.getConsumerManagerWorkTime() +"'"
                +", consumer_manager_emergency_tel = '" + consumerManagerDao.getConsumerManagerEmergencyTel() +"'"
                +", consumer_manager_work_unit = '" + consumerManagerDao.getConsumerManagerWorkUnit() +"'"
                +", consumer_manager_category = '" + consumerManagerDao.getConsumerManagerCategory() +"'"
                +", consumer_manager_img = '" + consumerManagerDao.getConsumerManagerImg() +"' " +
                " where consumer_manager_id = '"+consumerManagerDao.getConsumerManagerId()+"';";
        logger.info("修改客户经理信息："+sql);
        jdbcTemplate.execute(sql);
    }
    /**
     * 根据用户id查询对应的客户经理 TODO
     */
    public ConsumerManagerDao selectConsumerManagerDaoByUserId(String UserId) throws SQLException{
        String sql = "select id"
                +", consumer_manager_id"
                +", consumer_manager_name"
                +", consumer_manager_tel"
                +", consumer_manager_service_area"
                +", consumer_manager_administrative_region"
                +", consumer_manager_duty"
                +", consumer_manager_work_time"
                +", consumer_manager_emergency_tel"
                +", consumer_manager_work_unit"
                +", consumer_manager_category"
                +", consumer_manager_img"
                + " from d_consumer_manager where id = '"+UserId+"'";
        logger.info("修改客户经理信息："+sql);
        return jdbcTemplate.queryForObject(sql,new ConsumerManagerRowMapper());
    }

    /**
     * 查询所有客户经理列表
     */
    public List<ConsumerManagerDao> selectConsumerManagerDaos() throws SQLException{
        String sql = "select id"
                +", consumer_manager_id"
                +", consumer_manager_name"
                +", consumer_manager_tel"
                +", consumer_manager_service_area"
                +", consumer_manager_administrative_region"
                +", consumer_manager_duty"
                +", consumer_manager_work_time"
                +", consumer_manager_emergency_tel"
                +", consumer_manager_work_unit"
                +", consumer_manager_category"
                +", consumer_manager_img"
                + " from d_consumer_manager;";
        logger.info("查询所有客户经理列表："+sql);
        return jdbcTemplate.query(sql,new ConsumerManagerRowMapper());
    }



    class ConsumerManagerRowMapper implements RowMapper<ConsumerManagerDao> {
        @Override
        public ConsumerManagerDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ConsumerManagerDao(
                    rs.getString("id"),
                    rs.getString("consumer_manager_id"),
                    rs.getString("consumer_manager_name"),
                    rs.getString("consumer_manager_tel"),
                    rs.getString("consumer_manager_service_area"),
                    rs.getString("consumer_manager_administrative_region"),
                    rs.getString("consumer_manager_duty"),
                    rs.getString("consumer_manager_work_time"),
                    rs.getString("consumer_manager_emergency_tel"),
                    rs.getString("consumer_manager_work_unit"),
                    rs.getString("consumer_manager_category"),
                    rs.getString("consumer_manager_img")
            );
        }

    }

}
