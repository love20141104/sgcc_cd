package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.PageStatisticsDto;
import com.sgcc.dto.PayQueryDTO;
import com.sgcc.service.ApiStatisticsService;
import com.sgcc.service.ChartService;
import com.sgcc.service.PageStatisticsService;
import com.sgcc.service.WechatPayResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "", tags = "接口统计接口")
@RestController
@RequestMapping(value = "/Statistics")
public class StatisticsController {
    @Autowired
    private ApiStatisticsService apiStatisticsService;

    @Autowired
    private PageStatisticsService pageStatisticsService;

    @Autowired
    private WechatPayResultService wechatPayResultService;

    @Autowired
    private ChartService chartService;


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


    /******************************************缴费统计************************************************/
    @ApiOperation(value = "缴费结果-统计", notes = "")
    @GetMapping(value = "/payResult")
    public Result queryPayResultByYearOrMonth(@RequestBody PayQueryDTO payQueryDTO) {
        return wechatPayResultService.findPayResultByYearOrMonth(payQueryDTO);
    }

    @ApiOperation(value = "总销售额和日均销售额", notes = "")
    @GetMapping(value = "/chart/totalFeesAndAverage")
    public Result queryTotalFeesAndAverageChart() {
        return chartService.findTotalFeesAvgChart();
    }

    @ApiOperation(value = "柱状图-支付笔数", notes = "")
    @GetMapping(value = "/chart/paymentTimes")
    public Result queryPaymentTimesChart() {
        return chartService.findPaymentTimesChart();
    }


    @ApiOperation(value = "柱状图-缴费金额", notes = "")
    @GetMapping(value = "/chart/paymentAmount")
    public Result queryPaymentAmountChart() {
        return chartService.findPaymentAmountChart();
    }


}
