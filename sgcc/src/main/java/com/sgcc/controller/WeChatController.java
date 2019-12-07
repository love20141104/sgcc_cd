package com.sgcc.controller;

import com.example.Utils;
import com.example.constant.WechatURLConstants;
import com.example.errorcode.WechatURLErrorcode;
import com.example.result.Result;
import com.sgcc.dto.MsgDTO;
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
     * 获取微信公众号所有用户信息
     * @return Result
     */
//    @ApiOperation(value = "getUserInfos", notes = "")
//    @GetMapping(value = "/userInfos")
//    public Result getUserInfos(@RequestParam String openID) {
//        return weChatService.getUserInfos(openID);
//    }


    /**
     * 获取AccessToken
     *
     * @return Result
     */
    @ApiOperation(value = "getAccessToken", notes = "")
    @GetMapping(value = "/AccessToken")
    public Result getAccessToken() {
        return weChatService.getAccessToken();
    }

    /**
     * 获取JsApiTicket
     *
     * @return Result
     */
    @ApiOperation(value = "getJsApiTicket", notes = "")
    @GetMapping(value = "/JsApiTicket")
    public Result getJsApiTicket() {
        JSAPITicketDTO jsapiTicketDTO = weChatService.getJsApiTicket();
        if (jsapiTicketDTO.getErrcode() == WechatURLErrorcode.SYS_BUSY) {
            try {
                Thread.sleep(1000);
                return Result.success(weChatService.getJsApiTicket());
            } catch (InterruptedException e) {

            }
        }
        return Result.success(jsapiTicketDTO);
    }

    /**
     * 获取Signature
     * @return Result
     */
    /**
     * @param url url
     * @return SignatureDTO
     */
    @ApiOperation(value = "WXConfig", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "url")
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
    public Result getPrepay(@RequestParam String openId, @RequestParam String totalFee) {

        try {
            return weChatService.getPrepay(openId, totalFee);
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
     * 发送消息
     *
     * @return
     */
    @ApiOperation(value = "sendMessage", notes = "")
    @PostMapping(value = "/sendMessage/{openId}")
    public Result sendMessage(@PathVariable("openId") String openId, @RequestBody MsgDTO msgDTO) {
        try {
            return weChatService.sendMsg(openId, msgDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * 获取模板
     *
     * @return
     */
    @ApiOperation(value = "getTempList", notes = "")
    @GetMapping(value = "/getTempList")
    public Result getTempList() {
        return weChatService.getTempList();
    }

    /**
     * 获取图文素材
     */
    @ApiOperation(value = "Materials", notes = "获取素材")
    @PostMapping(value = "/Materials")
    public Result getMaterial(@RequestParam String type, @RequestParam int offset, @RequestParam int count) {
        try {
            return weChatService.getMaterial(type, offset, count);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR, e);
        }
    }


    /**
     * 新增图文消息图片
     *
     * @return
     */
    @ApiOperation(value = "uploadImg", notes = "")
    @PostMapping(value = "/uploadImg", headers = "content-type=multipart/form-data")
    public Result uploadImg(@ApiParam(required = true) MultipartFile file) {
        try {
            return weChatService.uploadImg(file);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * @return
     */
//    @ApiOperation(value = "GetAllUsers", notes = "")
//    @GetMapping(value = "/AllUsers")
//    public Result GetAllUserInfos(){
//        try {
//            return weChatService.GetAllUserInfos();
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.failure(TopErrorCode.GENERAL_ERR);
//        }
//    }

    /**
     * 获取公众号所用用户信息并存到数据库
     */
//    @ApiOperation(value = "SyncAllUsers", notes = "")
//    @GetMapping(value = "/AllUsers")
//    public Result SyncAllUsers(){
//        try {
//            return weChatService.SyncUserInfos();
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.failure(TopErrorCode.GENERAL_ERR);
//        }
//    }


    /**
     * 查询所有用户信息
     * @return
     */
    @ApiOperation(value = "findUsers", notes = "")
    @GetMapping(value = "/users")
    public Result findUsers() {
        return weChatService.findUsers();
    }


    /**
     * 根据昵称查询
     * @return
     */
    @ApiOperation(value = "findUsersByNickName", notes = "")
    @GetMapping(value = "/users/nickName")
    public Result findUsersByNickName(@RequestParam(required = false) String nickName,@RequestParam int pageNo,@RequestParam int pageSize) {
        return weChatService.findUsersByNickName(nickName,pageNo,pageSize);
    }

    /**
     * 分页查询
     * @return
     */
    @ApiOperation(value = "findPageList", notes = "")
    @GetMapping(value = "/users/pageList")
    public Result findPageList(@RequestParam int pageNo,@RequestParam int pageSize) {
        return weChatService.findPageList(pageNo,pageSize);
    }


}


