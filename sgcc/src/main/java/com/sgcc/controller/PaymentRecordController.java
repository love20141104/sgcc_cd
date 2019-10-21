package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.OrderTransDTO;
import com.sgcc.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "", tags = "缴费记录接口")
@RestController
@RequestMapping(value = "/record")
@Controller
public class PaymentRecordController {

    @Autowired
    private RecordService recordService;

    @ApiOperation(value = "查询缴费记录", notes = "")
    @GetMapping(value = "/recordInfo/{userNo}")
    public Result queryRecordInfo(@PathVariable(required = true) String userNo) {
        return recordService.queryRecordInfo(userNo);
    }

    @ApiOperation(value = "订单信息", notes = "")
    @PostMapping(value = "/orderInfo")
    public Result orderInfo(@RequestBody(required = true) OrderTransDTO orderTransDTO) {
        return recordService.queryOrderInfo(orderTransDTO);
    }

}
