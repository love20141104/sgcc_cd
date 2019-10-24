package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.commerce.dto.*;
import com.sgcc.inhabitant.dto.InhabitantNewDTO;
import com.sgcc.inhabitant.dto.InhabitantRenameSubmitDTO;
import com.sgcc.inhabitant.dto.InhabitantRenameUpdateDTO;
import com.sgcc.inhabitant.dto.InhabitantSubmitDTO;
import com.sgcc.service.SgccBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "", tags = "电力业务办理接口")
@RestController
@RequestMapping(value = "/BusinessService")
@Controller
public class BusinessServiceController {
    @Autowired
    private SgccBusinessService sgccBusinessService;

//    @ApiOperation(value = "新装提交-居民和个体工商业", notes = "")
//    @PostMapping(value = "/NewInstall")
//    public Result SubmitNewInstall(@RequestBody Map<String,String> dto , @RequestParam("isCommerce") boolean isCommerce ) {
//        if( isCommerce ){
//            CommerceNewSubmitDTO obj = new CommerceNewSubmitDTO();
//            try{
//                BeanUtils.populate(obj, dto);
//            }catch (Exception e )
//            {
//
//            }
//            sgccBusinessService.SubmitCommerceNew(obj);
//            return Result.success();
//        }
//        else
//        {
//
//        }
//        return Result.failure(TopErrorCode.PARAMETER_ERR);
//    }






    // -------------------------居民更名过户--------------------------------------

    @ApiOperation(value = "更名过户-新增", notes = "")
    @PostMapping(value = "/renameAndTransfer/inhabitant/{openId}")
    public Result addRenameAndTransferOrder(@PathVariable String openId,
                                            @RequestBody InhabitantRenameSubmitDTO inhabitantRenameSubmitDTO) {
        return  sgccBusinessService.addRenameOrder(inhabitantRenameSubmitDTO,openId);
    }

    @ApiOperation(value = "更名过户-查询", notes = "")
    @GetMapping(value = "/renameAndTransfer/inhabitant/")
    public Result queryRenameAndTransferAll() {
        return  sgccBusinessService.queryRenameAll();
    }

    @ApiOperation(value = "更名过户-查询", notes = "")
    @GetMapping(value = "/renameAndTransfer/inhabitant/{infoId}")
    public Result queryRenameAndTransferOrderList(@PathVariable String infoId) {
        return  sgccBusinessService.queryRenameByInfoId(infoId);
    }

    @ApiOperation(value = "更名过户-删除", notes = "")
    @DeleteMapping(value = "/renameAndTransfer/inhabitant")
    public Result delRenameAndTransferOrder(@RequestBody List<String> ids) {
        return  sgccBusinessService.delRenameOrder(ids);
    }

    @ApiOperation(value = "更名过户-修改", notes = "")
    @PutMapping(value = "/renameAndTransfer/inhabitant/{infoId}")
    public Result updateIncreaseCapacity(@PathVariable String infoId,@RequestBody InhabitantRenameUpdateDTO dto) {
        return  sgccBusinessService.updateRenameOrder(infoId,dto);
    }


    // -------------------------个体工商业增容--------------------------------------

    @ApiOperation(value = "增容提交-个体工商业", notes = "")
    @PostMapping(value = "/increaseCapacity/order/{openId}")
    public Result addIncreaseCapacity(@PathVariable String openId,@RequestBody CommerceIncreaseCapacitySubmitDTO dto) {
        return  sgccBusinessService.addIncreaseCapacityOrders(dto,openId);
    }

    @ApiOperation(value = "增容查询-个体工商业", notes = "")
    @GetMapping(value = "/increaseCapacity/commerce/{id}")
    public Result queryIncreaseCapacityListById(@PathVariable String id) {
        return  sgccBusinessService.queryIncreaseCapacityAllById(id);
    }

    @ApiOperation(value = "增容查询-个体工商业", notes = "")
    @GetMapping(value = "/increaseCapacity/commerce")
    public Result queryIncreaseCapacityList() {
        return  sgccBusinessService.findIncreaseCapacityAll();
    }

    @ApiOperation(value = "增容修改-个体工商业", notes = "")
    @PutMapping(value = "/increaseCapacity/commerce/{id}")
    public Result updateIncreaseCapacityForGeOrderList(@PathVariable String id,
                                                       @RequestBody CommerceIncreaseCapacityUpdateDTO dto) {
        return  sgccBusinessService.updateIncreaseCapacityOrders(dto,id);
    }

    @ApiOperation(value = "增容删除-个体工商业", notes = "")
    @DeleteMapping(value = "/increaseCapacity/commerce")
    public Result updateIncreaseCapacityForGeOrderList(@RequestBody List<String> ids) {
        return  sgccBusinessService.delIncreaseCapacityOrders(ids);
    }


