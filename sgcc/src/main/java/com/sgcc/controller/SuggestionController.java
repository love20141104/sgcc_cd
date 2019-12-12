package com.sgcc.controller;

import com.example.Utils;
import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.ReplierAndCheckerDao;
import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionReplyInstDao;
import com.sgcc.dao.SuggestionReplyMappingDao;
import com.sgcc.dto.*;
import com.sgcc.dtomodel.wechat.template.TemplateData;
import com.sgcc.dtomodel.wechat.template.TemplateMessage;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.service.SuggestionService;
import com.sgcc.service.WeChatService;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Api(value = "", tags = "意见建议接口")
@RestController
@RequestMapping(value = "/Suggestions")
@Controller
public class SuggestionController {
    @Autowired
    private SuggestionService suggestionService;
    @Autowired
    private WeChatService weChatService;

    @ApiOperation(value = "获取意见列表", notes = "")
    @GetMapping(value = "/users/{id}")
    public Result Suggestions(@PathVariable("id") String userId) {
        List<SuggestionViewDTO> dtos = suggestionService.getSuggestions(userId);

        if( dtos == null )
            return Result.failure( TopErrorCode.NO_DATAS);

        if( dtos.size() < 1)
            return Result.success( );

        return Result.success(dtos);
    }

    @ApiOperation(value = "提交意见", notes = "")
    @PostMapping(value = "/users/{id}")
    public Result submit(@RequestBody SuggestionSubmitDTO suggestionSubmitDTO,
                         @PathVariable("id") String userId) {
        Result ret = suggestionService.submit(suggestionSubmitDTO,userId);
        // Todo 发送消息到信息回复人员
        SuggestionReplyInstDao dao = suggestionService.GetBySuggestionID( ret.getMsg() );
        if( dao != null )
        {
            TemplateMessage temp = new TemplateMessage();
            temp.setTemplate_id("HAv_qhY1qWVNXw20c1Fc_UBv02vtPAFSh1EiZvtp_qk");
            temp.setTouser(dao.getReply_openid());
            temp.setUrl("");

            Map<String, TemplateData> map = new LinkedHashMap<>();
            map.put("serviceInfo",new TemplateData("你好，有新的意见建议需要回复!","#000000"));
            map.put("serviceType",new TemplateData("意见建议","#000000"));
            map.put("serviceStatus",new TemplateData("刚提交","#000000"));
            map.put("time",new TemplateData(Utils.GetCurrentTime(),"#000000"));
            map.put("remark",new TemplateData("请尽快回复，谢谢!","#000000"));
            temp.setData( map );

            weChatService.SimpleSendMsg( temp );
        }
        return ret;
    }

    @ApiOperation(value = "回复意见", notes = "")
    @PostMapping(value = "/SuggestionReply/{id}")
    public Result suggestionReply(@RequestBody  SuggestionReplyContentDTO dto,
                         @PathVariable("id") String openID) {
        if( dto == null )
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        suggestionService.ReplyContent( dto );
        // Todo 发送消息到信息审核人员
        SuggestionReplyInstDao dao = suggestionService.GetBySuggestionID( dto.getSuggestion_id() );
        if( dao != null )
        {
            TemplateMessage temp = new TemplateMessage();
            temp.setTemplate_id("AmIrZpXB1wgKG9mrqDd0KWSAT9ML8l18Mhx-6n18ZgE");
            temp.setTouser( dao.getCheck_openid() );
            temp.setUrl("");

            Map<String, TemplateData> map = new LinkedHashMap<>();
            map.put("first",new TemplateData("你好，有新的意见建议回复需要审核!","#000000"));
            map.put("keyword1",new TemplateData("意见建议回复人员","#000000"));
            map.put("keyword2",new TemplateData(Utils.GetCurrentTime(),"#000000"));
            map.put("keyword3",new TemplateData(dto.getReply_content(),"#000000"));
            map.put("remark",new TemplateData("请尽快审核，谢谢!","#000000"));
            temp.setData( map );

            weChatService.SimpleSendMsg( temp );
        }

        return Result.success();
    }

