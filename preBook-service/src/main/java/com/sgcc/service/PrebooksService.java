package com.sgcc.service;

import com.example.IDUtil;
import com.example.Utils;
import com.example.constant.PrebookStartTimeConstants;
import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.BlacklistDao;
import com.sgcc.dao.CheckerInfoDao;
import com.sgcc.dao.PreBookHouseholdDao;
import com.sgcc.dao.PrebookInfoDao;
import com.sgcc.dto.*;
import com.sgcc.dtomodel.wechat.template.TemplateData;
import com.sgcc.dtomodel.wechat.template.TemplateMessage;
import com.sgcc.entity.event.PrebookInfoEventEntity;
import com.sgcc.entity.query.PrebookInfoQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.PrebookModel;
import com.sgcc.producer.PrebookProducer;
import com.sgcc.utils.DateUtils;
import com.sgcc.utils.EasyPoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PrebooksService {

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private PrebookInfoQueryEntity prebookInfoQueryEntity;

    @Autowired
    private PrebookInfoEventEntity prebookInfoEventEntity;

    @Autowired
    private PrebookProducer prebookProducer;

    /**********************************用户*************************************/

    /**
     * 提前一小时提醒客户取票
     * @param date
     * @return
     */
    public Result advanceSendMessage(String date) {
        try {
            List<PrebookInfoDao> prebookInfoDaos = prebookInfoQueryEntity.getPrebookList();
            PrebookModel model = new PrebookModel();
            List<PrebookInfoDao> daos = model.advanceSendMessageTrans(prebookInfoDaos,date);
            if (daos.size() < 1)
                return Result.success();
            for (PrebookInfoDao dao : daos) {
                String time = Utils.GetTimeForYMD(dao.getStartDate())+" "+
                        Utils.GetTimeForHMS(dao.getStartDate())+
                        "~"+Utils.GetTimeForHMS(dao.getEndDate());

                TemplateMessage temp = new TemplateMessage();
                temp.setTemplate_id("t9BlCV_CQ-K6o5tO7X68UonpWNq1YszrGgSFe0PraVU");
                temp.setTouser( dao.getUserOpenId());              // 发送给用户
                temp.setUrl("https://sgcc.link/appointmentList");                      // 进入页面
                Map<String, TemplateData> map = new LinkedHashMap<>();
                map.put("first",new TemplateData("你好，请你及时到营业厅取票!","#000000"));
                map.put("keyword1",new TemplateData(dao.getContact(),"#000000"));
                map.put("keyword2",new TemplateData(dao.getContactTel(),"#000000"));
                map.put("keyword3",new TemplateData(time,"#000000"));
                map.put("keyword4",new TemplateData("预约成功","#000000"));
                map.put("remark",new TemplateData("请尽快赴约，谢谢!","#000000"));
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
     * 测试
     * @return
     */
    public Result advanceSendMessageBackstage(String id) {
        try {
            PrebookInfoDao dao = prebookInfoQueryEntity.getPrebooklListById(id);
            String time = Utils.GetTimeForYMD(dao.getStartDate())+" "+
                    Utils.GetTimeForHMS(dao.getStartDate())+
                    "~"+Utils.GetTimeForHMS(dao.getEndDate());
            TemplateMessage temp = new TemplateMessage();
            temp.setTemplate_id("t9BlCV_CQ-K6o5tO7X68UonpWNq1YszrGgSFe0PraVU");
            temp.setTouser( dao.getUserOpenId() );              // 发送给用户
            temp.setUrl("https://sgcc.link/appointmentList");                      // 进入页面
            Map<String, TemplateData> map = new LinkedHashMap<>();
            map.put("first",new TemplateData("你好，请你及时到营业厅取票!","#000000"));
            map.put("keyword1",new TemplateData(dao.getContact(),"#000000"));
            map.put("keyword2",new TemplateData(dao.getContactTel(),"#000000"));
            map.put("keyword3",new TemplateData(time,"#000000"));
            map.put("keyword4",new TemplateData("预约成功","#000000"));
            map.put("remark",new TemplateData("请尽快赴约，谢谢!","#000000"));
            temp.setData( map );
            weChatService.SimpleSendMsg( temp );
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }



    public Result advanceSendMessageToChecker() {
        try {
            List<CheckerInfoDao> checkerInfoDaos = prebookInfoQueryEntity.getAllCheckers();
            for (CheckerInfoDao dao : checkerInfoDaos){
                List<PrebookInfoDao> prebookInfoDaos =
                        prebookInfoQueryEntity.getCheckList(dao.getServiceHallId(),1,null);
                if (prebookInfoDaos.size() > 0){
                    TemplateMessage temp = new TemplateMessage();
                    temp.setTemplate_id("AmIrZpXB1wgKG9mrqDd0KWSAT9ML8l18Mhx-6n18ZgE");
                    temp.setTouser( dao.getUserOpenId() );                      // 发送给审核人
                    temp.setUrl("https://sgcc.link/appointmentBack");                      // 进入页面

                    Map<String, TemplateData> map = new LinkedHashMap<>();
                    map.put("first",new TemplateData("你好，有新预约信息需要审核!","#000000"));
                    map.put("keyword1",new TemplateData(dao.getCheckerName(),"#000000"));
                    map.put("keyword2",new TemplateData(Utils.GetCurrentTime(),"#000000"));
                    map.put("keyword3",new TemplateData("有新的预约需要处理！","#000000"));
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
     * 批量加入黑名单
     * @return
     */
    public Result addBlackListBackstage(List<String> ids) {
        try {
            List<PrebookInfoDao> dao = prebookInfoQueryEntity.getPrebooklListByIds(ids);
            PrebookModel model = new PrebookModel();
            List<BlacklistDao> daos = model.getNotTakeTicketListTrans(dao);
            if (daos.size() < 1)
                return Result.success();
            prebookInfoEventEntity.addBlacklist(daos);
            prebookInfoEventEntity.updatePrebookBlacklist(ids);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    public Result addBlackList(Date date) {
        try {
            List<PrebookInfoDao> prebookInfoDaos = prebookInfoQueryEntity.getNotTakeTicketList();
            PrebookModel model = new PrebookModel();
            List<BlacklistDao> daos = model.getNotTakeTicketListTrans(prebookInfoDaos,date);
            if (daos.size() < 1 || prebookInfoDaos.size() < 1 )
                return Result.success();
            // 加入黑名单
            prebookInfoEventEntity.addBlacklist(daos);
            // 修改工单中黑名单状态
            List<String> ids = model.updatePrebookBlacklistTrans(prebookInfoDaos);
            prebookInfoEventEntity.updatePrebookBlacklist(ids);
            return Result.success();

        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }




    /**
     * 查询预约时间段
     * @return
     */
    public Result getTimeSlot(String day) {
        try {
            List<String> timeList = PrebookStartTimeConstants.TIME_LIST;
            List<TimeSlotDTO> timeSlotDTOS = new ArrayList<>();
            for (String str : timeList) {
                String time = str.substring(2,str.length());
                Map<String,Date> dates = DateUtils.splitDate(time,day,"~");
                List<PrebookInfoDao> prebookInfoDaos =prebookInfoQueryEntity
                        .getPrebookCount(Utils.GetTime(dates.get("startDate")),Utils.GetTime(dates.get("endDate")));
                timeSlotDTOS.add(new TimeSlotDTO(time,prebookInfoDaos.size()));
            }
            return Result.success(timeSlotDTOS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

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
            if (count > 5)
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
     * 查询当前用户是否在黑名单中有3次记录，有则不能预约
     * @param openId
     * @return
     */
    public Result getBlacklistByOpenId(String openId) {

        try {
            boolean flag = false;
            int count = prebookInfoQueryEntity.getBlacklistByOpenId(openId);
            if (count == 3 || count > 3)
                flag = true;
            return Result.success(flag);
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
            Map<String,Date> dates = DateUtils.splitDate(dto.getPrebookDate(),dto.getDay(),"~");

            // 当前某个时间段达到规定预约数量
            List<PrebookInfoDao> prebookInfoDaos =
                    prebookInfoQueryEntity.getPrebookCount(Utils.GetTime(dates.get("startDate")),Utils.GetTime(dates.get("endDate")));
            if (prebookInfoDaos.size() == 4)
                return Result.failure(TopErrorCode.PREBOOK_FULL);

            // 当前预约次数达到上限
            List<PrebookInfoDao> prebookInfoDaoList = prebookInfoQueryEntity.getPrebookSize(dto.getUserOpenId());
            List<PrebookInfoDao> daos = model.getPrebookSizeTrans(prebookInfoDaoList,dates.get("startDate"));
            if (daos.size() == 1)
                return Result.failure(TopErrorCode.EXCEEDING_LIMIT);

            synchronized (this) {
                PrebookInfoDao prebookInfoDao = model.addPrebookTrans(dto,dates.get("startDate"),dates.get("endDate"));
                PrebookInfoDao flag = prebookInfoEventEntity.addPrebook(prebookInfoDao);

                List<PreBookHouseholdDao> householdDaos = model.addHouseHoldTrans(prebookInfoDao,dto);
                prebookInfoEventEntity.addHouseHold(householdDaos);


                if (null != flag && !Strings.isNullOrEmpty(flag.getServiceHallName())){
                    CheckerInfoDao checkerInfoDao = prebookInfoQueryEntity.getCheckerInfo(flag.getServiceHallId());
                    if (null != checkerInfoDao && !Strings.isNullOrEmpty(checkerInfoDao.getId())){
                        TemplateMessage temp = new TemplateMessage();
                        temp.setTemplate_id("AmIrZpXB1wgKG9mrqDd0KWSAT9ML8l18Mhx-6n18ZgE");
                        temp.setTouser( checkerInfoDao.getUserOpenId() );                      // 发送给审核人
                        temp.setUrl("https://sgcc.link/appointmentBack");                      // 进入页面

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
            List<PreBookHouseholdDao> daos = prebookInfoQueryEntity.getHouseHoldByPrebookId(id);

            CheckerInfoDao checkerInfoDao;
            PrebookDetailViewDTO prebookDetailViewDTO = null;
            PrebookModel model = new PrebookModel();

            if (!Strings.isNullOrEmpty(prebookInfoDao.getCheckerId())){
                checkerInfoDao = prebookInfoQueryEntity.getCheckerById(prebookInfoDao.getCheckerId());
                if (null != checkerInfoDao && !Strings.isNullOrEmpty(checkerInfoDao.getCheckerName()))
                    prebookDetailViewDTO = model.getPrebookInfoDetailTrans(
                            prebookInfoDao,checkerInfoDao.getCheckerName(),daos);
            }else {
                prebookDetailViewDTO = model.getPrebookInfoDetailTrans(prebookInfoDao,null,daos);
            }

            return Result.success(prebookDetailViewDTO);
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
    public Result getCheckList(String openId, int status,Boolean isPrinted,String condition) {
        try {
            if (Strings.isNullOrEmpty(openId) || (status < 0 && status > 3))
                return Result.failure(TopErrorCode.PARAMETER_ERR);
            CheckerInfoDao checkerInfoDao = prebookInfoQueryEntity.getCheckerByOpenId(openId);
            if (Strings.isNullOrEmpty(checkerInfoDao.getId()))
                return Result.failure(TopErrorCode.NO_DATAS);
            List<PrebookInfoDao> prebookInfoDaos = null;
            if (Strings.isNullOrEmpty(condition)){
                prebookInfoDaos = prebookInfoQueryEntity.getCheckList(checkerInfoDao.getServiceHallId(),status,isPrinted);
            }else {
                prebookInfoDaos = prebookInfoQueryEntity.getCheckList(checkerInfoDao.getServiceHallId(),status,isPrinted,condition);
            }
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
            List<PreBookHouseholdDao> daos = prebookInfoQueryEntity.getHouseHoldByPrebookId(id);
            CheckerInfoDao checkerInfoDao;
            PrebookDetailViewDTO prebookDetailViewDTO;
            PrebookModel model = new PrebookModel();
            if (!Strings.isNullOrEmpty(prebookInfoDao.getCheckerId())){
                checkerInfoDao = prebookInfoQueryEntity.getCheckerById(prebookInfoDao.getCheckerId());
                prebookDetailViewDTO = model.getCheckDetailListTrans(prebookInfoDao,checkerInfoDao.getCheckerName(),daos);
            }else {
                prebookDetailViewDTO = model.getCheckDetailListTrans(prebookInfoDao,null,daos);
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

            CheckerInfoDao infoDao = prebookInfoQueryEntity.getCheckerByOpenId(prebookInfoEditDTO.getUserOpenId());

            PrebookModel model = new PrebookModel();
            PrebookInfoDao prebookInfoDao = model.updateCheckPrebookTrans(prebookInfoEditDTO,infoDao);
            PrebookInfoDao dao = prebookInfoEventEntity.updateCheckPrebook(prebookInfoDao);

            if (Strings.isNullOrEmpty(dao.getCheckerId()))
                return Result.failure(TopErrorCode.NO_DATAS);

            CheckerInfoDao checkerInfoDao = prebookInfoQueryEntity.getCheckerById(dao.getCheckerId());
            if (dao.getStatus()==2) {

                dao.setLineupNo(IDUtil.getRandString(4));   // 通过则生成预约号
                dao.setLineupTime(Utils.GetCurTime());
                int count = prebookInfoEventEntity.updateLineUp(dao);

                if (count > 0){
                    // 推送消息给用户
                    String time = Utils.GetTimeForYMD(dao.getStartDate())+" "+
                            Utils.GetTimeForHMS(dao.getStartDate())+
                            "~"+Utils.GetTimeForHMS(dao.getEndDate());

                    TemplateMessage temp = new TemplateMessage();
                    temp.setTemplate_id("t9BlCV_CQ-K6o5tO7X68UonpWNq1YszrGgSFe0PraVU");
                    temp.setTouser( dao.getUserOpenId());              // 发送给用户
                    temp.setUrl("https://sgcc.link/appointmentList");                      // 进入页面

                    Map<String, TemplateData> map = new LinkedHashMap<>();
                    map.put("first",new TemplateData("你好，你的预约已成功!","#000000"));
                    map.put("keyword1",new TemplateData(dao.getContact(),"#000000"));
                    map.put("keyword2",new TemplateData(dao.getContactTel(),"#000000"));
                    map.put("keyword3",new TemplateData(time,"#000000"));
                    map.put("keyword4",new TemplateData("预约成功","#000000"));
                    map.put("remark",new TemplateData("请尽快赴约，谢谢!","#000000"));
                    temp.setData( map );
                    weChatService.SimpleSendMsg( temp );

                    // 推送消息给工作人员
                    TemplateMessage temp2 = new TemplateMessage();
                    temp2.setTemplate_id("Yfv4siCzMo9MkeM9BEs61SlBf1KMTj2pHtMxn-OTYnc");
                    temp2.setTouser( dao.getCheckerId() );
                    temp2.setUrl("https://sgcc.link/appointmentBack");
                    Map<String, TemplateData> map2 = new LinkedHashMap<>();
                    map2.put("first",new TemplateData("您好，客户已预约成功，请尽快处理!","#000000"));
                    map2.put("keyword1",new TemplateData(dao.getContact(),"#000000"));
                    map2.put("keyword2",new TemplateData(dao.getContactTel(),"#000000"));
                    map2.put("keyword3",new TemplateData(Utils.GetTime(dao.getSubmitDate()),"#000000"));
                    map2.put("keyword4",new TemplateData("预约成功","#000000"));
                    map2.put("remark",new TemplateData("请尽快处理，谢谢!","#000000"));
                    temp2.setData( map2 );
                    weChatService.SimpleSendMsg( temp2 );

                }

            }else if (dao.getStatus()==3){

                TemplateMessage temp = new TemplateMessage();
                temp.setTemplate_id("Yfv4siCzMo9MkeM9BEs61SlBf1KMTj2pHtMxn-OTYnc");
                temp.setTouser( dao.getUserOpenId() );
                temp.setUrl("https://sgcc.link/appointmentList");

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
        try {
            if (checkerSubmitDTO == null)
                return Result.failure(TopErrorCode.PARAMETER_ERR);

            List<CheckerInfoDao> checkerInfoDaos =
                    prebookInfoQueryEntity.getCheckerByServcieHallId(checkerSubmitDTO.getServiceHallId());
            if (checkerInfoDaos.size() == 1 || checkerInfoDaos.size() > 1)
                return Result.failure(TopErrorCode.IS_EXIST_CHECKER);

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

    /**
     * 查询所有黑名单
     * @return
     */
    public Result getAllCheckers() {

        try {
            List<CheckerInfoDao> checkerInfoDaos = prebookInfoQueryEntity.getAllCheckers();
            PrebookModel model = new PrebookModel();
            List<CheckerViewDTO> checkerViewDTOS = model.getAllCheckersTrans(checkerInfoDaos);
            if (checkerViewDTOS.size() > 0)
                return Result.success(checkerViewDTOS);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    public Map getRoleByOpenId(String openId) {
        Integer roleByOpenId = prebookInfoQueryEntity.getRoleByOpenId(openId);
        Integer prebookCount=0;
        if(roleByOpenId>0){
            prebookCount=prebookInfoQueryEntity.getCountByOpenId(openId);
        }
        HashMap<String, Integer> map = new HashMap<>();
        map.put("prebookRole",roleByOpenId);
        map.put("prebookCount",prebookCount);
        return map;
    }


    /**
     *修改打印状态
     * @param printStatusUpdateDTO
     * @return
     */
    public Result updatePrintStatus(PrintStatusUpdateDTO printStatusUpdateDTO) {
        if (printStatusUpdateDTO == null)
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        try {
            int[] count =  prebookInfoEventEntity.updatePrintStatus(printStatusUpdateDTO.getIds());
            if (printStatusUpdateDTO.getIds().size() == count.length){
                return Result.success();
            }
            return Result.failure(TopErrorCode.SQL_ERR);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    /**
     * 获取所有税票预约信息
     * @return
     */
//    public Result getAllPrebook(){
//
//        try {
//            List<PrebookInfoDao> prebookInfoDaos = prebookInfoQueryEntity.getAllPrebook();
//            PrebookModel model = new PrebookModel();
//            List<PrebookListViewDTO> prebookListViewDTOS = model.getAllPrebookTrans(prebookInfoDaos);
//            return Result.success(prebookListViewDTOS);
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.failure(TopErrorCode.GENERAL_ERR);
//        }
//
//    }

    /**
     * 修改是否到营业厅取票的状态
     * @param ids
     * @return
     */
    public Result updateTicketStatus(List<String> ids) {
        if (ids.size() < 0)
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        try {
            int[] count = prebookInfoEventEntity.updateTicketStatus(ids);
            if (ids.size() == count.length){
                return Result.success();
            }
            return Result.failure(TopErrorCode.SQL_ERR);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * 查询所有黑名单信息
     * @return
     */
    public Result getBlacklist() {
        try {
            List<BlacklistDao> blacklistDaos = prebookInfoQueryEntity.getBlacklist();
            PrebookModel model = new PrebookModel();
            List<BlacklistViewDTO> blacklistViewDTOS = model.getBlacklistTrans(blacklistDaos);
            return Result.success(blacklistViewDTOS);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    /**
     * 取消预约
     * @param id
     * @return
     */
    public Result cancelPrebook(String id) {
        try {
            PrebookInfoDao prebookInfoDao = prebookInfoQueryEntity.getPrebooklListById(id);
            if (prebookInfoDao.getStatus() != 3 &&
                    prebookInfoDao.getIsPrinted()==false &&
                    prebookInfoDao.getTicketStatus()==0){
                Date date = new Date();
                int count = prebookInfoEventEntity.cancelPrebook(id,date);
                if (count > 0){
                    // 推送消息给客户
                    TemplateMessage temp = new TemplateMessage();
                    temp.setTemplate_id("Ng58N4cns1ekU9KVMh_MxThDJHQHVLGhSIsUWHnPoV4");
                    temp.setTouser( prebookInfoDao.getUserOpenId() );
                    temp.setUrl("https://sgcc.link/appointmentList");
                    Map<String, TemplateData> map = new LinkedHashMap<>();
                    map.put("first",new TemplateData("您的预约已取消!","#000000"));
                    map.put("keyword1",new TemplateData(prebookInfoDao.getContact(),"#000000"));
                    map.put("keyword2",new TemplateData(Utils.GetTime(date),"#000000"));
                    map.put("remark",new TemplateData("谢谢使用!","#000000"));
                    temp.setData( map );
                    weChatService.SimpleSendMsg( temp );

                   if (!Strings.isNullOrEmpty(prebookInfoDao.getCheckerId())){
                       CheckerInfoDao checkerInfoDao =
                               prebookInfoQueryEntity.getCheckerInfoById(prebookInfoDao.getCheckerId());
                       SendMsgMQDTO sendMsgMQDTO = new SendMsgMQDTO(
                               checkerInfoDao.getUserOpenId(),
                               prebookInfoDao.getContact(),
                               Utils.GetTime(date)
                       );
                       prebookProducer.sendMessage(sendMsgMQDTO);
                   }
                }

                return Result.success();
            }else {
                return Result.success(false);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     *  导出excel
     * @param date
     * @param response
     * @return
     */
    public Result exportMultiExcel(String date, HttpServletResponse response) {
        try {
            if (Strings.isNullOrEmpty(date))
                return Result.failure(TopErrorCode.PARAMETER_ERR);
            PrebookModel model= new PrebookModel();
            List<PrebookInfoDao> prebookInfoDaos = prebookInfoQueryEntity.getPrebookByDate(date);
            List<PreBookHouseholdDao> daos = prebookInfoQueryEntity.getAllHouseHold();
            List<List<?>> excelDTOList = model.getPrebookByDateTrans(prebookInfoDaos,daos);
            String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            EasyPoiUtil.exportMultipleSheetExcel(excelDTOList,new String[]{"税票预约-已完成","税票预约-未完成"},ExportExcelDTO.class,fileName,response);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }


    }


}
