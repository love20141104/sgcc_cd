package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dto.SuggestionViewDTO;
import com.sgcc.entity.event.SuggestionQueryEntity;
import com.sgcc.exception.TopErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestionService {
    @Autowired
    private SuggestionQueryEntity suggestionQueryEntity;

//    public List<SuggestionViewDTO> getSuggestionsByUserId(String openId) {
//
//        return suggestionQueryEntity.getPrebookInfosByUser(openId);
//    }
//
//    public Result submitPrebookInfo(PrebookDTO prebookDTO, String openId) {
//        //TODO param check
//        System.out.println("service:threadID : "+Thread.currentThread().getId());
//        if(null != prebookEventEntity.submitPrebookInfo(prebookDTO)){
//            if(!Strings.isNullOrEmpty(prebookDTO.getPrebookCode())){
//                return Result.success(prebookDTO);
//            }else {
//                return Result.failure(TopErrorCode.GENERAL_ERR,"超过最大预约次数");
//            }
//        }else {
//            return Result.failure(TopErrorCode.GENERAL_ERR,"该时间预约已满");
//        }
//    }
}

