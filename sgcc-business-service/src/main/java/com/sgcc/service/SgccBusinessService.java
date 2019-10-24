package com.sgcc.service;

import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.commerce.Entity.Event.CommerceIncreaseCapacityEventEntity;
import com.sgcc.commerce.Entity.Query.CommerceIncreaseCapacityQueryEntity;
import com.sgcc.commerce.dao.CommerceIncreaseCapacityDao;
import com.sgcc.commerce.dto.CommerceIncreaseCapacitySubmitDTO;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.inhabitant.Entity.Event.InhabitantRenameEventEntity;
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
import com.sgcc.inhabitant.dao.InhabitantNewDao;
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
    private CommerceIncreaseCapacityEventEntity commerceIncreaseCapacityEventEntity;

    @Autowired
    private CommerceIncreaseCapacityQueryEntity commerceIncreaseCapacityQueryEntity;


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
     * @param dto
     * @param id
     * @return
     */
    public Result updateIncreaseCapacityOrders(CommerceIncreaseCapacityUpdateDTO dto, String id){

        if (dto == null || Strings.isNullOrEmpty(id))
            return Result.failure(TopErrorCode.NO_DATAS);

        try {
            CommerceModel commerceModel = new CommerceModel();
            CommerceIncreaseCapacityDao commerceIncreaseCapacityDao =
                    commerceModel.updateIncreaseCapacityTransform(dto,id);
            commerceIncreaseCapacityDao.setId(id);
            int count = commerceIncreaseCapacityEventEntity.updateIncreaseCapacityOrder(commerceIncreaseCapacityDao);
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
    public Result addIncreaseCapacityOrders(CommerceIncreaseCapacitySubmitDTO dto, String openId){

        if (dto == null)
            return Result.failure(TopErrorCode.NO_DATAS);

        try {

            CommerceModel commerceModel = new CommerceModel(openId,dto);
            commerceModel.insertIncreaseCapacityByGeTransform();
            int count = commerceIncreaseCapacityEventEntity.addIncreaseCapacityOrder(
                    commerceModel.getCommerceIncreaseCapacityDao());
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
     *根据id查询所有个体增容提交单
     * @param id
     * @return
     */
    public Result queryIncreaseCapacityAllById(String id){

        if (Strings.isNullOrEmpty(id))
            return Result.failure(TopErrorCode.NO_DATAS);
        try {

            List<CommerceIncreaseCapacityDao> daos = commerceIncreaseCapacityQueryEntity.
                    findIncreaseCapacityOrderList(id);

            CommerceModel commerceModel = new CommerceModel(null,daos);
            commerceModel.queryIncreaseCapacityByGeTransform();
            return Result.success(commerceModel.getCommerceIncreaseCapacitySubmitDTOS());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.NO_DATAS);
        }



    }

    /**
     查询所有个体增容提交单
     * @return
     */
    public Result findIncreaseCapacityAll(){

        try {

            List<CommerceIncreaseCapacityDao> daos = commerceIncreaseCapacityQueryEntity.findIncreaseCapacityAll();
            CommerceModel commerceModel = new CommerceModel(null,daos);
            commerceModel.queryIncreaseCapacityByGeTransform();
            return Result.success(commerceModel.getCommerceIncreaseCapacitySubmitDTOS());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.NO_DATAS);
        }



    }

    /****************************************************************************************************************/

    /**
     * 新增居民增容订单
     * @param dto
     * @param openId
     * @return
     */
//    public Result addIncreaseCapacityOrder(InhabitantIncreaseCapacityDTO dto,String openId){
//
//        if (Strings.isNullOrEmpty(dto.getHouseId()) ||
//                Strings.isNullOrEmpty(dto.getContactTel()) ||
//                Strings.isNullOrEmpty(dto.getName()) ||
//                Strings.isNullOrEmpty(dto.getIdcard()) ||
//                Strings.isNullOrEmpty(dto.getAplicant()) ||
//                dto.getCurrentCapacity() == null)
//            return Result.failure(TopErrorCode.NO_DATAS);
//
//        try {
//
//            InhabitantModel inhabitantModel = new InhabitantModel(dto,openId);
//            inhabitantModel.insertIncreaseCapacityTransform();
//            int count = inhabitantIncreaseCapacityEventEntity.addIncreaseCapacityOrder(
//                    inhabitantModel.getInhabitantIncreaseCapacityDao());
//            if (count > 0){
//                return Result.success("新增成功");
//            }else {
//                return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
//        }
//    }


    /**
     * 修改更名过户订单列表
     * @return
     */
    public Result updateRenameOrder(String infoId, InhabitantRenameUpdateDTO dto){

        if (Strings.isNullOrEmpty(infoId) || dto == null)
            return Result.failure(TopErrorCode.NO_DATAS);

        try {
            InhabitantModel model = new InhabitantModel();
            InhabitantRenameDao dao = model.updateRenameTransform(dto,infoId);
            int count = inhabitantRenameEventEntity.updateRenameOrder(dao);
            if (count > 0){
                return Result.success();
            }else {
                return Result.failure(TopErrorCode.GENERAL_ERR);
            }

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
     * @param inhabitantRenameSubmitDTO
     * @param openId
     * @return
     */
    public Result addRenameOrder(InhabitantRenameSubmitDTO inhabitantRenameSubmitDTO, String openId){

        if (Strings.isNullOrEmpty(inhabitantRenameSubmitDTO.getContactTel()) ||
                Strings.isNullOrEmpty(inhabitantRenameSubmitDTO.getHouseId()) ||
                Strings.isNullOrEmpty(inhabitantRenameSubmitDTO.getHouseName()) ||
                Strings.isNullOrEmpty(inhabitantRenameSubmitDTO.getIdCard()) ||
                inhabitantRenameSubmitDTO.getChange() == null)
            return Result.failure(TopErrorCode.NO_DATAS);

        try {

            InhabitantModel inhabitantModel = new InhabitantModel(inhabitantRenameSubmitDTO,openId);
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
     * 查询所有更名过户订单
     * @param
     * @return
     */
    public Result queryRenameAll(){
        try {
            List<InhabitantRenameDao> inhabitantRenameDaos = inhabitantRenameQueryEntity.queryAll();
            InhabitantModel inhabitantModel = new InhabitantModel(inhabitantRenameDaos);
            inhabitantModel.queryRenameAllTransform();
            return Result.success(inhabitantModel.getInhabitantRenameDetailDTOs());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.NO_DATAS);
        }
    }

    /**
     * 根据infoId查询更名过户表单
     * @param infoId
     * @return
     */
    public Result queryRenameByInfoId(String infoId){

        if (Strings.isNullOrEmpty(infoId))
            return Result.failure(TopErrorCode.NO_DATAS);

        try {
            List<InhabitantRenameDao> daos = inhabitantRenameQueryEntity.queryRenameByInfoId(infoId);
            InhabitantModel inhabitantModel = new InhabitantModel(daos);
            inhabitantModel.queryRenameByIdTransform();
            return Result.success(inhabitantModel.getInhabitantRenameSubmitDTOS());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.NO_DATAS);
        }

    }



    /**
     * 查询当前用户所有订单列表
     * @return
     */
    public Result queryOrderByOpenIdAll(String openId){

        if (Strings.isNullOrEmpty(openId))
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
