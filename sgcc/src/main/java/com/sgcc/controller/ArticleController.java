package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.ArticleMappingDTO;
import com.sgcc.dto.ArticleSubmitDTO;
import com.sgcc.dto.ArticleUpdateDTO;
import com.sgcc.dto.ArticleViewDTO;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "", tags = "文章")
@RestController
@RequestMapping(value = "/Articles")
@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "Get recommended articles", notes = "")
    @GetMapping(value = "")
    public Result GetArticles( @RequestParam("isRecommended") boolean isRecommended ) {
        List<ArticleViewDTO> dtos = articleService.GetArticles( isRecommended );

        if( dtos == null )
            return Result.failure(TopErrorCode.NO_DATAS);

        if(dtos.size() < 1)
            return Result.success();

        return Result.success(dtos);
    }

    @ApiOperation(value = "Get articles by type", notes = "")
    @GetMapping(value = "")
    public Result GetArticles( @RequestParam("articleType") String articleType ) {
        List<ArticleViewDTO> dtos = articleService.GetArticles( articleType );

        if( dtos == null )
            return Result.failure(TopErrorCode.NO_DATAS);

        if(dtos.size() < 1)
            return Result.success();

        return Result.success(dtos);
    }

    @ApiOperation(value = "Get All articles ", notes = "")
    @GetMapping(value = "")
    public Result GetArticles( ) {
        List<ArticleMappingDTO> dtos = articleService.GetArticles( );

        if( dtos == null )
            return Result.failure(TopErrorCode.NO_DATAS);

        if(dtos.size() < 1)
            return Result.success();

        return Result.success(dtos);
    }

    @ApiOperation(value = "Add article", notes = "")
    @PostMapping(value = "")
    public Result AddArticle( @RequestBody ArticleSubmitDTO dto ) {
        articleService.submit( dto );
        return Result.success();
    }

    @ApiOperation(value = "Update article", notes = "")
    @PutMapping(value = "/{id}")
    public Result UpdatedArticle( @PathVariable String id, @RequestBody ArticleUpdateDTO dto ) {
        articleService.update( dto );
        return Result.success();
    }

    @ApiOperation(value = "Delete articles ", notes = "")
    @PostMapping(value = "")
    public Result DeleteArticles( @RequestBody List<String> ids) {
        articleService.deletes(ids);
        return Result.success();
    }
}
