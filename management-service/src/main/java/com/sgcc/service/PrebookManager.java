package com.sgcc.service;

import com.example.constant.PrebookStartTimeConstants;
import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.PreBookDao;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.entity.event.PrebookEventEntity;
import com.sgcc.entity.query.PrebookQueryEntity;
import com.sgcc.exception.TopErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class PrebookManager {
    @Autowired
    private ProbookService probookService;
    @Autowired
    private PrebookEventEntity prebookEventEntity;
    @Autowired
    private PrebookQueryEntity prebookQueryEntity;
    /**
     *  ================================= 预约信息start =================================
     */

    /**
     * 后台管理系统修改预约信息
     *
     * @param prebookCode
     * @param prebookDTO
     * @return
     */
    public Result updatePrebookDTO(String prebookCode, PrebookDTO prebookDTO) {
        try {
            //参数检查 start
            if (null == prebookDTO) {
                throw new RuntimeException("prebookDTO为空");
            }
            if (Strings.isNullOrEmpty(prebookCode) || Strings.isNullOrEmpty(prebookDTO.getPrebookCode())) {
                throw new RuntimeException("prebookCode为空");
            }
            if (!prebookDTO.getPrebookCode().equals(prebookCode)) {
                throw new RuntimeException("prebookCode:" + prebookCode + ",prebookCode:" + prebookDTO.getPrebookCode() + ",两者不匹配！！");
            }
            if (Strings.isNullOrEmpty(prebookDTO.getServiceHallId())) {
                throw new RuntimeException("serviceHallId为空");
            }
            if (!PrebookStartTimeConstants.TIME_LIST.contains(prebookDTO.getPrebookStartTime())) {
                throw new RuntimeException("预约时间段错误");
            }
            if (Strings.isNullOrEmpty(prebookDTO.getContact()) || Strings.isNullOrEmpty(prebookDTO.getContactTel())) {
                throw new RuntimeException("联系人相关信息为空");
            }
            if (Strings.isNullOrEmpty(prebookDTO.getUserId())) {
                throw new RuntimeException("userid为空");
            }
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String S = format1.format(format1.parse(prebookDTO.getPrebookDate().trim()));
            prebookDTO.setPrebookDate(S);
            prebookDTO.setPrebookStartTime(prebookDTO.getPrebookStartTime().trim());
            prebookDTO.setContact(prebookDTO.getContact().trim());
            prebookDTO.setUserId(prebookDTO.getUserId().trim());
            prebookDTO.setContactTel(prebookDTO.getContactTel().trim());
            prebookDTO.setServiceHallId(prebookDTO.getServiceHallId().trim());
            //参数检查 end
            //改mysql
            PreBookDao preBookDao = prebookEventEntity.updatePrebook(prebookDTO);
            if (null != preBookDao) {
                System.out.println("id:" + preBookDao.getId() + " 的预约信息已修改");
            } else {
                System.out.println("修改失败");
                throw new RuntimeException("修改失败");
            }

            //如果redis中存在则更新
            if(null != prebookQueryEntity.findByIdInRedis(preBookDao.getId())){
                prebookEventEntity.cacheSubmitPreBookDao(preBookDao);
            }

            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        }
    }

    /**
     * 后台管理系统作废预约信息
     *
     * @param prebookCode
     * @return
     */
    public Result deletePrebookDTO(String prebookCode) {

        try {
            //参数检查 start
            if (Strings.isNullOrEmpty(prebookCode)) {
                throw new RuntimeException("prebookCode为空");
            }
            //参数检查 end
            String id = prebookEventEntity.deletePrebook(prebookCode);
            if (!Strings.isNullOrEmpty(id)) {
                System.out.println("id:" + id + " 的预约信息已删除");
            } else {
                System.out.println("删除失败");
                throw new RuntimeException("删除失败");
            }
            return Result.success();
        } catch (Exception e) {
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
    }

    /**
     * 后台管理系统增加预约信息
     *
     * @param prebookDTO
     * @param openId
     * @return
     */
    public Result insertPrebookDTO(PrebookDTO prebookDTO, String openId) {

        return probookService.submitPrebookInfo(prebookDTO, openId);
    }

    /**
     * 后台管理系统查询预约信息
     *
     * @param user_open_id
     * @param service_hall_id
     * @param prebook_code
     * @param prebook_date_start
     * @param prebook_date_end
     * @return
     */
    public Result selectPrebookDTO(
            String user_open_id
            , String service_hall_id
            , String prebook_code
            , String prebook_date_start
            , String prebook_date_end
    ) {
        try {
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            prebook_date_start = format1.format(format1.parse(prebook_date_start.trim()));
            prebook_date_end = format1.format(format1.parse(prebook_date_end.trim()));
            return Result.success(prebookQueryEntity.getPrebook(user_open_id, service_hall_id, prebook_code, prebook_date_start, prebook_date_end));
        } catch (ParseException e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }


    }
    /**
     * ================================= 预约信息end =======================================
     */
}
