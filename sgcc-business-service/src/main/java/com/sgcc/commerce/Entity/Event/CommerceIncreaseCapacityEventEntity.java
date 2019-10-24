package com.sgcc.commerce.Entity.Event;

import com.sgcc.commerce.dao.CommerceIncreaseCapacityDao;
import com.sgcc.commerce.repository.CommerceIncreaseCapacityRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class CommerceIncreaseCapacityEventEntity  {

    @Autowired
    private CommerceIncreaseCapacityRepository commerceIncreaseCapacityRepository;

    public int addIncreaseCapacityOrder(CommerceIncreaseCapacityDao dao){
        return commerceIncreaseCapacityRepository.addIncreaseCapacityOrder(dao);
    }

    public int updateIncreaseCapacityOrder(Double capacity,String orderNo){

        CommerceIncreaseCapacityDao dao = new CommerceIncreaseCapacityDao();
        dao.setCurrentCapacity(capacity);
        return commerceIncreaseCapacityRepository.updateIncreaseCapacity(dao);
    }

    public void delIncreaseCapacityOrder(List<String> ids){
        commerceIncreaseCapacityRepository.delIncreaseCapacity(ids);
    }

}
