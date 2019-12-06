package com.sgcc.service;

import com.example.Utils;
import com.example.constant.WechatURLConstants;
import com.example.result.Result;
import com.sgcc.dto.*;
import com.sgcc.dtomodel.wechat.JSAPITicketDTO;
import com.sgcc.dtomodel.wechat.template.TemplateData;
import com.sgcc.dtomodel.wechat.template.TemplateMessage;
import com.sgcc.entity.WeChatEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.wxpay.Sgcc_WXPay;
import com.sgcc.wxpay.sdk.WXPayUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class WeChatService {

    @Autowired
    private WeChatEntity weChatEntity;

    static int retries = 0;
    /**
     * 获取微信服务号的AccessToken
     * @return
     */
    public Result getAccessToken(){
        return Result.success(weChatEntity.getAccessToken());
    }

    /**
     * 获取微信js-api-ticket
     */
    public JSAPITicketDTO getJsApiTicket(){
        return weChatEntity.getJsApiTicket();
    }

    /**
     * 获取Signature
     * @param url
     * @return
     */
    public Result getSignature(String url){
        System.out.println("url:"+url);
        return Result.success(weChatEntity.getSignature(url));
    }

    /**
     * 获取图文素材
     */
    public Result getMaterial(String type,int offset,int count){
        return Result.success(weChatEntity.getMaterial(type,offset,count));
    }


    /**
     * 新增图文消息图片
     */
    public Result uploadImg(MultipartFile file){
        return Result.success(weChatEntity.uploadImg(file));
    }



    /**
     *
     * @param openId
     * @param totalFee
     * @return
     * @throws Exception
     */
    public Result getPrepay(String openId,String totalFee) throws Exception {
        Sgcc_WXPay sgcc_wxPay = new Sgcc_WXPay();
        Map<String,String > prepay = sgcc_wxPay.unifiedorder(openId,totalFee);

        prepay.get("prepay_id");
        Map<String,String > requ = new HashMap<>();
        requ.put("appId", WechatURLConstants.APPID);
        requ.put("timeStamp",Utils.create_timestamp());
        requ.put("nonceStr",Utils.create_nonce_str());
        requ.put("package","prepay_id="+ prepay.get("prepay_id"));
        requ.put("signType","MD5");
        //appId, timeStamp, nonceStr, package, signType
        //requ.put("paySign",Utils.createSign(requ,WechatURLConstants.APPKEY));
        requ.put("paySign",Utils.encryption(requ,WechatURLConstants.MD5,true,WechatURLConstants.APPKEY));
        return Result.success(requ);
    }

    /**
     *
     * @param noticeXml
     * @return
     */
    public Result PayNotice(String noticeXml) {
        try {
            Map<String,String> notice = WXPayUtil.xmlToMap(noticeXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Result sendTempMsg(String openId){
        try {
            Map<String, TemplateData> data = new LinkedHashMap<>();
            data.put("first",new TemplateData("停电通知","#173177"));
            data.put("keyword1",new TemplateData("2019-11-12 8:00 ~ 2019-11-12 18:00\n","#173177"));
            data.put("keyword2",new TemplateData("华府大道沿线","#173177"));
            data.put("keyword3",new TemplateData("线路检修","#173177"));
            data.put("remark",new TemplateData("如有不便敬请谅解！","#173177"));

//            data.put("first",new TemplateData("申请成功模板消息测试","#173177"));
//            data.put("event",new TemplateData("超级","#173177"));
//            data.put("dept",new TemplateData("神部","#173177"));
//            data.put("date",new TemplateData("2019年10月09日","#173177"));
//            data.put("remark",new TemplateData("申请成功！","#173177"));

            TemplateMessage templateMessage = new TemplateMessage(
                    "ozATnici3NmV_yv1mnfofZRd1IUUfZ2UwKJqRF896IQ",//"PtiXzgOlsGB2B2NaOMNtJhHdYaxD5Df41pZEe8RIj1A",
                    openId,     //  o7sDrsqAggP4dwbNnVMEC-JX__tE    o7sDrso9Jk1F_lhoItpSY2xTqEmY
                    "https://cdgd.pryun.vip",
                    data
            );
            weChatEntity.sendTempMsg(templateMessage);
            return Result.success();
        } catch (Exception e) {
            System.out.println("模板消息发送失败！");
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 缴费成功通知
     * @param openId
     * @return
     */
    public Result sendRechargeSuccessTempMsg(String openId,String userNo,Double money){
        try {
            Map<String, TemplateData> data = new LinkedHashMap<>();
            data.put("first",new TemplateData("电费充值成功","#173177"));
            data.put("keyword1",new TemplateData(userNo,"#173177"));
            data.put("keyword2",new TemplateData( money.toString(),"#173177"));
            data.put("keyword3",new TemplateData("电费充值","#173177"));
            data.put("keyword4",new TemplateData("微信支付","#173177"));
            data.put("remark",new TemplateData("电费下发可能存在延迟,如有疑问请电话咨询客户经理。","#173177"));


            TemplateMessage templateMessage = new TemplateMessage(
                    "YCaPG0ADMBTYaj4eHiQqJF2y2fmeCwNefuPDQUjWTNw",//"PtiXzgOlsGB2B2NaOMNtJhHdYaxD5Df41pZEe8RIj1A",
                    openId,     //  o7sDrsqAggP4dwbNnVMEC-JX__tE    o7sDrso9Jk1F_lhoItpSY2xTqEmY
                    "https://cdgd.pryun.vip",
                    data
            );
            weChatEntity.sendTempMsg(templateMessage);
            return Result.success();
        } catch (Exception e) {
            System.out.println("充值提醒消息发送失败！");
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }


    /**
     * 获取微信公众号所有openids
     * @return Result
     */
//    private List<String> getOpenIds(String nextOpenID) {
//        try {
//            WxOpenIdInfoDTO dto = weChatEntity.getOpenIds(nextOpenID));
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.failure(TopErrorCode.GENERAL_ERR);
//        }
//
//    }



    /**
     * 获取微信公众号所有用户信息
     * @return Result
     */
    public Result getUserInfos(String nextOpenID) {
        try {

            UserIDListDTO dto = weChatEntity.getOpenIds(nextOpenID);
            if( dto.getData() == null || dto.getData().getOpenIds() == null )
                return Result.failure(TopErrorCode.NO_DATAS);

            List<String> ids = dto.getData().getOpenIds();
            List<UserListSubmitDTO> userListSubmitDTOlist = new ArrayList<>();
            for ( String id : ids ) {
                userListSubmitDTOlist.add( new UserListSubmitDTO(id,"zh_CN"));
            }

            UserInfoList list = weChatEntity.getUserInfosByOpenIds(userListSubmitDTOlist);
            if( list == null || list.getUser_info_list() == null )
                return Result.failure(TopErrorCode.NO_DATAS);

            UserTableDTO ret = new UserTableDTO();
            for( UserInfo item : list.getUser_info_list() )
            {
                if( item.getSubscribe() == "1" )
                {
                    UserInfoViewDTO t = new UserInfoViewDTO();
                    BeanUtils.copyProperties(item,t);
                    ret.getUserInfoList().add(t);
                }
            }
            return Result.success(ret);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 获取微信公众号所有用户信息
     * @return Result
     */
    public Result GetAllUserInfos() {
        String nextOpenID = "";
        try
        {
            // 取出所有公众号的用户ID
            List<String> ids = new ArrayList<>();
            while( true ){
                UserIDListDTO dto = weChatEntity.getOpenIds( nextOpenID );
                if( dto.getData() == null || dto.getData().getOpenIds() == null )
                    return Result.failure(TopErrorCode.NO_DATAS) ;
                if( dto.getCount() < 1 )
                    break;
                ids.addAll( dto.getData().getOpenIds());
                nextOpenID = dto.getNext_openid();
            }

            // 创建 post 数据结构
            List<UserListSubmitDTO> userListSubmitDTOlist = new ArrayList<>();
            for ( String id : ids ) {
                userListSubmitDTOlist.add( new UserListSubmitDTO(id,"zh_CN"));
            }

            // 循环多少次
            int cycle = ids.size()/100;
            if( ids.size()%100 != 0 )
                cycle ++ ;

            UserTableDTO ret = new UserTableDTO();

            for( int i = 0 ; i < cycle ; i++ )
            {
                UserInfoList list = weChatEntity.getUserInfosByOpenIds(userListSubmitDTOlist.subList(i*cycle,100));
                if( list == null || list.getUser_info_list() == null )
                    return Result.failure(TopErrorCode.NO_DATAS);

                for( UserInfo item : list.getUser_info_list() )
                {
                    if( item.getSubscribe() == "1" )
                    {
                        UserInfoViewDTO t = new UserInfoViewDTO();
                        BeanUtils.copyProperties(item,t);
                        ret.getUserInfoList().add(t);
                    }
                }
            }

            return Result.success(ret);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.NO_DATAS);
        }
    }

    public void SyncUserInfos( UserTableDTO param ) {
        // 写入用户数据表
    }
}
