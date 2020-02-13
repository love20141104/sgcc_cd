package com.sgcc.repository;

import com.example.Utils;
import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.NoticeDao;
import com.sgcc.dao.RushRepairProgressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class NoticeRepository {

    private Logger logger = Logger.getLogger(NoticeRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${precompile}")
    private Boolean precompile;

    public void delNoticeProgress(String id){
        String sql = "delete from b_rush_repair_progress where id = ?";
        jdbcTemplate.update(sql,new Object[]{id});
    }


    public void addNoticeProgress(List<RushRepairProgressDao> daos){

        String addSql = "insert into b_rush_repair_progress(id,notice_id,progress_state,repair_personnel,cause_of_failure," +
                "progress_img1,progress_img2,progress_img3,submit_date) values(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.batchUpdate(addSql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,daos.get(i).getId());
                ps.setString(2,daos.get(i).getNotice_id());
                ps.setInt(3,daos.get(i).getProgress_state());
                ps.setString(4,daos.get(i).getRepair_personnel());
                ps.setString(5,daos.get(i).getCause_of_failure());
                ps.setString(6,daos.get(i).getProgressImg1());
                ps.setString(7,daos.get(i).getProgressImg2());
                ps.setString(8,daos.get(i).getProgressImg3());
                ps.setString(9, Utils.GetTime(daos.get(i).getSubmit_date()));
            }

            @Override
            public int getBatchSize() {
                return daos.size();
            }
        });

