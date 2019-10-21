package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.BusinessCategoryDto;
import com.sgcc.dto.BusinessGuideDto;
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

    @ApiOperation(value = "查询业务指南列表", notes = "")
    @GetMapping(value = "/businessGuides")
    public Result find(String cid) {
        return businessGuideService.getBusinessGuideList(cid);
    }

    @ApiOperation(value = "修改业务指南", notes = "")
    @PutMapping
    public Result update(BusinessGuideDto businessGuideDto) {
        return businessGuideService.updateBusinessGuide(businessGuideDto);
    }
    @ApiOperation(value = "添加业务指南", notes = "")
    @PostMapping
    public Result save(BusinessGuideDto businessGuideDto) {
        return businessGuideService.saveBusinessGuide(businessGuideDto);
    }

    @ApiOperation(value = "删除业务指南", notes = "")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id")String cid) {
        return businessGuideService.deleteBusinessGuide(cid);
    }


    //分类
    @ApiOperation(value = "查询业务指南列表分类", notes = "")
    @GetMapping(value = "/categorys")
    public Result findCategory() {
        return businessGuideService.getBusinessCategoryList();
    }

    @ApiOperation(value = "修改业务指南分类", notes = "")
    @PutMapping("/category")
    public Result updateCategory(BusinessCategoryDto businessCategoryDto) {
        return businessGuideService.updateBusinessCategory(businessCategoryDto);
    }
    @ApiOperation(value = "添加业务指南分类", notes = "")
    @PostMapping("/category")
    public Result saveCategory(BusinessCategoryDto businessCategoryDto) {
        return businessGuideService.saveBusinessCategory(businessCategoryDto);
    }

    @ApiOperation(value = "删除业务指南分类", notes = "")
    @DeleteMapping("/category/{id}")
    public Result deleteCategory(@PathVariable("id")String id) {
        return businessGuideService.deleteBusinessCategory(id);
    }
}
