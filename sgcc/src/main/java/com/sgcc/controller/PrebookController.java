package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.PrebookInfoEditDTO;
import com.sgcc.dto.PrebookInfoSubmitDTO;
import com.sgcc.dto.TakeNumberDTO;
import com.sgcc.service.PrebooksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "", tags = "线上预约接口")
@RestController
@RequestMapping(value = "/prebook")
public class PrebookController {

    @Autowired
    private PrebooksService prebooksService;

    /**********************************用户*****************************************/
    @ApiOperation(value = "线上预约-基础信息", notes = "")
    @GetMapping(value = "/open-id/{openId}")
    public Result getBasicInfo(@PathVariable String openId) {
        return prebooksService.getBasicInfo(openId);
    }


    @ApiOperation(value = "线上预约-提交", notes = "")
    @PostMapping(value = "/open-id/bookingForm")
    public Result addPrebook(@RequestBody PrebookInfoSubmitDTO dto) {
        return prebooksService.addPrebook(dto);
    }

    @ApiOperation(value = "线上预约-预约列表", notes = "")
    @GetMapping(value = "/open-id/info")
    public Result getPrebookInfo(@RequestParam String openId,@RequestParam int status) {
        return prebooksService.getPrebookInfo(openId,status);
    }

    @ApiOperation(value = "线上预约-预约详情", notes = "")
    @GetMapping(value = "/open-id/infoDetail")
    public Result getPrebookInfoDetail(@RequestParam String id) {
        return prebooksService.getPrebookInfoDetail(id);
    }

//    @ApiOperation(value = "线上预约-预约取号", notes = "")
//    @PostMapping(value = "/open-id/takeNumber")
//    public Result getPrebookNumber(@RequestBody TakeNumberDTO dto) {
//        return prebooksService.getPrebookNumber(dto);
//    }


    /**********************************工作人员*************************************/

    @ApiOperation(value = "线上预约-审核列表", notes = "")
    @GetMapping(value = "/open-id/checkList")
    public Result getCheckList(@RequestParam String openId,@RequestParam int status) {
        return prebooksService.getCheckList(openId,status);
    }

    @ApiOperation(value = "线上预约-审核详情", notes = "")
    @GetMapping(value = "/open-id/checkDetailList")
    public Result getCheckDetailList(@RequestParam String id) {
        return prebooksService.getCheckDetailList(id);
    }

    @ApiOperation(value = "线上预约-审核预约", notes = "")
    @PutMapping(value = "/open-id/checkPrebook")
    public Result updateCheckPrebook(@RequestBody PrebookInfoEditDTO prebookInfoEditDTO) {
        return prebooksService.updateCheckPrebook(prebookInfoEditDTO);
    }



}
