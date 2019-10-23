package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.service.ApiStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "", tags = "接口统计接口")
@RestController
@RequestMapping(value = "/apiStatistics")
public class ApiStatisticsController {
    @Autowired
    private ApiStatisticsService apiStatisticsService;

    @ApiOperation(value = "查询过去12月每个月的接口访问次数", notes = "")
    @GetMapping(value = "/month12")
    public Result find() {
        return apiStatisticsService.getApiStatistcsMonthDtoList();
    }

}
