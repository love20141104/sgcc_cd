package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.PageStatisticsDto;
import com.sgcc.service.ApiStatisticsService;
import com.sgcc.service.PageStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "", tags = "接口统计接口")
@RestController
@RequestMapping(value = "/Statistics")
public class ApiStatisticsController {
    @Autowired
    private ApiStatisticsService apiStatisticsService;

    @Autowired
    private PageStatisticsService pageStatisticsService;

    @ApiOperation(value = "根据不同时间类型查询过去时间段的接口访问次数", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "datetype", value = "MONTH:从本月开始，往前推11个个月的数据," +
                    "WEEK:本周7天数据," +
                    "DAY:本月数据," +
                    "HOUR:今天数据", required = true, dataType = "String")
    })
    @GetMapping(value = "api/{datetype}")
    public Result apimonth12(@PathVariable String datetype) {
        return apiStatisticsService.getApiStatistcsDateDtoList(datetype);
    }

    @ApiOperation(value = "根据不同时间类型查询过去时间段的页面访问次数", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "datetype", value = "MONTH:从本月开始，往前推11个个月的数据," +
                    "WEEK:本周7天数据," +
                    "DAY:本月数据," +
                    "HOUR:今天数据", required = true, dataType = "String")
    })
    @GetMapping(value = "page/{datetype}")
    public Result pagemonth12(@PathVariable String datetype) {
        return pageStatisticsService.getPageStatistcsDateDtoList(datetype);
    }


    @ApiOperation(value = "每次跳转页面后添加统计", notes = "")
    @PostMapping(value = "/page")
    public Result addPageStatistics(@RequestBody PageStatisticsDto pageStatisticsDto) {
        return pageStatisticsService.addPageStatistics(pageStatisticsDto);
    }

}
