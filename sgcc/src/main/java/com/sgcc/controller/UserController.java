package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Api(value = "", tags = "月度账单接口")
@RestController
@RequestMapping(value = "/bill")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询月度账单", notes = "")
    @GetMapping(value = "/billInfo/{userNo}")
    public Result queryBillInfo(@PathVariable(required = true) String userNo,
                                @RequestParam(required = true) long date) {
        return userService.queryBillInfoById(userNo,date);
    }


}
