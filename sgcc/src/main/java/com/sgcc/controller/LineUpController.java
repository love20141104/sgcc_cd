package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.BasicInputDTO;
import com.sgcc.dto.LineUpQueryInputDTO;
import com.sgcc.dto.OnlineQueuingInputDTO;
import com.sgcc.service.FastDFSService;
import com.sgcc.service.LineUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "", tags = "线上排号")
@RestController
@RequestMapping(value = "/lineUp")
@Controller
public class LineUpController {

    @Autowired
    private LineUpService lineUpService;


    @ApiOperation(value = "心跳", notes = "")
    @PostMapping(value = "/user/heartBeat")
    public Result heartBeat() {
        return lineUpService.heartBeat();
    }


    @ApiOperation(value = "线上排队", notes = "")
    @PostMapping(value = "/user/number")
    public Result onlineQueuing(@RequestBody OnlineQueuingInputDTO dto) {
        return lineUpService.onlineQueuing(dto);
    }


    @ApiOperation(value = "排队查询", notes = "")
    @PostMapping(value = "/user/waitingNum")
    public Result lineUpQuery(@RequestBody LineUpQueryInputDTO dto) {
        return lineUpService.lineUpQuery(dto);
    }




}
