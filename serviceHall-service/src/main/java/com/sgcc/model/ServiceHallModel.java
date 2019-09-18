package com.sgcc.model;

import com.example.MapUtil;
import com.sgcc.dao.ServiceHallDao;
import com.sgcc.dtomodel.map.ServiceHall_ComputedDistanceDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceHallModel {
    List<ServiceHallDao> m_AllHallDaoList  = new ArrayList<>();
    Double m_UserLat = 0.00D;
    Double m_UserLng = 0.00D;

    public ServiceHallModel( Double lat,double lng ){
        m_UserLat = lat ;
        m_UserLng = lng;
    }
    public ServiceHallModel( List<ServiceHallDao> list ){
        m_AllHallDaoList = list;
    }
    public ServiceHallModel( Double lat,double lng,List<ServiceHallDao> list){
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

    public List<ServiceHall_ComputedDistanceDTO> Dao2Dto( List<ServiceHallDao> rets ,boolean isGetDistance  )
    {
        // 转成DTO,TODO 建立营业厅网点充血模型
        List<ServiceHall_ComputedDistanceDTO> datas = new ArrayList<>();
        for( ServiceHallDao item : rets )
        {
            ServiceHall_ComputedDistanceDTO dto = new ServiceHall_ComputedDistanceDTO();
            if( isGetDistance )
            {
                Double distance = MapUtil.getDistance( m_UserLat ,m_UserLng ,item.getServiceHallLatitude(),item.getServiceHallLongitude());
                dto.setServicehall_distance(distance);
            }
            dto.setServicehall_name(item.getServiceHallName());
            dto.setServicehall_addr(item.getServiceHallAddr());
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
    public List<ServiceHallDao> GetHalls( String district )
    {
        List<ServiceHallDao> rets = new ArrayList<>();
        for( ServiceHallDao dao : m_AllHallDaoList )
        {
            if( dao.getServiceHallDistrict().equals(district)){
                rets.add(dao);
            }
        }
        return rets;
    }

    public List<ServiceHall_ComputedDistanceDTO> GetDtos( List<ServiceHall_ComputedDistanceDTO> datas,int distance,int count )
    {
        if( datas.size() > 0 )
        {
            Collections.sort(datas,datas.get(0));
        }

        for( int idx = 0 ; idx < datas.size() ;idx++   )
        {
            if( idx >= count  )
            {
                return datas.subList(0,count);
            }
            else {
                if( datas.get(idx).getServicehall_distance() > distance )
                {
                    return datas.subList(0,idx);
                }
            }
        }
        return datas;
    }
    public List<ServiceHall_ComputedDistanceDTO> NearestServiceHalls() {
        List<ServiceHallDao> daos = Get10KMHalls();
        if (daos.size() < 1)
            return null;

        List<ServiceHall_ComputedDistanceDTO> dtos = Dao2Dto(daos, true);
        if (dtos.size() < 1)
            return null;

        return GetDtos(dtos, 10, 5);
    }

    public List<ServiceHall_ComputedDistanceDTO> ServiceHalls(String district)
    {
        List<ServiceHallDao> daos = GetHalls(  district );
        List<ServiceHall_ComputedDistanceDTO> dtos = Dao2Dto( daos , false );
        if( dtos.size() < 1 )
            return null;

        return dtos;
    }
}
