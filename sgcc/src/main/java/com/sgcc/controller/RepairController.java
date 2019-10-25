package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.service.RepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "", tags = "故障报修接口")
@RestController
@RequestMapping(value = "/repair")
@Controller
public class RepairController {


    @Autowired
    private RepairService repairService;

    @ApiOperation(value = "故障报修-查询")
    @GetMapping("/orderList/info")
    public Result queryOrderList(){
        return repairService.findRepairOrderAll();
    }

}
