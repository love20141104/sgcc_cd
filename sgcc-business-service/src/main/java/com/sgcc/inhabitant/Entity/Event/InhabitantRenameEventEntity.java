package com.sgcc.inhabitant.Entity.Event;

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
public class InhabitantRenameEventEntity {

    @Autowired
    private InhabitantRenameRepository inhabitantRenameRepository;

    /**
     * 新增更名过户订单
     * @param dao
     * @return
     */
    public int addRenameOrder(InhabitantRenameDao dao){
        return inhabitantRenameRepository.addRenameOrder(dao);
    }

    /**
     * 删除
     * @param ids
     */
    public void delRenameOrder(List<String> ids){
        inhabitantRenameRepository.delRenameOrderList(ids);
    }

    /**
     * 修改
     * @param infoId
     * @param name
     */
    public void updateRenameOrder(String infoId,String name){
        inhabitantRenameRepository.updateRenameOrderList(infoId,name);
    }

}
