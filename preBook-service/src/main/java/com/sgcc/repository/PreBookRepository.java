package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.PreBookDao;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PreBookRepository {

    private Logger logger = Logger.getLogger(PreBookRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${precompile}")
    private Boolean precompile;

    /**
     * 查询所有预约信息
     * @return
     */
    public List<PreBookDao> findAllPreBookList(){
        if (precompile) {
            String sql = "select id,user_id,service_hall_id,business_type_id,prebook_code,prebook_date," +
                    "prebook_start_time,contact,contact_tel,submit_time from b_prebook";
            List<PreBookDao> preBookDaoList = jdbcTemplate.query(sql,new Object[]{}, new PreBookRowMapper());
            logger.info("SQL:" + sql);
            return preBookDaoList;
        }else {
            String sql = "select id,user_id,service_hall_id,business_type_id,prebook_code,prebook_date," +
                    "prebook_start_time,contact,contact_tel,submit_time from b_prebook";
            List<PreBookDao> preBookDaoList = jdbcTemplate.query(sql, new PreBookRowMapper());
            logger.info("SQL:" + sql);
            return preBookDaoList;
        }
    }

    /**
     * 添加预约信息
     *
     * @param preBookDao
     */
    public void addPreBook(PreBookDao preBookDao) {
        if (precompile) {
            String sql = "insert into b_prebook(id,user_open_id,service_hall_id,prebook_code,prebook_date," +
                    "prebook_start_time,contact,contact_tel,submit_time) values(?,?,?,?,?, ?,?,?,?)";
            logger.info("添加预约信息sql:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    preBookDao.getId()
                    ,preBookDao.getUserId()
                    ,preBookDao.getServiceHallId()
                    ,preBookDao.getPrebookCode()
                    ,preBookDao.getPrebookDate()

                    ,preBookDao.getPrebookStartTime()
                    ,preBookDao.getContact()
                    ,preBookDao.getContactTel()
                    ,preBookDao.getSubmitDate()
            });
        }else {
            String sql = "insert into b_prebook(id,user_open_id,service_hall_id,prebook_code,prebook_date," +
                    "prebook_start_time,contact,contact_tel,submit_time) values('" + preBookDao.getId() + "'" +
                    ",'" + preBookDao.getUserId() + "','" + preBookDao.getServiceHallId() + "','" + preBookDao.getPrebookCode() + "'" +
                    ",'" + preBookDao.getPrebookDate() + "','" + preBookDao.getPrebookStartTime() + "'" +
                    ",'" + preBookDao.getContact() + "','" + preBookDao.getContactTel() + "','" + preBookDao.getSubmitDate() + "')";
            logger.info("添加预约信息sql:" + sql);
            jdbcTemplate.execute(sql);
        }
    }


    /**
     * 修改预约信息
     * @param prebookDTO
     */
    @Transactional
    public List<PreBookDao> updatePreBook(PrebookDTO prebookDTO) {
        if (precompile) {
            String sql = "update b_prebook set user_open_id=? ,contact=? " +
                    ",contact_tel=? ,service_hall_id=? " +
                    ",prebook_date=? " +
                    ",prebook_start_time=? where prebook_code=? ";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                        prebookDTO.getUserId()
                        ,prebookDTO.getContact()
                        ,prebookDTO.getContactTel()
                        ,prebookDTO.getServiceHallId()
                        ,prebookDTO.getPrebookDate()

                        ,prebookDTO.getPrebookStartTime()
                        , prebookDTO.getPrebookCode()
                        });
            String sql_select = "select id,user_open_id,service_hall_id,prebook_code,prebook_date,prebook_start_time,contact" +
                    ",contact_tel,submit_time from b_prebook where prebook_code = '" + prebookDTO.getPrebookCode() + "'";
            logger.info("SQL:" + sql_select);
            return jdbcTemplate.query(sql_select, new PreBookRowMapper());
        }else {
            String sql = "update b_prebook set user_open_id='" + prebookDTO.getUserId() + "',contact='" + prebookDTO.getContact() + "'" +
                    ",contact_tel='" + prebookDTO.getContactTel() + "',service_hall_id='" + prebookDTO.getServiceHallId() + "'" +
                    ",prebook_date='" + prebookDTO.getPrebookDate() + "'" +
                    ",prebook_start_time='" + prebookDTO.getPrebookStartTime() +
                    "' where prebook_code='" + prebookDTO.getPrebookCode() + "'";
            jdbcTemplate.update(sql);
            logger.info("SQL:" + sql);
            String sql_select = "select id,user_open_id,service_hall_id,prebook_code,prebook_date,prebook_start_time,contact" +
                    ",contact_tel,submit_time from b_prebook where prebook_code = '" + prebookDTO.getPrebookCode() + "'";
            logger.info("SQL:" + sql_select);
            return jdbcTemplate.query(sql_select, new PreBookRowMapper());
        }

    }

    /**
     * 作废mysql中的预约信息
     *
     * @param prebookCode
     * @return
     */
    @Transactional
    public List<String> deletePrebook(String prebookCode) {
        if (precompile) {
            String sql_select = "select id from b_prebook where prebook_code = ? ";
            List<String> id = jdbcTemplate.queryForList(sql_select,new Object[]{prebookCode}, String.class);

            String sql_delete = "delete from b_prebook where prebook_code =  ? ";
            jdbcTemplate.update(sql_delete,new Object[]{prebookCode});
            return id;
        }else {
            String sql_select = "select id from b_prebook where prebook_code = '" + prebookCode + "'";
            List<String> id = jdbcTemplate.queryForList(sql_select, String.class);

            String sql_delete = "delete from b_prebook where prebook_code =  '" + prebookCode + "'";
            jdbcTemplate.execute(sql_delete);
            return id;
        }
    }


    /**
     * 根据多条件取预约信息
     * @param user_open_id
     * @param service_hall_id
     * @param prebook_code
     * @param prebook_date_start
     * @param prebook_date_end
     * @return
     */

    @Transactional
    public List<PreBookDao> getPrebook(String user_open_id, String service_hall_id, String prebook_code, String prebook_date_start, String prebook_date_end) {
        if (precompile) {
            Object[] objects = {};
            ArrayList<Object> objects1 = new ArrayList<>();
            String sql = "select id,user_open_id,service_hall_id,prebook_code,prebook_date,prebook_start_time,contact,contact_tel,submit_time from b_prebook ";
            StringBuffer sql_where = new StringBuffer();
            if (!Strings.isNullOrEmpty(user_open_id)) {
                sql_where.append("user_open_id like ? and ");
                objects1.add("%"+user_open_id+"%");
            }
            if (!Strings.isNullOrEmpty(service_hall_id)) {
                sql_where.append("service_hall_id like ? and ");
                objects1.add("%"+service_hall_id+"%");
            }
            if (!Strings.isNullOrEmpty(prebook_code)) {
                sql_where.append("prebook_code like ? and ");
                objects1.add("%"+prebook_code+"%");
            }
            if (!Strings.isNullOrEmpty(prebook_date_start)) {
                sql_where.append("prebook_date >= ? and ");
                objects1.add(prebook_date_start);
            }
            if (!Strings.isNullOrEmpty(prebook_date_start)) {
                sql_where.append("prebook_date <= ? and ");
                objects1.add(prebook_date_start);
            }

            if (!Strings.isNullOrEmpty(sql_where.toString())) {
                sql += " where " + sql_where.toString().substring(0, sql_where.toString().length() - 4);
            }
            if(objects1.size()>0){
                Object[] objects2 = new Object[objects1.size()];
                for (int i = 0; i <objects1.size() ; i++) {
                    objects2[i]=objects1.get(i);
                }
                objects=objects2;
            }
            return jdbcTemplate.query(sql,objects, new PreBookRowMapper());
        }else {
            String sql = "select id,user_open_id,service_hall_id,prebook_code,prebook_date,prebook_start_time,contact,contact_tel,submit_time from b_prebook ";
            StringBuffer sql_where = new StringBuffer();
            if (!Strings.isNullOrEmpty(user_open_id)) {
                sql_where.append("user_open_id like '%").append(user_open_id).append("%' and ");
            }
            if (!Strings.isNullOrEmpty(service_hall_id)) {
                sql_where.append("service_hall_id like '%").append(service_hall_id).append("%' and ");
            }
            if (!Strings.isNullOrEmpty(prebook_code)) {
                sql_where.append("prebook_code like '%").append(prebook_code).append("%' and ");
            }
            if (!Strings.isNullOrEmpty(prebook_date_start)) {
                sql_where.append("prebook_date >= '").append(prebook_date_start).append("' and ");
            }
            if (!Strings.isNullOrEmpty(prebook_date_start)) {
                sql_where.append("prebook_date <= '").append(prebook_date_end).append("' and ");
            }

            if (!Strings.isNullOrEmpty(sql_where.toString())) {
                sql += " where " + sql_where.toString().substring(0, sql_where.toString().length() - 4);
            }

            return jdbcTemplate.query(sql, new PreBookRowMapper());
        }
    }


    class PreBookRowMapper implements RowMapper<PreBookDao>{
        DateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        public PreBookDao mapRow(ResultSet rs, int i) throws SQLException {
            return new PreBookDao(
                    rs.getString("id"),
                    rs.getString("user_open_id"),
                    rs.getString("service_hall_id"),
                    rs.getString("prebook_date"),
                    rs.getString("prebook_start_time"),
                    rs.getString("prebook_code"),
                    rs.getString("contact"),
                    rs.getString("contact_tel"),
                    rs.getString("submit_time")
            );
        }
    }


}
