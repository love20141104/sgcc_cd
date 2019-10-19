package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dto.ElectricityTypeDTO;
import com.sgcc.dto.MonthlyBillsDTO;
import com.sgcc.entity.BillInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class BillService {

    @Autowired
    private BillInfoEntity billInfoEntity;

    public Result queryBillInfoById(String userId){
        try {

            MonthlyBillsDTO monthlyBillsDTO = new MonthlyBillsDTO();
            monthlyBillsDTO.setUserName("张三");
            monthlyBillsDTO.setUserNo("1234567890");
            monthlyBillsDTO.setBillMonth(new Date().getMonth());
            monthlyBillsDTO.setStartNum(16366);
            monthlyBillsDTO.setEndNum(16699);
            monthlyBillsDTO.setTotalNum(monthlyBillsDTO.getEndNum()-monthlyBillsDTO.getStartNum());
            monthlyBillsDTO.setTotalSum(201.43);

            Map<String, ElectricityTypeDTO> map = new LinkedHashMap<>();


            ElectricityTypeDTO electricityTypeDTO = null;
            electricityTypeDTO = new ElectricityTypeDTO();
            electricityTypeDTO.setName("第一阶梯");
            electricityTypeDTO.setTotalNum(180);
            electricityTypeDTO.setTotalSum(117.63);

            electricityTypeDTO = new ElectricityTypeDTO();
            electricityTypeDTO.setName("第二阶梯");
            electricityTypeDTO.setTotalNum(100);
            electricityTypeDTO.setTotalSum(67.20);

            electricityTypeDTO = new ElectricityTypeDTO();
            electricityTypeDTO.setName("第三阶梯");
            electricityTypeDTO.setTotalNum(100);
            electricityTypeDTO.setTotalSum(67.20);


            ElectricityTypeDTO electricityTypeDTO2 = null;
            electricityTypeDTO2 = new ElectricityTypeDTO();
            electricityTypeDTO2.setName("第一阶梯");
            electricityTypeDTO2.setTotalNum(180);
            electricityTypeDTO2.setTotalSum(117.63);

            electricityTypeDTO2 = new ElectricityTypeDTO();
            electricityTypeDTO2.setName("第二阶梯");
            electricityTypeDTO2.setTotalNum(100);
            electricityTypeDTO2.setTotalSum(67.20);

            electricityTypeDTO2 = new ElectricityTypeDTO();
            electricityTypeDTO2.setName("第三阶梯");
            electricityTypeDTO2.setTotalNum(100);
            electricityTypeDTO2.setTotalSum(67.20);

            map.put("electricityTypeDTO",electricityTypeDTO);
            map.put("electricityTypeDTO2",electricityTypeDTO2);
            monthlyBillsDTO.setMap(map);
            return Result.success(monthlyBillsDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询月度账单失败!");
        }
    }



}
