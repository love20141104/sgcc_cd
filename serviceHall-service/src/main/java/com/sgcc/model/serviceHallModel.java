package com.sgcc.model;

import com.example.MapUtil;
import com.sgcc.dao.ServiceHallDao;
import com.sgcc.dtomodel.map.ServiceHall_ComputedDistanceDTO;

import java.util.ArrayList;
import java.util.List;

public class serviceHallModel {
    List<ServiceHallDao> m_AllHallDaoList  = new ArrayList<>();
    Double m_UserLat = 0.00D;
    Double m_UserLng = 0.00D;
    public serviceHallModel( Double lat,double lng ){
        m_UserLat = lat ;
        m_UserLng = lng;
    }
    public serviceHallModel( List<ServiceHallDao> list ){
        m_AllHallDaoList = list;
    }
    public serviceHallModel( Double lat,double lng,List<ServiceHallDao> list){
        m_UserLat = lat;
        m_UserLng = lng;
        m_AllHallDaoList = list;
    }

    public List<ServiceHallDao> Get10KMHalls()
    {
        // 先根据 附近10KM 清洗
        List<ServiceHallDao> rets = new ArrayList<>();
        for ( ServiceHallDao item :  m_AllHallDaoList)
        {
            if( (m_UserLat - MapUtil.KM10 <= item.getServiceHallLatitude() && item.getServiceHallLatitude() <=  m_UserLat + MapUtil.KM10)
                    && ( m_UserLng- MapUtil.KM10 <= item.getServiceHallLongitude() && item.getServiceHallLongitude() <= m_UserLng + MapUtil.KM10))
            {
                rets.add(item);
            }
        }
        return rets;
    }

    public List<ServiceHall_ComputedDistanceDTO> Dao2Dto( List<ServiceHallDao> rets )
    {
        // 转成DTO,TODO 建立营业厅网点充血模型
        List<ServiceHall_ComputedDistanceDTO> datas = new ArrayList<>();
        for( ServiceHallDao item : rets )
        {
            Double distance = MapUtil.getDistance( m_UserLat ,m_UserLng ,item.getServiceHallLatitude(),item.getServiceHallLongitude());
            ServiceHall_ComputedDistanceDTO dto = new ServiceHall_ComputedDistanceDTO();
            dto.setServicehall_name(item.getServiceHallName());
            dto.setServicehall_addr(item.getServiceHallAddr());
            dto.setServicehall_distance(distance);
            dto.setServicehall_district(item.getServiceHallDistrict());
            dto.setServicehall_id(item.getServiceHallId());
            dto.setServicehall_lat(item.getServiceHallLatitude());
            dto.setServicehall_lng(item.getServiceHallLongitude());
            dto.setServicehall_opentime(item.getServiceHallOpenTime());
            dto.setServicehall_tel(item.getServiceHallTel());
            datas.add(dto);
        }
        return datas;
    }
}
