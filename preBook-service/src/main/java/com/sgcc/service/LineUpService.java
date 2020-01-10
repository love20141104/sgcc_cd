package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dao.LineUpDao;
import com.sgcc.dto.*;
import com.sgcc.entity.LineUpEntity;
import com.sgcc.entity.event.LineUpEventEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.LineUpModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineUpService {

    @Autowired
    private LineUpEntity lineUpEntity;

    @Autowired
    private LineUpEventEntity lineUpEventEntity;

    public BasicInputDTO test() {
        BasicInputDTO lineUpInfoOutDTO = null;
        try {

            lineUpInfoOutDTO = new BasicInputDTO();
            lineUpInfoOutDTO.setData(new InputDataDTO());
        }catch (Exception e){
            e.printStackTrace();
        }

        return lineUpInfoOutDTO;
    }





    /**
     * 检查设备是否正常运行
     * @return
     */
    public Result heartBeat() {
        try {
            // TODO 调用心跳接口
            boolean flag = false;
            LineUpInfoOutDTO lineUpInfoOutDTO = lineUpEntity.heartBeat();
            if (lineUpInfoOutDTO.getCode().equals("200")){
                flag = true;
            }else {
                flag = false;
            }
            return Result.success(flag);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    /**
     * 线上排队
     * @param dto
     * @return
     */
    public Result onlineQueuing(OnlineQueuingInputDTO dto) {
        try {
            // TODO 调用心跳接口
            LineUpInfoOutDTO lineUpInfoOutDTO = lineUpEntity.heartBeat();
            if (lineUpInfoOutDTO.getCode().equals("200")){
                // TODO 调用线上排队接口
                BasicInputDTO basicInputDTO = new BasicInputDTO();


                LineUpInfoOutDTO lineUpDTO = lineUpEntity.onlineQueuing();
                LineUpModel model = new LineUpModel();
                LineUpDao lineUpDao = model.addLineUpTrans(lineUpDTO.getData(),dto);
                lineUpEventEntity.addLineUp(lineUpDao);
                return Result.success(lineUpDTO.getData());
            }else {
                return Result.failure(TopErrorCode.DEVICE_EXCEPTION);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }



    /**
     * 排队查询
     * @param dto
     * @return
     */
    public Result lineUpQuery(LineUpQueryInputDTO dto) {

        try {
            // TODO 调用心跳接口
            LineUpInfoOutDTO lineUpInfoOutDTO = lineUpEntity.heartBeat();
            if (lineUpInfoOutDTO.getCode().equals("200")){
                // TODO 调用排队查询接口

                LineUpInfoOutDTO lineUpDTO = lineUpEntity.lineUpQuery();
                return Result.success(lineUpDTO.getData());
            }else {
                return Result.failure(TopErrorCode.DEVICE_EXCEPTION);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }




}