    // -------------------------个体工商业新装--------------------------------------
    // -------------------------个体工商业新装--------------------------------------
    @ApiOperation(value = "新装提交-个体工商业", notes = "")
    @PostMapping(value = "/NewInstall/Commerce")
    public Result SubmitCommerceNewInstall(@RequestBody CommerceNewSubmitDTO dto ) {
        sgccBusinessService.SubmitCommerceNew(dto);
        return Result.success();
    }
    @ApiOperation(value = "新装更新-个体工商业", notes = "")
    @PutMapping(value = "/NewInstall/Commerce")
    public Result UpdateCommerceNewInstall(@RequestBody CommerceNewDTO dto ) {
        sgccBusinessService.UpdateCommerceNew(dto);
        return Result.success();
    }
    @ApiOperation(value = "新装查询-个体工商业", notes = "")
    @GetMapping(value = "/NewInstall/Commerce")
    public Result GetCommerceNewInstalls() {
        return Result.success( sgccBusinessService.GetAllCommerceNewRecords() );
    }
    @ApiOperation(value = "新装查询-个体工商业", notes = "")
    @GetMapping(value = "/NewInstall/Commerce/{id}")
    public Result GetCommerceNewInstall(@PathVariable("id") String id ) {
        return Result.success(sgccBusinessService.GetCommerceNewRecord(id));
    }
    @ApiOperation(value = "新装删除-个体工商业", notes = "")
    @PostMapping(value = "/NewInstall/Commerce/Deletes")
    public Result DeleteCommerceNewInstalls(@RequestBody DeleteDTO dto ) {
        sgccBusinessService.DeleteCommerceNewRecors(dto);
        return Result.success();
    }

    // -------------------------居民新装--------------------------------------
    // -------------------------居民新装--------------------------------------
    @ApiOperation(value = "新装提交-居民", notes = "")
    @PostMapping(value = "/NewInstall/Inhabitant")
    public Result SubmitInhabitantNewInstall(@RequestBody InhabitantSubmitDTO dto ) {
        sgccBusinessService.SubmitInhabitantNew(dto);
        return Result.success();
    }

    @ApiOperation(value = "新装更新-居民", notes = "")
    @PutMapping(value = "/NewInstall/Inhabitant")
    public Result UpdateInhabitantNewInstall(@RequestBody InhabitantNewDTO dto ) {
        sgccBusinessService.UpdateInhabitantNew(dto);
        return Result.success();
    }

    @ApiOperation(value = "新装查询-居民", notes = "")
    @GetMapping(value = "/NewInstall/Inhabitant")
    public Result GetInhabitantNewInstalls() {
        return Result.success( sgccBusinessService.GetAllInhabitantNewRecords() );
    }

    @ApiOperation(value = "新装查询-居民", notes = "")
    @GetMapping(value = "/NewInstall/Inhabitant/{id}")
    public Result GetInhabitantNewInstall(@PathVariable("id") String id ) {
        return Result.success(sgccBusinessService.GetInhabitantNewRecord(id));
    }

    @ApiOperation(value = "新装删除-居民", notes = "")
    @PostMapping(value = "/NewInstall/Inhabitant/Deletes")
    public Result DeleteInhabitantNewInstalls(@RequestBody DeleteDTO dto ) {
        sgccBusinessService.DeleteInhabitantNewRecors(dto);
        return Result.success();
    }
    // -------------------------税票变更--------------------------------------
    // -------------------------税票变更--------------------------------------
    @ApiOperation(value = "税票变更提交", notes = "")
    @PostMapping(value = "/CommerceChangeTaxInfo")
    public Result SubmitCommerceChangeTaxInfo(@RequestBody CommerceChangeTaxInfoSubmitDTO dto ) {
        sgccBusinessService.SubmitCommerceChangeTaxInfo(dto);
        return Result.success();
    }

    @ApiOperation(value = "税票变更更新", notes = "")
    @PutMapping(value = "/CommerceChangeTaxInfo")
    public Result UpdateCommerceChangeTaxInfo(@RequestBody CommerceChangeTaxInfoDTO dto ) {
        sgccBusinessService.UpdateCommerceChangeTaxInfo(dto);
        return Result.success();
    }

    @ApiOperation(value = "税票变更查询", notes = "")
    @GetMapping(value = "/CommerceChangeTaxInfo")
    public Result GetCommerceChangeTaxInfos() {
        return Result.success( sgccBusinessService.GetAllCommerceChangeTaxInfoRecords() );
    }

    @ApiOperation(value = "税票变更查询", notes = "")
    @GetMapping(value = "/CommerceChangeTaxInfo/{id}")
    public Result GetCommerceChangeTaxInfo(@PathVariable("id") String id ) {
        return Result.success(sgccBusinessService.GetCommerceChangeTaxInfoRecord(id));
    }

    @ApiOperation(value = "税票变更删除", notes = "")
    @PostMapping(value = "/CommerceChangeTaxInfo/Deletes")
    public Result DeleteCommerceChangeTaxInfos(@RequestBody DeleteDTO dto ) {
        sgccBusinessService.DeleteCommerceChangeTaxInfoRecors(dto);
        return Result.success();
    }
}
