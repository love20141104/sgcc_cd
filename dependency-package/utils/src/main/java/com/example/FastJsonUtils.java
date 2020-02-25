package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

public class FastJsonUtils {

    /**
     * 功能描述：把JSON数据转换成指定的java对象
     * @param jsonData JSON数据
     * @param clazz 指定的java对象
     * @return 指定的java对象
     */
    public static <T> T getJsonToBean(String jsonData, Class<T> clazz) {
        return JSON.parseObject(jsonData, clazz);
    }
    /**
     * 功能描述：把Object数据转换成指定的java对象
     * @param jsonData JSON数据
     * @param clazz 指定的java对象
     * @return 指定的java对象
     */
    public static <T> T getJsonToBean(Object jsonData, Class<T> clazz) {
        return JSON.parseObject(Object2String(jsonData), clazz);
    }

    /**
     * 功能描述：把java对象转换成JSON数据
     * @param object java对象
     * @return JSON数据
     */
    public static String getBeanToJson(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 功能描述：把JSON数据转换成指定的java对象列表
     * @param jsonData JSON数据
     * @param clazz 指定的java对象
     * @return List<T>
     */
    public static <T> List<T> getJsonToList(String jsonData, Class<T> clazz) {
        return JSON.parseArray(jsonData, clazz);
    }

    /**
     * 功能描述：把JSON数据转换成较为复杂的List<Map<String, Object>>
     * @param jsonData JSON数据
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getJsonToListMap(String jsonData) {
        return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
        });
    }
    /**
     * List集合转换成json字符串
     * @param obj
     * @return
     */
    public static String list2Json(Object obj){
        return JSONArray.toJSONString(obj, true);
    }

    /**
     * object转List
     * (不需要实体类)
     * @param jsonStr
     * @return
     */
    public static JSONArray json2List(Object jsonStr){
        return JSON.parseArray(Object2String(jsonStr));
    }
    /**
     * Object 转 json
     *
     * @param jsonStr
     * @return
     */
    public static JSONObject Object2json(Object jsonStr){
        return  JSONObject.parseObject(Object2String(jsonStr));
    }

    /**
     *   Object转JSON字符串:
     *
     * @param jsonStr
     * @return
     */
    public static String Object2String(Object jsonStr){
        return	JSONObject.toJSONString(jsonStr);
    }


}
