package com.sgcc.inhabitant.Entity.Query;

import com.sgcc.inhabitant.Repository.InhabitantRenameRepository;
import com.sgcc.inhabitant.dao.InhabitantRenameDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class InhabitantRenameQueryEntity {

    @Autowired
    private InhabitantRenameRepository inhabitantRenameRepository;

    /**
     * 根据当前户号信息查询更名过户订单
     * @param houseId
     * @return
     */
    public List<InhabitantRenameDao> queryRenameOrder(String houseId){
        return inhabitantRenameRepository.findRenameOrderList(houseId);
    }

    /**
     * 查询所有更名过户订单
     * @return
     */
    public List<InhabitantRenameDao> queryAll(){
        return inhabitantRenameRepository.findAll();
    }


}
