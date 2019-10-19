package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.service.FastDFSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "", tags = "工具")
@RestController
@RequestMapping(value = "/Utils")
@Controller
public class UtilController {
    @Autowired
    private FastDFSService fastDFSService;
    @ApiOperation(value = "上传文件", notes = "")
    @PostMapping(value = "/Files")
    public Result submitPrebookInfo(@RequestBody byte[] bytes, @RequestParam("suffix") String suffix) {
        return Result.success(fastDFSService.uploadIMG(bytes,suffix));
    }
}
