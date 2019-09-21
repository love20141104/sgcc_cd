package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dto.SuggestionViewDTO;
import com.sgcc.dto.SuggetionSubmitDTO;
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

    public List<SuggestionViewDTO> getSuggestions( String openId ) {
        SuggestionModel sugst = new SuggestionModel( suggestionQueryEntity.GetAllSuggestions(openId) );
        return sugst.GetAllSuggestions();
    }

    public Result submit( SuggetionSubmitDTO submitDTO, String openId) {
        SuggestionModel sugst = new SuggestionModel( submitDTO ,openId);
        int resultcode = sugst.submit();
        if( resultcode != 0 )
        {
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
        return Result.success();
    }
}

