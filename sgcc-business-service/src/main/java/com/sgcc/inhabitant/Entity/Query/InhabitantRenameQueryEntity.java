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
     * 根据当前id查询更名过户订单
     * @param infoId
     * @return
     */
    public List<InhabitantRenameDao> queryRenameByInfoId(String infoId){
        return inhabitantRenameRepository.findRenameOrderList(infoId);
    }

    /**
     * 查询所有更名过户订单
     * @return
     */
    public List<InhabitantRenameDao> queryAll(){
        return inhabitantRenameRepository.findAll();
    }


}
