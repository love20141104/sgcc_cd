package com.sgcc.entity;

import com.sgcc.dao.ServiceHallDao;
import com.sgcc.repository.ServiceHallsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceHallEntity {

    @Autowired
    private ServiceHallsRepository repository;

    private List<ServiceHallDao> m_hallDaoList = new ArrayList<>();

    /**
     * 查询所有网点
     * @return
     */
    public List<ServiceHallDao> findHallList(){
        List<ServiceHallDao> hallDaoList = null;
        if( m_hallDaoList.size() == 0 )
        {
            hallDaoList = repository.findHallList();
            m_hallDaoList = hallDaoList;
        }
        else {
            hallDaoList = m_hallDaoList;
        }
        return hallDaoList;
    }

    /**
     * 批量新增网点
     * @param list
     */
    public void saveServiceHalls(List<ServiceHallDao> list){
        repository.saveServiceHalls(list);
    }
    /**
     * 新增网点
     * @param dao
     */
    public void saveServiceHall(ServiceHallDao dao){
        repository.saveServiceHalls(new ArrayList<ServiceHallDao>(){{add(dao);}});
    }
    /**
     * 删除网点
     * @param ids
     */
    public void delServiceHalls(List<String> ids){
        repository.delServiceHalls(ids);
    }
    /**
     * 批量修改网点
     * @param list
     */
    public void updateServiceHalls(List<ServiceHallDao> list){
        repository.updateServiceHall(list);
    }
    /**
     * 修改网点
     * @param dao
     */
    public void updateServiceHall(ServiceHallDao dao){
        repository.updateServiceHall(new ArrayList<ServiceHallDao>(){{add(dao);}});
    }
}
