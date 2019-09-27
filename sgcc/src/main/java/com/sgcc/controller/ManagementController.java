package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dtomodel.prebook.PrebookDTO;

import com.sgcc.service.PrebookManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "", tags = "管理工具接口")
@RestController
@RequestMapping(value = "/PrebookManager")
@Controller
public class ManagementController {
    @Autowired
    private PrebookManager prebookManager;

    /**
     *  ================================= 预约信息start =================================
     */
    @ApiOperation(value = "修改预约信息", notes = "")
    @PostMapping(value = "/PrebookInfo/prebookCode")
    public Result updatePrebookDTO(@RequestParam String prebookCode, @RequestBody PrebookDTO prebookDTO) {
        return prebookManager.updatePrebookDTO(prebookCode,prebookDTO);
    }

    @ApiOperation(value = "删除预约信息", notes = "")
    @DeleteMapping(value = "/PrebookInfo/prebookCode")
    public Result deletePrebookDTO(@RequestParam String prebookCode) {
        return prebookManager.deletePrebookDTO(prebookCode);
    }

    @ApiOperation(value = "新增预约信息", notes = "")
    @PostMapping(value = "/PrebookInfo/open-Id/{openId}")
    public Result insertPrebookDTO(@PathVariable String openId,@RequestBody PrebookDTO prebookDTO) {
        return prebookManager.insertPrebookDTO(prebookDTO,openId);
    }

    @ApiOperation(value = "查询预约信息", notes = "")
    @GetMapping(value = "/PrebookInfo}")
    public Result selectPrebookDTO(
            @RequestParam(required = false) String user_open_id
            , @RequestParam(required = false) String service_hall_id
            , @RequestParam(required = false) String prebook_code
            , @RequestParam(required = false) String prebook_date_start
            , @RequestParam(required = false) String prebook_date_end

    ) {
        return prebookManager.selectPrebookDTO(user_open_id,service_hall_id,prebook_code,prebook_date_start,prebook_date_end);
    }
    /**
     *  ================================= 预约信息end +=================================
     */

}
