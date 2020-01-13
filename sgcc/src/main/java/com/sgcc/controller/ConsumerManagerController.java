package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.ConsumerManagerDTO;
import com.sgcc.dto.ConsumerManagerInsertDTO;
import com.sgcc.service.ConsumerManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "", tags = "客户经理接口")
@RestController
@RequestMapping(value = "/ConsumerManager")
@Controller
public class ConsumerManagerController {

    @Autowired
    private ConsumerManagerService consumerManagerService;



    @ApiOperation(value = "查询所有客户经理", notes = "")
    @GetMapping(value = "/ConsumerManager")
    public Result selectConsumerManager() {
        return consumerManagerService.selectConsumerManager();
    }
    @ApiOperation(value = "根据地区查询客户经理", notes = "")
    @GetMapping(value = "/ConsumerManager/area")
    public Result selectConsumerManagerByArea(String area) {
        return consumerManagerService.selectConsumerManagerByArea(area);
    }

    @ApiOperation(value = "根据地区查询客户经理", notes = "")
    @GetMapping(value = "/ConsumerManager/key")
    public Result selectConsumerManagerByKey(String area,String key) {
        return consumerManagerService.selectConsumerManagerByKey(area,key);
    }

    @ApiOperation(value = "查询用户对应的客户经理", notes = "")
    @GetMapping(value = "/ConsumerManager/openId/{openId}")
    public Result selectConsumerManagerByUserId(@PathVariable String openId) {
        return consumerManagerService.selectConsumerManagerById("");
    }

    @ApiOperation(value = "查询客户经理", notes = "")
    @GetMapping(value = "/ConsumerManager/{Id}")
    public Result GetConsumerManagerById(@PathVariable String Id) {
        return consumerManagerService.selectConsumerManagerById(Id);
    }
    /**
     ***********************管理工具接口start*********************************
     */

    @ApiOperation(value = "新增客户经理", notes = "")
    @PostMapping(value = "/ManagementTool/ConsumerManager")
    public Result insertConsumerManager(@RequestBody ConsumerManagerInsertDTO consumerManagerInsertDTO) {
        return consumerManagerService.insertConsumerManager(consumerManagerInsertDTO);
    }

    @ApiOperation(value = "删除客户经理", notes = "")
    @DeleteMapping(value = "/ManagementTool/ConsumerManager/consumerManager-Id/{consumerManagerId}")
    public Result deleteConsumerManager(@PathVariable String consumerManagerId) {
        return consumerManagerService.deleteConsumerManager(consumerManagerId);
    }

    @ApiOperation(value = "删除客户经理", notes = "")
    @DeleteMapping(value = "/ManagementTool/ConsumerManagers")
    public Result deleteConsumerManager(@RequestBody List<String> consumerManagerIds) {
        return consumerManagerService.deleteConsumerManagers(consumerManagerIds);
    }

    @ApiOperation(value = "修改客户经理", notes = "")
    @PutMapping(value = "/ManagementTool/ConsumerManager")
    public Result updateConsumerManager(@RequestBody ConsumerManagerDTO consumerManagerDTO) {
        return consumerManagerService.updateConsumerManager(consumerManagerDTO);
    }
    /**
     ***********************管理工具接口end*********************************
     */
}
