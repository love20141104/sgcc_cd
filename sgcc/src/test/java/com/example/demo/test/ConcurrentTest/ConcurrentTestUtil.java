package com.example.demo.test.ConcurrentTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.Utils;
import com.sgcc.config.HttpsClientRequestFactory;
import org.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 压力测试工具
 */
public class ConcurrentTestUtil {

    private static RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
    private static final String userName = "xiemin";

    public static void sendGet(String url,String message) throws JSONException {
        System.out.println("----------------------------------");
        System.out.println("测试接口："+message);
        JSONObject responseEntity = restTemplate.getForObject(url, JSONObject.class);
        List<Object> list = (List<Object>) responseEntity.get("data");
        System.out.println("data:"+list.size());
//        for (Object o : list){
//            System.out.println("data:"+o);
//        }
        System.out.println("----------------------------------");
    }



    public static void sendPost(String url, Map<String,Object> map,String message) throws JSONException {
        System.out.println("----------------------------------");
        System.out.println("测试接口："+message);
        JSONObject msg = new JSONObject();
        if (!map.isEmpty()){
            for (Map.Entry<String,Object> m : map.entrySet()){
                msg.put(m.getKey(),m.getValue());
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity request = new HttpEntity(msg.toString(), headers);
            JSONObject result = restTemplate.postForObject(url, request, JSONObject.class);
            System.out.println("data:"+result.get("data"));
        }
        System.out.println("----------------------------------");
    }



}
