package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dto.RecordDTO;
import com.sgcc.dto.RecordDetailDTO;
import com.sgcc.dto.RecordTypeDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordService {

    public Result queryRecordInfo(String userNo) {

        RecordDetailDTO recordDetailDTO = new RecordDetailDTO("1","营业厅",100.00,"2019.08.02");
        RecordDetailDTO recordDetailDTO2 = new RecordDetailDTO("2","微信",100.00,"2019.08.23");
        List<RecordDetailDTO> recordTypeDTOS = new ArrayList<>();
        recordTypeDTOS.add(recordDetailDTO);
        recordTypeDTOS.add(recordDetailDTO2);

        RecordTypeDTO recordTypeDTO1 = new RecordTypeDTO("2019-08",200.00,recordTypeDTOS);
        RecordTypeDTO recordTypeDTO2 = new RecordTypeDTO("2019-07",200.00,recordTypeDTOS);
        RecordTypeDTO recordTypeDTO3 = new RecordTypeDTO("2019-06",200.00,recordTypeDTOS);
        RecordTypeDTO recordTypeDTO4 = new RecordTypeDTO("2019-05",200.00,recordTypeDTOS);
        RecordTypeDTO recordTypeDTO5 = new RecordTypeDTO("2019-04",200.00,recordTypeDTOS);
        RecordTypeDTO recordTypeDTO6 = new RecordTypeDTO("2019-03",200.00,recordTypeDTOS);

        List<RecordTypeDTO> recordDTOs = new ArrayList<>();
        recordDTOs.add(recordTypeDTO1);
        recordDTOs.add(recordTypeDTO2);
        recordDTOs.add(recordTypeDTO3);
        recordDTOs.add(recordTypeDTO4);
        recordDTOs.add(recordTypeDTO5);
        recordDTOs.add(recordTypeDTO6);

        RecordDTO recordDTO = new RecordDTO("刘德华",userNo,recordDTOs);


        return Result.success(recordDTO);
    }


}
