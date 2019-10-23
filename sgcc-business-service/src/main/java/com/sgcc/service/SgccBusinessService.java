package com.sgcc.service;

import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.commerce.Entity.Event.CommerceIncreaseCapacityEventEntity;
import com.sgcc.commerce.Entity.Query.CommerceIncreaseCapacityQueryEntity;
import com.sgcc.commerce.dao.CommerceIncreaseCapacityDao;
import com.sgcc.commerce.dto.CommerceIncreaseCapacityDTO;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.inhabitant.Entity.Event.InhabitantIncreaseCapacityEventEntity;
import com.sgcc.inhabitant.Entity.Event.InhabitantRenameEventEntity;
import com.sgcc.inhabitant.Entity.Query.InhabitantIncreaseCapacityQueryEntity;
import com.sgcc.inhabitant.Entity.Query.InhabitantRenameQueryEntity;
import com.sgcc.inhabitant.Model.InhabitantModel;
import com.sgcc.inhabitant.dao.InhabitantRenameDao;
import com.sgcc.inhabitant.dto.InhabitantIncreaseCapacityDTO;
import com.sgcc.inhabitant.dto.InhabitantRenameDTO;
import com.sgcc.inhabitant.dto.InhabitantRenameOrderListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SgccBusinessService {

    @Autowired
    private InhabitantRenameEventEntity inhabitantRenameEventEntity;

    @Autowired
    private InhabitantRenameQueryEntity inhabitantRenameQueryEntity;

    @Autowired
    private InhabitantIncreaseCapacityEventEntity inhabitantIncreaseCapacityEventEntity;

    @Autowired
    private InhabitantIncreaseCapacityQueryEntity inhabitantIncreaseCapacityQueryEntity;

    @Autowired
    private CommerceIncreaseCapacityEventEntity commerceIncreaseCapacityEventEntity;

    @Autowired
    private CommerceIncreaseCapacityQueryEntity commerceIncreaseCapacityQueryEntity;


    /**
     *删除个体增容订单
     * @param ids
     * @return
     */
    public Result delIncreaseCapacityOrders(List<String> ids){

        if (ids.size() <= 0)
            return Result.failure(TopErrorCode.NO_DATAS);

        try {
            commerceIncreaseCapacityEventEntity.delIncreaseCapacityOrder(ids);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
        }
    }


    /**
     * 修改个体增容订单
     * @param capacity
     * @param orderNo
     * @return
     */
    public Result updateIncreaseCapacityOrders(Double capacity, String orderNo){

        if (capacity == null || Strings.isNullOrEmpty(orderNo))
            return Result.failure(TopErrorCode.NO_DATAS);

        try {

            int count = commerceIncreaseCapacityEventEntity.updateIncreaseCapacityOrder(capacity,orderNo);
            if (count > 0){
                return Result.success("修改成功");
            }else {
                return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
        }
    }


    /**
     * 新增个体增容订单
     * @param dto
     * @param openId
     * @return
     */
    public Result addIncreaseCapacityOrders(CommerceIncreaseCapacityDTO dto, String openId){

        if (dto == null)
            return Result.failure(TopErrorCode.NO_DATAS);

        try {

            InhabitantModel inhabitantModel = new InhabitantModel(openId,dto);
            inhabitantModel.insertIncreaseCapacityByGeTransform();
            int count = commerceIncreaseCapacityEventEntity.addIncreaseCapacityOrder(
                    inhabitantModel.getCommerceIncreaseCapacityDao());
            if (count > 0){
                return Result.success("新增成功");
            }else {
                return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
        }
    }


    /**
     *根据openId查询所有个体增容提交单
     * @param openId
     * @return
     */
    public Result queryIncreaseCapacityAll(String openId){

        if (Strings.isNullOrEmpty(openId))
            return Result.failure(TopErrorCode.NO_DATAS);
        try {

            List<CommerceIncreaseCapacityDao> daos = commerceIncreaseCapacityQueryEntity.
                    findIncreaseCapacityOrderList(openId);

            InhabitantModel inhabitantModel = new InhabitantModel(openId,daos);
            inhabitantModel.queryIncreaseCapacityByGeTransform();
            return Result.success(inhabitantModel.getCommerceIncreaseCapacityDTOS());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.NO_DATAS);
        }



    }




    /**
     * 新增增容订单
     * @param dto
     * @param openId
     * @return
     */
    public Result addIncreaseCapacityOrder(InhabitantIncreaseCapacityDTO dto,String openId){

        if (Strings.isNullOrEmpty(dto.getHouseId()) ||
                Strings.isNullOrEmpty(dto.getContactTel()) ||
                Strings.isNullOrEmpty(dto.getName()) ||
                Strings.isNullOrEmpty(dto.getIdcard()) ||
                Strings.isNullOrEmpty(dto.getAplicant()) ||
                dto.getCurrentCapacity() == null)
            return Result.failure(TopErrorCode.NO_DATAS);

        try {

            InhabitantModel inhabitantModel = new InhabitantModel(dto,openId);
            inhabitantModel.insertIncreaseCapacityTransform();
            int count = inhabitantIncreaseCapacityEventEntity.addIncreaseCapacityOrder(
                    inhabitantModel.getInhabitantIncreaseCapacityDao());
            if (count > 0){
                return Result.success("新增成功");
            }else {
                return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
        }
    }


    /**
     * 修改增容订单列表
     * @return
     */
    public Result updateRenameOrder(String infoId,String name){

        if (Strings.isNullOrEmpty(infoId) || Strings.isNullOrEmpty(name))
            return Result.failure(TopErrorCode.NO_DATAS);

        try {
            inhabitantRenameEventEntity.updateRenameOrder(infoId,name);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }



    /**
     *删除更名过户订单
     * @param ids
     * @return
     */
    public Result delRenameOrder(List<String> ids){

        if (ids.size() <= 0)
            return Result.failure(TopErrorCode.NO_DATAS);

        try {
            inhabitantRenameEventEntity.delRenameOrder(ids);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
        }
    }



    /**
     * 新增更名过户订单
     * @param inhabitantRenameDTO
     * @param openId
     * @return
     */
    public Result addRenameOrder(InhabitantRenameDTO inhabitantRenameDTO,String openId){

        if (Strings.isNullOrEmpty(inhabitantRenameDTO.getContactTel()) ||
                Strings.isNullOrEmpty(inhabitantRenameDTO.getHouseId()) ||
                Strings.isNullOrEmpty(inhabitantRenameDTO.getHouseName()) ||
                Strings.isNullOrEmpty(inhabitantRenameDTO.getIdCard()) ||
                inhabitantRenameDTO.getChange() == null)
            return Result.failure(TopErrorCode.NO_DATAS);

        try {

            InhabitantModel inhabitantModel = new InhabitantModel(inhabitantRenameDTO,openId);
            inhabitantModel.insertRenameTransform();
            int count = inhabitantRenameEventEntity.addRenameOrder(inhabitantModel.getInhabitantRenameDao());
            if (count > 0){
                return Result.success("新增成功");
            }else {
                return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
        }
    }

    /**
     * 查询更名过户订单
     * @param
     * @return
     */
    public Result queryRenameOrder(){
        try {
            List<InhabitantRenameDao> inhabitantRenameDaos = inhabitantRenameQueryEntity.queryAll();
            InhabitantModel inhabitantModel = new InhabitantModel(inhabitantRenameDaos);
            inhabitantModel.queryRenameTransform();
            return Result.success(inhabitantModel.getInhabitantRenameDTOS());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.NO_DATAS);
        }
    }

    /**
     * 查询当前用户所有更名过户订单列表
     * @return
     */
    public Result queryRenameOrderList(String orderNo){

        if (Strings.isNullOrEmpty(orderNo))
            return Result.failure(TopErrorCode.NO_DATAS);

        List<InhabitantRenameOrderListDTO> dtos = new ArrayList<>();
        InhabitantRenameOrderListDTO dto1 = new InhabitantRenameOrderListDTO(
                UUID.randomUUID().toString().replace("-","6"),
                "0481234590",
                "刘德华",
                "高新区天府五街美年广场A座1144号",
                "已提交"
        );

        InhabitantRenameOrderListDTO dto2 = new InhabitantRenameOrderListDTO(
                UUID.randomUUID().toString().replace("-","6"),
                "0485432190",
                "梁朝伟",
                "高新区天府五街美年广场A座1144号",
                "已提交"
        );
        dtos.add(dto1);
        dtos.add(dto2);

        return Result.success(dtos);
    }


}
