package com.sgcc.controller;

import com.example.Utils;
import com.example.constant.WechatURLConstants;
import com.example.errorcode.WechatURLErrorcode;
import com.example.result.Result;
import com.sgcc.dtomodel.wechat.JSAPITicketDTO;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.service.WeChatService;
import com.sgcc.wxpay.Sgcc_WXPay;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
        JSAPITicketDTO jsapiTicketDTO = weChatService.getJsApiTicket();
        if( jsapiTicketDTO.getErrcode() == WechatURLErrorcode.SYS_BUSY )
        {
            try {
                Thread.sleep(1000);
                return Result.success(weChatService.getJsApiTicket());
            }
            catch (InterruptedException e )
            {

            }
        }
        return Result.success(jsapiTicketDTO);
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

    /**
     * 发送模板消息
     * @return
     */
    @ApiOperation(value = "sendTempMessage", notes = "")
    @PostMapping(value = "/sendTempMessage")
    public Result sendTempMessage(@RequestParam String openId){
        try {
            return weChatService.sendTempMsg(openId);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * 获取图文素材
     */
    @ApiOperation(value = "Materials", notes = "获取素材")
    @PostMapping(value = "/Materials")
    public Result getMaterial(@RequestParam String type,@RequestParam int offset,@RequestParam int count){
        try {
            return weChatService.getMaterial(type,offset,count);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR,e);
        }
    }



    /**
     * 新增图文消息图片
     * @return
     */
    @ApiOperation(value = "uploadImg", notes = "")
    @PostMapping(value = "/uploadImg",headers = "content-type=multipart/form-data")
    public Result uploadImg(@ApiParam(required = true) MultipartFile file){
        try {
            return weChatService.uploadImg(file);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }




}
