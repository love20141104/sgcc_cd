package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.OrderTransDTO;
import com.sgcc.dto.PayResultSubmitDTO;
import com.sgcc.dto.inhabitant.InhabitantInfoCorrectEditDTO;
import com.sgcc.dto.inhabitant.InhabitantInfoCorrectSubmitDTO;
import com.sgcc.service.RecordService;
import com.sgcc.service.SgccBusinessService;
import com.sgcc.service.UserService;
import com.sgcc.service.WechatPayResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "", tags = "用户相关业务接口")
@RestController
@RequestMapping(value = "/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private WechatPayResultService wechatPayResultService;

    @Autowired
    private SgccBusinessService sgccBusinessService;

    @ApiOperation(value = "查询月度账单", notes = "")
    @GetMapping(value = "/bill/billInfo/{userNo}")
    public Result queryBillInfo(@PathVariable(required = true) String userNo,
                                @RequestParam(required = true) long date) {
        return userService.queryBillInfoById(userNo,date);
    }


    @ApiOperation(value = "查询缴费记录", notes = "")
    @GetMapping(value = "/record/recordInfo/{userNo}")
    public Result queryRecordInfo(@PathVariable(required = true) String userNo) {
        return recordService.queryRecordInfo(userNo);
    }

    @ApiOperation(value = "订单信息", notes = "")
    @PostMapping(value = "/record/orderInfo")
    public Result orderInfo(@RequestBody(required = true) OrderTransDTO orderTransDTO) {
        return recordService.queryOrderInfo(orderTransDTO);
    }



    @ApiOperation(value = "查询缴费结果", notes = "")
    @GetMapping(value = "/payResult/payInfo")
    public Result querypayResult() {
        return wechatPayResultService.findPayResult();
    }


    @ApiOperation(value = "新增缴费结果", notes = "")
    @PostMapping(value = "/payResult/payInfo")
    public Result addpayResult(@RequestBody PayResultSubmitDTO payResultSubmitDTO) {
        return  wechatPayResultService.insertPayResult(payResultSubmitDTO);
    }


    /******************************************信息修正*********************************************/

    @ApiOperation(value = "信息修正-新增", notes = "")
    @PostMapping(value = "/infoCorrect")
    public Result addInfoCorrectOrder(@RequestBody InhabitantInfoCorrectSubmitDTO dto) {
        return userService.addInfoCorrectOrder(dto);
    }


    @ApiOperation(value = "信息修正-查询", notes = "")
    @GetMapping(value = "/infoCorrect")
    public Result queryInfoCorrectOrder() {
        return userService.getInfoCorrectOrderList();
    }


    @ApiOperation(value = "信息修正-修改", notes = "")
    @PutMapping(value = "/infoCorrect")
    public Result updateInfoCorrectOrder(@RequestBody InhabitantInfoCorrectEditDTO dto) {
        return userService.updateInfoCorrectOrder(dto);
    }

    @ApiOperation(value = "信息修正-删除", notes = "")
    @DeleteMapping(value = "/infoCorrect")
    public Result delInfoCorrectOrder(@RequestBody List<String> ids) {
        return userService.delInfoCorrectOrder(ids);
    }





//    @ApiOperation(value = "新增居民增容订单", notes = "")
//    @PostMapping(value = "/increaseCapacity/order/{openId}")
//    public Result addIncreaseCapacityOrder(@RequestBody InhabitantIncreaseCapacityDTO dto, @PathVariable String openId) {
//        return  sgccBusinessService.addIncreaseCapacityOrder(dto,openId);
//    }






}
