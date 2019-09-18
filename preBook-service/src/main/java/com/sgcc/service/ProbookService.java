package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.entity.event.PrebookEventEntity;
import com.sgcc.entity.query.PrebookQueryEntity;
import com.sgcc.exception.TopErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProbookService {

    @Autowired
    private PrebookEventEntity prebookEventEntity;
    @Autowired
    private PrebookQueryEntity prebookQueryEntity;
    /**
     * 根据用户id查询所有的预约信息
     * @param openId
     * @return
     */
    public List<PrebookDTO> getPrebookInfosByUser(String openId) {
        return prebookQueryEntity.getPrebookInfosByUser(openId);
    }

    /**
     * 用户提交在线预约
     * @param prebookDTO
     * @param openId
     * @return
     */
    public Result submitPrebookInfo(PrebookDTO prebookDTO, String openId) {
        //TODO param check
        System.out.println("service:threadID : "+Thread.currentThread().getId());
        if(null != prebookEventEntity.submitPrebookInfo(prebookDTO)){
            return Result.success(prebookDTO);
        }else {
            return Result.failure(TopErrorCode.GENERAL_ERR,"该时间预约已满");
        }
    }

    /**
     * 根据营业亭id查询营业厅预约状态
     * @param serviceHallId
     */
    public List<PrebookDTO> getPrebookInfosByServiceHall(String serviceHallId) {

        return prebookQueryEntity.getPrebookInfosByServiceHall(serviceHallId);
    }
}
