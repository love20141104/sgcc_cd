package com.sgcc.inhabitant.Entity.Event;

import com.sgcc.inhabitant.Repository.InhabitantIncreaseCapacityRepository;
import com.sgcc.inhabitant.dao.InhabitantIncreaseCapacityDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class InhabitantIncreaseCapacityEventEntity {

    @Autowired
    private InhabitantIncreaseCapacityRepository inhabitantIncreaseCapacityRepository;

    /**
     * 新增增容订单
     * @param dao
     * @return
     */
    public int addIncreaseCapacityOrder(InhabitantIncreaseCapacityDao dao){
        return inhabitantIncreaseCapacityRepository.addIncreaseCapacityOrder(dao);
    }
}
