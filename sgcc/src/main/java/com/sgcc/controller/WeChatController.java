package com.sgcc.controller;

import com.example.Utils;
import com.example.constant.WechatURLConstants;
import com.example.result.Result;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.service.WeChatService;
import com.sgcc.wxpay.Sgcc_WXPay;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "", tags = "微信API")
@RestController
@RequestMapping(value = "/Wechat")
@Controller
public class WeChatController {

    @Autowired
    private WeChatService weChatService;
    /**
     * 获取AccessToken
     * @return Result
     */
    @ApiOperation(value = "getAccessToken", notes = "")
    @GetMapping(value = "/AccessToken")
    public Result getAccessToken() {
        return weChatService.getAccessToken();
    }

    /**
     * 获取JsApiTicket
     * @return Result
     */
    @ApiOperation(value = "getJsApiTicket", notes = "")
    @GetMapping(value = "/JsApiTicket")
    public Result getJsApiTicket() {
        return weChatService.getJsApiTicket();
    }

    /**
     * 获取Signature
     * @return Result
     */
    /**
     *
     * @param url   url
     * @return  SignatureDTO
     */
    @ApiOperation(value = "WXConfig", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url",value = "url")
    })
    @GetMapping(value = "/WXConfig")
    public Result getSignature(@RequestParam String url) {
        return weChatService.getSignature(url);
    }

    /**
     * 获取预付单
     */
    @ApiOperation(value = "Prepay", notes = "")
    @GetMapping(value = "/Prepay")
    public Result getPrepay(@RequestParam String openId,@RequestParam String totalFee) {

        try {
            return weChatService.getPrepay(openId,totalFee);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 获取预付单
     */
    @ApiOperation(value = "PayNotice", notes = "")
    @PostMapping(value = "/PayNotice")
    public Result PayNotice(@RequestBody String noticeXml) {

        try {
            return weChatService.PayNotice(noticeXml);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }
}
