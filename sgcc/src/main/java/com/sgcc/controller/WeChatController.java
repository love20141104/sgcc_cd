package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.service.WeChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "", tags = "微信API")
@RestController
@RequestMapping(value = "/WechatAPI")
@Controller
public class WeChatController {

    @Autowired
    private WeChatService weChatService;
    /**
     * 获取AccessToken
     * @return Result
     */
    @ApiOperation(value = "getAccessToken", notes = "")
    @GetMapping(value = "/getAccessToken")
    public Result getAccessToken() {
        return weChatService.getAccessToken();
    }

    /**
     * 获取JsApiTicket
     * @return Result
     */
    @ApiOperation(value = "getJsApiTicket", notes = "")
    @GetMapping(value = "/getJsApiTicket")
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
    @ApiOperation(value = "getSignature", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url",value = "url")
    })
    @GetMapping(value = "/WXConfig")
    public Result getSignature(@RequestParam String url) {
        return weChatService.getSignature(url);
    }


}
