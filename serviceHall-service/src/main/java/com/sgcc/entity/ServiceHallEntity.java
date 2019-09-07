package com.sgcc.entity;

import com.sgcc.dao.ServiceHallDao;
import com.sgcc.repository.ServiceHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceHallEntity {

    @Autowired
    private ServiceHallRepository repository;

    /**
     * 查询所有网点
     * @return
     */
    public List<ServiceHallDao> findHallList(){
        return repository.findHallList();
    }

    /**
     * 新增网点
     * @param list
     */
    public void saveServiceHall(List<ServiceHallDao> list){
        repository.saveServiceHall(list);
    }

    /**
     * 删除网点
     * @param list
     */
    public void delServiceHall(List<ServiceHallDao> list){
        repository.delServiceHall(list);
    }

    /**
     * 修改网点
     * @param list
     */
    public void updateServiceHall(List<ServiceHallDao> list){
        repository.updateServiceHall(list);
    }

}
