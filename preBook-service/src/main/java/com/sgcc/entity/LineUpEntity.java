package com.sgcc.entity;

import com.sgcc.dto.EncryptedDTO;
import com.sgcc.dto.LineUpInfoOutDTO;
import com.sgcc.repository.PreBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LineUpEntity {

    private static final String URL = "http://120.27.51.118:8080/ThreeTypeOneApi/currency";

    @Autowired
    private RestTemplate restTemplate;


    public LineUpInfoOutDTO onlineQueuing(){

        EncryptedDTO encryptedDTO = new EncryptedDTO();

        LineUpInfoOutDTO lineUpInfoOutDTO = restTemplate.getForObject(URL,LineUpInfoOutDTO.class,encryptedDTO);
        return lineUpInfoOutDTO;
    }





}
