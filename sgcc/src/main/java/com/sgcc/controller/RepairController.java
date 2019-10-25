package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.RepairEditDTO;
import com.sgcc.dto.RepairSubmitDTO;
import com.sgcc.service.RepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "", tags = "故障报修接口")
@RestController
@RequestMapping(value = "/repair")
@Controller
public class RepairController {


    @Autowired
    private RepairService repairService;

    @ApiOperation(value = "故障报修-查询表单")
    @GetMapping("/orderList/infoList/{openId}")
    public Result queryOrderAllList(@PathVariable String openId){
        return repairService.findRepairOrderList(openId);
    }


    @ApiOperation(value = "故障报修-查询")
    @GetMapping("/order/info")
    public Result queryOrderList(){
        return repairService.findRepairOrderAll();
    }

    @ApiOperation(value = "故障报修-查询")
    @GetMapping("/order/info/{repairId}")
    public Result queryOrderById(@PathVariable String repairId){
        return repairService.findRepairOrderById(repairId);
    }


    @ApiOperation(value = "故障报修-新增")
    @PostMapping("/order/info")
    public Result addOrder(@RequestBody RepairSubmitDTO dto){
        return repairService.addRepairOrder(dto);
    }

    @ApiOperation(value = "故障报修-修改")
    @PutMapping("/order/info")
    public Result updateOrder(@RequestBody RepairEditDTO dto){
        return repairService.updateRepairOrder(dto);
    }

    @ApiOperation(value = "故障报修-删除")
    @DeleteMapping("/order/info")
    public Result delOrder(@RequestBody List<String> ids){
        return repairService.delRepairOrder(ids);
    }






}
