package com.sgcc.model;

import com.example.MapUtil;
import com.sgcc.dao.ServiceHallDao;
import com.sgcc.dto.ServiceHallComputedDistanceDTO;
import com.sgcc.dto.ServiceHallMappingDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceHallModel {
    List<ServiceHallDao> m_AllHallDaoList  = new ArrayList<>();
    Double m_UserLat = 0.00D;
    Double m_UserLng = 0.00D;

    public ServiceHallModel(  ){

    }
    
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
    public List<ServiceHallComputedDistanceDTO> Dao2Dto(List<ServiceHallDao> rets , boolean isGetDistance  )
    {
        // 转成DTO,TODO 建立营业厅网点充血模型
        List<ServiceHallComputedDistanceDTO> datas = new ArrayList<>();
        for( ServiceHallDao item : rets )
        {
            ServiceHallComputedDistanceDTO dto = new ServiceHallComputedDistanceDTO();
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
    public List<ServiceHallComputedDistanceDTO> GetDtos(List<ServiceHallComputedDistanceDTO> datas, int distance, int count )
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
    public List<ServiceHallComputedDistanceDTO> NearestServiceHalls() {
        List<ServiceHallDao> daos = Get10KMHalls();
        if (daos.size() < 1)
            return null;

        List<ServiceHallComputedDistanceDTO> dtos = Dao2Dto(daos, true);
        if (dtos.size() < 1)
            return null;

        return GetDtos(dtos, 10, 5);
    }
    public List<ServiceHallComputedDistanceDTO> ServiceHalls(String district)
    {
        List<ServiceHallDao> daos = GetHalls(  district );
        List<ServiceHallComputedDistanceDTO> dtos = Dao2Dto( daos , false );
        if( dtos.size() < 1 )
            return null;

        return dtos;
    }
    public ServiceHallMappingDTO DAO2MapDTO(ServiceHallDao dao)
    {
        if( dao == null )
            return null;
        ServiceHallMappingDTO dto = new ServiceHallMappingDTO();
        dto.setId(dao.getId());
        dto.setServiceHallAddr(dao.getServiceHallAddr());
        dto.setServiceHallAvailable(dao.getServiceHallAvailable());
        dto.setServiceHallBusinessDesc(dao.getServiceHallBusinessDesc());
        dto.setServiceHallBusinessDistrict(dao.getServiceHallBusinessDistrict());
        dto.setServiceHallCollect(dao.getServiceHallCollect());
        dto.setServiceHallDistrict(dao.getServiceHallDistrict());
        dto.setServiceHallId(dao.getServiceHallId());
        dto.setServiceHallLandmarkBuilding(dao.getServiceHallLandmarkBuilding());
        dto.setServiceHallLatitude(dao.getServiceHallLatitude());
        dto.setServiceHallLongitude(dao.getServiceHallLongitude());
        dto.setServiceHallName(dao.getServiceHallName());
        dto.setServiceHallOpenTime(dao.getServiceHallOpenTime());
        dto.setServiceHallOwner(dao.getServiceHallOwner());
        dto.setServiceHallRank(dao.getServiceHallRank());
        dto.setServiceHallTel(dao.getServiceHallTel());
        dto.setServiceHallTraffic(dao.getServiceHallTraffic());
        return dto;
    }

    public ServiceHallDao MapDTO2DAO(ServiceHallMappingDTO dto )
    {
        if( dto == null )
            return null;
        ServiceHallDao dao = new ServiceHallDao();
        dao.setId(dao.getId());
        dao.setServiceHallAddr(dto.getServiceHallAddr());
        dao.setServiceHallAvailable(dto.getServiceHallAvailable());
        dao.setServiceHallBusinessDesc(dto.getServiceHallBusinessDesc());
        dao.setServiceHallBusinessDistrict(dto.getServiceHallBusinessDistrict());
        dao.setServiceHallCollect(dto.getServiceHallCollect());
        dao.setServiceHallDistrict(dto.getServiceHallDistrict());
        dao.setServiceHallId(dto.getServiceHallId());
        dao.setServiceHallLandmarkBuilding(dto.getServiceHallLandmarkBuilding());
        dao.setServiceHallLatitude(dto.getServiceHallLatitude());
        dao.setServiceHallLongitude(dto.getServiceHallLongitude());
        dao.setServiceHallName(dto.getServiceHallName());
        dao.setServiceHallOpenTime(dto.getServiceHallOpenTime());
        dao.setServiceHallOwner(dto.getServiceHallOwner());
        dao.setServiceHallRank(dto.getServiceHallRank());
        dao.setServiceHallTel(dto.getServiceHallTel());
        dao.setServiceHallTraffic(dto.getServiceHallTraffic());
        return dao;
    }

    public List<ServiceHallMappingDTO> DAOs2MapDTOs(List<ServiceHallDao> daos)
    {
        if( daos == null || daos.size() < 1)
            return null;
        List<ServiceHallMappingDTO> dtos = new ArrayList<>();
        for(ServiceHallDao dao:daos)
        {
            dtos.add( DAO2MapDTO(dao));
        }
        return dtos;
    }

    public List<ServiceHallDao> MapDTOs2DAOs(List<ServiceHallMappingDTO> dtos )
    {
        if( dtos == null || dtos.size() < 1)
            return null;
        List<ServiceHallDao> daos = new ArrayList<>();
        for(ServiceHallMappingDTO dto:dtos)
        {
            daos.add( MapDTO2DAO(dto));
        }
        return daos;
    }
}
