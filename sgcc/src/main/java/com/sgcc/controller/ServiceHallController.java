package com.sgcc.controller;
import com.example.result.Result;

import com.sgcc.dto.ServiceHallDeleteDTO;
import com.sgcc.dto.ServiceHallMappingDTO;
import com.sgcc.dto.UpdateServiceHallDTO;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "", tags = "地图接口")
@RestController
@RequestMapping(value = "/ServiceHall")
@Controller
public class ServiceHallController {
    @Autowired
    private ServiceHallService serviceHallService;
    @Autowired
    private PrebookService prebookService;


    /**
     * 根据定位查最近的5个
     * @param lat
     * @param lng
     * @return
     */
    @ApiOperation(value = "Nearest ServiceHall", notes = "")
    @GetMapping(value = "/NearestServiceHalls")
    public Result NearestServiceHalls(@RequestParam Double lat,@RequestParam Double lng) {
        return serviceHallService.NearestServiceHalls(lat,lng);
    }

    /**
     * 查行政区域所有营业厅
     * @param district
     * @return
     */
    @ApiOperation(value = "ServiceHall District", notes = "")
    @GetMapping(value = "/Districts/{district}")
    public Result ServiceHalls(@PathVariable String district) {
        return serviceHallService.serviceHalls(district);
    }

    /**
     * 根据用户id查询所有的预约信息
     * @param openId
     * @return
     */
    @ApiOperation(value = "查询用户的预约信息", notes = "")
    @GetMapping(value = "/PrebookInfos/user/{openId}")
    public Result getPrebookInfos(@PathVariable String openId) {
        return prebookService.getPrebookInfosByUser(openId);
    }

    /**
     * 用户提交在线预约
     * @return
     */
    @ApiOperation(value = "用户提交在线预约", notes = "")
    @PostMapping(value = "/PrebookInfos/user/{openId}")
    public Result submitPrebookInfo(@RequestBody PrebookDTO prebookDTO,@PathVariable String openId) {
        System.out.println("controller:threadID : "+Thread.currentThread().getId());
        return prebookService.submitPrebookInfo(prebookDTO,openId);
    }


    /**
     * 根据营业亭id查询营业厅预约状态
     * @param serviceHallId
     * @return
     */
    @ApiOperation(value = "查询营业厅预约状态", notes = "")
    @GetMapping(value = "/PrebookInfo/ServiceHall-Id/{serviceHallId}")
    public Result submitPrebookInfo(
            @PathVariable String serviceHallId
            ,@RequestParam
                    String prebookDate
    ) {
        return prebookService.getPrebookInfosByServiceHall(serviceHallId,prebookDate);
    }

    /**
     * 后台管理系统查询营业厅
     */
    @ApiOperation(value = "后台管理系统查询营业厅", notes = "")
    @GetMapping(value = "/ServiceHalls")
    public Result getServiceHalls(  )
    {
        return serviceHallService.findHallList();
    }
    /**
     * 后台管理系统修改营业厅
     */
    @ApiOperation(value = "后台管理系统修改营业厅", notes = "")
    @PutMapping(value = "/ServiceHalls/{id}")
    public Result updateServiceHalls(@RequestBody UpdateServiceHallDTO dto )
    {
        return serviceHallService.updateServiceHall(dto);
    }
    /**
     * 后台管理系统新增营业厅
     */
    @ApiOperation(value = "后台管理系统新增营业厅", notes = "")
    @PostMapping(value = "/ServiceHalls")
    public Result AddServiceHall(@RequestBody ServiceHallMappingDTO dto)
    {
        return serviceHallService.saveServiceHall(dto);
    }
    /**
     * 后台管理系统删除营业厅
     */
    @ApiOperation(value = "后台管理系统删除营业厅", notes = "")
    @DeleteMapping(value = "/ServiceHalls/{id}}")
    public Result deleteServiceHall(@PathVariable("id") String serviceHallId ) {
        return serviceHallService.delServiceHall(serviceHallId);
    }
    /**
     * 后台管理系统批量删除营业厅
     */
    @ApiOperation(value = "后台管理系统批量删除营业厅", notes = "")
    @PostMapping(value = "/ServiceHalls/Deletes")
    public Result deletes(@RequestBody ServiceHallDeleteDTO dto ) {
        return serviceHallService.delServiceHalls(dto.getIds());
    }
}