//        jdbcTemplate.update(addSql,new Object[]{
//                dao.getId(),
//                dao.getNotice_id(),
//                dao.getProgress_state(),
//                dao.getRepair_personnel(),
//                dao.getCause_of_failure(),
//                dao.getProgressImg1(),
//                dao.getProgressImg2(),
//                dao.getProgressImg3(),
//                Utils.GetTime(dao.getSubmit_date())
//        });
    }

    public List<RushRepairProgressDao> findNoticeProgressByState(Integer state,String noticeId){
        String sql = "select id,notice_id,progress_state,repair_personnel,cause_of_failure,progress_img1,progress_img2," +
                "progress_img3,submit_date from b_rush_repair_progress where progress_state = ? and notice_id = ?";
        return jdbcTemplate.query(sql,new Object[]{state,noticeId},new RushRepairProgressRowMapper());
    }


    public List<RushRepairProgressDao> findNoticeProgress(){
        String sql = "select id,notice_id,progress_state,repair_personnel,cause_of_failure,progress_img1,progress_img2," +
                "progress_img3,submit_date from b_rush_repair_progress";
        return jdbcTemplate.query(sql,new RushRepairProgressRowMapper());
    }




    /**
     * 根据区域查询停电公告信息
     * @param district
     * @return
     */
    public List<NoticeDao> findNoticeList(String district,String keyword){
        if (precompile) {
            String sql = "select id,notice_id,notice_district,notice_type,notice_range,notice_date "
                    +"from b_blackout_notice "
                    +"where notice_district=? ";

            if (!Strings.isNullOrEmpty(keyword)){
                sql += "and notice_range like ?";
                logger.info("SQL:" + sql);
                return jdbcTemplate.query(sql,new Object[]{district,"%"+keyword+"%"}, new NoticeRowMapper());
            }

            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql,new Object[]{district}, new NoticeRowMapper());
        }else {
            String sql = "select id,notice_id,notice_district,notice_type,notice_range,notice_date "
                    +"from b_blackout_notice "
                    +"where notice_district='" + district + "'";
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql, new NoticeRowMapper());
        }
    }

    public List<NoticeDao> findNoticeListAll(){
        if (precompile) {
            String sql = "select id,notice_id,notice_district,notice_type,notice_range,notice_date "
                    +"from b_blackout_notice ";
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql,new Object[]{}, new NoticeRowMapper());
        }else {
            String sql = "select id,notice_id,notice_district,notice_type,notice_range,notice_date "
                    +"from b_blackout_notice ";
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql, new NoticeRowMapper());
        }
    }


    /**
     * 根据id查询停电公告信息
     * @param id
     * @return
     */
    public List<NoticeDao> findNoticeById(String id){
        if (precompile) {
            String sql = "select id,notice_id,notice_district,notice_type,notice_range,notice_date "
                    +"from b_blackout_notice "
                    +"where notice_id=? ";
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql,new Object[]{id}, new NoticeRowMapper());
        }else {
            String sql = "select id,notice_id,notice_district,notice_type,notice_range,notice_date "
                    +"from b_blackout_notice "
                    +"where notice_id='" + id + "'";
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql, new NoticeRowMapper());
        }
    }

    /**
     * 查询所有停电公告信息
     * @return
     */
    public List<NoticeDao> findAll(){
        if (precompile) {
            String sql = "select id,notice_id,notice_district,notice_type,notice_range,notice_date "
                    +"from b_blackout_notice ";
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql,new Object[]{}, new NoticeRowMapper());
        }else {

            String sql = "select id,notice_id,notice_district,notice_type,notice_range,notice_date "
                    +"from b_blackout_notice ";
            logger.info("SQL:" + sql);
            return jdbcTemplate.query(sql, new NoticeRowMapper());
        }
    }




    /**
     * 新增停电公告
     * @param noticeDao
     */
    public int insertNotice(NoticeDao noticeDao){
        if (precompile) {
            String sql = "insert into b_blackout_notice(id,notice_id,notice_district,notice_type,notice_range,notice_date) "
                    +"values(?,?,?,?,? ,?)";
            logger.info("SQL:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    noticeDao.getId()
                    ,noticeDao.getNoticeId()
                    ,noticeDao.getNoticeDistrict()
                    ,noticeDao.getTypeName()
                    ,noticeDao.getRange()
                    ,noticeDao.getNoticeDate()
            });
            return findNoticeById(noticeDao.getNoticeId()).size();
        }else {
            String sql = "insert into b_blackout_notice(id,notice_id,notice_district,notice_type," +
                    "notice_range,notice_date) " +
                    "values('" + noticeDao.getId() + "','" + noticeDao.getNoticeId() + "','" + noticeDao.getNoticeDistrict() + "'," +
                    "'" + noticeDao.getTypeName() + "','" + noticeDao.getRange() + "','" + noticeDao.getNoticeDate() + "')";
            logger.info("SQL:" + sql);
            jdbcTemplate.execute(sql);
            return findNoticeById(noticeDao.getNoticeId()).size();
        }
    }


    public void addNoticeInfoBatch(List<NoticeDao> noticeDaos) {
        String sql = "insert into b_blackout_notice(id,notice_id,notice_district,notice_type,notice_range,notice_date) values(?,?,?,?,? ,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1,noticeDaos.get(i).getId());
                ps.setString(2,noticeDaos.get(i).getNoticeId());
                ps.setString(3,noticeDaos.get(i).getNoticeDistrict());
                ps.setString(4,noticeDaos.get(i).getTypeName());
                ps.setString(5,noticeDaos.get(i).getRange());
                ps.setString(6,noticeDaos.get(i).getNoticeDate());
            }
            @Override
            public int getBatchSize() {
                return noticeDaos.size();
            }
        });
    }



    /**
     * 修改停电公告
     * @param noticeDao
     */
    public void updateNotice(NoticeDao noticeDao){
        if (precompile) {
            String sql = "update b_blackout_notice set notice_range=? " +
                    ",notice_district= ? " +
                    ",notice_type= ? " +
                    ",notice_date= ? " +
                    "where notice_id= ? ";
            logger.info("修改停电公告:" + sql);
            jdbcTemplate.update(sql,new Object[]{
                    noticeDao.getRange()
                    ,noticeDao.getNoticeDistrict()
                    ,noticeDao.getTypeName()
                    , noticeDao.getNoticeDate()
                    ,noticeDao.getNoticeId()
            });
        }else {
            String sql = "update b_blackout_notice set notice_range='" + noticeDao.getRange() + "'";

            StringBuffer stringBuffer = new StringBuffer();
            String whereSql = " where notice_id='" + noticeDao.getNoticeId() + "'";
            if (!Strings.isNullOrEmpty(noticeDao.getNoticeDistrict()))
                stringBuffer.append(",").append("notice_district='" + noticeDao.getNoticeDistrict() + "'");
            if (!Strings.isNullOrEmpty(noticeDao.getTypeName()))
                stringBuffer.append(",").append("notice_type='" + noticeDao.getTypeName() + "'");
            if (!Strings.isNullOrEmpty(noticeDao.getNoticeDate()))
                stringBuffer.append(",").append("notice_date='" + noticeDao.getNoticeDate() + "'");

            if (!Strings.isNullOrEmpty(stringBuffer.toString())) {
                sql += stringBuffer.toString() + whereSql;
            } else {
                sql += whereSql;
            }
            logger.info("修改停电公告:" + sql);
            jdbcTemplate.execute(sql);
        }
    }


    /**
     * 删除停电公告信息
     * @param ids
     */
    public void delNoticeById(List<String> ids){
        if (precompile) {
            String sql = "delete from b_blackout_notice where notice_id =? ";
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
        }else {
            String sql = "delete from b_blackout_notice where notice_id in('" + Utils.joinStrings(ids, "','") + "')";
            logger.info("SQL:" + sql);
            jdbcTemplate.execute(sql);
        }
    }

    public Boolean noticeOverdue(String noticeId) {
        if (precompile) {
            String sql = "select notice_date from b_blackout_notice where notice_id = ? ";
            logger.info("SQL:" + sql);
            String s = jdbcTemplate.queryForObject(sql,new Object[]{noticeId}, String.class);
            if (!Strings.isNullOrEmpty(s)) {
                if (Utils.GetCurTime().getTime() < Utils.GetDate(s.substring(11, 21) + " 23:59:59").getTime()) {
                    return false;
                }
            }
            return true;
        }else {
            String sql = "select notice_date from b_blackout_notice where notice_id ='" + noticeId + "'";
            logger.info("SQL:" + sql);
            String s = jdbcTemplate.queryForObject(sql, String.class);
            if (!Strings.isNullOrEmpty(s)) {
                if (Utils.GetCurTime().getTime() < Utils.GetDate(s.substring(11, 21) + " 23:59:59").getTime()) {
                    return false;
                }
            }
            return true;
        }
    }

