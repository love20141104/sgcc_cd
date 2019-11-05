package com.sgcc.model;

import com.sgcc.dao.NoticeDao;
import com.sgcc.dto.AddFormDTO;
import com.sgcc.dto.NoticeFormDTO;
import com.sgcc.dto.QueryFormDTO;
import com.sgcc.dto.UpdateFormDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class NoticeDomainModel {

    private NoticeDao noticeDao;

    private AddFormDTO addFormDTO;

    private UpdateFormDTO updateFormDTO;

    private List<NoticeDao> noticeDaos = new ArrayList<>();

    private List<NoticeFormDTO> noticeFormDTOS = new ArrayList<>();

    private List<QueryFormDTO> queryFormDTOS = new ArrayList<>();

    public NoticeDomainModel(List<NoticeDao> noticeDaos) {
        this.noticeDaos = noticeDaos;
    }

    public NoticeDomainModel(UpdateFormDTO updateFormDTO) {
        this.updateFormDTO = updateFormDTO;
    }

    public NoticeDomainModel(AddFormDTO addFormDTO) {
        this.addFormDTO = addFormDTO;
    }

    /**
     * 根据区域查询停电公告dao转dto
     */
    public void selectByDistrictTransform(){
        this.noticeDaos.forEach(noticeDao -> {
            this.noticeFormDTOS.add(new NoticeFormDTO(
                    noticeDao.getTypeName(),
                    noticeDao.getNoticeDate(),
                    noticeDao.getRange()
                    )
            );
        });
    }

    /**
     * 新增停电公告dao转dto
     */
    public void insertTransform(){
        String id = UUID.randomUUID().toString();
        this.noticeDao = new NoticeDao(
                id,
                id,
                this.addFormDTO.getNoticeDistrict(),
                this.addFormDTO.getTypeName(),
                this.addFormDTO.getRange(),
                this.addFormDTO.getTime()
        );

    }


    /**
     * 修改停电公告dao转dto
     */
    public void updateTransform(){
        this.noticeDao = new NoticeDao(
                this.updateFormDTO.getNoticeId(),
                this.updateFormDTO.getNoticeId(),
                this.updateFormDTO.getNoticeDistrict(),
                this.updateFormDTO.getTypeName(),
                this.updateFormDTO.getRange(),
                this.updateFormDTO.getNoticeDate()
        );

    }


    public void selectAllTransform() {
        this.noticeDaos.forEach(noticeDao -> {
            this.queryFormDTOS.add(new QueryFormDTO(
                            noticeDao.getId(),
                            noticeDao.getNoticeId(),
                            noticeDao.getNoticeDistrict(),
                            noticeDao.getTypeName(),
                            noticeDao.getRange(),
                            noticeDao.getNoticeDate()
                    )
            );
        });
    }
}
