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
import com.sgcc.inhabitant.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.sgcc.commerce.Entity.Event.CommerceChangeTaxInfoEventEntity;
import com.sgcc.commerce.Entity.Event.CommerceNewEventEntity;
import com.sgcc.commerce.Entity.Query.CommerceChangeTaxInfoQueryEntity;
import com.sgcc.commerce.Entity.Query.CommerceNewQueryEntity;
import com.sgcc.commerce.Model.CommerceModel;
import com.sgcc.commerce.dao.CommerceChangeTaxInfoDao;
import com.sgcc.commerce.dao.CommerceNewDao;
import com.sgcc.commerce.dto.*;
import com.sgcc.inhabitant.Entity.Event.InhabitantNewEventEntity;
import com.sgcc.inhabitant.Entity.Query.InhabitantNewQueryEntity;
import com.sgcc.inhabitant.Model.InhabitantModel;
import com.sgcc.inhabitant.dao.InhabitantNewDao;
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


    @Autowired
    private CommerceNewQueryEntity commerceNewQueryEntity;
    @Autowired
    private CommerceNewEventEntity commerceNewEventEntity;
    @Autowired
    private InhabitantNewEventEntity inhabitantNewEventEntity;
    @Autowired
    private InhabitantNewQueryEntity inhabitantNewQueryEntity;
    @Autowired
    private CommerceChangeTaxInfoEventEntity commerceChangeTaxInfoEventEntity;
    @Autowired
    private CommerceChangeTaxInfoQueryEntity commerceChangeTaxInfoQueryEntity;
//CommerceChangeTaxInfoEventEntity
    // -------------------------个体工商业新装--------------------------------------
    // 微信端用
    public void SubmitCommerceNew( CommerceNewSubmitDTO dto )
    {
        CommerceModel model = new CommerceModel();
        CommerceNewDao dao = model.CommerceNewSubmitDTO2Dao(dto);
        if( dao == null )
            return;
        commerceNewEventEntity.SaveCommerceNew(dao);
    }

    public void UpdateCommerceNew( CommerceNewDTO dto )
    {
        CommerceModel model = new CommerceModel();
        CommerceNewDao dao = model.CommerceNewDTO2Dao(dto);
        if( dao == null )
            return;
        commerceNewEventEntity.UpdateCommerceNew(dao);
    }

    public List<CommerceNewDTO> GetAllCommerceNewRecords()
    {
        List<CommerceNewDao> daos = commerceNewQueryEntity.GetAll();
        if( daos ==null || daos.size() < 1 )
            return null;
        CommerceModel model = new CommerceModel();
        return model.CommerceNewDaos2Dtos(daos);
    }

    public CommerceNewDTO GetCommerceNewRecord(String id)
    {
        CommerceNewDao dao = commerceNewQueryEntity.GetById(id);
        if( dao ==null )
            return null;
        CommerceModel model = new CommerceModel();
        return model.CommerceNewDao2DTO(dao);
    }

    public void DeleteCommerceNewRecors( DeleteDTO dto )
    {
        if( dto == null || dto.getIds() == null || dto.getIds().size() < 1)
            return;
        commerceNewEventEntity.Deletes(dto.getIds());
    }

    // -------------------------居民新装--------------------------------------
    // 微信端用
    public void SubmitInhabitantNew( InhabitantSubmitDTO dto )
    {
        InhabitantModel model = new InhabitantModel();
        InhabitantNewDao dao = model.InhabitantSubmitDTO2Dao(dto);
        if( dao == null )
            return;
        inhabitantNewEventEntity.SaveInhabitantNew(dao);
    }

    public void UpdateInhabitantNew( InhabitantNewDTO dto )
    {
        InhabitantModel model = new InhabitantModel();
        InhabitantNewDao dao = model.InhabitantNewDTO2Dao(dto);
        if( dao == null )
            return;
        inhabitantNewEventEntity.UpdateInhabitantNew(dao);
    }

    public List<InhabitantNewDTO> GetAllInhabitantNewRecords()
    {
        List<InhabitantNewDao> daos = inhabitantNewQueryEntity.GetAll();
        if( daos ==null || daos.size() < 1 )
            return null;
        InhabitantModel model = new InhabitantModel();
        return model.InhabitantNewDaos2Dtos(daos);
    }

    public InhabitantNewDTO GetInhabitantNewRecord(String id)
    {
        InhabitantNewDao dao = inhabitantNewQueryEntity.GetById(id);
        if( dao ==null )
            return null;
        InhabitantModel model = new InhabitantModel();
        return model.InhabitantNewDao2DTO(dao);
    }

    public void DeleteInhabitantNewRecors( DeleteDTO dto )
    {
        if( dto == null || dto.getIds() == null || dto.getIds().size() < 1)
            return;
        inhabitantNewEventEntity.Deletes(dto.getIds());
    }
    // -------------------------税票变更--------------------------------------
    // 微信端用
    public void SubmitCommerceChangeTaxInfo( CommerceChangeTaxInfoSubmitDTO dto )
    {
        CommerceModel model = new CommerceModel();
        CommerceChangeTaxInfoDao dao = model.CommerceChangeTaxInfoSubmitDTO2Dao(dto);
        if( dao == null )
            return;
        commerceChangeTaxInfoEventEntity.SaveCommerceChangeTaxInfo(dao);
    }

    public void UpdateCommerceChangeTaxInfo( CommerceChangeTaxInfoDTO dto )
    {
        CommerceModel model = new CommerceModel();
        CommerceChangeTaxInfoDao dao = model.CommerceChangeTaxInfoDTO2Dao(dto);
        if( dao == null )
            return;
        commerceChangeTaxInfoEventEntity.UpdateCommerceChangeTaxInfo(dao);
    }

    public List<CommerceChangeTaxInfoDTO> GetAllCommerceChangeTaxInfoRecords()
    {
        List<CommerceChangeTaxInfoDao> daos = commerceChangeTaxInfoQueryEntity.GetAll();
        if( daos ==null || daos.size() < 1 )
            return null;
        CommerceModel model = new CommerceModel();
        return model.CommerceChangeTaxInfoDaos2Dtos(daos);
    }

    public CommerceChangeTaxInfoDTO GetCommerceChangeTaxInfoRecord(String id)
    {
        CommerceChangeTaxInfoDao dao = commerceChangeTaxInfoQueryEntity.GetById(id);
        if( dao ==null )
            return null;
        CommerceModel model = new CommerceModel();
        return model.CommerceChangeTaxInfoDao2DTO(dao);
    }

    public void DeleteCommerceChangeTaxInfoRecors( DeleteDTO dto )
    {
        if( dto == null || dto.getIds() == null || dto.getIds().size() < 1)
            return;
        commerceChangeTaxInfoEventEntity.Deletes(dto.getIds());
    }
}
