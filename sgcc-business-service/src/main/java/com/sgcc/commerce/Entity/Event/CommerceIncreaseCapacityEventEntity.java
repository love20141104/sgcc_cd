package com.sgcc.commerce.Entity.Event;

import com.sgcc.commerce.dao.CommerceIncreaseCapacityDao;
import com.sgcc.commerce.repository.CommerceIncreaseCapacityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommerceIncreaseCapacityEventEntity  {

    @Autowired
    private CommerceIncreaseCapacityRepository commerceIncreaseCapacityRepository;

    public int addIncreaseCapacityOrder(CommerceIncreaseCapacityDao dao){
        return commerceIncreaseCapacityRepository.addIncreaseCapacityOrder(dao);
    }

    public int updateIncreaseCapacityOrder(CommerceIncreaseCapacityDao dao){
        return commerceIncreaseCapacityRepository.updateIncreaseCapacity(dao);
    }

    public void delIncreaseCapacityOrder(List<String> ids){
        commerceIncreaseCapacityRepository.delIncreaseCapacity(ids);
    }

}
