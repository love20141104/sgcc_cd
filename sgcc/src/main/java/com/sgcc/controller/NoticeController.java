package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.AddFormDTO;
import com.sgcc.dto.NoticeFormDTO;
import com.sgcc.dto.UpdateFormDTO;
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

    @ApiOperation(value = "查询停电公告", notes = "")
    @GetMapping(value = "/noticeInfo/{district}")
    public Result queryNoticeInfo(@PathVariable(required = true) String district) {
        return noticeService.queryNoticeInfo(district);
    }


    @ApiOperation(value = "新增停电公告", notes = "")
    @PostMapping(value = "/noticeInfo")
    public Result addNoticeInfo(@RequestBody AddFormDTO addFormDTO) {
        return noticeService.insertNoticeInfo(addFormDTO);
    }


    @ApiOperation(value = "修改停电公告", notes = "")
    @PutMapping(value = "/noticeInfo")
    public Result updateNoticeInfo(@RequestBody UpdateFormDTO updateFormDTO) {
        return noticeService.updateNoticeInfo(updateFormDTO);
    }


    @ApiOperation(value = "删除停电公告", notes = "")
    @DeleteMapping(value = "/noticeInfo")
    public Result delNoticeInfo(@RequestBody List<String> ids) {
        return noticeService.delNoticeInfo(ids);
    }


}