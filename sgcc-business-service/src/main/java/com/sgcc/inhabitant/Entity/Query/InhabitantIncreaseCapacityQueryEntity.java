package com.sgcc.inhabitant.Entity.Query;

import com.sgcc.inhabitant.Repository.InhabitantIncreaseCapacityRepository;
import com.sgcc.inhabitant.dao.InhabitantIncreaseCapacityDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class InhabitantIncreaseCapacityQueryEntity {

    @Autowired
    private InhabitantIncreaseCapacityRepository inhabitantIncreaseCapacityRepository;

    /**
     * 根据订单号查询增容订单详细
     * @param orderNo
     * @return
     */
    public List<InhabitantIncreaseCapacityDao> findOrderDetail(String orderNo){
        return inhabitantIncreaseCapacityRepository.findOrderDetail(orderNo);
    }




}
