package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.HouseholdInfoPostDTO;
import com.sgcc.dto.OrderTransDTO;
import com.sgcc.dto.PayResultSubmitDTO;
import com.sgcc.dto.UserSubscribeDTO;
import com.sgcc.dto.commerce.CommerceInfoCorrectEditDTO;
import com.sgcc.dto.commerce.CommerceInfoCorrectSubmitDTO;
import com.sgcc.dto.inhabitant.InhabitantInfoCorrectEditDTO;
import com.sgcc.dto.inhabitant.InhabitantInfoCorrectSubmitDTO;
import com.sgcc.service.*;
import com.sgcc.sgccenum.SubscribeCateEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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

    @Autowired
    private HouseholdService householdService;

    @ApiOperation(value = "查询月度账单", notes = "")
    @GetMapping(value = "/bill/billInfo/{userNo}")
    public Result queryBillInfo(@PathVariable(required = true) String userNo,
                                @RequestParam(required = true) long date) {
        return userService.queryBillInfoById(userNo, date);
    }

    @ApiOperation(value = "订阅与关闭", notes = "")
    @PostMapping(value = "/Sub/{openId}")
    public Result MessageSub(@PathVariable(required = true) String openId,
                                @RequestBody(required = true) Map<String,String> keyValue) {
        return Result.success();
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



    @ApiOperation(value = "查询缴费结果", notes = "")
    @GetMapping(value = "/open-id/{openId}/payResult")
    public Result queryRecentlyTransform(@PathVariable String openId) {
        return wechatPayResultService.findRecentlyTransform(openId);
    }


    @ApiOperation(value = "新增缴费结果", notes = "")
    @PostMapping(value = "/payResult/payInfo")
    public Result addpayResult(@RequestBody PayResultSubmitDTO payResultSubmitDTO) {
        return wechatPayResultService.insertPayResult(payResultSubmitDTO);
    }


    /******************************************信息修正*********************************************/

    @ApiOperation(value = "信息修正-居民-新增", notes = "")
    @PostMapping(value = "/infoCorrect/inhabitant")
    public Result addInhabitantInfoCorrect(@RequestBody InhabitantInfoCorrectSubmitDTO dto) {
        return userService.addInhabitantInfoCorrect(dto);
    }


    @ApiOperation(value = "信息修正-居民-查询", notes = "")
    @GetMapping(value = "/infoCorrect/inhabitant")
    public Result queryInhabitantInfoCorrect() {
        return userService.queryInhabitantInfoCorrect();
    }


    @ApiOperation(value = "信息修正-居民-修改", notes = "")
    @PutMapping(value = "/infoCorrect/inhabitant")
    public Result updateInhabitantInfoCorrect(@RequestBody InhabitantInfoCorrectEditDTO dto) {
        return userService.updateInhabitantInfoCorrect(dto);
    }

    @ApiOperation(value = "信息修正-居民-删除", notes = "")
    @DeleteMapping(value = "/infoCorrect/inhabitant")
    public Result delInhabitantInfoCorrect(@RequestBody List<String> ids) {
        return userService.delInhabitantInfoCorrect(ids);
    }


    @ApiOperation(value = "信息修正-个体工商业-新增", notes = "")
    @PostMapping(value = "/infoCorrect/commerce")
    public Result addCommerceInfoCorrect(@RequestBody CommerceInfoCorrectSubmitDTO dto) {
        return userService.addCommerceInfoCorrect(dto);
    }


    @ApiOperation(value = "信息修正-个体工商业-查询", notes = "")
    @GetMapping(value = "/infoCorrect/commerce")
    public Result queryCommerceInfoCorrect() {
        return userService.queryCommerceInfoCorrect();
    }


    @ApiOperation(value = "信息修正-个体工商业-修改", notes = "")
    @PutMapping(value = "/infoCorrect/commerce")
    public Result updateCommerceInfoCorrect(@RequestBody CommerceInfoCorrectEditDTO dto) {
        return userService.updateCommerceInfoCorrect(dto);
    }

    @ApiOperation(value = "信息修正-个体工商业-删除", notes = "")
    @DeleteMapping(value = "/infoCorrect/commerce")
    public Result delCommerceInfoCorrect(@RequestBody List<String> ids) {
        return userService.delCommerceInfoCorrect(ids);
    }


    /********************************户号管理相关接口***********************************/
    /**
     * 用户绑定户号
     */
    @ApiOperation(value = "用户绑定户号", notes = "")
    @PostMapping(value = "/open-id/{openId}")
    public Result bindHousehold(@PathVariable String openId,@RequestBody HouseholdInfoPostDTO householdInfoPostDTO) {
        return householdService.bindHousehold(openId, householdInfoPostDTO.getHouseholdNumber(), householdInfoPostDTO.getPwd());
    }

    /**
     * 用户解邦户号
     */
    @ApiOperation(value = "用户解邦户号", notes = "")
    @DeleteMapping(value = "/open-id/{openId}/household-num/{householdId}")
    public Result removeBind(@PathVariable String openId, @PathVariable String householdId) {
        return householdService.removeBind(openId, householdId);
    }

    /**
     * 用户获取绑定户号列表
     */
    @ApiOperation(value = "用户获取绑定户号列表", notes = "")
    @GetMapping(value = "/open-id/{openId}/household-num")
    public Result getBindList(@PathVariable String openId) {
        return householdService.getBindList(openId);
    }

    /**
     * 数据库中记录的密码失效时，提示用户输入新密码修改密码
     */
    /*@ApiOperation(value = "数据库中记录的密码失效时,提示用户输入新密码修改密码", notes = "")
    @PutMapping(value = "/open-id/{openId}/household-num/{householdNum}")
    public Result changePWD(@PathVariable String openId, @PathVariable String householdNum, @RequestParam String pwd) {
        return householdService.changePWD(openId, householdNum, pwd);

    }*/


    /**
     * 设置默认户号
     */
    @ApiOperation(value = "设置默认户号", notes = "")
    @PostMapping(value = "/open-id/{openId}/default-household-num/{householdId}")
    public Result setDefaultHouseholdNum(@PathVariable String openId, @PathVariable String householdId) {
        return householdService.setDefaultHouseholdNum(openId, householdId);
    }

    /**
     * 用户取消关注
     */
    @ApiOperation(value = "用户取消关注", notes = "")
    @DeleteMapping(value = "/open-id/{openId}")
    public Result cancelFocusWechat(@PathVariable String openId) {
        return householdService.cancelFocusWechat(openId);
    }

    /**
     * 用户关注公众号
     */
//    @ApiOperation(value = "用户关注公众号", notes = "")
//    @PostMapping(value = "/open-id/{openId}")
//    public Result focusWechat(@PathVariable String openId) {
//        return householdService.focusWechat(openId);
//
//    }

    /**
     * 查询用户消息订阅状态
     */
    @ApiOperation(value = "查询用户消息订阅状态", notes = "")
    @GetMapping(value = "/open-id/{openId}/subscribeInfo")
    public Result getSubscribeInfo(@PathVariable String openId) {
        return householdService.getSubscribeInfo(openId);
    }

    /**
     * 用户修改消息订阅状态
     */
    @ApiOperation(value = "用户修改消息订阅状态", notes = "")
    @PutMapping(value = "/open-id/{openId}/subscribeInfo")
    public Result updateSubscribe(@PathVariable String openId
            , @RequestParam SubscribeCateEnum subscribeCateEnum
            , @RequestParam boolean isSubscribe) {
        return householdService.updateSubscribe(openId,subscribeCateEnum,isSubscribe);
    }
    /**
     * 后台查询用户和消息订阅状态
     */
    @ApiOperation(value = "后台根据用户是否可用isAvailable查询用户和消息订阅状态", notes = "")
    @GetMapping(value = "/open-id/userSubscribe")
    public Result getUserSubscribe(@RequestParam boolean isAvailable) {
        return householdService.getUserSubscribeList(isAvailable);
    }
    @ApiOperation(value = "后台修改用户和消息订阅状态", notes = "")
    @PutMapping(value = "/open-id/userSubscribe")
    public Result updateUserSubscribe(@RequestBody UserSubscribeDTO userSubscribeDTO) {
        return householdService.updateUserSubscribe(userSubscribeDTO);
    }

    /**
     * 查询缴费记录中其他非绑定用户户号
     */
    @ApiOperation(value = "查询缴费记录中其他非绑定用户户号", notes = "")
    @GetMapping(value = "/open-id/{openId}/othersHouseholdNum")
    public Result getNoBindList(@PathVariable String openId) {
        return householdService.getNoBindList(openId);
    }

    //todo 测试加密
    @ApiOperation(value = "测试加密", notes = "")
    @PostMapping(value = "/open-id/encrypt")
    public String getEncrypt( String pwd) {
        return householdService.encrypt(pwd);
    }
    //todo 测试解密
    @ApiOperation(value = "测试解密", notes = "")
    @PostMapping(value = "/open-id/decrypt")
    public String getDecryptt( String pwd) {
        return householdService.decrypt(pwd);
    }

    /**
     * 查询默认户号信息和实时电量
     * @param openId
     * @return
     */
    @ApiOperation(value = "查询默认户号信息和实时电量", notes = "")
    @GetMapping(value = "/open-id/{openId}/defaultHouseholdAndRealTimeElectricity")
    public Result getDefaultHouseholdAndRealTimeElectricity(@PathVariable String openId) {
        return userService.getDefaultHouseholdAndRealTimeElectricity(openId);
    }


    @ApiOperation(value = "查询默认户号信息", notes = "")
    @GetMapping(value = "/open-id/{openId}/defaultHousehold")
    public Result getDefaultHousehold(@PathVariable String openId) {
        return userService.getDefaultHousehold(openId);
    }





//    @ApiOperation(value = "新增居民增容订单", notes = "")
//    @PostMapping(value = "/increaseCapacity/order/{openId}")
//    public Result addIncreaseCapacityOrder(@RequestBody InhabitantIncreaseCapacityDTO dto, @PathVariable String openId) {
//        return  sgccBusinessService.addIncreaseCapacityOrder(dto,openId);
//    }


}
