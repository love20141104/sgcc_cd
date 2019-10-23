package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.commerce.dto.CommerceNewDTO;
import com.sgcc.commerce.dto.CommerceNewSubmitDTO;
import com.sgcc.commerce.dto.DeleteDTO;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.inhabitant.dto.InhabitantNewDTO;
import com.sgcc.inhabitant.dto.InhabitantSubmitDTO;
import com.sgcc.service.SgccBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

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

}
