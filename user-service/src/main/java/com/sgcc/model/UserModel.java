package com.sgcc.model;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.CommerceInfoCorrectDao;
import com.sgcc.dao.InhabitantInfoCorrectDao;
import com.sgcc.dao.PayResultDao;
import com.sgcc.dao.SubscribeDao;
import com.sgcc.des.DesUtil;
import com.sgcc.dto.*;
import com.sgcc.dto.commerce.CommerceInfoCorrectEditDTO;
import com.sgcc.dto.commerce.CommerceInfoCorrectQueryDTO;
import com.sgcc.dto.commerce.CommerceInfoCorrectSubmitDTO;
import com.sgcc.dto.inhabitant.InhabitantInfoCorrectEditDTO;
import com.sgcc.dto.inhabitant.InhabitantInfoCorrectQueryDTO;
import com.sgcc.dto.inhabitant.InhabitantInfoCorrectSubmitDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Data
@NoArgsConstructor
public class UserModel {

    private InhabitantInfoCorrectDao inhabitantInfoCorrectDao;

    private CommerceInfoCorrectDao commerceInfoCorrectDao;

    private PayStatisticsDTO payStatisticsDTO;

    private PayResultSubmitDTO payResultSubmitDTO;

    private PayResultDao payResultDao;

    private List<PayResultDao> payResultDaos = new ArrayList<>();

    private List<PayResultSubmitDTO> payResultSubmitDTOS = new ArrayList<>();

    private List<PayStatisticsDTO> payStatisticsDTOS = new ArrayList<>();

    private List<InhabitantInfoCorrectQueryDTO> inhabitantInfoCorrectQueryDTOS = new ArrayList<>();

    private List<CommerceInfoCorrectQueryDTO> commerceInfoCorrectQueryDTOS = new ArrayList<>();

    private List<PayResultViewDTO> payResultViewDTOS = new ArrayList<>();

    public UserModel(PayResultSubmitDTO payResultSubmitDTO) {
        this.payResultSubmitDTO = payResultSubmitDTO;
    }

    public UserModel(PayResultDao payResultDao) {
        this.payResultDao = payResultDao;
    }

    public UserModel(List<PayResultDao> payResultDaos) {
        this.payResultDaos = payResultDaos;
    }








    public DefaultNumInfoDTO getDefaultHouseholdTransform(HouseholdInfosDTO householdInfosDTO, RealTimeElectricityDTO realTimeElectricityDTO) throws Exception {
        DefaultNumInfoDTO defaultNumInfoDTO = new DefaultNumInfoDTO(
                householdInfosDTO.getHouseholder(),
                DesUtil.encrypt(householdInfosDTO.getHouseholdNumber()),
                householdInfosDTO.getHouseholdAddress(),
                realTimeElectricityDTO.getCurrentMonthPower(),
                realTimeElectricityDTO.getCurrentMonthFees(),
                realTimeElectricityDTO.getCurrentPowerBalance()
        );
        return defaultNumInfoDTO;
    }



    public void updateCommerceInfoCorrectTransform(CommerceInfoCorrectEditDTO dto) {
        this.commerceInfoCorrectDao = new CommerceInfoCorrectDao(
                dto.getCorrectId(),
                null,
                dto.getHouseId(),
                dto.getCorrectName(),
                dto.getCorrectIdcard(),
                dto.getCorrectTel(),
                dto.getCorrectIdcardPositiveImg(),
                dto.getCorrectIdcardBackImg(),
                dto.getCorrectLicenseImg(),
                dto.getCorrectNewName(),
                dto.getCorrectNewAddress(),
                dto.getCorrectNewTel(),
                dto.getPropertyRightImg1(),
                dto.getPropertyRightImg2(),
                dto.getPropertyRightImg3(),
                dto.getPropertyRightImg4(),
                dto.getPropertyRightImg5(),
                dto.getPropertyRightImg6(),
                null

        );


    }



