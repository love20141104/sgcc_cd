package com.sgcc.controller;
import com.example.result.Result;

import com.sgcc.service.ServiceHallService;
import com.sgcc.service.TestService;
import com.sgcc.service.WeChatService;
import com.sgcc.test.TestRedisDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.websocket.server.PathParam;
import java.util.List;

@Api(value = "", tags = "地图接口")
@RestController
@RequestMapping(value = "/ServiceHall")
@Controller
public class ServiceHallController {
    @Autowired
    private ServiceHallService serviceHallService;

    /**
     * 根据定位查最近的5个
     * @param lat
     * @param lng
     * @return
     */
    @ApiOperation(value = "Nearest ServiceHall", notes = "")
    @GetMapping(value = "/NearestServiceHalls")
    public Result getNearestServiceHalls(@RequestParam Double lat,@RequestParam Double lng) {
        return serviceHallService.NearestServiceHalls(lat,lng);
    }

    /**
     * 查行政区域所有营业厅
     * @param district
     * @return
     */
    @ApiOperation(value = "ServiceHall District", notes = "")
    @GetMapping(value = "/Districts/{district}")
    public Result getNearestServiceHalls(@PathVariable String district) {
        return serviceHallService.serviceHalls(district);
    }

    /**
     * 根据用户id查询所有的预约信息
     * @param openId
     * @return
     */
    @ApiOperation(value = "预约信息", notes = "")
    @GetMapping(value = "/PrebookInfos/user/{openId}")
    public Result getPrebookInfos(@PathVariable String openId) {
        return null;
    }

    /**
     * 用户提交在线预约
     * @return
     */
//    @ApiOperation(value = "提交预约信息", notes = "")
//    @PostMapping(value = "/PrebookInfo")
//    public Result submitPrebookInfo(@ResponseBody ) {
//
//        return null;
//    }
}
