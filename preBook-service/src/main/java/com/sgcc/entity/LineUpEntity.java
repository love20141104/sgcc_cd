package com.sgcc.entity;

import com.alibaba.fastjson.JSONObject;
import com.example.ThreeTypeOneApi.SM3Util;
import com.example.ThreeTypeOneApi.SM4Util;
import com.google.common.base.Strings;
import com.sgcc.dto.BasicInputDTO;
import com.sgcc.dto.EncryptedDTO;
import com.sgcc.dto.LineUpInfoOutDTO;
import com.sgcc.dto.ReturnResultDTO;
import com.sgcc.repository.PreBooksRepository;
import com.sgcc.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class LineUpEntity {


    @Autowired
    private RestTemplate restTemplate;

    /**
     * 加密版
     * @param basicInputDTO
     * @return
     */
    public LineUpInfoOutDTO operatePost(BasicInputDTO basicInputDTO){
        LineUpInfoOutDTO lineUpInfoOutDTO = null;
        try {
            // 加密data
            String encryptData = SM4Util.encryptEcb(Constant.PRIVATE_KEY, JSONObject.toJSONString(basicInputDTO.getData()));
            String signature = encryptData+Constant.APPID+Constant.PRIVATE_KEY;
            // 加密签名
            String encryptSign = SM3Util.encrypt(signature.trim());
            EncryptedDTO encryptedDTO = new EncryptedDTO(Constant.APPID,encryptData,encryptSign);
            ReturnResultDTO dto = restTemplate.postForObject(Constant.URL,encryptedDTO,ReturnResultDTO.class);
            // 解密data
            if (null != dto && !Strings.isNullOrEmpty(dto.getData())){
                String decryptData = SM4Util.decryptEcb(Constant.PRIVATE_KEY, dto.getData());
                Map<String,String> data = JSONObject.parseObject(decryptData,Map.class);
                lineUpInfoOutDTO = new LineUpInfoOutDTO(dto.getCode(),dto.getMsg(),data);
            }else {
                lineUpInfoOutDTO = new LineUpInfoOutDTO(dto.getCode(),dto.getMsg(),null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineUpInfoOutDTO;
    }

//    public String test(BasicInputDTO basicInputDTO){
//        String res = "";
//        try {
//            String encryptData = SM4Util.encryptEcb(Constant.PRIVATE_KEY, JSONObject.toJSONString(basicInputDTO.getData()));
//            System.out.println("data==>"+encryptData);
//            String signature = encryptData+Constant.APPID+Constant.PRIVATE_KEY;
//            String encryptSign = SM3Util.encrypt(signature.trim());
//            System.out.println("signature==>"+encryptSign);
//            EncryptedDTO encryptedDTO = new EncryptedDTO(Constant.APPID,encryptData,encryptSign);
//            ReturnResultDTO dto = restTemplate.postForObject(Constant.URL,encryptedDTO, ReturnResultDTO.class);
//            // 解密data
//            if (!Strings.isNullOrEmpty(dto.getData())){
//                res = SM4Util.decryptEcb(Constant.PRIVATE_KEY, dto.getData());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return res;
//    }

    /**
     * 非加密版
     * @param basicInputDTO
     * @return
     */
//    public LineUpInfoOutDTO operatePost(BasicInputDTO basicInputDTO){
//        System.out.println(JSONObject.toJSONString(basicInputDTO));
//        LineUpInfoOutDTO lineUpInfoOutDTO =
//                restTemplate.postForObject(URL,basicInputDTO,LineUpInfoOutDTO.class);
//        return lineUpInfoOutDTO;
//    }



}
