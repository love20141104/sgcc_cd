package com.sgcc.service;

import com.example.MapUtil;
import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.ServiceHallDao;
import com.sgcc.dtomodel.map.ServiceHall_ComputedDistanceDTO;
import com.sgcc.entity.ServiceHallEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceHallService {

    @Autowired
    private ServiceHallEntity serviceHallEntity;

    /**
     *查询网点
     */
    public Result findHallList(){
        List<ServiceHallDao> hallDaoList  = serviceHallEntity.findHallList();
        return Result.success(hallDaoList);
    }

    public Result NearestServiceHalls(Double lat,Double lng )
    {
        List<ServiceHallDao> hallDaoList  = serviceHallEntity.findHallList();
        // 先根据 附近10KM 清洗
        List<ServiceHallDao> rets = new ArrayList<>();
        for ( ServiceHallDao item :  hallDaoList)
        {
            if( (lat - MapUtil.KM10 <= item.getServiceHallLatitude() && item.getServiceHallLatitude() <=  lat + MapUtil.KM10)
                && ( lng- MapUtil.KM10 <= item.getServiceHallLongitude() && item.getServiceHallLongitude() <= lng + MapUtil.KM10))
            {
                rets.add(item);
            }
        }
        // 转成DTO,TODO 建立营业厅网点充血模型
        List<ServiceHall_ComputedDistanceDTO> datas = new ArrayList<>();
        for( ServiceHallDao item : rets )
        {
            Double distance = MapUtil.getDistance(lat,lng,item.getServiceHallLatitude(),item.getServiceHallLongitude());
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

        // 至多返回 10km内最近的 5个
        if( rets.size() > 0 )
        {
            Collections.sort(datas,datas.get(0));
        }

        for( int idx = 0 ; idx < datas.size() ;idx++   )
        {
            if( idx >= 5  )
            {
                return Result.success(datas.subList(0,idx));
            }
            else {
                if( datas.get(idx).getServicehall_distance() > 10 )
                {
                    return Result.success(datas.subList(0,idx));
                }
            }
        }
        return Result.success(datas);
    }
    /**
     * 新增网点
     * @param list
     * @return
     */
    public Result saveServiceHall(List<ServiceHallDao> list){
        try{
            for (int i = 0; i < list.size(); i++) {
                if(Strings.isNullOrEmpty(list.get(i).getServiceHallId())){
                    list.get(i).setServiceHallId(UUID.randomUUID().toString().substring(0,20));
                }
            }
            serviceHallEntity.saveServiceHall(list);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new  RuntimeException("新增网点失败");
        }

    }


    /**
     * 删除网点
     * @param list
     * @return
     */
    public Result delServiceHall(List<ServiceHallDao> list){
        try{
            serviceHallEntity.delServiceHall(list);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new  RuntimeException("删除网点失败");
        }

    }

    /**
     * 修改网点
     * @param list
     * @return
     */
    public Result updateServiceHall(List<ServiceHallDao> list){
        try{
            serviceHallEntity.updateServiceHall(list);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new  RuntimeException("修改网点失败");
        }

    }

    public void Initialize(){
        List<ServiceHallDao> hallDaoList = serviceHallEntity.findHallList();
    }


}
