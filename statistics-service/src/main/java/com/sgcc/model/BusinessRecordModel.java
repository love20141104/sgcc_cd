package com.sgcc.model;

import com.sgcc.dao.BusinessRecordDao;
import com.sgcc.dto.BusinessRecordDTO;
import com.sgcc.dto.BusinessRecordViewDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class BusinessRecordModel {
    private List<BusinessRecordDao> businessRecordDaos = new ArrayList<>();
    private List<BusinessRecordDTO> businessRecordDTOS = new ArrayList<>();
    private BusinessRecordViewDTO businessRecordViewDTO = new BusinessRecordViewDTO();

    public BusinessRecordModel(List<BusinessRecordDao> businessRecordDaos) {
        this.businessRecordDaos = businessRecordDaos;
    }

    public void daos2dtos() {
        this.businessRecordDaos.forEach(dao -> {
            businessRecordDTOS.add(
                    new BusinessRecordDTO(
                            dao.getBusinessCategory()
                            , dao.getApplications()
                    )
            );
        });
    }

    public void viewDTOTransform() {
        int total = 0;

        for (BusinessRecordDTO dto : businessRecordDTOS) {
            total += dto.getApplications();
        }
        this.businessRecordViewDTO = new BusinessRecordViewDTO(total, this.businessRecordDTOS);
    }

}