    @ApiOperation(value = "审核意见", notes = "")
    @PostMapping(value = "/SuggestionCheck/{id}")
    public Result suggestionCheck(@RequestBody SuggestionReplyCheckDTO dto,
                         @PathVariable("id") String openID)
    {
        if( dto == null )
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        if( dto.getCheck_state() == 0 && !Strings.isNullOrEmpty(dto.getCheck_reject()) )
        {
            // 审核未通过 todo
            // AmIrZpXB1wgKG9mrqDd0KWSAT9ML8l18Mhx-6n18ZgE
            suggestionService.ReplyReject( dto.getSuggestion_id(),dto.getCheck_reject() );

            TemplateMessage temp = new TemplateMessage();
            temp.setTemplate_id("AmIrZpXB1wgKG9mrqDd0KWSAT9ML8l18Mhx-6n18ZgE");
            temp.setTouser( dto.getCheck_openid() );
            temp.setUrl("");

            Map<String, TemplateData> map = new LinkedHashMap<>();
            map.put("first",new TemplateData("你好，你的回复未通过审核!","#000000"));
            map.put("keyword1",new TemplateData("意见建议回复审核人员","#000000"));
            map.put("keyword2",new TemplateData(Utils.GetCurrentTime(),"#000000"));
            map.put("keyword3",new TemplateData(dto.getCheck_reject(),"#000000"));
            map.put("remark",new TemplateData("请尽快修改，谢谢!","#000000"));
            temp.setData( map );

            weChatService.SimpleSendMsg( temp );
            return Result.success();
        }

        suggestionService.ReplyCheck( dto );
        SuggestionReplyMappingDao dao = suggestionService.GetFullReplyInfo( dto.getSuggestion_id());
        Result ret = suggestionService.reply( new SuggestionReplyDTO(dto.getSuggestion_id(),
                dao.getReply_openid(),dao.getReply_content()) );
        if( ret != null && ret.getResultCode() == 0  )
        {
            SuggestionDetailDTO detailDTO = (SuggestionDetailDTO)suggestionService.getSuggestion(dto.getSuggestion_id()).getData() ;
            if( detailDTO != null && !Strings.isNullOrEmpty(detailDTO.getUserId()) )
            {
                // Todo 发送消息到用户
                // Yfv4siCzMo9MkeM9BEs61SlBf1KMTj2pHtMxn-OTYnc

                TemplateMessage temp = new TemplateMessage();
                temp.setTemplate_id("Yfv4siCzMo9MkeM9BEs61SlBf1KMTj2pHtMxn-OTYnc");
                temp.setTouser( detailDTO.getUserId() );
                temp.setUrl("");

                Map<String, TemplateData> map = new LinkedHashMap<>();
                map.put("first",new TemplateData("您好，您的意见建议已回复!","#000000"));
                map.put("keyword1",new TemplateData(detailDTO.getSuggestionContact(),"#000000"));
                map.put("keyword2",new TemplateData(detailDTO.getSuggestionTel(),"#000000"));
                map.put("keyword3",new TemplateData(Utils.GetTime(detailDTO.getReplyDate()),"#000000"));
                map.put("keyword4",new TemplateData(dao.getReply_content(),"#000000"));
                map.put("remark",new TemplateData("感谢您的意见，谢谢!","#000000"));
                temp.setData( map );

                weChatService.SimpleSendMsg( temp );
            }
        }
        return Result.success();
    }


    @ApiOperation(value = "查看意见", notes = "")
    @GetMapping(value = "/{id}")
    public Result Suggestion(@PathVariable("id") String suggestionId) {
        return suggestionService.getSuggestion(suggestionId);
    }

    @ApiOperation(value = "批量删除意见", notes = "")
    @PostMapping(value = "/Deletes")
    public Result delete(@RequestBody SuggestionDeleteDTO dto ) {
        return suggestionService.delete(dto);
    }

