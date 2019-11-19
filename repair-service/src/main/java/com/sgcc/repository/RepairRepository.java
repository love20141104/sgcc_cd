package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.RepairDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class RepairRepository {
    private Logger logger = Logger.getLogger(RepairRepository.class.toString());
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${precompile}")
    private Boolean precompile;
    /**
     * 查询所有故障报修订单信息
     * @return
     */
    public List<RepairDao> findRepairOrderAll(){

        String sql = "select id,repair_id,user_open_id,repair_content,repair_contact,repair_tel," +
                "repair_address,repair_img1,repair_img2,repair_img3,repair_submit_date from b_repair";
        logger.info("SQL:" + sql);
        return jdbcTemplate.query(sql,new RepairRowMapper());
    }

    /**
     * 根据repairId查询故障报修订单信息
     * @param repairId
     * @return
     */
    public RepairDao findRepairOrderById(String repairId){
        if (precompile) {
            String sql = "select id,repair_id,user_open_id,repair_content,repair_contact,repair_tel," +
                    "repair_address,repair_img1,repair_img2,repair_img3,repair_submit_date from b_repair " +
                    "where repair_id=? ";
            logger.info("SQL:" + sql);
            return jdbcTemplate.queryForObject(sql,new Object[]{repairId}, new RepairRowMapper());
        }else {
            String sql = "select id,repair_id,user_open_id,repair_content,repair_contact,repair_tel," +
                    "repair_address,repair_img1,repair_img2,repair_img3,repair_submit_date from b_repair " +
                    "where repair_id='" + repairId + "'";
            logger.info("SQL:" + sql);
            return jdbcTemplate.queryForObject(sql, new RepairRowMapper());
        }
    }


    public List<RepairDao> findRepairOrderByOpenId(String openId){
        if (precompile) {
            String sql = "select id,repair_id,user_open_id,repair_content,repair_contact,repair_tel," +
                    "repair_address,repair_img1,repair_img2,repair_img3,repair_submit_date from b_repair " +
                    "where user_open_id=? ";
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql,new Object[]{openId}, new RepairRowMapper());
        }else {
            String sql = "select id,repair_id,user_open_id,repair_content,repair_contact,repair_tel," +
                    "repair_address,repair_img1,repair_img2,repair_img3,repair_submit_date from b_repair " +
                    "where user_open_id='" + openId + "'";
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql, new RepairRowMapper());
        }
    }



    /**
     * 新增故障报修订单
     * @param dao
     * @return
     */
    public int addRepairOrder(RepairDao dao) {
        if (precompile) {
            String sql = "insert into b_repair(id,repair_id,user_open_id,repair_content,repair_contact" +
                    ",repair_tel,repair_address,repair_img1,repair_img2,repair_img3" +
                    ",repair_submit_date) values(?,?,?,?,? ,?,?,?,?,? ,?)";
            logger.info("SQL:" + sql);
            return jdbcTemplate.update(sql,new Object[]{
                    dao.getId()
                    ,dao.getRepairId()
                    ,dao.getOpenId()
                    ,dao.getRepairContent()
                    ,dao.getRepairContact()

                    ,dao.getRepairTel()
                    ,dao.getRepairAddr()
                    ,dao.getRepairImg1()
                    ,dao.getRepairImg2()
                    ,dao.getRepairImg3()

                    ,Utils.GetTime(dao.getSubmitDate())
            });
        } else{
            String sql = "insert into b_repair(id,repair_id,user_open_id,repair_content,repair_contact,repair_tel," +
                    "repair_address,repair_img1,repair_img2,repair_img3,repair_submit_date) values('" + dao.getId() + "'," +
                    "'" + dao.getRepairId() + "','" + dao.getOpenId() + "','" + dao.getRepairContent() + "','" + dao.getRepairContact() + "'," +
                    "'" + dao.getRepairTel() + "','" + dao.getRepairAddr() + "','" + dao.getRepairImg1() + "','" + dao.getRepairImg2() + "'," +
                    "'" + dao.getRepairImg3() + "','" + Utils.GetTime(dao.getSubmitDate()) + "')";
            logger.info("SQL:" + sql);
        return jdbcTemplate.update(sql);
    }
    }


    public int updateRepairOrder(RepairDao dao){
        if (precompile) {
            String sql = "update b_repair set repair_content=? ,user_open_id=? ,repair_contact=? " +
                    " ,repair_tel=?, repair_address=?,repair_img1=?,repair_img2=?,repair_img3=? " +
                    " where repair_id =? ";
            logger.info("SQL:" + sql);
            return jdbcTemplate.update(sql,new Object[]{
                    dao.getRepairContent()
                    ,dao.getOpenId()
                    ,dao.getRepairContact()
                    ,dao.getRepairTel()
                    ,dao.getRepairAddr()

                    ,dao.getRepairImg1()
                    ,dao.getRepairImg2()
                    ,dao.getRepairImg3()
                    ,dao.getRepairId()
            });
        }else {
            String sql = "update b_repair set repair_content='" + dao.getRepairContent() + "'";

            StringBuffer stringBuffer = new StringBuffer();
            String whereSql = " where repair_id = '" + dao.getRepairId() + "'";
            if (!Strings.isNullOrEmpty(dao.getOpenId()))
                stringBuffer.append(",").append("user_open_id='" + dao.getOpenId() + "'");
            if (!Strings.isNullOrEmpty(dao.getRepairContact()))
                stringBuffer.append(",").append("repair_contact='" + dao.getRepairContact() + "'");
            if (!Strings.isNullOrEmpty(dao.getRepairTel()))
                stringBuffer.append(",").append("repair_tel='" + dao.getRepairTel() + "'");
            if (!Strings.isNullOrEmpty(dao.getRepairAddr()))
                stringBuffer.append(",").append("repair_address='" + dao.getRepairAddr() + "'");
            if (!Strings.isNullOrEmpty(dao.getRepairImg1()))
                stringBuffer.append(",").append("repair_img1='" + dao.getRepairImg1() + "'");
            if (!Strings.isNullOrEmpty(dao.getRepairImg2()))
                stringBuffer.append(",").append("repair_img2='" + dao.getRepairImg2() + "'");
            if (!Strings.isNullOrEmpty(dao.getRepairImg3()))
                stringBuffer.append(",").append("repair_img3='" + dao.getRepairImg3() + "'");

            if (!Strings.isNullOrEmpty(stringBuffer.toString())) {
                sql += stringBuffer + whereSql;
            } else {
                sql += whereSql;
            }
            logger.info("SQL:" + sql);
            return jdbcTemplate.update(sql);
        }

    }




    /**
     *删除故障报修订单
     * @param ids
     * @return
     */
    public int delRepairOrder(List<String> ids){
        if (precompile) {
            String sql = "delete from b_repair where repair_id =? ";
            logger.info("SQL:" + sql);
             jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
                 public int getBatchSize() {
                     return ids.size();
                 }
                 public void setValues(PreparedStatement ps, int i)
                         throws SQLException {
                     ps.setString(1,ids.get(i));
                 }
             });
             return 1;
        }else {
            String sql = "delete from b_repair where repair_id in('" + Utils.joinStrings(ids, "','") + "')";
            logger.info("SQL:" + sql);
            return jdbcTemplate.update(sql);
        }
    }



    class RepairRowMapper implements RowMapper<RepairDao>{

        @Override
        public RepairDao mapRow(ResultSet rs, int i) throws SQLException {
            return new RepairDao(
                    rs.getString("id"),
                    rs.getString("repair_id"),
                    rs.getString("user_open_id"),
                    rs.getString("repair_content"),
                    rs.getString("repair_contact"),
                    rs.getString("repair_tel"),
                    rs.getString("repair_address"),
                    rs.getString("repair_img1"),
                    rs.getString("repair_img2"),
                    rs.getString("repair_img3"),
                    Utils.GetDate(rs.getString("repair_submit_date"))
            );
        }
    }


}
