package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.commerce.dto.CommerceIncreaseCapacityDTO;
import com.sgcc.dto.OrderTransDTO;
import com.sgcc.dto.PayResultDTO;
import com.sgcc.inhabitant.dto.InhabitantIncreaseCapacityDTO;
import com.sgcc.inhabitant.dto.InhabitantRenameDTO;
import com.sgcc.service.RecordService;
import com.sgcc.service.SgccBusinessService;
import com.sgcc.service.UserService;
import com.sgcc.service.WechatPayResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public Result addpayResult(@RequestBody PayResultDTO payResultDTO) {
        return  wechatPayResultService.insertPayResult(payResultDTO);
    }






    @ApiOperation(value = "新增更名过户订单", notes = "")
    @PostMapping(value = "/renameAndTransfer/order/{openId}")
    public Result addRenameAndTransferOrder(@RequestBody InhabitantRenameDTO inhabitantRenameDTO, @PathVariable String openId) {
        return  sgccBusinessService.addRenameOrder(inhabitantRenameDTO,openId);
    }

    @ApiOperation(value = "查询更名过户订单详细", notes = "")
    @GetMapping(value = "/renameAndTransfer/orderDetail/")
    public Result queryRenameAndTransferOrderDetail() {
        return  sgccBusinessService.queryRenameOrder();
    }

    @ApiOperation(value = "查询所有更名过户订单列表", notes = "")
    @GetMapping(value = "/renameAndTransfer/orderList/{orderNo}")
    public Result queryRenameAndTransferOrderList(@PathVariable String orderNo) {
        return  sgccBusinessService.queryRenameOrderList(orderNo);
    }

    @ApiOperation(value = "删除更名过户订单", notes = "")
    @DeleteMapping(value = "/renameAndTransfer/order")
    public Result delRenameAndTransferOrder(@RequestParam List<String> ids) {
        return  sgccBusinessService.delRenameOrder(ids);
    }

    @ApiOperation(value = "修改所有更名过户订单", notes = "")
    @GetMapping(value = "/renameAndTransfer/order/{infoId}")
    public Result updateIncreaseCapacity(@PathVariable String infoId,@RequestParam String name) {
        return  sgccBusinessService.updateRenameOrder(infoId,name);
    }





//    @ApiOperation(value = "新增居民增容订单", notes = "")
//    @PostMapping(value = "/increaseCapacity/order/{openId}")
//    public Result addIncreaseCapacityOrder(@RequestBody InhabitantIncreaseCapacityDTO dto, @PathVariable String openId) {
//        return  sgccBusinessService.addIncreaseCapacityOrder(dto,openId);
//    }


    @ApiOperation(value = "增容提交-个体工商业", notes = "")
    @PostMapping(value = "/increaseCapacity/order/{openId}")
    public Result addIncreaseCapacity(@RequestBody CommerceIncreaseCapacityDTO dto, @PathVariable String openId) {
        return  sgccBusinessService.addIncreaseCapacityOrders(dto,openId);
    }

    @ApiOperation(value = "增容查询-个体工商业", notes = "")
    @GetMapping(value = "/increaseCapacity/commerce/{openId}")
    public Result queryIncreaseCapacityListById(@PathVariable String openId) {
        return  sgccBusinessService.queryIncreaseCapacityAllByOpenId(openId);
    }

    @ApiOperation(value = "增容查询-个体工商业", notes = "")
    @GetMapping(value = "/increaseCapacity/commerce")
    public Result queryIncreaseCapacityList() {
        return  sgccBusinessService.findIncreaseCapacityAll();
    }

    @ApiOperation(value = "增容修改-个体工商业", notes = "")
    @PutMapping(value = "/increaseCapacity/orderList/{orderNo}")
    public Result updateIncreaseCapacityForGeOrderList(@RequestParam Double capacity, @PathVariable String orderNo) {
        return  sgccBusinessService.updateIncreaseCapacityOrders(capacity,orderNo);
    }

    @ApiOperation(value = "增容删除-个体工商业", notes = "")
    @DeleteMapping(value = "/increaseCapacity/orderList")
    public Result updateIncreaseCapacityForGeOrderList(@RequestParam List<String> ids) {
        return  sgccBusinessService.delIncreaseCapacityOrders(ids);
    }



}
