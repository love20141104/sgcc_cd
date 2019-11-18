package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.PageStatisticsDao;
import com.sgcc.dto.HotPageDto;
import com.sgcc.dto.PageStatistcsDateDto;
import com.sgcc.dto.PageStatistcsMonthDto;
import com.sgcc.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class PageStatisticsRepository {
    private Logger logger = Logger.getLogger(PageStatisticsRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${precompile}")
    private Boolean precompile;

    @Transactional
    public void savePageStatistics(PageStatisticsDao pageStatisticsDao){
        if (precompile) {
            if (!Strings.isNullOrEmpty(pageStatisticsDao.getPageName()) && !pageStatisticsDao.getPageName().equalsIgnoreCase("null")) {
                String sql = "insert into b_page_statistics(id,page_url,page_name,user_open_id,visit_date,client_ip)"
                        + " values (?,?,?,?,? ,?)";
                logger.info("insertSQL:" + sql);
                jdbcTemplate.update(sql,new Object[]{
                        pageStatisticsDao.getId()
                        , pageStatisticsDao.getPageUrl()
                        , pageStatisticsDao.getPageName()
                        ,  pageStatisticsDao.getUserOpenId()
                        ,  Utils.GetTime(pageStatisticsDao.getVisitDate())

                        , pageStatisticsDao.getClientIp()
                });
            }
        }else {
            if (!Strings.isNullOrEmpty(pageStatisticsDao.getPageName()) && !pageStatisticsDao.getPageName().equalsIgnoreCase("null")) {
                String sql = "insert into b_page_statistics(id,page_url,page_name,user_open_id,visit_date,client_ip)"
                        + " values ('" + pageStatisticsDao.getId() + "','"
                        + pageStatisticsDao.getPageUrl() + "','"
                        + pageStatisticsDao.getPageName() + "','"
                        + pageStatisticsDao.getUserOpenId() + "','"
                        + Utils.GetTime(pageStatisticsDao.getVisitDate()) + "','"
                        + pageStatisticsDao.getClientIp() + "')";
                logger.info("insertSQL:" + sql);
                jdbcTemplate.update(sql);
            }
        }
    }

    public List<PageStatistcsDateDto> getPageStatisticsCountMonth(){
        String sql="select IFNULL(count(id),0) num  ,d.date visit_date from b_page_statistics b "
                +"right join (" +
                " select DATE_FORMAT(date_sub(curdate(), interval 11 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 10 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 9 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 8 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 7 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 6 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 5 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 4 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 3 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 2 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 1 month ),'%Y-%m') as date " +
                "union select DATE_FORMAT(date_sub(curdate(), interval 0 month ),'%Y-%m') as date) d "
                +" on d.date = DATE_FORMAT(b.visit_date ,'%Y-%m') "
                + " group by d.date ORDER BY d.date asc;";
        logger.info("select:"+sql);
         return jdbcTemplate.query(sql, new PageStatistcsMonthDtoRowMapper());
    }

    public PageStatistcsMonthDto getPageStatisticsCountDay(){
        PageStatistcsMonthDto pageStatistcsMonthDto = new PageStatistcsMonthDto();
        String sql="select IFNULL(count(id),0) num  ,d.date visit_date from b_page_statistics b "
        +"right join (select date_sub(curdate(), interval 9 day ) as date " +
                "union select date_sub(curdate(), interval 8 day ) as date " +
                "union select date_sub(curdate(), interval 7 day ) as date " +
                "union select date_sub(curdate(), interval 6 day ) as date " +
                "union select date_sub(curdate(), interval 5 day ) as date " +
                "union select date_sub(curdate(), interval 4 day ) as date " +
                "union select date_sub(curdate(), interval 3 day ) as date " +
                "union select date_sub(curdate(), interval 2 day ) as date " +
                "union select date_sub(curdate(), interval 1 day ) as date " +
                "union select date_sub(curdate(), interval 0 day ) as date) d "
                +" on d.date = DATE_FORMAT(b.visit_date ,'%Y-%m-%d') "
                + " group by d.date ORDER BY d.date asc;";
        logger.info("select:"+sql);
        List<PageStatistcsDateDto> query = jdbcTemplate.query(sql, new PageStatistcsMonthDtoRowMapper());
        pageStatistcsMonthDto.setPageStatistcsList(query);
        String sql2="select IFNULL(count(id),0) num  from b_page_statistics  "
                + " WHERE date_sub(curdate(), interval 9 day ) <= date(visit_date) ";
        try {
            Integer integer = jdbcTemplate.queryForObject(sql2, Integer.class);
            pageStatistcsMonthDto.setTotal(integer);
        }catch (Exception e){
            pageStatistcsMonthDto.setTotal(0);
        }
        return pageStatistcsMonthDto;
    }

    public List<HotPageDto> hotPageDtoList(){
        String sql="SELECT page_name,count(distinct(user_open_id)) user_num,COUNT(id) visit_num "
                + " FROM b_page_statistics where page_url like '%https://sgcc.link/%' and  page_url not in ("
                + " select article_url page_url from d_article article) "
                + " GROUP BY page_name ORDER BY COUNT(id) desc";
        logger.info("insertSQL:"+sql);
        List<HotPageDto> query = jdbcTemplate.query(sql, new HotPageDtoRowMapper());
        return  query;
    }

    class PageStatistcsMonthDtoRowMapper implements RowMapper<PageStatistcsDateDto> {

        @Override
        public PageStatistcsDateDto mapRow(ResultSet rs, int i) throws SQLException {
            PageStatistcsDateDto pageStatistcsDateDto = new PageStatistcsDateDto();
            if(null==rs.getString("num")){
                pageStatistcsDateDto.setUrlNum(0);
            }if(null==rs.getString("visit_date")){
                pageStatistcsDateDto.setDate(null);
            }
            pageStatistcsDateDto.setUrlNum(rs.getInt("num"));
            pageStatistcsDateDto.setDate(rs.getString("visit_date"));
            return pageStatistcsDateDto;
        }
    }
    class HotPageDtoRowMapper implements RowMapper<HotPageDto> {

        @Override
        public HotPageDto mapRow(ResultSet rs, int i) throws SQLException {
            HotPageDto hotPageDto = new HotPageDto();
            hotPageDto.setPageName(rs.getString("page_name"));
            hotPageDto.setUserNum(rs.getInt("user_num"));
            hotPageDto.setVisitNum(rs.getInt("visit_num"));
            return hotPageDto;
        }
    }
}
