package com.sgcc.service;

import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.RepairDao;
import com.sgcc.dto.RepairEditDTO;
import com.sgcc.dto.RepairSubmitDTO;
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
     * 查询故障报修订单列表
     * @return
     */
    public Result findRepairOrderList(String openId){
        if (Strings.isNullOrEmpty(openId))
            return Result.failure(TopErrorCode.ZERO_OBJ);

        try {
            List<RepairDao> repairDaos = repairQueryEntity.findRepairOrderByOpenId(openId);
            RepairModel repairModel = new RepairModel();
            repairModel.queryByOpenIdTransform(repairDaos);
            if (repairModel.getOrderDTOS().size() > 0){
                return Result.success(repairModel.getOrderDTOS());
            }else {
                return Result.failure(TopErrorCode.NO_DATAS);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    /**
     * 查询所有故障报修订单信息
     * @return
     */
    public Result findRepairOrderAll(){

        try {
            List<RepairDao> repairDaos = repairQueryEntity.findRepairOrderAll();
            RepairModel repairModel = new RepairModel(repairDaos);
            repairModel.queryAllTransform();
            if (repairModel.getRepairViewDTOS().size() > 0){
                return Result.success(repairModel.getRepairViewDTOS());
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
    public Result findRepairOrderById(String repairId){

        try {
            RepairDao repairDao = repairQueryEntity.findRepairOrderById(repairId);
            RepairModel repairModel = new RepairModel(repairDao);
            repairModel.queryByIdTransform();
            if (repairModel.getRepairViewDTO() != null){
                return Result.success(repairModel.getRepairViewDTO());
            }else {
                return Result.failure(TopErrorCode.NO_DATAS);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * 新增故障报修订单
     * @param
     * @return
     */
    public Result addRepairOrder(RepairSubmitDTO dto){
        if (dto == null)
            return Result.failure(TopErrorCode.ZERO_OBJ);

        try {
            RepairModel model = new RepairModel();
            model.addRepairTransform(dto);
            int count = repairEventEntity.addRepairOrder(model.getRepairDao());
            if (count > 0){
                return Result.success("新增成功！");
            }else {
                return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * 修改故障报修订单
     * @param
     * @return
     */
    public Result updateRepairOrder(RepairEditDTO dto){

        if (dto == null)
            return Result.failure(TopErrorCode.ZERO_OBJ);

        try {
            RepairModel model = new RepairModel();
            model.updateRepairTransform(dto);
            int count = repairEventEntity.updateRepairOrder(model.getRepairDao());
            if (count > 0){
                return Result.success("修改成功！");
            }else {
                return Result.failure(TopErrorCode.GENERAL_ERR);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * 删除故障报修订单
     * @param
     * @return
     */
    public Result delRepairOrder(List<String> ids){
        if (ids.size() <= 0)
            return Result.failure(TopErrorCode.ZERO_OBJ);

        try {
            int count = repairEventEntity.delRepairOrder(ids);
            if (count > 0){
                return Result.success("删除成功！");
            }else {
                return Result.failure(TopErrorCode.GENERAL_ERR);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }



}
