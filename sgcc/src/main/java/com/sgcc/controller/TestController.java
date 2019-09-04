package com.sgcc.controller;

import com.example.result.Result;

import com.sgcc.service.TestService;
import com.sgcc.service.WeChatService;
import com.sgcc.test.TestRedisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;


@Api(value = "", tags = "接口测试")
@RestController
@RequestMapping(value = "/test")
@Controller
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private WeChatService weChatService;
    /**
     * 测试数据库连接,从mysql中查找user
     * @return Result
     */
    @ApiOperation(value = "testmysql", notes = "")
    @GetMapping(value = "/testmysql")
    public Result testmysql() {
        return testService.findAllUesrs();
    }
    /**
     * 测试redis连接,将mysql中查找user到的user存入redis
     * @return Result
     */
    @ApiOperation(value = "testredis", notes = "")
    @PostMapping(value = "/testredis_save")
    public Result testRedisSave(@RequestBody List<TestRedisDTO> testRedisDTOS) {
        return Result.success(testService.saveInRedis(testRedisDTOS));
    }

    /**
     * 测试数据库连接,从mysql中查找user
     * @return Result
     */
    @ApiOperation(value = "testmysql", notes = "")
    @PostMapping(value = "/testredis_select")
    public Result testRedisSelect(@RequestBody List<String> ids) {
        return testService.findInRedis(ids);
    }

    /**
     * 测试数据库连接,从mysql中查找user
     * @return Result
     */
    @ApiOperation(value = "testmq", notes = "")
    @PostMapping(value = "/testmq")
    public Result testMq(@RequestBody TestRedisDTO testRedisDTO) {
        return testService.saveUser(testRedisDTO);
    }

    /**
     * 测试调用微信api
     */
    @ApiOperation(value = "testmq", notes = "")
    @GetMapping(value = "/testwechatapi")
    public Result testMq() {
        return weChatService.getAccessToken();
    }


}
