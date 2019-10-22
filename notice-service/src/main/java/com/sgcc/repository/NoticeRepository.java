package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class NoticeRepository {

    private Logger logger = Logger.getLogger(NoticeRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据区域查询停电公告信息
     * @param district
     * @return
     */
    public List<NoticeDao> findNoticeList(String district){

        String sql = "select id,notice_id,notice_district,notice_type,notice_range," +
                "notice_date from b_blackout_notice " +
                "where notice_district='"+district+"'";

        return jdbcTemplate.query(sql,new NoticeRowMapper());
    }

    /**
     * 根据id查询停电公告信息
     * @param id
     * @return
     */
    public List<NoticeDao> findNoticeById(String id){

        String sql = "select id,notice_id,notice_district,notice_type,notice_range," +
                "notice_date from b_blackout_notice " +
                "where notice_id='"+id+"'";

        return jdbcTemplate.query(sql,new NoticeRowMapper());
    }

    /**
     * 查询所有停电公告信息
     * @return
     */
    public List<NoticeDao> findAll(){

        String sql = "select id,notice_id,notice_district,notice_type,notice_range," +
                "notice_date from b_blackout_notice ";

        return jdbcTemplate.query(sql,new NoticeRowMapper());
    }




    /**
     * 新增停电公告
     * @param noticeDao
     */
    public int insertNotice(NoticeDao noticeDao){

        String sql = "insert into b_blackout_notice(id,notice_id,notice_district,notice_type," +
                "notice_range,notice_date) " +
                "values('"+noticeDao.getId()+"','"+noticeDao.getNoticeId()+"','"+noticeDao.getNoticeDistrict()+"'," +
                "'"+noticeDao.getTypeName()+"','"+noticeDao.getRange()+"','"+noticeDao.getNoticeDate()+"')";

        jdbcTemplate.execute(sql);
        return findNoticeById(noticeDao.getNoticeId()).size();
    }

    /**
     * 修改停电公告
     * @param noticeDao
     */
    public void updateNotice(NoticeDao noticeDao){

        String sql = "update b_blackout_notice set notice_range='"+noticeDao.getRange()+"'";

        StringBuffer stringBuffer = new StringBuffer();
        String whereSql = " where notice_id='"+noticeDao.getNoticeId()+"'";
        if (!Strings.isNullOrEmpty(noticeDao.getNoticeDistrict()))
            stringBuffer.append(",").append("notice_district='"+noticeDao.getNoticeDistrict()+"'");
        if (!Strings.isNullOrEmpty(noticeDao.getTypeName()))
            stringBuffer.append(",").append("notice_type='"+noticeDao.getTypeName()+"'");
        if (!Strings.isNullOrEmpty(noticeDao.getNoticeDate()))
            stringBuffer.append(",").append("notice_date='"+noticeDao.getNoticeDate()+"'");

        if (!Strings.isNullOrEmpty(stringBuffer.toString())){
            sql+= stringBuffer.toString()+whereSql;
        }else {
            sql+= whereSql;
        }
        logger.info("修改停电公告:"+sql);
        jdbcTemplate.execute(sql);
    }


    /**
     * 删除停电公告信息
     * @param ids
     */
    public void delNoticeById(List<String> ids){

        String sql = "delete from b_blackout_notice where notice_id in('"+ Utils.joinStrings(ids,"','") +"')";

        jdbcTemplate.execute(sql);
    }

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



}
