package com.sgcc.service;

import com.google.common.base.Strings;
import com.sgcc.dao.ConsumerManagerDao;
import com.sgcc.dto.ConsumerManagerDTO;
import com.sgcc.dto.ConsumerManagerInsertDTO;
import com.sgcc.entity.event.ConsumerManagerEventEntity;
import com.sgcc.entity.query.ConsumerManagerQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.ConsumerManagerDomainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.result.Result;

import javax.naming.NamingEnumeration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerManagerService {
    @Autowired
    private ConsumerManagerEventEntity consumerManagerEventEntity;
    @Autowired
    private ConsumerManagerQueryEntity consumerManagerQueryEntity;

    /**
     * 新增客户经理
     *
     * @param consumerManagerInsertDTO
     * @return
     */
    public Result insertConsumerManager(ConsumerManagerInsertDTO consumerManagerInsertDTO) {

        //参数检查start
        //TODO
        //参数检查end
        ConsumerManagerDomainModel consumerManagerDomainModel = new ConsumerManagerDomainModel(consumerManagerInsertDTO);
        consumerManagerDomainModel.insertTransform();
        ConsumerManagerDao consumerManagerDao = consumerManagerDomainModel.getConsumerManagerDao();

        try {
            //存入Mysql
            consumerManagerEventEntity.insertConsumerManager(consumerManagerDao);

            //重新加载redis中的数据
            List<ConsumerManagerDao> consumerManagerDaos = new ArrayList<>(consumerManagerQueryEntity.findAllConsumerManager());
            if (null != consumerManagerDaos && consumerManagerDaos.size() > 0) {
                consumerManagerEventEntity.saveAllInRedis(consumerManagerDaos);
            }

            //dao转dto
            consumerManagerDomainModel.selectTransform();
            ConsumerManagerDTO consumerManagerDTO = consumerManagerDomainModel.getConsumerManagerDTO();
            return Result.success(consumerManagerDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.SQL_ERR);
        }
    }

    /**
     * 删除客户经理
     */
    public Result deleteConsumerManager(String consumerManagerId) {
        //参数检查start
        //TODO
        //参数检查end

        try {
            consumerManagerEventEntity.deleteConsumerManager(consumerManagerId);
            //如果redis中存在则删除
            if (null != consumerManagerQueryEntity.findByIdInRedis(consumerManagerId)) {
                consumerManagerEventEntity.deleteRedisConsumerManager(consumerManagerId);
            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.SQL_ERR);
        }


    }

    /**
     * 批量删除客户经理信息
     */
    public Result deleteConsumerManagers(List<String> consumerManagerIds) {
        //参数检查start
        if (null == consumerManagerIds || consumerManagerIds.size() == 0) {
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
        //参数检查end

        try {
            consumerManagerEventEntity.deleteConsumerManagers(consumerManagerIds);

            consumerManagerIds.forEach(consumerManagerId -> {
                //如果redis中存在则删除  TODO 循环查redis，后期修改
                if (null != consumerManagerQueryEntity.findByIdInRedis(consumerManagerId)) {
                    consumerManagerEventEntity.deleteRedisConsumerManager(consumerManagerId);
                }
            });
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.SQL_ERR);
        }
    }

    /**
     * 修改客户经理
     */
    public Result updateConsumerManager(ConsumerManagerDTO consumerManagerDTO) {
        //参数检查start
        //TODO
        //参数检查end
        ConsumerManagerDomainModel consumerManagerDomainModel = new ConsumerManagerDomainModel(consumerManagerDTO);
        consumerManagerDomainModel.updateTransform();
        ConsumerManagerDao consumerManagerDao = consumerManagerDomainModel.getConsumerManagerDao();
        try {
            //修改mysql
            consumerManagerEventEntity.updateConsumerManager(consumerManagerDao);
            //从mysql中查询所有客户经理信息，加载到redis中
            List<ConsumerManagerDao> consumerManagerDaos = new ArrayList<>(consumerManagerQueryEntity.findAllConsumerManager());
            if (null != consumerManagerDaos && consumerManagerDaos.size() > 0) {
                consumerManagerEventEntity.saveAllInRedis(consumerManagerDaos);
            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.SQL_ERR);
        }

    }


    /**
     * 根据用户id查对应的客户经理信息
     */
    public Result selectConsumerManagerByUserId(String openId) {
        try {

            //TODO 根据用户id查询对应的客户经理id
            String consumerManagerId = "";
            ConsumerManagerDao consumerManagerDao = consumerManagerQueryEntity.findByIdInRedis(consumerManagerId);
            //如果redis中没查到
            if (null == consumerManagerDao) {
                //从mysql查出所有客户经理
                List<ConsumerManagerDao> consumerManagerDaos = new ArrayList<>(consumerManagerQueryEntity.findAllConsumerManager());
                //如果查询结果不为空
                if (null != consumerManagerDaos && consumerManagerDaos.size() > 0) {
                    //将结果重新加载到redis中
                    consumerManagerEventEntity.saveAllInRedis(consumerManagerDaos);
                    //再从redis中重新查找
                    consumerManagerDao = consumerManagerQueryEntity.findByIdInRedis(consumerManagerId);
                }
            }
            if (null == consumerManagerDao || Strings.isNullOrEmpty(consumerManagerDao.getConsumerManagerId())) {
                return Result.failure(TopErrorCode.NO_DATAS);
            } else {
                ConsumerManagerDomainModel consumerManagerDomainModel = new ConsumerManagerDomainModel(consumerManagerDao);
                consumerManagerDomainModel.selectTransform();
                ConsumerManagerDTO consumerManagerDTO = consumerManagerDomainModel.getConsumerManagerDTO();
                return Result.success(consumerManagerDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.SQL_ERR);
        }

    }

    /**
     * 查询所有客户经理信息
     */
    public Result selectConsumerManager() {

        try {
            List<ConsumerManagerDao> consumerManagerDaos = new ArrayList<>(consumerManagerQueryEntity.findAllConsumerManagerInRedis());
            if (null == consumerManagerDaos || consumerManagerDaos.size() == 0 || null == consumerManagerDaos.get(0)) {
                //从mysql中查询所有客户经理信息，加载到redis中
                consumerManagerDaos = new ArrayList<>(consumerManagerQueryEntity.findAllConsumerManager());
                if (null != consumerManagerDaos && consumerManagerDaos.size() > 0) {
                    consumerManagerEventEntity.saveAllInRedis(consumerManagerDaos);
                } else {
                    return Result.failure(TopErrorCode.NO_DATAS);
                }
            }
            //清洗
            ConsumerManagerDomainModel consumerManagerDomainModel = new ConsumerManagerDomainModel(consumerManagerDaos);
            consumerManagerDomainModel.selectAllTransform();
            return Result.success(consumerManagerDomainModel.getConsumerManagerListDTO());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.SQL_ERR);
        }
    }
}