    /**
     * 后台管理系统查询意见
     */
    @ApiOperation(value = "后台管理系统查询意见", notes = "")
    @GetMapping(value = "/Suggestions")
    public Result getSuggestions(  )
    {
        return suggestionService.getSuggestions();
    }
    /**
     * 后台管理系统修改意见
     */
    @ApiOperation(value = "后台管理系统修改意见", notes = "")
    @PutMapping(value = "/Suggestions/{id}")
    public Result updateSuggestion(@RequestBody SuggestionMappingDTO dto , @PathVariable("id") String suggestionId)
    {
        return suggestionService.update(dto);
    }
    /**
     * 后台管理系统新增意见
     */
    @ApiOperation(value = "后台管理系统新增意见", notes = "")
    @PostMapping(value = "/Suggestions/{id}")
    public Result AddSuggestion(@RequestBody SuggestionMappingDTO dto, @PathVariable("id") String suggestionId )
    {
        return suggestionService.AddSuggestion(dto);
    }
    /**
     * 后台管理系统删除意见
     */
    @ApiOperation(value = "后台管理系统删除意见", notes = "")
    @DeleteMapping(value = "/Suggestions/{id}}")
    public Result deletes(@RequestBody SuggestionDeleteDTO dto, @PathVariable("id") String suggestionId ) {
        return suggestionService.delete(dto);
    }
    /**
     * 后台管理系统批量删除意见
     */
    @ApiOperation(value = "后台管理系统批量删除意见", notes = "")
    @PostMapping(value = "/Suggestions/Deletes")
    public Result deletes(@RequestBody SuggestionDeleteDTO dto ) {
        return suggestionService.delete(dto);
    }

    /**
     * 查询回复者和审核者用户信息
     * @return
     */
    @ApiOperation(value = "Get config", notes = "")
    @GetMapping(value = "/ReplyConfig")
    public Result findReplyConfig( @RequestParam(value = "region", required = false) String region ) {
        return Result.success(suggestionService.GetReplierAndChecker( region));
    }

    /**
     * 保存回复者和审核者用户信息
     * @return
     */
    @ApiOperation(value = "Save config", notes = "")
    @PostMapping(value = "/ReplyConfig")
    public Result SaveReplyConfig( @RequestBody ReplierAndCheckerSubmitDTO dto ) {
        return suggestionService.SaveReplierAndChecker( dto );
    }

    /**
     * 修改回复者和审核者用户信息
     * @return
     */
    @ApiOperation(value = "Update config", notes = "")
    @PutMapping(value = "/ReplyConfig")
    public Result UpdateReplyConfig( @RequestBody ReplierAndCheckerUpdateDTO dto ) {
        return suggestionService.UpdateReplierAndChecker( dto );
    }

    /**
     * 查询回复者所有意见建议
     * @return
     */
    @ApiOperation(value = "findAllSuggestionsNotReply", notes = "")
    @GetMapping(value = "/NotReply")
    public Result findAllSuggestionsNotReply( @RequestParam String reply_openid ) {
        return Result.success( suggestionService.findNotReply(reply_openid) );
    }

    /**
     * 查询审核者所有待审核
     * @return
     */
    @ApiOperation(value = "findAllSuggestionsNotCheck", notes = "")
    @GetMapping(value = "/NotCheck")
    public Result findAllSuggestionsNotCheck( @RequestParam String check_openid ) {
        return Result.success( suggestionService.findNotCheck(check_openid) );
    }

    /**
     * 回复人员查询所有驳回
     * @return
     */
    @ApiOperation(value = "findAllSuggestionsReject", notes = "")
    @GetMapping(value = "/Reject")
    public Result findAllSuggestionsReject( @RequestParam String reply_openid ) {
        return Result.success( suggestionService.findCheckNotPassedByReplyOpenID(reply_openid) );
    }

    /**
     * 审核人员查询所有驳回
     * @return
     */
    @ApiOperation(value = "findRejected", notes = "")
    @GetMapping(value = "/Rejected")
    public Result findAllSuggestionsRejected( @RequestParam String check_openid ) {
        return Result.success( suggestionService.findRejected(check_openid) );
    }


}
