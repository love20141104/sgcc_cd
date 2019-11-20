package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.Enum.JobEnum;
import com.sgcc.dto.AddFormDTO;
import com.sgcc.dto.NewJobSubmitDTO;
import com.sgcc.dto.NoticeFormDTO;
import com.sgcc.dto.UpdateFormDTO;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.service.JobService;
import com.sgcc.service.MessageNotificationService;
import com.sgcc.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "", tags = "停电公告接口")
@RestController
@RequestMapping(value = "/notice")
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private JobService jobService;

    @Autowired
    private MessageNotificationService messageNotificationService;

    /******************************************工单**********************************************/

    @ApiOperation(value = "工单-新增", notes = "")
    @PostMapping(value = "/open-id/{openId}/job")
    public Result saveJob(@PathVariable String openId, @RequestBody NewJobSubmitDTO newJobSubmitDTO) {
        return jobService.saveJob(openId,newJobSubmitDTO);
    }

    @ApiOperation(value = "工单-删除", notes = "")
    @DeleteMapping(value = "/open-id/job")
    public Result delJob(@RequestBody List<String> ids) {
        return jobService.delJob(ids);
    }

    @ApiOperation(value = "工单状态-修改", notes = "")
    @PutMapping(value = "/open-id/job/{jobId}/status")
    public Result updateJobStatus(@PathVariable String jobId, @RequestParam JobEnum jobEnum) {
        return jobService.updateJobStatus(jobId,jobEnum);
    }


    @ApiOperation(value = "抢修进度-查询", notes = "")
    @GetMapping(value = "/open-id/jobNo/progress")
    public Result selectProgressByNoticeId(@PathVariable String noticeId) {
        return jobService.selectProgressByNoticeId(noticeId);
    }






    /*****************************************停电公告***********************************************/
    @ApiOperation(value = "停电公告-查询", notes = "")
    @GetMapping(value = "/noticeInfo/{district}")
    public Result queryNoticeInfo(@PathVariable(required = true) String district) {
        return noticeService.queryNoticeInfo(district);
    }

    @ApiOperation(value = "停电公告-查询", notes = "")
    @GetMapping(value = "/noticeInfo")
    public Result queryNoticeInfo() {
        return noticeService.findNoticeListAll();
    }

    @ApiOperation(value = "停电公告-新增", notes = "")
    @PostMapping(value = "/noticeInfo")
    public Result addNoticeInfo(@RequestBody AddFormDTO addFormDTO) {
        return noticeService.insertNoticeInfo(addFormDTO);
    }

    @ApiOperation(value = "停电公告-修改", notes = "")
    @PutMapping(value = "/noticeInfo")
    public Result updateNoticeInfo(@RequestBody UpdateFormDTO updateFormDTO) {
        return noticeService.updateNoticeInfo(updateFormDTO);
    }

    @ApiOperation(value = "停电公告-删除", notes = "")
    @DeleteMapping(value = "/noticeInfo")
    public Result delNoticeInfo(@RequestBody List<String> ids) {
        return noticeService.delNoticeInfo(ids);
    }

    @ApiOperation(value ="短信群法接口",notes = "")
    @PostMapping(value = "/messageNotification")
    public Result doSendSms(@RequestBody List<String> phoneNums,@RequestParam String content){
        try{
            messageNotificationService.doSendSms(phoneNums,content);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }
}
