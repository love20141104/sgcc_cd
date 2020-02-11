package com.sgcc.service;

import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.LineUpDao;
import com.sgcc.dto.*;
import com.sgcc.entity.LineUpEntity;
import com.sgcc.entity.event.LineUpEventEntity;
import com.sgcc.entity.query.LineUpQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.LineUpModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineUpService {

    @Autowired
    private LineUpEntity lineUpEntity;

    @Autowired
    private LineUpEventEntity lineUpEventEntity;

    @Autowired
    private LineUpQueryEntity lineUpQueryEntity;



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
            boolean flag = false;
            LineUpModel model = new LineUpModel();
            BasicInputDTO basicInputDTO = model.heartBeatTrans();
            LineUpInfoOutDTO lineUpInfoOutDTO = lineUpEntity.heartBeatAndOnlineQueuingAndLineUpQuery(basicInputDTO);
            if (lineUpInfoOutDTO.getCode().equals("1")){
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


    public Result checkLineUp(String openId) {
        try {
            boolean flag = false;
            LineUpModel model = new LineUpModel();
            List<LineUpDao> lineUpDaos = lineUpQueryEntity.getLineUpByOpenId(openId);
            List<LineUpDao> daos = model.getLineUpByOpenIdTrans(lineUpDaos);
            if (daos.size() < 1){
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
            LineUpModel model = new LineUpModel();
            List<LineUpDao> lineUpDaos = lineUpQueryEntity.getLineUpByOpenId(dto.getUserOpenId());
            List<LineUpDao> daos = model.getLineUpByOpenIdTrans(lineUpDaos);
            if (daos.size() == 1 || daos.size() > 1)
                return Result.failure(TopErrorCode.LINE_UP);
            // 调用心跳接口
            LineUpInfoOutDTO lineUpInfoOutDTO = lineUpEntity.heartBeatAndOnlineQueuingAndLineUpQuery(model.heartBeatTrans());
            if (lineUpInfoOutDTO.getCode().equals("1")){
                BasicInputDTO basicInputDTO = model.onlineQueuingTrans(dto);
                LineUpInfoOutDTO lineUpDTO = lineUpEntity.heartBeatAndOnlineQueuingAndLineUpQuery(basicInputDTO);
                if (lineUpDTO.getCode().equals("1")){
                    LineUpDao lineUpDao = model.addLineUpTrans(lineUpDTO.getData(),dto);
                    lineUpEventEntity.addLineUp(lineUpDao);
                }
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
            // 调用心跳接口
            LineUpModel model = new LineUpModel();
            LineUpInfoOutDTO lineUpInfoOutDTO = lineUpEntity.heartBeatAndOnlineQueuingAndLineUpQuery(model.heartBeatTrans());
            if (lineUpInfoOutDTO.getCode().equals("1")){
                BasicInputDTO basicInputDTO = model.lineUpQueryTrans(dto);
                LineUpInfoOutDTO lineUpDTO = lineUpEntity.heartBeatAndOnlineQueuingAndLineUpQuery(basicInputDTO);
                if (lineUpDTO.getCode().equals("1")){
                    return Result.success(lineUpDTO.getData());
                }else {
                    return Result.failure(TopErrorCode.DEVICE_EXCEPTION);
                }
            }else {
                return Result.failure(TopErrorCode.DEVICE_EXCEPTION);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    /**
     * 查询所有排号记录
     * @return
     */
    public Result getAllRecords(){
        try {
            LineUpModel model = new LineUpModel();
            List<LineUpDao> daos = lineUpQueryEntity.getAllRecords();
            List<LineUpViewDTO> lineUpViewDTO = model.getAllRecordsTrans(daos);
            return Result.success(lineUpViewDTO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     *根据openId查询排队号码
     * @return
     */
    public Result getLineUpNoByOpenId(String openId){
        try {
            List<LineUpDao> daos = lineUpQueryEntity.getLineUpNoByOpenId(openId);
            LineUpQueryInputDTO lineUpQueryInputDTO = new LineUpQueryInputDTO(daos.get(0).getLineUpNo());
            return Result.success(lineUpQueryInputDTO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


}
