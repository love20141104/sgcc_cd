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
@RequestMapping(value = "/map")
@Controller
public class MapController {
    @Autowired
    private ServiceHallService serviceHallService;

    @ApiOperation(value = "Nearest ServiceHall", notes = "")
    @GetMapping(value = "/NearestServiceHalls")
    public Result getNearestServiceHalls(@RequestParam Double lat,@RequestParam Double lng) {
        return serviceHallService.NearestServiceHalls(lat,lng);
    }

    @ApiOperation(value = "ServiceHall District", notes = "")
    @GetMapping(value = "/Districts/{district}")
    public Result getNearestServiceHalls(@PathVariable String district) {
        return serviceHallService.serviceHalls(district);
    }
}
