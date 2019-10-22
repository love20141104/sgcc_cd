package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.Service.QuestionService;
import com.sgcc.dtomodel.prebook.PrebookDTO;

import com.sgcc.dtomodel.question.CategrateInsertDTO;
import com.sgcc.dtomodel.question.QADTO;
import com.sgcc.dtomodel.question.QuestionCategoryDTO;
import com.sgcc.service.PrebookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "", tags = "管理工具接口")
@RestController
@RequestMapping(value = "/PrebookManager")
@Controller
public class ManagementController {
    @Autowired
    private PrebookService prebookManager;

    @Autowired
    private QuestionService questionManger;

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

    @ApiOperation(value = "批量删除预约信息", notes = "")
    @DeleteMapping(value = "/PrebookInfo/prebookCodes")
    public Result deletePrebookDTOs(@RequestBody List<String> prebookCodes) {
        return prebookManager.deletePrebookDTOs(prebookCodes);
    }

    @ApiOperation(value = "查询预约信息", notes = "")
    @GetMapping(value = "/PrebookInfo")
    public Result selectPrebookDTO(
            @RequestParam(required = false) String user_open_id
            , @RequestParam(required = false) String service_hall_id
            , @RequestParam(required = false) String prebook_code
            , @RequestParam(required = false) String prebook_date_start
            , @RequestParam(required = false) String prebook_date_end

    ) {
        return prebookManager.selectPrebookDTO(user_open_id,service_hall_id,prebook_code,prebook_date_start,prebook_date_end);
    }

    @ApiOperation(value = "新增问题分类", notes = "")
    @PostMapping(value = "/SuggestionInfo/addQCategory")
    public Result insertQuestionCategoryDTO(@RequestBody CategrateInsertDTO categrateInsertDTO) {
            return questionManger.insertQuestionCategory(categrateInsertDTO);
    }

    @ApiOperation(value = "修改问题分类", notes = "")
    @PostMapping(value = "/SuggestionInfo/updateQCategory")
    public Result updateQuestionCategoryDTO(@RequestBody QuestionCategoryDTO questionCategoryDTO) {
        return questionManger.updateQuestionCategory(questionCategoryDTO);
    }

    @ApiOperation(value = "删除问题分类", notes = "")
    @DeleteMapping(value = "/SuggestionInfo/deleteQCategory")
    public Result deleteQuestionCategoryDTO(@RequestBody List<String> categoryIds) {
        return questionManger.deleteQuestionCategory(categoryIds);
    }

    @ApiOperation(value = "查询问题分类", notes = "")
    @GetMapping(value = "/SuggestionInfo/queryQCategory")
    public Result selectQuestionCategoryDTO(
            @RequestParam(required = false) String categoryId
            , @RequestParam(required = false) String categoryDesc
            , @RequestParam boolean available
    ) {
        return questionManger.selectQuestionCategory(categoryId,categoryDesc,available);
    }

    @ApiOperation(value = "新增问题问答", notes = "")
    @PostMapping(value = "/SuggestionInfo/addQA/{categoryId}")
    public Result insertQADTO(@PathVariable String categoryId,@RequestBody QADTO qadto) {
        return questionManger.insertQA(categoryId,qadto);
    }

    @ApiOperation(value = "修改问题问答", notes = "")
    @PostMapping(value = "/SuggestionInfo/updateQA/{categoryId}")
    public Result updateQADTO(@PathVariable String categoryId,@RequestBody QADTO qadto) {
        return questionManger.updateQA(categoryId,qadto);
    }

    @ApiOperation(value = "删除问题问答", notes = "")
    @DeleteMapping(value = "/SuggestionInfo/deleteQA")
    public Result deleteQADTO(@RequestBody List<String> categoryIds) {
        return questionManger.deleteQA(categoryIds);
    }

    @ApiOperation(value = "查询问题问答", notes = "")
    @GetMapping(value = "/SuggestionInfo/queryQA")
    public Result selectQADTO(
            @RequestParam(required = false) String category_name
            , @RequestParam(required = false) String question_desc
            , @RequestParam(required = false) String answer
    ) {
        return questionManger.selectQADTO(category_name,question_desc,answer);
    }

    /**
     *  ================================= 常见问题end +=================================
     */







}
