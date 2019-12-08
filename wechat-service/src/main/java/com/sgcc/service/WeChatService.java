package com.sgcc.service;

import com.example.CurrentPage;
import com.example.Utils;
import com.example.constant.WechatURLConstants;
import com.example.result.Result;
import com.google.common.base.Converter;
import com.google.common.base.Strings;
import com.sgcc.dao.UserDao;
import com.sgcc.dto.*;
import com.sgcc.dto.MsgDTO;
import com.sgcc.dto.TempDTO;
import com.sgcc.dto.TempDetail;
import com.sgcc.dtomodel.wechat.JSAPITicketDTO;
import com.sgcc.dtomodel.wechat.template.TemplateData;
import com.sgcc.dtomodel.wechat.template.TemplateMessage;
import com.sgcc.entity.WeChatEntity;
import com.sgcc.entity.event.WeChatEventEntity;
import com.sgcc.entity.query.WeChatQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.wxpay.Sgcc_WXPay;
import com.sgcc.wxpay.sdk.WXPayUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.*;

@Service
public class WeChatService {

    @Autowired
    private WeChatEntity weChatEntity;

    @Autowired
    private WeChatQueryEntity weChatQueryEntity;

    @Autowired
    private WeChatEventEntity weChatEventEntity;



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

    public Result getTempList(){
        TemplateRetListDTO tmplt_list = weChatEntity.getTempList();
        if( tmplt_list == null )
            return Result.failure( TopErrorCode.NO_DATAS );

        List<TemplateViewDTO> list = new ArrayList<>();
        for( TemplateRetDTO item : tmplt_list.getTemplate_list() )
        {
            TemplateViewDTO temp = Convert2(item);
            if( temp != null )
                list.add(temp);
        }
        return Result.success(list);
    }

    private TemplateViewDTO Convert2( TemplateRetDTO src )
    {
        if( src == null )
            return null;

        TemplateViewDTO ret = new TemplateViewDTO();
        ret.setTemplate_id( src.getTemplate_id() );
        ret.setTitle( src.getTitle() );
        Map<String,String> content = Utils.ParseTemplateInfoContent( src.getContent() );
        if( content == null || content.size() < 2 )
            return null;
        ret.setDatas(content);
        return ret;
    }

    private String GetURL(String tempID )
    {
        switch ( tempID )
        {
            case "JXw2Xh4izWGxGNsLUkRexEGkxv42NdVcMLuiqLQ0EPg":     // 缴费成功通知
                return "http://weixin.sc.sgcc.com.cn/SEH/elecAnalysis/to_pay_recordPage";
            case "ALuxFbuNFnZmMkfoQ9nKmmdJUukBLIZ0LntwxmSZInY":     // 余额不足提醒
            case "h6L7RyWgqQJ9dkYfKJ5rV35-VkYMf7POBxQNJXIDaws":     // 电费阈值提醒
                return "http://weixin.sc.sgcc.com.cn/SEH/electricPower/microhall/list_page";
            case "VRAOumHGKZ-StF_nNKVmL9wY25-Sm0IRktxz8LNh1Ks":     // 月度账单
            case "ek1UgAqBw-3KITByVmBMdPvdTyMN8OXZqBW2MOFflOM":     // 电费月度账单
                return "http://weixin.sc.sgcc.com.cn/SEH/energyAnalysis/energyPowerPage";
            default:
                return "https://sgcc.link";
        }
    }
    private String GetKey(String tempID )
    {
        switch ( tempID )
        {
            case "JXw2Xh4izWGxGNsLUkRexEGkxv42NdVcMLuiqLQ0EPg":     // 缴费成功通知
                return "is_sub_notice_pay";
            case "ALuxFbuNFnZmMkfoQ9nKmmdJUukBLIZ0LntwxmSZInY":     // 余额不足提醒
                return "is_sub_pay";
            case "h6L7RyWgqQJ9dkYfKJ5rV35-VkYMf7POBxQNJXIDaws":     // 用电分析  VRAOumHGKZ-StF_nNKVmL9wY25-Sm0IRktxz8LNh1Ks
                return "is_sub_analysis";
            case "ek1UgAqBw-3KITByVmBMdPvdTyMN8OXZqBW2MOFflOM":     // 电费月度账单
                return "is_sub_bill";
            default:
                return "没有此模板";
        }
    }

