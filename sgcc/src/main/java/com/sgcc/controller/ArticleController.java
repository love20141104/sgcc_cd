package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.ArticleMappingDTO;
import com.sgcc.dto.ArticleSubmitDTO;
import com.sgcc.dto.ArticleUpdateDTO;
import com.sgcc.dto.ArticleViewDTO;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.service.ArticleService;
import io.netty.util.internal.StringUtil;
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

    @ApiOperation(value = "Get articles by type", notes = "")
    @GetMapping(value = "")
    public Result GetArticlesBy( @RequestParam(value ="articleType", required = false) String articleType,
                               @RequestParam(value ="isRecommended", required = false) boolean isRecommended )
    {
        List<ArticleViewDTO> dtos = null ;
        do{
            // 推荐阅读
            if( isRecommended )
            {
                dtos = articleService.GetArticles( isRecommended );
                break;
            }
            // 文章类型
            if( !StringUtil.isNullOrEmpty(articleType) )
            {
                dtos = articleService.GetArticles( articleType );
                break;
            }
            // 所有
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }while(false);

        if( dtos == null )
            return Result.failure(TopErrorCode.NO_DATAS);

        if(dtos.size() < 1)
            return Result.success();

        return Result.success(dtos);
    }

    @ApiOperation(value = "Get articles", notes = "")
    @GetMapping(value = "/manager/articles")
    public Result GetArticles( @RequestParam(value ="articleType", required = false) String articleType,
                               @RequestParam(value ="isRecommended", required = false) boolean isRecommended                          ) {
        List<ArticleMappingDTO> dtos = null ;
        do{
            // 推荐阅读
            if( isRecommended )
            {
                dtos = articleService.GetArticlesByRecommended( isRecommended );
                break;
            }
            // 文章类型
            if( !StringUtil.isNullOrEmpty(articleType) )
            {
                dtos = articleService.GetArticlesByArticleType( articleType );
                break;
            }
            // 所有
            dtos = articleService.GetAllArticles();
        }while(false);

        if( dtos == null )
            return Result.failure(TopErrorCode.NO_DATAS);

        if( dtos.size() < 1 )
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
    @PostMapping(value = "/deletes")
    public Result DeleteArticles( @RequestBody List<String> ids) {
        articleService.deletes(ids);
        return Result.success();
    }
}
