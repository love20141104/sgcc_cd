package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.service.FastDFSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "", tags = "工具")
@RestController
@RequestMapping(value = "/Utils")
@Controller
public class UtilController {
    @Autowired
    private FastDFSService fastDFSService;

    @ApiOperation(value = "上传文件", notes = "")
    @PostMapping(value = "/Upload")
    public Result upLoadIMG(@RequestParam("file") MultipartFile srcFile) {
        return Result.success(fastDFSService.uploadIMG(srcFile));
    }


}