    public void queryCommerceInfoCorrectTransform(List<CommerceInfoCorrectDao> commerceInfoCorrectDaos) {
        commerceInfoCorrectDaos.forEach(dao -> {
            this.commerceInfoCorrectQueryDTOS.add(new CommerceInfoCorrectQueryDTO(
                    dao.getCorrectId(),
                    dao.getUserOpenId(),
                    dao.getHouseId(),
                    dao.getCorrectName(),
                    dao.getCorrectIdcard(),
                    dao.getCorrectTel(),
                    dao.getCorrectIdcardPositiveImg(),
                    dao.getCorrectIdcardBackImg(),
                    dao.getCorrectLicenseImg(),
                    dao.getCorrectNewName(),
                    dao.getCorrectNewAddress(),
                    dao.getCorrectNewTel(),
                    dao.getPropertyRightImg1(),
                    dao.getPropertyRightImg2(),
                    dao.getPropertyRightImg3(),
                    dao.getPropertyRightImg4(),
                    dao.getPropertyRightImg5(),
                    dao.getPropertyRightImg6(),
                    Utils.GetTime(dao.getCorrectSubmitDate())
            ));
        });
    }



    public void addCommerceInfoCorrectTransform(CommerceInfoCorrectSubmitDTO dto){
        String id = UUID.randomUUID().toString();
        this.commerceInfoCorrectDao = new CommerceInfoCorrectDao(
                id,
                dto.getUserOpenId(),
                dto.getHouseId(),
                dto.getCorrectName(),
                dto.getCorrectIdcard(),
                dto.getCorrectTel(),
                dto.getCorrectIdcardPositiveImg(),
                dto.getCorrectIdcardBackImg(),
                dto.getCorrectLicenseImg(),
                dto.getCorrectNewName(),
                dto.getCorrectNewAddress(),
                dto.getCorrectNewTel(),
                dto.getPropertyRightImg1(),
                dto.getPropertyRightImg2(),
                dto.getPropertyRightImg3(),
                dto.getPropertyRightImg4(),
                dto.getPropertyRightImg5(),
                dto.getPropertyRightImg6(),
                Utils.GetCurTime()
        );
    }






    public void updateInhabitantInfoCorrectTransform(InhabitantInfoCorrectEditDTO dto){
        this.inhabitantInfoCorrectDao = new InhabitantInfoCorrectDao(
                dto.getCorrectId(),
                null,
                dto.getHouseId(),
                dto.getCorrectName(),
                dto.getCorrectIdcard(),
                dto.getCorrectTel(),
                dto.getCorrectIdcardPositiveImg(),
                dto.getCorrectIdcardBackImg(),
                dto.getCorrectNewName(),
                dto.getCorrectNewAddress(),
                dto.getCorrectNewTel(),
                null
        );

    }




    public void queryInhabitantInfoCorrectTransform(List<InhabitantInfoCorrectDao> infoCorrectDaos){
        infoCorrectDaos.forEach(dao -> {
            this.inhabitantInfoCorrectQueryDTOS.add(new InhabitantInfoCorrectQueryDTO(
                    dao.getCorrectId(),
                    dao.getUserOpenId(),
                    dao.getHouseId(),
                    dao.getCorrectName(),
                    dao.getCorrectIdcard(),
                    dao.getCorrectTel(),
                    dao.getCorrectIdcardPositiveImg(),
                    dao.getCorrectIdcardBackImg(),
                    dao.getCorrectNewName(),
                    dao.getCorrectNewAddress(),
                    dao.getCorrectNewTel(),
                    Utils.GetTime(dao.getCorrectSubmitDate())
            ));
        });


    }


    public void addInhabitantInfoCorrectTransform(InhabitantInfoCorrectSubmitDTO dto){
        String id = UUID.randomUUID().toString();
        this.inhabitantInfoCorrectDao = new InhabitantInfoCorrectDao(
                id,
                dto.getUserOpenId(),
                dto.getHouseId(),
                dto.getCorrectName(),
                dto.getCorrectIdcard(),
                dto.getCorrectTel(),
                dto.getCorrectIdcardPositiveImg(),
                dto.getCorrectIdcardBackImg(),
                dto.getCorrectNewName(),
                dto.getCorrectNewAddress(),
                dto.getCorrectNewTel(),
                Utils.GetCurTime()
        );
    }







    public void findResultStatisticsByMonth(PayQueryStatisticsDTO dto, String StartDate, String dateUnit){
            this.payStatisticsDTO = new PayStatisticsDTO(
                    StartDate,
                    dateUnit,
                    dto.getPayTotal(),
                    dto.getPaySum(),
                    null
            );
    }

