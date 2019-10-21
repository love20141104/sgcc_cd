package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.PreBookDao;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PreBooksRepository {

    private Logger logger = Logger.getLogger(PreBooksRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询所有预约信息
     * @return
     */
//    public List<PreBookDao> findAllPreBookList(){
//        String sql = "select id,user_id,service_hall_id,business_type_id,prebook_code,prebook_date," +
//                "prebook_start_time,contact,contact_tel,submit_time from b_prebook";
//        List<PreBookDao> preBookDaoList = jdbcTemplate.query(sql,new PreBookRowMapper());
//
//        return preBookDaoList;
//    }

    /**
     * 添加预约信息
     *
     * @param preBookDaoList
     */
    public void addPreBook(List<PreBookDao> preBookDaoList) {
        String sql = "insert into b_prebook(id,user_open_id,service_hall_id,prebook_code,prebook_date," +
                "prebook_start_time,contact,contact_tel,submit_time) values(?,?,?,?,?,?,?,?,?)";
        logger.info("添加预约信息sql:" + sql);
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, preBookDaoList.get(i).getId());
                ps.setString(2, preBookDaoList.get(i).getUserId());
                ps.setString(3, preBookDaoList.get(i).getServiceHallId());
                ps.setString(4, preBookDaoList.get(i).getPrebookCode());
                ps.setString(5, preBookDaoList.get(i).getPrebookDate());
                ps.setString(6, preBookDaoList.get(i).getPrebookStartTime());
                ps.setString(7, preBookDaoList.get(i).getContact());
                ps.setString(8, preBookDaoList.get(i).getContactTel());
                ps.setString(9, preBookDaoList.get(i).getSubmitDate());

            }

            @Override
            public int getBatchSize() {
                return preBookDaoList.size();
            }
        });

    }

//    /**
//     * 删除预约信息
//     * @param preBookDaoList
//     */
//    public void delPreBook(List<PreBookDao> preBookDaoList){
//        String sql = "delete from b_prebook where id=?";
//        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setString(1,preBookDaoList.get(i).getId());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return preBookDaoList.size();
//            }
//        });
//
//    }
//
//    /**
//     * 修改预约信息
//     * @param preBookDaoList
//     */
//    public void updatePreBook(List<PreBookDao> preBookDaoList){
//        String sql = "update b_prebook set contact=?,contact_tel=? where id=?";
//        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setString(1,preBookDaoList.get(i).getContact());
//                ps.setString(2,preBookDaoList.get(i).getContactTel());
//                ps.setString(3,preBookDaoList.get(i).getId());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return preBookDaoList.size();
//            }
//        });
//    }

    /**
     * 管理系统修改预约信息
     * @param prebookDTO
     */
    @Transactional
    public List<PreBookDao> updatePreBook(PrebookDTO prebookDTO) {
        try {


            List<PrebookDTO> prebookDTOS = new ArrayList<PrebookDTO>() {{
                add(prebookDTO);
            }};
            String sql = "update b_prebook set user_open_id=?,contact=?,contact_tel=?,service_hall_id=?,prebook_date=?,prebook_start_time=? where prebook_code=?";
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, prebookDTOS.get(i).getUserId());
                    ps.setString(2, prebookDTOS.get(i).getContact());
                    ps.setString(3, prebookDTOS.get(i).getContactTel());
                    ps.setString(4, prebookDTOS.get(i).getServiceHallId());
                    ps.setString(5, prebookDTOS.get(i).getPrebookDate());
                    ps.setString(6, prebookDTOS.get(i).getPrebookStartTime());
                    ps.setString(7, prebookDTOS.get(i).getPrebookCode());
                }

                @Override
                public int getBatchSize() {
                    return prebookDTOS.size();
                }
            });

            String sql_select = "select id,user_open_id,service_hall_id,prebook_code,prebook_date,prebook_start_time,contact,contact_tel,submit_time from b_prebook where prebook_code = '" + prebookDTO.getPrebookCode() + "'";
            return jdbcTemplate.query(sql_select, new PreBookRowMapper());
        }catch (Exception e){
            e.printStackTrace();
            return null;
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
        String sql_select = "select id from b_prebook where prebook_code = '" + prebookCode + "'";
        List<String> id = jdbcTemplate.queryForList(sql_select, String.class);

        String sql_delete = "delete from b_prebook where prebook_code =  '" + prebookCode + "'";
        jdbcTemplate.execute(sql_delete);
        return id;
    }

    /**
     * 批量作废mysql中的预约信息
     *
     * @param prebookCodes
     * @return
     */
    @Transactional
    public List<String> deletePrebooks(List<String> prebookCodes) {
        String sql_select = "select id from b_prebook where prebook_code " +
                "in('"+ Utils.joinStrings(prebookCodes,"','") +"')";
        List<String> ids = jdbcTemplate.queryForList(sql_select, String.class);

        String sql_delete = "delete from b_prebook where prebook_code " +
                "in('"+Utils.joinStrings(prebookCodes,"','")+"')";
        jdbcTemplate.execute(sql_delete);
        return ids;
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
        String sql = "select id,user_open_id,service_hall_id,prebook_code,prebook_date,prebook_start_time,contact,contact_tel,submit_time from b_prebook ";
        StringBuffer sql_where = new StringBuffer();
        if(!Strings.isNullOrEmpty(user_open_id)){
            sql_where.append("user_open_id like '%").append(user_open_id).append("%' and ");
        }
        if(!Strings.isNullOrEmpty(service_hall_id)){
            sql_where.append("service_hall_id like '%").append(service_hall_id).append("%' and ");
        }
        if(!Strings.isNullOrEmpty(prebook_code)){
            sql_where.append("prebook_code like '%").append(prebook_code).append("%' and ");
        }
        if(!Strings.isNullOrEmpty(prebook_date_start)){
            sql_where.append("prebook_date >= '").append(prebook_date_start).append("' and ");
        }
        if(!Strings.isNullOrEmpty(prebook_date_start)){
            sql_where.append("prebook_date <= '").append(prebook_date_end).append("' and ");
        }

        if(!Strings.isNullOrEmpty(sql_where.toString())){
            sql +=" where " + sql_where.toString().substring(0,sql_where.toString().length() - 4);
        }

        return jdbcTemplate.query(sql,new PreBookRowMapper());
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
