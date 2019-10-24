package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.BannerDTO;
import com.sgcc.dto.BannerSubmitDTO;
import com.sgcc.dto.DeleteDTO;
import com.sgcc.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "", tags = "系统设置接口")
@RestController
@RequestMapping(value = "/Settings")
@Controller
public class SystemSettingController {
    @Autowired
    private BannerService bannerService;
    // -------------------------banner设置--------------------------------------
    // -------------------------banner设置--------------------------------------
    @ApiOperation(value = "banner提交", notes = "")
    @PostMapping(value = "/Banner")
    public Result SubmitBanner(@RequestBody BannerSubmitDTO dto ) {
        bannerService.SubmitBanner(dto);
        return Result.success();
    }

    @ApiOperation(value = "banner更新", notes = "")
    @PutMapping(value = "/Banner")
    public Result UpdateBanner(@RequestBody BannerDTO dto ) {
        bannerService.UpdateBanner(dto);
        return Result.success();
    }

    @ApiOperation(value = "banner查询", notes = "")
    @GetMapping(value = "/Banner")
    public Result GetBanners() {
        return Result.success( bannerService.GetAllBanners() );
    }

    @ApiOperation(value = "banner查询", notes = "")
    @GetMapping(value = "/Banner/{id}")
    public Result GetBanner(@PathVariable("id") String id ) {
        return Result.success(bannerService.GetBanner(id));
    }

    @ApiOperation(value = "banner删除", notes = "")
    @PostMapping(value = "/Banner/Deletes")
    public Result DeleteBanners(@RequestBody DeleteDTO dto ) {
        bannerService.DeleteBanners(dto);
        return Result.success();
    }
}
