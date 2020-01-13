package com.sgcc.entity;

import com.alibaba.fastjson.JSONObject;
import com.sgcc.dto.BasicInputDTO;
import com.sgcc.dto.EncryptedDTO;
import com.sgcc.dto.LineUpInfoOutDTO;
import com.sgcc.repository.PreBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LineUpEntity {

    private static final String URL = "http://120.27.51.118:8081/ThreeTypeOneApi/currency";

    @Autowired
    private RestTemplate restTemplate;


    public LineUpInfoOutDTO operatePost(BasicInputDTO basicInputDTO){
        System.out.println(JSONObject.toJSONString(basicInputDTO));
        LineUpInfoOutDTO lineUpInfoOutDTO =
                restTemplate.postForObject(URL,basicInputDTO,LineUpInfoOutDTO.class);
        return lineUpInfoOutDTO;
    }





}