    public void findResultStatisticsByYear(PayQueryStatisticsDTO payQueryStatisticsDTO,
                                           List<PayQueryStatisticsDTO> payQueryStatisticsDTOS,
            String StartDate,String dateUnit){
        payQueryStatisticsDTOS.forEach(dto -> {
            this.payStatisticsDTOS.add(new PayStatisticsDTO(
                    StartDate,
                    dateUnit,
                    dto.getPayTotal(),
                    dto.getPaySum(),
                    null
            ));
        });

        this.payStatisticsDTO = new PayStatisticsDTO(
                StartDate,
                dateUnit,
                payQueryStatisticsDTO.getPayTotal(),
                payQueryStatisticsDTO.getPaySum(),
                this.getPayStatisticsDTOS()
        );

    }






    /**
     * 新增支付结果dto转dao
     */
    public void insertTransform(){
        String id = UUID.randomUUID().toString();
        this.payResultDao = new PayResultDao(
                id,
                id,
                this.payResultSubmitDTO.getOrderNo(),
                this.payResultSubmitDTO.getUserNo(),
                this.payResultSubmitDTO.getOpenId(),
                this.payResultSubmitDTO.getMoney(),
                "微信",
                Utils.GetCurTime()
        );


    }

    /**
     * 查询支付结果dao转dto
     */
    public void findTransform(){
        this.payResultDaos.forEach(payResultDao->{
            this.payResultViewDTOS.add(new PayResultViewDTO(
                    payResultDao.getPayId(),
                    payResultDao.getOrderNo(),
                    payResultDao.getUserNo(),
                    payResultDao.getOpenId(),
                    payResultDao.getMoney(),
                    payResultDao.getPaymentChannel(),
                    Utils.GetTime(payResultDao.getOrderSubmitTime())
            ));
        });
    }


    public PayResultViewsDTO findRecentlyTransform(double total){
        this.payResultDaos.forEach(payResultDao->{
            this.payResultViewDTOS.add(new PayResultViewDTO(
                    payResultDao.getPayId(),
                    payResultDao.getOrderNo(),
                    payResultDao.getUserNo(),
                    payResultDao.getOpenId(),
                    payResultDao.getMoney(),
                    payResultDao.getPaymentChannel(),
                    Utils.GetTime(payResultDao.getOrderSubmitTime())
            ));
        });

        PayResultViewsDTO payResultViewsDTO = new PayResultViewsDTO();
        payResultViewsDTO.setTotal(total);
        payResultViewsDTO.setPayResultViewDTOS(this.payResultViewDTOS);
        return payResultViewsDTO;
    }



    public SubscribeDao updateSubscribeTransform(String openId, Map<String, Integer> keyValue) {

        SubscribeDao subscribeDao = new SubscribeDao();
        for (String key:keyValue.keySet()){
            if (key.equals("is_sub_bill"))
                subscribeDao.setIs_sub_bill(keyValue.get(key));
            if (key.equals("is_sub_pay"))
                subscribeDao.setIs_sub_pay(keyValue.get(key));
            if (key.equals("is_sub_notice_pay"))
                subscribeDao.setIs_sub_notice_pay(keyValue.get(key));
            if (key.equals("is_sub_analysis"))
                subscribeDao.setIs_sub_analysis(keyValue.get(key));
        }
        subscribeDao.setUser_open_id(openId);
        return subscribeDao;
    }

    public List<UserSubDTO> findSubscribeTransform(SubscribeDao dao) {
        List<UserSubDTO> list = new ArrayList<>();

        UserSubDTO userSubDTO1 = new UserSubDTO("is_sub_bill","月度账单",dao.getIs_sub_bill());
        UserSubDTO userSubDTO2 = new UserSubDTO("is_sub_pay","缴费通知",dao.getIs_sub_pay());
        UserSubDTO userSubDTO3 = new UserSubDTO("is_sub_notice_pay","欠费提醒",dao.getIs_sub_notice_pay());
//        UserSubDTO userSubDTO4 = new UserSubDTO("is_sub_analysis","用能分析",dao.getIs_sub_analysis());

        list.add(userSubDTO1);
        list.add(userSubDTO2);
        list.add(userSubDTO3);
//        list.add(userSubDTO4);
        return list;

    }



}
