package com.sgcc.entity.query;

import com.sgcc.dao.PreBookDao;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.model.PrebookModel;
import com.sgcc.repository.PrebookRedisRepository;
import com.sgcc.test.TestRedisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class PrebookQueryEntity {
    @Autowired
    private PrebookRedisRepository prebookRedisRepository;


    /**
     * 根据用户id查询所有的预约信息
     *
     * @param openId
     */
    public List<PrebookDTO> getPrebookInfosByUser(String openId) {


        PrebookModel prebookModel = new PrebookModel(
                prebookRedisRepository
                        .findAllByUserId(
                                openId
                        )
        );
        return prebookModel.getPrebookDTOS();
    }


    /**
     * 根据营业亭id查询营业厅预约状态
     *
     * @param serviceHallId
     */
    public List<PrebookDTO> getPrebookInfosByServiceHall(String serviceHallId) {
        PrebookModel prebookModel = new PrebookModel(
                prebookRedisRepository.findAllByServiceHallId(
                        serviceHallId
                )
        );
        return prebookModel.getPrebookDTOS();
    }

    /**
     * 根据预约ids查找预约信息
     * @param ids
     * @return
     */
    public List<PreBookDao> findPrebookList(List<String> ids) {
       return new ArrayList<PreBookDao>(){{
            ids.forEach(id ->{
                add(prebookRedisRepository.findById(id).get());
            });
        }};
    }
}
