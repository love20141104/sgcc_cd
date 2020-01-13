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

    private static final String URL = "http://120.27.51.118:8080/ThreeTypeOneApi/currency";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 心跳接口
     */
    // heartBeat(传入参数)
    public LineUpInfoOutDTO heartBeat(BasicInputDTO basicInputDTO){
        // TODO 将传入的参数进行加密
//        EncryptedDTO encryptedDTO = new EncryptedDTO();
        System.out.println(JSONObject.toJSONString(basicInputDTO));
        LineUpInfoOutDTO lineUpInfoOutDTO =
                restTemplate.postForObject(URL,JSONObject.toJSONString(basicInputDTO),LineUpInfoOutDTO.class);
        return lineUpInfoOutDTO;
    }


    /**
     * 线上排队接口
     * @return
     */
    // onlineQueuing(传入参数)
    public LineUpInfoOutDTO onlineQueuing(){
        // TODO 将传入的参数进行加密

        EncryptedDTO encryptedDTO = new EncryptedDTO();

        LineUpInfoOutDTO lineUpInfoOutDTO =
                restTemplate.postForObject(URL,encryptedDTO,LineUpInfoOutDTO.class);
        return lineUpInfoOutDTO;
    }


    /**
     * 排队查询接口
     * @return
     */
    // onlineQueuing(传入参数)
    public LineUpInfoOutDTO lineUpQuery(){
        // TODO 将传入的参数进行加密

        EncryptedDTO encryptedDTO = new EncryptedDTO();

        LineUpInfoOutDTO lineUpInfoOutDTO =
                restTemplate.postForObject(URL,encryptedDTO,LineUpInfoOutDTO.class);
        return lineUpInfoOutDTO;
    }






}
