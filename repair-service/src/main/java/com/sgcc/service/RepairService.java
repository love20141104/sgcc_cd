package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.entity.event.RepairEventEntity;
import com.sgcc.entity.query.RepairQueryEntity;
import com.sgcc.exception.TopErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

        return Result.success();
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
