package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.*;
import com.sgcc.service.BusinessGuideService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/businessGuide")
@Api(value = "", tags = "业务指南接口")
public class BusinessGuideController {
    @Autowired
    private BusinessGuideService businessGuideService;

    /*@ApiOperation(value = "查询业务指南列表", notes = "")
    @GetMapping(value = "/businessGuides")
    public Result find( String cid) {
        return businessGuideService.getBusinessGuideList(cid);
    }*/

    @ApiOperation(value = "查询业务指南列表", notes = "")
    @GetMapping(value = "/List")
    public Result findBusinessGuides( String cid,String title) {
        return businessGuideService.getList(cid,title);
    }

    @ApiOperation(value = "查询业务指南列表", notes = "")
    @GetMapping(value = "/{id}")
    public Result findBusinessGuides(@PathVariable String id) {
        return businessGuideService.getBusinessGuide(id);
    }

    @ApiOperation(value = "后台查询业务指南列表", notes = "")
    @GetMapping(value = "/backstageBusinessGuides")
    public Result findBackstage( String cid) {
        return businessGuideService.getBackstageBusinessGuideList(cid);
    }

    @ApiOperation(value = "修改业务指南", notes = "")
    @PutMapping
    public Result update(@RequestBody BusinessGuideDto businessGuideDto) {
        return businessGuideService.updateBusinessGuide(businessGuideDto);
    }
    @ApiOperation(value = "添加业务指南", notes = "")
    @PostMapping
    public Result save(@RequestBody BusinessGuideSubmitDto businessGuideSubmitDto) {
        return businessGuideService.saveBusinessGuide(businessGuideSubmitDto);
    }

    @ApiOperation(value = "删除业务指南", notes = "")
    @DeleteMapping("")
    public Result delete(@RequestBody BusinessGuideDeleteDto businessGuideDeleteDto) {
        return businessGuideService.deleteBusinessGuide(businessGuideDeleteDto);
    }


    //分类
    @ApiOperation(value = "查询业务指南列表分类", notes = "")
    @GetMapping(value = "/categorys")
    public Result findCategory() {
        return businessGuideService.getBusinessCategoryList();
    }

    @ApiOperation(value = "修改业务指南分类", notes = "")
    @PutMapping("/category")
    public Result updateCategory(@RequestBody BusinessCategoryDto businessCategoryDto) {
        return businessGuideService.updateBusinessCategory(businessCategoryDto);
    }
    @ApiOperation(value = "添加业务指南分类", notes = "")
    @PostMapping("/category")
    public Result saveCategory(@RequestBody BusinessCategorySubmitDto businessCategorySubmitDto) {
        return businessGuideService.saveBusinessCategory(businessCategorySubmitDto);
    }

    @ApiOperation(value = "删除业务指南分类", notes = "")
    @DeleteMapping("/category")
    public Result deleteCategory(@RequestBody BusinessCategoryDeleteDto businessCategoryDeleteDto) {
        return businessGuideService.deleteBusinessCategory(businessCategoryDeleteDto);
    }
}
