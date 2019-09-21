package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dto.SuggestionDetailDTO;
import com.sgcc.dto.SuggestionUpdateDTO;
import com.sgcc.dto.SuggestionViewDTO;
import com.sgcc.dto.SuggestionSubmitDTO;
import com.sgcc.entity.event.SuggestionQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.SuggestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionService {
    @Autowired
    private SuggestionQueryEntity suggestionQueryEntity;

    public Result getSuggestion( String sugstId ) {
        SuggestionModel sugst = new SuggestionModel( );
        SuggestionDetailDTO ret = sugst.GetSuggestion(sugstId);
        if( ret == null )
            return Result.failure(TopErrorCode.GENERAL_ERR);
        return Result.success(ret);
    }

    public List<SuggestionViewDTO> getSuggestions( String openId ) {
        SuggestionModel sugst = new SuggestionModel( suggestionQueryEntity.GetAllSuggestions(openId) );
        return sugst.GetAllSuggestions();
    }

    public Result submit(SuggestionSubmitDTO submitDTO, String openId) {
        SuggestionModel sugst = new SuggestionModel( submitDTO );
        List<SuggestionViewDTO> dtos = sugst.submit();
        if( dtos == null || dtos.size() < 1 )
        {
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
        return Result.success(dtos);
    }

    public Result update(SuggestionUpdateDTO updateDTO ) {
        SuggestionModel sugst = new SuggestionModel( updateDTO );
        SuggestionViewDTO dto = sugst.reply();
        if( dto == null )
        {
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
        return Result.success(dto);
    }
}

