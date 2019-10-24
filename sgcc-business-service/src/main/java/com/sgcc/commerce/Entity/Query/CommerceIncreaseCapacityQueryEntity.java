package com.sgcc.commerce.Entity.Query;

import com.google.common.base.Strings;
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
public class CommerceIncreaseCapacityQueryEntity {

    @Autowired
    private CommerceIncreaseCapacityRepository commerceIncreaseCapacityRepository;

    public List<CommerceIncreaseCapacityDao> findIncreaseCapacityOrderList(String openId){
        return commerceIncreaseCapacityRepository.findIncreaseCapacityOrderList(openId);
    }


}