    public Result sendMsg(String openId, MsgDTO msgDTO){
        if( msgDTO == null || Strings.isNullOrEmpty(msgDTO.getTempId()) ||
                msgDTO.getData() == null || msgDTO.getData().size() < 1)
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        try {
            Map<String, TemplateData> data = new LinkedHashMap<>();
            if( msgDTO.getData().containsKey("first"))
            {
                data.put("first",new TemplateData(msgDTO.getData().get("first"),"#173177"));
            }
            else if( msgDTO.getData().containsKey("remark"))
            {
                data.put("remark",new TemplateData(msgDTO.getData().get("remark"),"#173177"));
            }

            for( String key : msgDTO.getData().keySet() )
            {
                if( !(key.contains("first") && key.contains("remark")) )
                {
                    data.put(key,new TemplateData(msgDTO.getData().get(key),"#000000"));
                }
            }

            Integer flag = weChatQueryEntity.findUsersByOpenID(openId,GetKey(msgDTO.getTempId()));
            System.out.println(flag);
            if (flag != 1)
                return Result.success();

            TemplateMessage templateMessage = new TemplateMessage(
                    msgDTO.getTempId(),
                    openId,
                    GetURL(msgDTO.getTempId()),
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
    public Result SyncUserInfos() {
        String nextOpenID = "";
        try
        {
            UserIDListDTO dto = weChatEntity.getOpenIds( nextOpenID );      // openID一次最多获取10000
            if( dto.getCount() < 1 )
                return Result.failure(TopErrorCode.NO_DATAS);

            List<String> ids = new ArrayList<>();
            ids.addAll( dto.getData().getOpenid());

            // 去取数据次数
            int cycle1 = dto.getTotal()/10000;
            if( dto.getTotal()%10000 != 0 )
                cycle1 ++ ;

            while( cycle1 > 1 ){
                dto = weChatEntity.getOpenIds( dto.getNext_openid() );
                ids.addAll( dto.getData().getOpenid());
                cycle1 -- ;
            }

            // 创建 post 数据结构
            List<UserSubmitDTO> userListSubmitDTOlist = new ArrayList<>();
            for ( String id : ids ) {
                userListSubmitDTOlist.add( new UserSubmitDTO(id,"zh_CN"));
            }

            // 循环多少次
            int perSize = 100;
            int cycle = ids.size()/perSize;
            if( ids.size()%perSize != 0 )
                cycle ++ ;

            System.out.println("cycle = " + cycle);
            System.out.println("ids.size() = " + ids.size());

            for( int i = 0 ; i < cycle ; i++ )
            {
                if( ids.size() - i*perSize < perSize )
                    SaveUsers(new UserListSubmitDTO(userListSubmitDTOlist.subList(i*perSize,ids.size())));
                else
                    SaveUsers(new UserListSubmitDTO(userListSubmitDTOlist.subList(i*perSize, (i+1)*perSize)));
            }

            return Result.success(ids);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    private void SaveUsers( UserListSubmitDTO dto )
    {
        UserInfoList list = weChatEntity.getUserInfosByOpenIds(dto);    // 最多一次拉取100条数据
        if( list == null || list.getUser_info_list() == null )
            return ;

        UserTableDTO ret = new UserTableDTO();
        if( ret.getUserInfoList() == null ){
            ret.setUserInfoList(new ArrayList<>());
        }

        for( UserInfo item : list.getUser_info_list() )
        {
            if( item.getSubscribe() == 1 )
            {
                UserInfoViewDTO t = new UserInfoViewDTO();
                BeanUtils.copyProperties(item,t);
                ret.getUserInfoList().add(t);
            }
        }
        weChatEventEntity.saveUsers(ret.getUserInfoList());
    }

    public Result findUsers() {
        try {
            return Result.success( weChatQueryEntity.findUsers());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    public Result findUsersByNickName(String nickName,int pageNo,int pageSize ) {
        if ( pageNo < 1 || pageSize < 1)
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        try {
            if (Strings.isNullOrEmpty(nickName)){
                return this.findPageList(pageNo,pageSize);
            }else {
                CurrentPage<UserDao> userDaos = weChatQueryEntity.findUsersByNickName(nickName,pageNo,pageSize);
                if( userDaos == null ||userDaos.getPageItems() == null || userDaos.getTotal() < 1 )
                    return Result.failure(TopErrorCode.NO_DATAS);

                UserPageableListDTO ret = new UserPageableListDTO(userDaos.getPageNo(),userDaos.getPageSize(),
                        userDaos.getPagesAvailable(),userDaos.getTotal(),userDaos.getPageItems());
                return Result.success(ret);
            }

        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }


    public Result findPageList(int pageNo,int pageSize) {
        if ( pageNo < 1 || pageSize < 1)
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        try {
            CurrentPage<UserDao> userDaos = weChatQueryEntity.findPageList(pageNo,pageSize);
            if( userDaos == null ||userDaos.getPageItems() == null || userDaos.getTotal() < 1 )
                return Result.failure(TopErrorCode.NO_DATAS);

            UserPageableListDTO ret = new UserPageableListDTO(userDaos.getPageNo(),userDaos.getPageSize(),
                    userDaos.getPagesAvailable(),userDaos.getTotal(),userDaos.getPageItems());

            return Result.success(ret);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }



    }


    public Result findUsersByFullNickName(String fullNickName) {
        if (Strings.isNullOrEmpty(fullNickName))
            return Result.failure(TopErrorCode.NO_DATAS);
        try {
            List<UserDao> userDaos = weChatQueryEntity.findUsersByFullNickName(fullNickName);
            if (userDaos.size() < 1)
                return Result.failure(TopErrorCode.NO_DATAS);
            return Result.success(userDaos.get(0));
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }





}
