package com.sgcc.repository;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.ServiceHallDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ServiceHallRepository {

    private Logger logger = Logger.getLogger(ServiceHallRepository.class.toString());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ServiceHallDao> findHallList(){
        String sql = "select id,service_hall_id,service_hall_name,service_hall_addr,service_hall_opentime,service_hall_longitude," +
                "service_hall_latitude,service_hall_district,service_hall_tel,service_hall_available,service_hall_business_desc,"+
                "service_hall_traffic,service_hall_landmark_building,service_hall_owner,service_hall_business_district,"+
                "service_hall_rank,service_hall_collect from d_service_hall where service_hall_available = 1";

        try {
            return jdbcTemplate.query(sql, new ServiceRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("失败！！");
        }

    }

    /**
     * 修改网点
     * @param serviceHallDao
     */
    public void updateServiceHall(ServiceHallDao serviceHallDao) {
        String sql = "update d_service_hall set service_hall_name='"+serviceHallDao.getServiceHallName()+"'" +
                ",service_hall_addr='"+serviceHallDao.getServiceHallAddr()+"'" +
                ",service_hall_opentime='"+serviceHallDao.getServiceHallOpenTime()+"'" +
                ",service_hall_district='"+serviceHallDao.getServiceHallDistrict()+"'" +
                ",service_hall_owner='"+serviceHallDao.getServiceHallOwner()+"'" +
                ",service_hall_available="+serviceHallDao.getServiceHallAvailable()+"" +
                ",service_hall_traffic='"+serviceHallDao.getServiceHallTraffic()+"'" +
                ",service_hall_rank='"+serviceHallDao.getServiceHallRank()+"'" +
                ",service_hall_collect="+serviceHallDao.getServiceHallCollect()+"";

        StringBuffer stringBuffer = new StringBuffer();
        String whereSql = " where service_hall_id='"+serviceHallDao.getServiceHallId()+"'";
        if (!Strings.isNullOrEmpty(serviceHallDao.getServiceHallTel()))
            stringBuffer.append(",").append("service_hall_tel='"+serviceHallDao.getServiceHallTel()+"'");
        if (!(serviceHallDao.getServiceHallLongitude()-0.0<1e-6))
            stringBuffer.append(",").append("service_hall_longitude='"+serviceHallDao.getServiceHallLongitude()+"'");
        if (!(serviceHallDao.getServiceHallLatitude()-0.0<1e-6))
            stringBuffer.append(",").append("service_hall_latitude='"+serviceHallDao.getServiceHallLatitude()+"'");
        if (!Strings.isNullOrEmpty(serviceHallDao.getServiceHallLandmarkBuilding()))
            stringBuffer.append(",").append("service_hall_landmark_building='"+serviceHallDao.getServiceHallLandmarkBuilding()+"'");
        if (!Strings.isNullOrEmpty(serviceHallDao.getServiceHallBusinessDesc()))
            stringBuffer.append(",").append("service_hall_business_desc='"+serviceHallDao.getServiceHallBusinessDesc()+"'");
        if (!Strings.isNullOrEmpty(serviceHallDao.getServiceHallBusinessDistrict()))
            stringBuffer.append(",").append("service_hall_business_district='"+serviceHallDao.getServiceHallBusinessDistrict()+"'");

        if (!Strings.isNullOrEmpty(stringBuffer.toString())){
            sql+= stringBuffer.toString()+whereSql;
        }else {
            sql+= whereSql;
        }

        logger.info("updateSQL:"+sql);
        jdbcTemplate.update(sql);
    }


    /**
     * 新增网点
     * @param serviceHallDao
     */
    public void saveServiceHall(ServiceHallDao serviceHallDao){
        String sql = "INSERT INTO d_service_hall(id,service_hall_id,service_hall_name,service_hall_addr" +
                ",service_hall_opentime,service_hall_longitude,service_hall_latitude,service_hall_district," +
                "service_hall_tel,service_hall_owner,service_hall_landmark_building,service_hall_traffic,service_hall_rank," +
                "service_hall_collect,service_hall_business_desc,service_hall_business_district) " +
                "values('"+serviceHallDao.getId()+"','"+serviceHallDao.getServiceHallId()+
                "','"+serviceHallDao.getServiceHallName()+"','"+serviceHallDao.getServiceHallAddr()+"','"+serviceHallDao.getServiceHallOpenTime()+
                "',"+serviceHallDao.getServiceHallLongitude()+","+serviceHallDao.getServiceHallLatitude()+",'"+
                serviceHallDao.getServiceHallDistrict()+"','"+serviceHallDao.getServiceHallTel()+"','"+serviceHallDao.getServiceHallOwner()+
                "','"+serviceHallDao.getServiceHallLandmarkBuilding()+"','"+serviceHallDao.getServiceHallTraffic()+"'," +
                "'"+serviceHallDao.getServiceHallRank()+"',"+serviceHallDao.getServiceHallCollect()+"," +
                "'"+serviceHallDao.getServiceHallBusinessDesc()+"','"+serviceHallDao.getServiceHallBusinessDistrict()+"')";
        logger.info("insertSQL:"+sql);
        jdbcTemplate.update(sql);
    }


    /**
     * 删除网点
     * @param ids
     */
    public void delServiceHalls(List<String> ids){
        String strIds = Utils.joinStrings(ids,"','");
        String sql = "delete from d_service_hall where service_hall_id in('"+ strIds +"')";
        jdbcTemplate.execute(sql);
    }


    class ServiceRowMapper implements RowMapper<ServiceHallDao> {
        @Override
        public ServiceHallDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ServiceHallDao(
                    rs.getString("id"),
                    rs.getString("service_hall_id"),
                    rs.getString("service_hall_name"),
                    rs.getString("service_hall_addr"),
                    rs.getString("service_hall_opentime"),
                    rs.getString("service_hall_tel"),
                    rs.getDouble("service_hall_latitude"),
                    rs.getDouble("service_hall_longitude"),
                    rs.getString("service_hall_district"),
                    rs.getBoolean("service_hall_available"),
                    rs.getString("service_hall_business_desc"),
                    rs.getString("service_hall_traffic"),
                    rs.getString("service_hall_landmark_building"),
                    rs.getString("service_hall_owner"),
                    rs.getString("service_hall_business_district"),
                    rs.getString("service_hall_rank"),
                    rs.getBoolean("service_hall_collect")
                    );
        }

    }




}
