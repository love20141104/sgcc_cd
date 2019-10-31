package com.sgcc.interceptor;

import com.sgcc.dao.ApiStatisticsDao;
import com.sgcc.producer.ApiStatisticsProducer;
import org.elasticsearch.common.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiStatisticsInterceptor implements HandlerInterceptor {
    @Autowired
    private ApiStatisticsProducer apiStatisticsProducer;
    static Logger logger = LoggerFactory.getLogger(ApiStatisticsInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        String userOpenId=request.getParameter("userOpenId");
        String apiUrl=request.getRequestURL().toString();
       // if(!Strings.isNullOrEmpty(userOpenId)&&!apiUrl.contains("Statistics")){
            // 记录下请求内容
            String requestMethod=request.getMethod();
        String requestURI = request.getRequestURI();

        int startIndex = requestURI.lastIndexOf("/") + 1;

        String substring = requestURI.substring(startIndex);
        if(isValidUUID(substring)||isInteger(substring)||isOpenId(substring)){
            requestURI=requestURI.substring(0,startIndex)+"{id}";
        }


        String clientIp=getIpAddress();
            ApiStatisticsDao apiStatisticsDao = new ApiStatisticsDao(UUID.randomUUID().toString(), apiUrl,requestMethod,requestURI, userOpenId, new Date(), clientIp,null);
            //发mq
            apiStatisticsProducer.apiStatisticsMQ(apiStatisticsDao);
            logger.info(apiStatisticsDao.toString());
       // }

    }

    /**
     * 标准的UUID
     * 32位16进制的数字，用分隔符分成8-4-4-4-12的格式
     */
    private static final Pattern UUID_PATTERN = Pattern.compile("[0-9a-f]{8}(-[0-9a-f]{4}){3}-[0-9a-f]{12}");
    private static final Pattern OPENID_PATTERN = Pattern.compile("[-A-Za-z0-9_]{28}");
    public final static Pattern REGEX_INTEGER = Pattern.compile("^[-\\+]?\\d+$");

    /**
     * 判断一个字符串是否是有效的UUID
     *
     * @param uuid
     * @return
     */
    public static boolean isValidUUID(String uuid) {
        Matcher matcher = UUID_PATTERN.matcher(uuid);
        return matcher.matches();
    }
    public static boolean isInteger(String orginal) {
        Matcher isNum = REGEX_INTEGER.matcher(orginal);
        return isNum.matches();
    }

    public static boolean isOpenId(String str){
        Matcher matcher = OPENID_PATTERN.matcher(str);
        return matcher.matches();
    }

    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }
    //获取ip
    public static String getIpAddress() {
        HttpServletRequest request = getHttpServletRequest();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        }else if (ip != null && ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }


}