//    public List<NoticeDao> getNoticeByTime(String distr,int time) {
//
//        String sql = "select id,notice_id,notice_district,notice_type,notice_range,notice_date "
//                +"from b_blackout_notice where DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(notice_date)";
//        logger.info("SQL:" + sql);
//        return jdbcTemplate.query(sql,new Object[]{time}, new NoticeRowMapper());
//    }




    class NoticeRowMapper implements RowMapper<NoticeDao> {
        @Override
        public NoticeDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new NoticeDao(
                    rs.getString("id"),
                    rs.getString("notice_id"),
                    rs.getString("notice_district"),
                    rs.getString("notice_type"),
                    rs.getString("notice_range"),
                    rs.getString("notice_date")
            );
        }

    }

    class RushRepairProgressRowMapper implements RowMapper<RushRepairProgressDao>{

        @Override
        public RushRepairProgressDao mapRow(ResultSet rs, int i) throws SQLException {
            return new RushRepairProgressDao(
                    rs.getString("id"),
                    rs.getString("notice_id"),
                    rs.getInt("progress_state"),
                    rs.getString("repair_personnel"),
                    rs.getString("cause_of_failure"),
                    rs.getString("progress_img1"),
                    rs.getString("progress_img2"),
                    rs.getString("progress_img3"),
                    Utils.GetDate(rs.getString("submit_date"))
            );
        }
    }


}
