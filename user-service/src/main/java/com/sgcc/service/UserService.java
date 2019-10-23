package com.sgcc.service;

import com.example.Utils;
import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dto.ElectricityTypeDTO;
import com.sgcc.dto.MonthlyBillsDTO;
import com.sgcc.exception.TopErrorCode;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {


    public Result queryBillInfoById(String userNo,long date){
        if (Strings.isNullOrEmpty(userNo))
            return Result.failure(TopErrorCode.NO_DATAS);

        try {

            MonthlyBillsDTO monthlyBillsDTO = new MonthlyBillsDTO();
            monthlyBillsDTO.setUserName("张三");
            monthlyBillsDTO.setUserNo(userNo);
            monthlyBillsDTO.setBillMonth(new Date(date));
            monthlyBillsDTO.setStartDate(Utils.GetMonth(new Date(date)));
            monthlyBillsDTO.setEndDate(Utils.GetTimeForYMD(new Date(date)));
            monthlyBillsDTO.setStartNum(16366);
            monthlyBillsDTO.setEndNum(16699);
            monthlyBillsDTO.setTotalNum(monthlyBillsDTO.getEndNum()-monthlyBillsDTO.getStartNum());
            monthlyBillsDTO.setTotalSum(201.43);

            List<ElectricityTypeDTO> flist = new ArrayList<>();
            List<ElectricityTypeDTO> slist = new ArrayList<>();


            ElectricityTypeDTO electricityTypeDTO = null;
            electricityTypeDTO = new ElectricityTypeDTO();
            electricityTypeDTO.setName("第一阶梯");
            electricityTypeDTO.setTotalNum(180);
            electricityTypeDTO.setTotalSum(117.63);
            flist.add(electricityTypeDTO);

            electricityTypeDTO = new ElectricityTypeDTO();
            electricityTypeDTO.setName("第二阶梯");
            electricityTypeDTO.setTotalNum(100);
            electricityTypeDTO.setTotalSum(67.20);
            flist.add(electricityTypeDTO);

            electricityTypeDTO = new ElectricityTypeDTO();
            electricityTypeDTO.setName("第三阶梯");
            electricityTypeDTO.setTotalNum(100);
            electricityTypeDTO.setTotalSum(67.20);
            flist.add(electricityTypeDTO);


            ElectricityTypeDTO electricityTypeDTO2 = null;
            electricityTypeDTO2 = new ElectricityTypeDTO();
            electricityTypeDTO2.setName("峰段");
            electricityTypeDTO2.setTotalNum(180);
            electricityTypeDTO2.setTotalSum(117.63);
            slist.add(electricityTypeDTO2);

            electricityTypeDTO2 = new ElectricityTypeDTO();
            electricityTypeDTO2.setName("平段");
            electricityTypeDTO2.setTotalNum(100);
            electricityTypeDTO2.setTotalSum(67.20);
            slist.add(electricityTypeDTO2);

            electricityTypeDTO2 = new ElectricityTypeDTO();
            electricityTypeDTO2.setName("谷段");
            electricityTypeDTO2.setTotalNum(100);
            electricityTypeDTO2.setTotalSum(67.20);
            slist.add(electricityTypeDTO2);

            monthlyBillsDTO.setFlist(flist);
            monthlyBillsDTO.setSlist(slist);
            return Result.success(monthlyBillsDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询月度账单失败!");
        }
    }





}