package com.example.controller;

import com.example.result.Result;

import com.example.service.TestService;
import com.example.test.TestRedisDTO;
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


}
