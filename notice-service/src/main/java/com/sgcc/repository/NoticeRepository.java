package com.sgcc.repository;

import com.google.common.base.Strings;
import com.sgcc.dao.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class NoticeRepository {

    private Logger logger = Logger.getLogger(NoticeRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void findNoticeList(NoticeDao noticeDao){

//        String sql = "insert into b_blackout_notice(id,notice_id,notice_district,notice_type," +
//                "notice_range,notice_progress,notice_progress_time,notice_date) " +
//                "values('"+noticeDao.getId()+"','"+noticeDao.getNoticeId()+"','"+noticeDao.getNoticeDistrict()+"'," +
//                "'"+noticeDao.getTypeName()+"','"+noticeDao.getRange()+"','"+noticeDao.getProgress()+"'," +
//                "'"+noticeDao.getProgressTime()+"','"+noticeDao.getNoticeDate()+"')";
//
//        jdbcTemplate.execute(sql);
    }




    /**
     * 新增停电公告
     * @param noticeDao
     */
    public void insertNotice(NoticeDao noticeDao){

        String sql = "insert into b_blackout_notice(id,notice_id,notice_district,notice_type," +
                "notice_range,notice_progress,notice_progress_time,notice_date) " +
                "values('"+noticeDao.getId()+"','"+noticeDao.getNoticeId()+"','"+noticeDao.getNoticeDistrict()+"'," +
                "'"+noticeDao.getTypeName()+"','"+noticeDao.getRange()+"','"+noticeDao.getProgress()+"'," +
                "'"+noticeDao.getProgressTime()+"','"+noticeDao.getNoticeDate()+"')";

        jdbcTemplate.execute(sql);
    }

    /**
     * 修改停电公告
     * @param noticeDao
     */
    public void updateNotice(NoticeDao noticeDao){

        String sql = "update b_blackout_notice set notice_progress='"+noticeDao.getProgress()+"'," +
                "notice_progress_time='"+noticeDao.getProgressTime()+"'";

        StringBuffer stringBuffer = new StringBuffer();
        String whereSql = " where notice_district='"+noticeDao.getNoticeDistrict()+"'";
        if (!Strings.isNullOrEmpty(noticeDao.getId()))
            stringBuffer.append(",").append("id='"+noticeDao.getId()+"'");
        if (!Strings.isNullOrEmpty(noticeDao.getNoticeId()))
            stringBuffer.append(",").append("notice_id='"+noticeDao.getNoticeId()+"'");
        if (!Strings.isNullOrEmpty(noticeDao.getNoticeDistrict()))
            stringBuffer.append(",").append("notice_district='"+noticeDao.getNoticeDistrict()+"'");
        if (!Strings.isNullOrEmpty(noticeDao.getTypeName()))
            stringBuffer.append(",").append("notice_type='"+noticeDao.getTypeName()+"'");
        if (!Strings.isNullOrEmpty(noticeDao.getRange()))
            stringBuffer.append(",").append("notice_range='"+noticeDao.getRange()+"'");
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

        String sql = "delete from b_blackout_notice where notice_id";

        jdbcTemplate.execute(sql);
    }






}
