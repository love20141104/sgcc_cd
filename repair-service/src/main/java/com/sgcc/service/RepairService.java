package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dao.RepairDao;
import com.sgcc.dto.RepairViewDTO;
import com.sgcc.entity.event.RepairEventEntity;
import com.sgcc.entity.query.RepairQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.RepairModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {

    @Autowired
    private RepairQueryEntity repairQueryEntity;

    @Autowired
    private RepairEventEntity repairEventEntity;


    /**
     * 查询所有故障报修订单信息
     * @return
     */
    public Result findRepairOrderAll(){

        try {
            List<RepairDao> repairDaos = repairQueryEntity.findRepairOrderAll();
            RepairModel repairModel = new RepairModel();
            List<RepairViewDTO> repairViewDTOS = repairModel.queryAllTransform(repairDaos);
            if (repairViewDTOS.size() > 0){
                return Result.success(repairViewDTOS);
            }else {
                return Result.failure(TopErrorCode.NO_DATAS);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * 根据repairId查询故障报修订单信息
     * @param
     * @return
     */
    public Result findRepairOrderById(){

        return Result.success();
    }

    /**
     * 新增故障报修订单
     * @param
     * @return
     */
    public Result addRepairOrder(){

        return Result.success();
    }

    /**
     * 修改故障报修订单
     * @param
     * @return
     */
    public Result updateRepairOrder(){

        return Result.success();
    }

    /**
     * 删除故障报修订单
     * @param
     * @return
     */
    public Result delRepairOrder(){

        return Result.success();
    }



}
