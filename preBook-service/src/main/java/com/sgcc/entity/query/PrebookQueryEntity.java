package com.sgcc.entity.query;

import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.model.PrebookModel;
import com.sgcc.repository.PrebookRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
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


        PrebookModel prebookModel = new PrebookModel(prebookRedisRepository.findAllByUserId(
                openId));
        return prebookModel.getPrebookDTOS();
    }


    /**
     * 根据营业亭id查询营业厅预约状态
     *
     * @param serviceHallId
     */
    public List<PrebookDTO> getPrebookInfosByServiceHall(String serviceHallId) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(now.getYear(), now.getMonth(), now.getDay());
        now = calendar.getTime();
        calendar.add(Calendar.DATE, 4);
        Date threeDayAgo = calendar.getTime();

        PrebookModel prebookModel = new PrebookModel(prebookRedisRepository.findAllByServiceHallId(
                serviceHallId
        )
        );
        return prebookModel.getPrebookDTOS();
    }
}
