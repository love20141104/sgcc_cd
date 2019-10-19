package com.sgcc.entity;

import com.sgcc.dao.ServiceHallDao;
import com.sgcc.repository.ServiceHallRepository;
import com.sgcc.repository.ServiceHallsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceHallEntity {

    @Autowired
    private ServiceHallsRepository repository;
    @Autowired
    private ServiceHallRepository serviceHallRepository;

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

    public List<ServiceHallDao> findHalls(){
        return repository.findHallList();
    }

    public List<ServiceHallDao> findHallById(String id){
        List<ServiceHallDao> list = repository.findHallById(id);
        return list;
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
        int[] res = repository.saveServiceHalls(new ArrayList<ServiceHallDao>(){{add(dao);}});
        if (res.length > 0)
            m_hallDaoList.add(0,findHallById(dao.getId()).get(0));
    }
    /**
     * 删除网点
     * @param ids
     */
    public void delServiceHalls(List<String> ids){
        repository.delServiceHalls(ids);
        m_hallDaoList.clear();
    }
    /**
     * 批量修改网点
     * @param list
     */
    public void updateServiceHalls(List<ServiceHallDao> list){
        repository.updateServiceHall(list);
        m_hallDaoList.clear();
    }
    /**
     * 修改网点
     * @param dao
     */
    public void updateServiceHall(ServiceHallDao dao){
//        repository.updateServiceHall(new ArrayList<ServiceHallDao>(){{add(dao);}});
        serviceHallRepository.updateServiceHall(dao);
        m_hallDaoList.clear();
    }
}
