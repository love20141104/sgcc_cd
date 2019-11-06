package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.PageStatisticsDto;
import com.sgcc.dto.PayQueryDTO;
import com.sgcc.service.ApiStatisticsService;
import com.sgcc.service.ChartService;
import com.sgcc.service.PageStatisticsService;
import com.sgcc.service.WechatPayResultService;
import com.sgcc.sgccenum.DateRangeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "", tags = "统计接口")
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


    @ApiOperation(value = "查询过去12月的页面访问次数", notes = "")
    @GetMapping(value = "page/month")
    public Result pagemonth() {
        return pageStatisticsService.getPageStatistcsMonth();
    }

    @ApiOperation(value = "查询过去30天的页面访问次数", notes = "")
    @GetMapping(value = "page/day")
    public Result pageday() {
        return pageStatisticsService.getPageStatistcsDay();
    }


    @ApiOperation(value = "每次跳转页面后添加统计", notes = "")
    @PostMapping(value = "/page")
    public Result addPageStatistics(@RequestBody PageStatisticsDto pageStatisticsDto) {
        return pageStatisticsService.addPageStatistics(pageStatisticsDto);
    }

    @ApiOperation(value = "查询热门功能", notes = "")
    @GetMapping(value = "page/hot")
    public Result getHotPage(){
        return pageStatisticsService.getHotPage();
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

    /****************************************阅读量仪表盘start************************************************/

    @ApiOperation(value = "阅读量仪表盘数据", notes = "")
    @GetMapping(value = "/ReadingQuantity")
    public Result getReadingQuantityStatistcs(@RequestParam DateRangeEnum dateRangeEnum) {
        return chartService.getReadingQuantityStatistcs(dateRangeEnum);
    }
    /****************************************阅读量仪表盘end************************************************/

}
