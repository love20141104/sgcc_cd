package com.sgcc.service;

import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.ServiceHallDao;
import com.sgcc.dto.ServiceHallComputedDistanceDTO;
import com.sgcc.dto.ServiceHallMappingDTO;
import com.sgcc.dto.UpdateServiceHallDTO;
import com.sgcc.entity.ServiceHallEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.ServiceHallModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
        ServiceHallModel serviceHallModel = new ServiceHallModel(lat,lng,hallDaoList);
        return Result.success(serviceHallModel.NearestServiceHalls());
    }

    public Result serviceHalls( String district )
    {
        List<ServiceHallDao> hallDaoList  = serviceHallEntity.findHallList();
        ServiceHallModel serviceHallModel = new ServiceHallModel(hallDaoList);
        List<ServiceHallComputedDistanceDTO> rets = serviceHallModel.ServiceHalls(district);
        if( rets == null || rets.size() == 0 )
            return Result.success("没有找到营业厅信息",rets);

        return Result.success(rets);
    }
    /**
     * 新增网点
     * @param dto
     * @return
     */
    public Result saveServiceHall(ServiceHallMappingDTO dto){
        try{
            if( dto == null )
                return Result.failure(TopErrorCode.PARAMETER_ERR);
//            if (Strings.isNullOrEmpty(dto.getServiceHallId()) || Strings.isNullOrEmpty(dto.getId())) {
//                dto.setServiceHallId(UUID.randomUUID().toString());
//                dto.setId(dto.getServiceHallId());
//            }


            ServiceHallModel model = new ServiceHallModel();
            serviceHallEntity.saveServiceHall(model.MapDTO2DAO(dto));
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new  RuntimeException("新增网点失败");
        }

    }

    /**
     * 批量新增网点
     * @param list
     * @return
     */
    public Result saveServiceHalls(List<ServiceHallMappingDTO> list){
        try{
            if( list == null || list.size() < 1 )
                return Result.failure(TopErrorCode.PARAMETER_ERR);
//            for ( int i = 0; i < list.size(); i++ ) {
//                if(Strings.isNullOrEmpty(list.get(i).getServiceHallId())){
//                    list.get(i).setServiceHallId(UUID.randomUUID().toString());
//                    list.get(i).setId(list.get(i).getServiceHallId());
//                }
//            }
            ServiceHallModel model = new ServiceHallModel();
            serviceHallEntity.saveServiceHalls(model.MapDTOs2DAOs(list));
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new  RuntimeException("新增网点失败");
        }
    }

    /**
     * 批量删除网点
     * @param ids
     * @return
     */
    public Result delServiceHalls(List<String> ids){
        try{
            if( ids == null || ids.size() < 1 )
                return Result.failure(TopErrorCode.PARAMETER_ERR);
            serviceHallEntity.delServiceHalls(ids);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new  RuntimeException("删除网点失败");
        }

    }
    /**
     * 删除网点
     * @param id
     * @return
     */
    public Result delServiceHall(String id){
        try{
            if(Strings.isNullOrEmpty((id)))
                return Result.failure(TopErrorCode.PARAMETER_ERR);
            serviceHallEntity.delServiceHalls(new ArrayList<String>(){{add(id);}});
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new  RuntimeException("删除网点失败");
        }

    }
    /**
     * 批量修改网点
     * @param list
     * @return
     */
    public Result updateServiceHalls(List<ServiceHallMappingDTO> list){
        try{
            if( list == null || list.size() < 1 )
                return Result.failure(TopErrorCode.PARAMETER_ERR);
            ServiceHallModel model = new ServiceHallModel();
            serviceHallEntity.updateServiceHalls(model.MapDTOs2DAOs(list));
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            throw new  RuntimeException("修改网点失败");
        }
    }
    /**
     * 修改网点
     * @param dto
     * @return
     */
    public Result updateServiceHall(UpdateServiceHallDTO dto){
        try{
            if( dto == null )
                return Result.failure(TopErrorCode.PARAMETER_ERR);
            ServiceHallModel model = new ServiceHallModel();
            serviceHallEntity.updateServiceHall(model.updateDTO2DAO(dto));
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
