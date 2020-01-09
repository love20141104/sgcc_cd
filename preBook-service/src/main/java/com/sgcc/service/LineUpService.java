package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dto.OnlineQueuingInputDTO;
import com.sgcc.exception.TopErrorCode;
import org.springframework.stereotype.Service;

@Service
public class LineUpService {

    /**
     * 线上排队
     * @param dto
     * @return
     */
    public Result onlineQueuing(OnlineQueuingInputDTO dto) {
        try {
            // TODO 调用心跳接口

            // TODO 调用线上排队接口

            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }





}
