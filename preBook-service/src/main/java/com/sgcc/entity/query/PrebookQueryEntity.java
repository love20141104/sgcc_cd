package com.sgcc.entity.query;

import com.sgcc.dao.PreBookDao;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.dtomodel.prebook.ServiceHallPrebookStatusDTO;
import com.sgcc.model.PrebookDomainModel;
import com.sgcc.repository.PrebookRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PrebookQueryEntity {
    @Autowired
    private PrebookRedisRepository prebookRedisRepository;


//    /**
//     * 根据用户id查询所有的预约信息
//     *
//     * @param openId
//     */
//    public List<PrebookDTO> getPrebookInfosByUser(String openId) {
//
//
//        PrebookDomainModel prebookModel = new PrebookDomainModel(openId);
//        return prebookModel.getPrebookDTOS();
//    }


    /**
     * 根据营业亭id查询营业厅预约状态
     *
     * @param serviceHallId
     */
    public List<PreBookDao> getPrebookInfosByServiceHall(String serviceHallId, String prebookDate) {

        List<PreBookDao> preBookDaos = prebookRedisRepository.findAllByServiceHallIdAndPrebookDate(
                serviceHallId, prebookDate
        );
        return preBookDaos;
    }

    /**
     * 用户当天的预约次数是否超过限制
     *
     * @param userId
     * @param date
     * @return
     */
    public boolean findAllByUserIdAndPrebookDate(String userId, String date, String prebookStartTime) {
        List<PreBookDao> prebookDaos =
                prebookRedisRepository.findAllByUserIdAndPrebookDate(userId, date);
        //判断是否为空
        if (null == prebookDaos  ) {
            return false;
        }
        //若不为空,且当日预约次数未达到限制
        else if(prebookDaos.size() < 2){
            //判断是否存在相同时间段的预约
            for (PreBookDao prebookDao : prebookDaos) {
                //如果有同一个时间段的预约信息，则不允许相同预约重复提交
                if (prebookDao.getPrebookStartTime().equals(prebookStartTime)) {
                    return true;
                }
            }
            //如果没有同一个时间段的预约信息，则可以继续提交
            return false;
        }
        //预约次数达到限制，不可以预约
        else {
            System.out.println("userid:"+prebookDaos.get(0).getUserId()+" userid:"+userId+"  Size:"+prebookDaos.size());
            return true;
        }
    }


    /**
     * 根据预约ids查找预约信息
     *
     * @param ids
     * @return
     */
    public List<PreBookDao> findPrebookList(List<String> ids) {
        return new ArrayList<PreBookDao>() {{
            ids.forEach(id -> {
                add(prebookRedisRepository.findById(id).get());
            });
        }};
    }

    /**
     * 判断此营业厅该时段预约次数是否超过限制
     *
     * @param serviceHallId
     * @param prebookDate
     * @param prebookStartTime
     * @return
     */
    public boolean findAllByServiceHallIdAndPrebookDateAndPrebookStartTime(
            String serviceHallId, String prebookDate, String prebookStartTime
    ) {
        List<PreBookDao> prebookDaos =
                prebookRedisRepository.findAllByServiceHallIdAndPrebookDateAndPrebookStartTime(serviceHallId, prebookDate, prebookStartTime);
        if (null == prebookDaos || prebookDaos.size() < 4) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 根据用户id查预约信息
     *
     * @param openId
     * @return
     */
    public List<PreBookDao> findAllByUserId(String openId) {
        return prebookRedisRepository.findAllByUserId(openId);
    }

}
