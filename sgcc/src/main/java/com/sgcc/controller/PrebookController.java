package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.*;
import com.sgcc.service.PrebooksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "", tags = "税票预约接口")
@RestController
@RequestMapping(value = "/prebook")
public class PrebookController {

    @Autowired
    private PrebooksService prebooksService;

    /**********************************用户*****************************************/
    @ApiOperation(value = "提醒用户准时赴约", notes = "")
    @GetMapping(value = "/remind/appointment")
    public Result remindAppointment(@RequestParam String id) {
        return prebooksService.advanceSendMessageBackstage(id);
    }


    @ApiOperation(value = "加入黑名单", notes = "")
    @PostMapping(value = "/admin/blacklist")
    public Result addBlackList(@RequestBody List<String> ids) {
        return prebooksService.addBlackListBackstage(ids);
    }

//    @ApiOperation(value = "提醒审核人审核", notes = "")
//    @GetMapping(value = "/remind/check")
//    public Result remindCheck() {
//        return prebooksService.advanceSendMessageToChecker();
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

    @ApiOperation(value = "税票预约-取消预约", notes = "")
    @ApiImplicitParam(name = "id",value = "预约工单标识",required = true,paramType = "query",dataType = "String")
    @PutMapping(value = "/open-id/cancel")
    public Result cancelPrebook(@RequestParam String id) {
        return prebooksService.cancelPrebook(id);
    }


    /**********************************工作人员*************************************/

    @ApiOperation(value = "税票预约-审核列表", notes = "")
    @ApiImplicitParams(
        {
            @ApiImplicitParam(name = "openId",value = "公众号用户标识",required = true,paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "status", value = "预约状态", required = true, paramType = "query", dataType = "int",example = "1"),
            @ApiImplicitParam(name = "isPrinted",value = "是否打印",required = false,paramType = "query",dataType = "boolean"),
            @ApiImplicitParam(name = "condition",value = "搜索条件",required = false,paramType = "query",dataType = "String")
        }
    )
    @GetMapping(value = "/open-id/checkList")
    public Result getCheckList(@RequestParam String openId,
                               @RequestParam int status,
                               @RequestParam(required = false) Boolean isPrinted,
                               @RequestParam(required = false) String condition) {
        return prebooksService.getCheckList(openId,status,isPrinted,condition);
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

    @ApiOperation(value = "税票预约-导出excel", notes = "")
    @ApiImplicitParam(name = "date",value = "导出需要的时间段数据",required = true,paramType = "query",dataType = "String")
    @GetMapping("/exportMultiExcel")
    public void exportMultiExcel(@RequestParam String date, HttpServletResponse response){
        prebooksService.exportMultiExcel(date,response);
    }



}
