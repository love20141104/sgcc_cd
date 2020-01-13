package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.*;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.service.PrebooksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "", tags = "税票预约接口")
@RestController
@RequestMapping(value = "/prebook")
public class PrebookController {

    @Autowired
    private PrebooksService prebooksService;

    /**********************************用户*****************************************/
//    @ApiOperation(value = "线上预约-基础信息", notes = "")
//    @GetMapping(value = "/open-id/{openId}")
//    public Result getBasicInfo(@PathVariable String openId) {
//        return prebooksService.getBasicInfo(openId);
//    }

    @ApiOperation(value = "税票预约-黑名单限制预约", notes = "")
    @GetMapping(value = "/open-id/blacklist")
    public Result getBlacklistByOpenId(@RequestParam String openId) {
        return prebooksService.getBlacklistByOpenId(openId);
    }

    @ApiOperation(value = "税票预约-黑名单查询", notes = "")
    @GetMapping(value = "/admin/blacklist")
    public Result getBlacklist() {
        return prebooksService.getBlacklist();
    }


    @ApiOperation(value = "税票预约-预约时间段", notes = "")
    @GetMapping(value = "/open-id/timeSlot/{day}")
    public Result getTimeSlot(@PathVariable String day) {
        return prebooksService.getTimeSlot(day);
    }

    @ApiOperation(value = "税票预约-提交", notes = "")
    @PostMapping(value = "/open-id/bookingForm")
    public Result addPrebook(@RequestBody PrebookInfoSubmitDTO dto) {
        return prebooksService.addPrebook(dto);
    }


    @ApiOperation(value = "税票预约-预约列表", notes = "")
    @GetMapping(value = "/open-id/info")
    public Result getPrebookInfo(@RequestParam String openId,@RequestParam int status) {
        return prebooksService.getPrebookInfo(openId,status);
    }

    @ApiOperation(value = "税票预约-预约详情", notes = "")
    @GetMapping(value = "/open-id/infoDetail")
    public Result getPrebookInfoDetail(@RequestParam String id) {
        return prebooksService.getPrebookInfoDetail(id);
    }


    /**********************************工作人员*************************************/

    @ApiOperation(value = "税票预约-审核列表", notes = "")
    @GetMapping(value = "/open-id/checkList")
    public Result getCheckList(@RequestParam String openId,@RequestParam int status,@RequestParam(required = false) Boolean isPrinted) {
        return prebooksService.getCheckList(openId,status,isPrinted);
    }


    @ApiOperation(value = "税票预约-审核详情", notes = "")
    @GetMapping(value = "/open-id/checkDetailList")
    public Result getCheckDetailList(@RequestParam String id) {
        return prebooksService.getCheckDetailList(id);
    }

    @ApiOperation(value = "税票预约-审核预约", notes = "")
    @PutMapping(value = "/open-id/checkPrebook")
    public Result updateCheckPrebook(@RequestBody PrebookInfoEditDTO prebookInfoEditDTO) {
        return prebooksService.updateCheckPrebook(prebookInfoEditDTO);
    }


    @ApiOperation(value = "税票预约-打印", notes = "")
    @PutMapping(value = "/open-id/print")
    public Result updatePrintStatus(@RequestBody PrintStatusUpdateDTO PrintStatusUpdateDTO) {
        return prebooksService.updatePrintStatus(PrintStatusUpdateDTO);
    }


    @ApiOperation(value = "税票预约-查询审核人", notes = "")
    @GetMapping(value = "/open-id/config/checker")
    public Result getAllCheckers() {
        return prebooksService.getAllCheckers();
    }

    @ApiOperation(value = "税票预约-新增审核人", notes = "")
    @PostMapping(value = "/open-id/config/checker")
    public Result addChecker(@RequestBody CheckerSubmitDTO CheckerSubmitDTO) {
        return prebooksService.addChecker(CheckerSubmitDTO);
    }

    @ApiOperation(value = "税票预约-修改审核人", notes = "")
    @PutMapping(value = "/open-id/config/checker")
    public Result updateChecker(@RequestBody CheckerEditDTO checkerEditDTO) {
        return prebooksService.updateChecker(checkerEditDTO);
    }

    @ApiOperation(value = "税票预约-删除审核人", notes = "")
    @DeleteMapping(value = "/open-id/config/checker")
    public Result delChecker(@RequestBody List<String> ids) {
        return prebooksService.delChecker(ids);
    }


    @ApiOperation(value = "税票预约-取票状态", notes = "")
    @PutMapping(value = "/open-id/tax-ticket/status")
    public Result updateTicketStatus(@RequestBody List<String> ids) {
        return prebooksService.updateTicketStatus(ids);
    }

    @ApiOperation(value = "税票预约-后台查询", notes = "")
    @GetMapping(value = "/backstage")
    public Result getAllPrebook() {
        return prebooksService.getAllPrebook();
    }




}
