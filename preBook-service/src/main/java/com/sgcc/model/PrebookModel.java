package com.sgcc.model;

import com.sgcc.dao.PreBookDao;

import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class PrebookModel implements Serializable {

    private static final long serialVersionUID = -317554114324907897L;
    List<PrebookDTO> prebookDTOS = new ArrayList<>();
    List<PreBookDao> preBookDaos = new ArrayList<>();

    public PrebookModel(List<PreBookDao> preBookDaos) {
        this.prebookDTOS = null;//todo
        this.preBookDaos = preBookDaos;

        this.prebookDTOS = new ArrayList<PrebookDTO>() {{
            preBookDaos.forEach(preBookDao -> {
                add(new PrebookDTO(
                        preBookDao.getUserId()
                        , preBookDao.getServiceHallId()
                        , preBookDao.getPrebookDate()
                        , preBookDao.getPrebookStartTime()
                        , preBookDao.getPrebookCode()
                        , preBookDao.getContact()
                        , preBookDao.getContactTel()
                        , preBookDao.getSubmitDate()
                ));
            });
        }};

    }

    public PrebookModel dto2dao(List<PrebookDTO> prebookDTOS) {
        this.prebookDTOS = prebookDTOS;

        this.preBookDaos = new ArrayList<PreBookDao>() {{
            prebookDTOS.forEach(prebookDTO -> {
                //TODO 编码规则
                prebookDTO.setPrebookCode(UUID.randomUUID().toString());
                add(
                        new PreBookDao(
                                DateUtils.getSeconds() + (DateUtils.daysBetweenTwoDate(new Date(), prebookDTO.getPrebookDate()) * 24 * 3600)
                                , UUID.randomUUID().toString()
                                , prebookDTO.getUserId()
                                , prebookDTO.getServiceHallId()
                                , prebookDTO.getPrebookDate()
                                , prebookDTO.getPrebookStartTime()
                                , prebookDTO.getPrebookCode()
                                , prebookDTO.getContact()
                                , prebookDTO.getContactTel()
                                , prebookDTO.getSubmitDate()
                        )

                );
            });

        }};
        return this;
    }


}
