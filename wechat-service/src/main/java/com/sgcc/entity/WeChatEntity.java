package com.sgcc.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.FileUtil;
import com.example.Utils;
import com.example.constant.CommonConstants;
import com.example.constant.WechatURLConstants;
import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dto.*;
import com.sgcc.dtomodel.wechat.*;
import com.sgcc.dtomodel.wechat.template.TemplateMessage;
import com.sgcc.wechat.SignatureModel;
import com.sgcc.dao.AccessTokenDao;
import com.sgcc.dao.JSApiTicketDao;
import com.sgcc.entity.event.AccessTokenEntity;
import com.sgcc.entity.query.AccessTokenQueryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeChatEntity {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AccessTokenEntity accessTokenEntity;
    @Autowired
    private AccessTokenQueryEntity accessTokenQueryEntity;

    /**
     * 获取微信服务号的AccessToken
     * @return
     */
    public AccessTokenDTO getAccessToken(){
        AccessTokenDao accessTokenDao = null;
        try {
            accessTokenDao = accessTokenQueryEntity.findAccessToken();
            System.out.println("accessTokenDao access_token = "+ accessTokenDao.getAccess_token());
            return accessTokenDao.build();
        }
        catch ( java.util.NoSuchElementException e ){
            System.out.println("accessTokenDao access_token = null");
            AccessTokenDTO accessTokenDTO = restTemplate.getForObject(WechatURLConstants.GETACCESSTOKEN,AccessTokenDTO.class);
            accessTokenEntity.saveAccessToken(new AccessTokenDao(accessTokenDTO));
            return accessTokenDTO;
        }
    }

    /**
     * 获取微信js-api-ticket
     */
    public JSAPITicketDTO getJsApiTicket(){
        JSApiTicketDao jsApiTicketDao = null;
        try {
            jsApiTicketDao = accessTokenQueryEntity.findJSApiTicket();
            return jsApiTicketDao.build();
        }
        catch ( java.util.NoSuchElementException e ){
            JSAPITicketDTO jsapiTicketDTO = restTemplate
                    .getForObject
                            (
                                    WechatURLConstants.GET_JSAPI_TICKET
                                            .replace(
                                                    "ACCESS_TOKEN"
                                                    ,getAccessToken().getAccess_token()
                                            )
                                    ,JSAPITicketDTO.class);
            if( jsapiTicketDTO.getErrcode() == CommonConstants.SUCCESS )
            {
                accessTokenEntity.saveJSApiTicket(new JSApiTicketDao(jsapiTicketDTO));
            }
            return jsapiTicketDTO;
        }
    }

    /**
     * 获取签名
     * @param url
     * @return
     */
    public WXConfigDTO getSignature(String url) {
        return new SignatureModel(getJsApiTicket().getTicket(),url).build();
    }

    public byte[] downloadMedia(String mediaId)
    {
        String URL = WechatURLConstants.URL_GET_MEDIA.replace("ACCESS_TOKEN",getAccessToken().getAccess_token())
                                                     .replace("MEDIA_ID",mediaId);
        ResponseEntity<byte[]> rsp = restTemplate.getForEntity(URL,byte[].class );
        if( rsp.getStatusCode() == HttpStatus.OK ){
            return rsp.getBody();
        }
        return null;
    }



    /**
     *根据消息模板发送消息
     * @param templateMessage
     * @return
     * @throws Exception
     */
    public TempMessageDTO sendTempMsg(TemplateMessage templateMessage) throws Exception {

        String URL = WechatURLConstants.SEND_MESSAGE_TEMPLATE.replace("ACCESS_TOKEN",getAccessToken().getAccess_token());

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TemplateMessage> requestEntity = new HttpEntity<>(templateMessage, requestHeaders);
        System.out.println("发送模板消息URL："+URL);
        TempMessageDTO tempMessageDTO = restTemplate.postForObject(URL,requestEntity,TempMessageDTO.class);
        if (tempMessageDTO == null || tempMessageDTO.getErrcode()!=0){
            throw new Exception("模版消息发送失败，"+tempMessageDTO.getErrmsg());
        }
        return tempMessageDTO;
    }


  /*  public Result getRecharge(String openId) throws Exception {

        String URL = URLConstants.GETRECHARGE;

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("openId",openId);
        hashMap.put("date",Utils.GetTime(new Date()));
        HttpEntity<HashMap> requestEntity = new HttpEntity<>(hashMap, requestHeaders);

        JSONObject jsonObject = restTemplate.postForObject(URL, requestEntity, JSONObject.class);
        Result result= Utils.parseRecharge(jsonObject);
        return result;
    }*/





    /**
     * 获取素材列表
     * @param type
     * @param offset
     * @param count
     * @return
     */
    public Object getMaterial(String type,int offset,int count) {
        GetMaterialDTO getMaterialDTO = new GetMaterialDTO(type,offset,count);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GetMaterialDTO> requestEntity = new HttpEntity<>(getMaterialDTO, requestHeaders);
        try{
            String URL = WechatURLConstants.URL_GET_MEDIA_LIST.replace("ACCESS_TOKEN",getAccessToken().getAccess_token());
            MaterialsDTO materialsDTO = restTemplate.postForObject(URL,requestEntity,MaterialsDTO.class);
            return materialsDTO;
        }catch (Exception e){
            String s = restTemplate.postForObject(WechatURLConstants.GETACCESSTOKEN,requestEntity,String.class);
            return "\"String\":{" +s + "}";

        }

    }

    /**
     * 获取模板列表
     * @return
     */
    public TemplateRetListDTO getTempList()
    {
        String URL = WechatURLConstants.TEMPLATE.replace("ACCESS_TOKEN",getAccessToken().getAccess_token());
        TemplateRetListDTO ret  = restTemplate.getForObject(URL, TemplateRetListDTO.class);
        System.out.println(ret);
        if( ret == null || ret.getTemplate_list() == null || ret.getTemplate_list().size() < 1)
            return null;

        return ret;
    }


    /**
     * 新增图文消息图片
     * @return
     * @throws Exception
     */
    public Object uploadImg(MultipartFile media) {
        String URL = WechatURLConstants.UPLOAD_IMG
                .replace("ACCESS_TOKEN",getAccessToken().getAccess_token());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("multipart/form-data"));

        MultiValueMap<String,Object> param = new LinkedMultiValueMap<>();
        File file = null;
        try {
            file = FileUtil.multipartFileToFile(media);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileSystemResource fileSystemResource = new FileSystemResource(file);
        param.add("media",fileSystemResource);

        HttpEntity<MultiValueMap<String,Object>> requestEntity =
                new HttpEntity<MultiValueMap<String,Object>>(param, headers);
        String result = restTemplate.postForObject(URL,requestEntity,String.class);
        return JSONObject.parseObject(result);
    }

    /**
     * 获取公众号中所有user_open_id
     */
    public UserIDListDTO getOpenIds(String nextOpenID){

        String URL = WechatURLConstants.GET_OPENIDS
                .replace("ACCESS_TOKEN",getAccessToken().getAccess_token());
        if( !Strings.isNullOrEmpty(nextOpenID) )
        {
            URL += "&next_openid=" + nextOpenID;
        }
        System.out.println(URL);
        UserIDListDTO userIDListDTO = restTemplate.getForObject(URL, UserIDListDTO.class);
        return userIDListDTO;
    }


    /**
     * 获取公众号中所有用户信息
     */
    public UserInfoList getUserInfosByOpenIds(UserListSubmitDTO userListDTOS){

        String URL = WechatURLConstants.BATCH_GET_USER
                .replace("ACCESS_TOKEN",getAccessToken().getAccess_token());
        System.out.println(URL);
        if (userListDTOS != null){
            return restTemplate.postForObject(URL, userListDTOS, UserInfoList.class);
        }

        return null;
    }





}
