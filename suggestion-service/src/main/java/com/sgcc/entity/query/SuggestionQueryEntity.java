package com.sgcc.entity.query;

import com.sgcc.dao.*;
import com.sgcc.repository.ReplierAndCheckerRepository;
import com.sgcc.repository.SuggestionRedisRepository;
import com.sgcc.repository.SuggestionReplyRepository;
import com.sgcc.repository.SuggestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuggestionQueryEntity {
    @Autowired
    private SuggestionsRepository suggestionsRepository;
    @Autowired
    private SuggestionRedisRepository suggestionRedisRepository;
    @Autowired
    private ReplierAndCheckerRepository replierAndCheckerRepository;
    @Autowired
    private SuggestionReplyRepository suggestionReplyRepository;



    public List<SuggestionReplyInfoDao> getSuggestionReplyByOpenId(String openId,Integer status){
        return suggestionReplyRepository.getSuggestionReplyByOpenId(openId,status);
    }


    public SuggestionReplyInstDao getInstReply(String sgstId )
    {
        return suggestionReplyRepository.getInst( sgstId );
    }
    public SuggestionReplyMappingDao GetBySuggestionID(String sgstid )
    {
        return suggestionReplyRepository.GetBySuggestionID(sgstid);
    }

    public List<SuggestionRedisDao> GetAllsuggestions(String userId)
    {
        return suggestionRedisRepository.findAllByUserId(userId);
    }


    public List<SuggestionDao> GetAllSuggestions(String userId )
    {
        return suggestionsRepository.findAllByUserID(userId);
    }

    public SuggestionRedisDao GetRedisSuggestion(String suggestionId )
    {
        return suggestionRedisRepository.findBySuggestionId(suggestionId);
    }

    public SuggestionReplyInfoDao GetSuggestion(String suggestionId )
    {
        return suggestionsRepository.findBySuggestionId(suggestionId);
    }
    public List<SuggestionDao> findAllByReplyOpenID( String reply_openid)
    {
        return suggestionsRepository.findAllByReplyOpenID(reply_openid);
    }

    public List<SuggestionDao> findAllByCheckOpenID( String reply_openid)
    {
        return suggestionsRepository.findAllByCheckOpenID(reply_openid);
    }

    public List<SuggestionRejectDao> findCheckNotPassedByReplyOpenID( String reply_openid)
    {
        return suggestionsRepository.findCheckNotPassedByReplyOpenID(reply_openid);
    }

    public List<SuggestionRejectDao> findRejected( String check_openid)
    {
        return suggestionsRepository.findRejected(check_openid);
    }

    public SuggestionDao findById(String id)
    {
        return suggestionsRepository.findById(id);
    }
    public List<SuggestionDao> GetSuggestions()
    {
        return suggestionsRepository.findAll();
    }


    public List<ReplierAndCheckerDao> findAllByRegion(String Region){
        return replierAndCheckerRepository.findAllByRegion(Region);
    }

    public List<ReplierAndCheckerDao> GetAll(){
        return replierAndCheckerRepository.findAll();
    }

    public Integer getRoleByOpenId(String openId){
        Boolean replier = replierAndCheckerRepository.replierByOpenId(openId);
        Boolean checker = replierAndCheckerRepository.checkerByOpenId(openId);
        if(replier&&checker){
            return 4;
        }
        if(checker){
            return 3;
        }
        if(replier){
            return 2;
        }else {
            return 1;
        }
    }
    public List<SuggestionReplyCheckInfoDao> suggestionReplyCheckInfoList(String checkerOpenid ,Boolean checkState){
        return  suggestionReplyRepository.suggestionReplyCheckInfoDaoList(checkerOpenid,checkState);
    }

    public String getReplyOpenId(String userLocation) {
        return  replierAndCheckerRepository.getReplyOpenId(userLocation);
    }
}
