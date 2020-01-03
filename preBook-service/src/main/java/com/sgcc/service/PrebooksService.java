package com.sgcc.service;

import com.example.Utils;
import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.CheckerInfoDao;
import com.sgcc.dao.PrebookInfoDao;
import com.sgcc.dto.*;
import com.sgcc.dtomodel.wechat.template.TemplateData;
import com.sgcc.dtomodel.wechat.template.TemplateMessage;
import com.sgcc.entity.event.PrebookInfoEventEntity;
import com.sgcc.entity.query.PrebookInfoQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.PrebookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrebooksService {

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private PrebookInfoQueryEntity prebookInfoQueryEntity;

    @Autowired
    private PrebookInfoEventEntity prebookInfoEventEntity;

    /**********************************用户*************************************/
    /**
     * 获取网点基础信息、排队信息和设备状态
     * @return
     */
    public Result getBasicInfo(String openId) {
        try {
            boolean flag = false;
            List<HallInfoDTO> hallInfoDTOS = new ArrayList<HallInfoDTO>();
            HallInfoDTO hallInfoDTO = new HallInfoDTO("123456", "高新营业厅");
            hallInfoDTOS.add(hallInfoDTO);

            Map<String,String> deviceInfo = new LinkedHashMap<>();

            // TODO 调用心跳接口
            deviceInfo.put("deviceId","6722d35aa124a82d");
            deviceInfo.put("deviceName","线上预约排号终端");
            deviceInfo.put("deviceStatus","1"); // 正常

            if (deviceInfo.get("deviceStatus").equals("不正常"))
                return Result.failure(TopErrorCode.DEVICE_EXCEPTION);

            // TODO 需要调用排队查询接口
            LineUpInfoOutDTO lineUpInfoDTO = new LineUpInfoOutDTO();
            lineUpInfoDTO.setCode("200");
            lineUpInfoDTO.setMsg("成功");
            Map<String,String> map = new LinkedHashMap<>();
            map.put("lineUpNo","WA1002");
            map.put("lineUpTime","2019-12-31 12:30:00");
            map.put("waitingNum","10");
            lineUpInfoDTO.setData(map);

            //  查询当前用户是否在黑名单中
            int count = prebookInfoQueryEntity.getBlacklistByOpenId(openId);
            if (count > 3)
                flag = true;

            PrebookModel model = new PrebookModel();
            BasicInfoDTO basicInfoDTO = model.getBasicInfoTrans(hallInfoDTOS,lineUpInfoDTO,deviceInfo,flag);

            return Result.success(basicInfoDTO);


        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    /**
     * 提交预约单
     * @param dto
     * @return
     */
    public Result addPrebook(PrebookInfoSubmitDTO dto) {

        if (null == dto)
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        try {
            PrebookModel model = new PrebookModel();
            PrebookInfoDao prebookInfoDao = model.addPrebookTrans(dto);
            PrebookInfoDao flag = prebookInfoEventEntity.addPrebook(prebookInfoDao);

            if (null != flag && !Strings.isNullOrEmpty(flag.getServiceHallName())){
                CheckerInfoDao checkerInfoDao = prebookInfoQueryEntity.getCheckerInfo(flag.getServiceHallId());
                if (null != checkerInfoDao && !Strings.isNullOrEmpty(checkerInfoDao.getId())){
                    TemplateMessage temp = new TemplateMessage();
                    temp.setTemplate_id("AmIrZpXB1wgKG9mrqDd0KWSAT9ML8l18Mhx-6n18ZgE");
                    temp.setTouser( checkerInfoDao.getUserOpenId() );              // 发送给审核人
                    temp.setUrl("https://sgcc.link/approval");                      // 进入页面

                    Map<String, TemplateData> map = new LinkedHashMap<>();
                    map.put("first",new TemplateData("你好，有新预约信息需要审核!","#000000"));
                    map.put("keyword1",new TemplateData(flag.getContact(),"#000000"));
                    map.put("keyword2",new TemplateData(Utils.GetCurrentTime(),"#000000"));
                    map.put("keyword3",new TemplateData(flag.getBusinessTypeName(),"#000000"));
                    map.put("remark",new TemplateData("请尽快审核，谢谢!","#000000"));
                    temp.setData( map );

                    weChatService.SimpleSendMsg( temp );
                }

            }

            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 查询我的所有预约列表
     * @param openId
     * @return
     */
    public Result getPrebookInfo(String openId,int status) {
        if (Strings.isNullOrEmpty(openId) || (status < 0 && status > 3))
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        try {
            List<PrebookInfoDao> prebookInfoDaos = prebookInfoQueryEntity.getPrebookInfo(openId,status);
            PrebookModel model = new PrebookModel();
            List<PrebookInfoViewDTO> prebookInfoViewDTOS = model.getPrebookInfoTrans(prebookInfoDaos);

            return Result.success(prebookInfoViewDTOS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 查询我的预约详情
     * @param id
     * @return
     */
    public Result getPrebookInfoDetail(String id) {

        if (Strings.isNullOrEmpty(id))
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        try {
            PrebookInfoDao prebookInfoDao = prebookInfoQueryEntity.getPrebookInfoDetail(id);
            CheckerInfoDao checkerInfoDao;
            PrebookDetailViewDTO prebookDetailViewDTO = null;
            PrebookModel model = new PrebookModel();
            if (!Strings.isNullOrEmpty(prebookInfoDao.getCheckerId())){
                checkerInfoDao = prebookInfoQueryEntity.getCheckerByOpenId(prebookInfoDao.getCheckerId());
                if (null != checkerInfoDao && !Strings.isNullOrEmpty(checkerInfoDao.getCheckerName()))
                    prebookDetailViewDTO = model.getPrebookInfoDetailTrans(prebookInfoDao,checkerInfoDao.getCheckerName());
            }else {
                prebookDetailViewDTO = model.getPrebookInfoDetailTrans(prebookInfoDao,null);
            }

            return Result.success(prebookDetailViewDTO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 取号
     * @param dto
     * @return
     */
    public Result getPrebookNumber(TakeNumberDTO dto) {
        if (Strings.isNullOrEmpty(dto.getHallId()) || Strings.isNullOrEmpty(dto.getPhone()))
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        try {

            Map<String,String> data = new LinkedHashMap<>();
            data.put("lineUpNo",dto.getHallId());
            data.put("lineUpNo",dto.getPhone());

            BasicInputDTO basicInputDTO = new BasicInputDTO("appId",
                    "signature",
                    new InputDataDTO(
                            "serviceCode",
                            "appId",
                            "deviceId",
                            data
                    )
            );

            // TODO 调用网上排号取号接口
            if (true){
                LineUpInfoOutDTO lineUpInfoOutDTO = new LineUpInfoOutDTO();
                lineUpInfoOutDTO.setCode("200");
                lineUpInfoOutDTO.setMsg("成功");
                Map<String,String> takeNumMap = new LinkedHashMap<>();
                takeNumMap.put("lineUpNo","WA001");
                takeNumMap.put("businessTypeName","高压业务");
                takeNumMap.put("lineUpTime","2019-12-31 12:30:00");
                lineUpInfoOutDTO.setData(takeNumMap);

                return Result.success(lineUpInfoOutDTO);
            }else {
                return Result.failure(TopErrorCode.DEVICE_EXCEPTION);
            }




        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }




    /**********************************工作人员*************************************/

    /**
     * 获取审核列表或已审核列表
     * @param openId
     * @param status
     * @return
     */
    public Result getCheckList(String openId, int status) {
        if (Strings.isNullOrEmpty(openId) || (status < 0 && status > 3))
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        try {
            CheckerInfoDao checkerInfoDao = prebookInfoQueryEntity.getCheckerByOpenId(openId);
            List<PrebookInfoDao> prebookInfoDaos = prebookInfoQueryEntity.getCheckList(checkerInfoDao.getServiceHallName(),status);
            PrebookModel model = new PrebookModel();
            List<PrebookInfoViewDTO> prebookInfoViewDTOS = model.getPrebookInfoTrans(prebookInfoDaos);
            return Result.success(prebookInfoViewDTOS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 获取审核详情
     * @param id
     * @return
     */
    public Result getCheckDetailList(String id) {
        if (Strings.isNullOrEmpty(id))
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        try {
            PrebookInfoDao prebookInfoDao = prebookInfoQueryEntity.getCheckDetailList(id);
            CheckerInfoDao checkerInfoDao;
            PrebookDetailViewDTO prebookDetailViewDTO;
            PrebookModel model = new PrebookModel();
            if (!Strings.isNullOrEmpty(prebookInfoDao.getCheckerId())){
                checkerInfoDao = prebookInfoQueryEntity.getCheckerByOpenId(prebookInfoDao.getCheckerId());
                prebookDetailViewDTO = model.getCheckDetailListTrans(prebookInfoDao,checkerInfoDao.getCheckerName());
            }else {
                prebookDetailViewDTO = model.getCheckDetailListTrans(prebookInfoDao,null);
            }

            return Result.success(prebookDetailViewDTO);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }


    }


    /**
     *审核用户预约
     * @param prebookInfoEditDTO
     * @return
     */
    public Result updateCheckPrebook(PrebookInfoEditDTO prebookInfoEditDTO) {
        if (prebookInfoEditDTO == null)
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        try {
            PrebookModel model = new PrebookModel();
            PrebookInfoDao prebookInfoDao = model.updateCheckPrebookTrans(prebookInfoEditDTO);
            PrebookInfoDao dao = prebookInfoEventEntity.updateCheckPrebook(prebookInfoDao);

            if (Strings.isNullOrEmpty(dao.getCheckerId()))
                return Result.failure(TopErrorCode.NO_DATAS);

            CheckerInfoDao checkerInfoDao = prebookInfoQueryEntity.getCheckerByOpenId(dao.getCheckerId());
            if (dao.getStatus()==2) {
                // TODO 调用线上排队接口
                // 不返回排号信息则发送排号失败消息
                if (true){

                    dao.setLineupNo("WA001");
                    dao.setLineupTime(Utils.GetCurTime());
                    int count = prebookInfoEventEntity.updateLineUp(dao);

                    if (count > 0){
                        // 推送消息给用户
                        TemplateMessage temp = new TemplateMessage();
                        temp.setTemplate_id("Yfv4siCzMo9MkeM9BEs61SlBf1KMTj2pHtMxn-OTYnc");
                        temp.setTouser( dao.getUserOpenId() );
                        temp.setUrl("https://sgcc.link/proposalList");
                        Map<String, TemplateData> map = new LinkedHashMap<>();
                        map.put("first",new TemplateData("您好，您的预约成功!","#000000"));
                        map.put("keyword1",new TemplateData(checkerInfoDao.getCheckerName(),"#000000"));
                        map.put("keyword2",new TemplateData(checkerInfoDao.getCheckTel(),"#000000"));
                        map.put("keyword3",new TemplateData(Utils.GetCurrentTime(),"#000000"));
                        map.put("keyword4",new TemplateData("预约号为："+dao.getLineupNo(),"#000000"));
                        map.put("remark",new TemplateData("感谢您的预约，谢谢!","#000000"));
                        temp.setData( map );

                        // 推送消息给工作人员
//                    TemplateMessage temp2 = new TemplateMessage();
//                    temp2.setTemplate_id("Yfv4siCzMo9MkeM9BEs61SlBf1KMTj2pHtMxn-OTYnc");
//                    temp2.setTouser( dao.getUserOpenId() );
//                    temp2.setUrl("https://sgcc.link/proposalList");
//                    Map<String, TemplateData> map2 = new LinkedHashMap<>();
//                    map2.put("first",new TemplateData("您好，您的预约成功!","#000000"));
//                    map2.put("keyword1",new TemplateData(checkerInfoDao.getCheckerName(),"#000000"));
//                    map2.put("keyword2",new TemplateData(checkerInfoDao.getCheckTel(),"#000000"));
//                    map2.put("keyword3",new TemplateData(Utils.GetCurrentTime(),"#000000"));
//                    map2.put("keyword4",new TemplateData(dao.getLineupNo(),"#000000"));
//                    map2.put("remark",new TemplateData("感谢您的预约，谢谢!","#000000"));
//                    temp2.setOutData( map );


                        weChatService.SimpleSendMsg( temp );
                    }

                }else {
                    // 推送给用户
                    TemplateMessage temp = new TemplateMessage();
                    temp.setTemplate_id("AmIrZpXB1wgKG9mrqDd0KWSAT9ML8l18Mhx-6n18ZgE");
                    temp.setTouser( dao.getUserOpenId() );
                    temp.setUrl("https://sgcc.link/approval");

                    Map<String, TemplateData> map = new LinkedHashMap<>();
                    map.put("first",new TemplateData("你好，有新的预约消息请查看!","#000000"));
                    map.put("keyword1",new TemplateData(checkerInfoDao.getCheckerName(),"#000000"));
                    map.put("keyword2",new TemplateData(Utils.GetCurrentTime(),"#000000"));
                    map.put("keyword3",new TemplateData("系统正在维护","#000000"));
                    map.put("remark",new TemplateData("感谢您的使用!","#000000"));
                    temp.setData( map );


                }

            }else if (dao.getStatus()==3){

                TemplateMessage temp = new TemplateMessage();
                temp.setTemplate_id("Yfv4siCzMo9MkeM9BEs61SlBf1KMTj2pHtMxn-OTYnc");
                temp.setTouser( dao.getUserOpenId() );
                temp.setUrl("https://sgcc.link/proposalList");

                Map<String, TemplateData> map = new LinkedHashMap<>();
                map.put("first",new TemplateData("您好，您的预约审核不通过!","#000000"));
                map.put("keyword1",new TemplateData(checkerInfoDao.getCheckerName(),"#000000"));
                map.put("keyword2",new TemplateData(checkerInfoDao.getCheckTel(),"#000000"));
                map.put("keyword3",new TemplateData(Utils.GetCurrentTime(),"#000000"));
                map.put("keyword4",new TemplateData(dao.getRejectReason(),"#000000"));
                map.put("remark",new TemplateData("感谢您的预约，谢谢!","#000000"));
                temp.setData( map );

                weChatService.SimpleSendMsg( temp );


            }
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 添加审核人
     * @param checkerSubmitDTO
     * @return
     */
    public Result addChecker(CheckerSubmitDTO checkerSubmitDTO) {
        if (checkerSubmitDTO == null)
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        try {

            PrebookModel model = new PrebookModel();
            CheckerInfoDao checkerInfoDao = model.addCheckerTrans(checkerSubmitDTO);
            prebookInfoEventEntity.addChecker(checkerInfoDao);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 修改审核人信息
     * @param checkerEditDTO
     * @return
     */
    public Result updateChecker(CheckerEditDTO checkerEditDTO) {
        if (checkerEditDTO == null)
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        try {
            PrebookModel model = new PrebookModel();
            CheckerInfoDao checkerInfoDao = model.updateCheckerTrans(checkerEditDTO);
            prebookInfoEventEntity.updateChecker(checkerInfoDao);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    public Result delChecker(List<String> ids) {
        if (ids.size() == 0)
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        try {
            prebookInfoEventEntity.delChecker(ids);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }




}
