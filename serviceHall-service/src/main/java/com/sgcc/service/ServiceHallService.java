package com.sgcc.service;

import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.ServiceHallDao;
import com.sgcc.entity.ServiceHallEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<ServiceHallDao> hallDaoList = serviceHallEntity.findHallList();
        for (int i = 0; i < hallDaoList.size(); i++) {
            System.out.println(hallDaoList.get(i).getServiceHallName());
        }
        return Result.success(hallDaoList);
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



}
