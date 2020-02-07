package com.sgcc.model;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.NoticeDao;
import com.sgcc.dao.RushRepairProgressDao;
import com.sgcc.dto.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.*;

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


    public List<NoticeListDTO> selectByDistrictTransform(List<NoticeDao> noticeDaos, List<RushRepairProgressDao> rushRepairProgressDaos){
        List<NoticeListDTO> noticeListDTOS = new ArrayList<>();

        noticeDaos.forEach(noticeDao -> {
            List<ProgressDTO> progressDTOS = new ArrayList<>();
            String dateUtil = noticeDao.getNoticeDate();
            Date date = Utils.GetDate(dateUtil.substring(dateUtil.indexOf("至")+1,dateUtil.length())+":59");
            System.out.println("DATE："+date);
            rushRepairProgressDaos.forEach(progressDao->{
                if (progressDao.getNotice_id().equals(noticeDao.getId())){
                    List<String> imgs = new ArrayList<>();
                    if (!Strings.isNullOrEmpty(progressDao.getProgressImg1())) {
                        imgs.add(progressDao.getProgressImg1());
                    }
                    if (!Strings.isNullOrEmpty(progressDao.getProgressImg2())){
                        imgs.add(progressDao.getProgressImg2());
                    }
                    if (!Strings.isNullOrEmpty(progressDao.getProgressImg3())){
                        imgs.add(progressDao.getProgressImg3());
                    }
                    progressDTOS.add(new ProgressDTO(
                            new SimpleDateFormat("MM/dd").format(progressDao.getSubmit_date()),
                            new SimpleDateFormat("HH:mm").format(progressDao.getSubmit_date()),
                            progressDao.getProgress_state(),
                            progressDao.getRepair_personnel(),
                            progressDao.getCause_of_failure(),
                            imgs
                    ));
                }
            });

            // 排序
            Collections.sort(progressDTOS, new Comparator<ProgressDTO>() {
                @Override
                public int compare(ProgressDTO o1, ProgressDTO o2) {
                    // 升序
                    return o1.getProgressState().compareTo(o2.getProgressState());
                    // 降序
                    // return o2.getProgressState().compareTo(o1.getProgressState());
                }
            });

            if(Utils.GetCurTime().getTime() < date.getTime()) {
                noticeListDTOS.add(new NoticeListDTO(
                        noticeDao.getTypeName(),
                        noticeDao.getNoticeDate(),
                        noticeDao.getRange(),
                        progressDTOS
                        )
                );
            }
        });

        return noticeListDTOS;
    }


    /**
     * 根据区域查询停电公告dao转dto
     */
    public void selectByDistrictTransform(){
        this.noticeDaos.forEach(noticeDao -> {
            String dateUtil = noticeDao.getNoticeDate();
            Date date = Utils.GetDate(dateUtil.substring(dateUtil.indexOf("至")+1,dateUtil.length())+":59");
            System.out.println("DATE："+date);
            if(Utils.GetCurTime().getTime() < date.getTime()) {
                this.noticeFormDTOS.add(new NoticeFormDTO(
                        noticeDao.getTypeName(),
                        noticeDao.getNoticeDate(),
                        noticeDao.getRange()
                        )
                );
            }
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
                this.addFormDTO.getNoticeDate()
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


    public static void main(String[] args) {
        String str = "2019-12-12 05:00至2019-12-12 07:30";
        System.out.println(""+str.substring(str.indexOf("至")+1,str.length())+":59");
    }


}
