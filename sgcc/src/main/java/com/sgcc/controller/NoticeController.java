package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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



        return Result.success();
    }


}
