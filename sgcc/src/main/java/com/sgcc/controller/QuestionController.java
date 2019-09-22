package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.Service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "", tags = "常见问题")
@RestController
@RequestMapping(value = "/Question")
@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    /**
     * 查问题分类列表
     *
     * @return
     */
    @ApiOperation(value = "QuestionCategory", notes = "")
    @GetMapping(value = "/QuestionCategory")
    public Result getQuestionCategory() {
        return Result.success(questionService.getQuestionCategory());
    }

    /**
     * 根据问题分类id查询QA
     *
     * @param categoryId
     * @return
     */
    @ApiOperation(value = "QuestionAnwser", notes = "")
    @GetMapping(value = "/QuestionAnwser/category-Id/{categoryId}")
    public Result getQAList(@PathVariable String categoryId) {
        return Result.success(questionService.getQAList(categoryId));
    }

    /**
     * 添加问题分类
     */

    /**
     * 修改问题分类
     */

    /**
     * 作废问题分类
     */

    /**
     * 在问题分类下添加问题回答
     */

    /**
     * 修改问题分类下的问题回答
     */

    /**
     * 作废问题分类下的问题回答
     */


}