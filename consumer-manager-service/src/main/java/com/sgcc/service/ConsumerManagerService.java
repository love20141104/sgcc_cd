package com.sgcc.service;

import com.google.common.base.Strings;
import com.sgcc.dao.ConsumerManagerDao;
import com.sgcc.dto.ConsumerManagerDTO;
import com.sgcc.dto.ConsumerManagerGroupDTO;
import com.sgcc.dto.ConsumerManagerInsertDTO;
import com.sgcc.entity.event.ConsumerManagerEventEntity;
import com.sgcc.entity.query.ConsumerManagerQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.ConsumerManagerDomainModel;
import com.sgcc.util.StreetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.result.Result;

import java.sql.SQLException;
import java.util.*;

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
        if (
                Strings.isNullOrEmpty(
                        consumerManagerInsertDTO.getConsumerManagerName()) ||
                        Strings.isNullOrEmpty(consumerManagerInsertDTO.getConsumerManagerTel()) ||
                        Strings.isNullOrEmpty(consumerManagerInsertDTO.getConsumerManagerServiceArea()) ||
                        //Strings.isNullOrEmpty(consumerManagerInsertDTO.getConsumerManagerAdministrativeRegion()) ||
                        Strings.isNullOrEmpty(consumerManagerInsertDTO.getConsumerManagerDuty()) ||
                        Strings.isNullOrEmpty(consumerManagerInsertDTO.getConsumerManagerWorkTime())
                        //Strings.isNullOrEmpty(consumerManagerInsertDTO.getConsumerManagerEmergencyTel()) ||
                        //Strings.isNullOrEmpty(consumerManagerInsertDTO.getConsumerManagerWorkUnit()) ||
                        //Strings.isNullOrEmpty(consumerManagerInsertDTO.getConsumerManagerCategory()) ||
                        //Strings.isNullOrEmpty(consumerManagerInsertDTO.getConsumerManagerImg())
                ) {
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
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
        if (Strings.isNullOrEmpty(consumerManagerDTO.getConsumerManagerId()) ||
                Strings.isNullOrEmpty(consumerManagerDTO.getConsumerManagerName()) ||
                Strings.isNullOrEmpty(consumerManagerDTO.getConsumerManagerTel()) ||
                Strings.isNullOrEmpty(consumerManagerDTO.getConsumerManagerServiceArea()) ||
                Strings.isNullOrEmpty(consumerManagerDTO.getConsumerManagerAdministrativeRegion()) ||
                Strings.isNullOrEmpty(consumerManagerDTO.getConsumerManagerDuty()) ||
                Strings.isNullOrEmpty(consumerManagerDTO.getConsumerManagerWorkTime()) ||
                Strings.isNullOrEmpty(consumerManagerDTO.getConsumerManagerEmergencyTel()) ||
                Strings.isNullOrEmpty(consumerManagerDTO.getConsumerManagerWorkUnit()) ||
                Strings.isNullOrEmpty(consumerManagerDTO.getConsumerManagerCategory()) ||
                Strings.isNullOrEmpty(consumerManagerDTO.getConsumerManagerImg())) {
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
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
     * 取出五条客户经理信息，返回一条数据
     * @param consumerManagerID
     * @return
     */
    public Result selectConsumerManagerById(String consumerManagerID) {
        try {

//            List<ConsumerManagerDao> consumerManagerDaos = new ArrayList<>(consumerManagerQueryEntity.findFiveConsumerManagerDaos());
//            ConsumerManagerDomainModel consumerManagerDomainModel = new ConsumerManagerDomainModel(consumerManagerDaos.get(0));
            ConsumerManagerDao dao = consumerManagerQueryEntity.findById(consumerManagerID);
            ConsumerManagerDomainModel consumerManagerDomainModel = new ConsumerManagerDomainModel(dao);
            consumerManagerDomainModel.selectTransform();
            ConsumerManagerDTO consumerManagerDTO = consumerManagerDomainModel.getConsumerManagerDTO();
            return Result.success(consumerManagerDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.SQL_ERR);
        }

    }


    /**
     * 根据用户id查对应的客户经理信息
     */

//    public Result selectConsumerManagerByUserId(String openId) {
//        try {
//
//            //TODO 根据用户id查询对应的客户经理id
//            String consumerManagerId = "test1";
//            ConsumerManagerDao consumerManagerDao = consumerManagerQueryEntity.findByIdInRedis(consumerManagerId);
//            //如果redis中没查到
//            if (null == consumerManagerDao) {
//                //从mysql查出所有客户经理
//                List<ConsumerManagerDao> consumerManagerDaos = new ArrayList<>(consumerManagerQueryEntity.findAllConsumerManager());
//                //如果查询结果不为空
//                if (null != consumerManagerDaos && consumerManagerDaos.size() > 0) {
//                    //将结果重新加载到redis中
//                    consumerManagerEventEntity.saveAllInRedis(consumerManagerDaos);
//                    //再从redis中重新查找
//                    consumerManagerDao = consumerManagerQueryEntity.findByIdInRedis(consumerManagerId);
//                }
//            }
//            if (null == consumerManagerDao || Strings.isNullOrEmpty(consumerManagerDao.getConsumerManagerId())) {
//                return Result.failure(TopErrorCode.NO_DATAS);
//            } else {
//                ConsumerManagerDomainModel consumerManagerDomainModel = new ConsumerManagerDomainModel(consumerManagerDao);
//                consumerManagerDomainModel.selectTransform();
//                ConsumerManagerDTO consumerManagerDTO = consumerManagerDomainModel.getConsumerManagerDTO();
//                return Result.success(consumerManagerDTO);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.failure(TopErrorCode.SQL_ERR);
//        }
//
//    }

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
            return Result.success(consumerManagerDomainModel.getConsumerManagerGroupDTO());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.SQL_ERR);
        }
    }

    public Result selectConsumerManagerByArea( String area) {
        if (Strings.isNullOrEmpty(area)){
            area="";
        }
        String street = StreetUtil.subStreet(area);
        area = StreetUtil.city(area);
        if(street.contains(area)){
            street=street.replaceAll(area,"");
        }
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
            ConsumerManagerGroupDTO groupDTO = consumerManagerDomainModel.getConsumerManagerGroupDTO();
            Map<String, List<ConsumerManagerDTO>> gourpMap = groupDTO.getGourpMap();
            ArrayList<Map> mapList = new ArrayList<>();
            if(gourpMap.get(area)!=null&&gourpMap.get(area).size()>0){
                List<ConsumerManagerDTO> list = gourpMap.get(area);
                ArrayList<ConsumerManagerDTO> has = new ArrayList<>();
                ArrayList<ConsumerManagerDTO> nothas = new ArrayList<>();
                String finalStreet = street;
                list.forEach(dto->{
                    if(dto.getConsumerManagerServiceArea().contains(finalStreet)){
                        has.add(dto);
                    }else {
                        nothas.add(dto);
                    }
                });
                has.addAll(nothas);
                HashMap<String, Object> map = new HashMap<>();
                map.put("area",area);
                map.put("data",has);
                mapList.add(map);
                return Result.success(mapList);
            }
            if (!Strings.isNullOrEmpty(area)&&(gourpMap.get(area)==null||gourpMap.get(area).size()<1)){
                return Result.success();
            }
            gourpMap.keySet().forEach(k->{
                List<ConsumerManagerDTO> list = gourpMap.get(k);
                HashMap<String, Object> map = new HashMap<>();
                map.put("area",k);
                map.put("data",list);
                mapList.add(map);
            });
            return Result.success(mapList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.SQL_ERR);
        }
    }
    public void  initRedis(){
        List<ConsumerManagerDao> consumerManagerDaos  = null;
        try {
            consumerManagerDaos = new ArrayList<>(consumerManagerQueryEntity.findAllConsumerManager());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (null != consumerManagerDaos && consumerManagerDaos.size() > 0) {
                consumerManagerEventEntity.saveAllInRedis(consumerManagerDaos);
        }
    }

    public Result selectConsumerManagerByKey(String area, String key) {
        if (Strings.isNullOrEmpty(area)){
            area="";
        }
        if(area.contains("市")){
            area=area.replaceAll("市","");
        }
        if(area.contains("县")){
            area=area.replaceAll("县","");
        }
        if(area.contains("区")){
            area=area.replaceAll("区","");
        }
        try {
            List<ConsumerManagerDao> consumerManagerDaos = consumerManagerQueryEntity.findConsumerManagerBykey(key);
            //清洗
            ConsumerManagerDomainModel consumerManagerDomainModel = new ConsumerManagerDomainModel(consumerManagerDaos);
            consumerManagerDomainModel.selectAllTransform();
            ConsumerManagerGroupDTO groupDTO = consumerManagerDomainModel.getConsumerManagerGroupDTO();
            Map<String, List<ConsumerManagerDTO>> gourpMap = groupDTO.getGourpMap();
            HashMap<String, List<ConsumerManagerDTO>> hashMap = new LinkedHashMap<>();
            if(gourpMap.get(area)!=null&&gourpMap.get(area).size()>0){
                hashMap.put(area,gourpMap.get(area));

                gourpMap.keySet().forEach(k->{
                    hashMap.put(k,gourpMap.get(k));
                });
                groupDTO.setGourpMap(hashMap);
            }
            Map<String, List<ConsumerManagerDTO>> dtoGourpMap = groupDTO.getGourpMap();
            ArrayList<Map> mapList = new ArrayList<>();
            dtoGourpMap.keySet().forEach(k->{
                List<ConsumerManagerDTO> list = dtoGourpMap.get(k);
                HashMap<String, Object> map = new HashMap<>();
                map.put("area",k);
                map.put("data",list);
                mapList.add(map);
            });

            return Result.success(mapList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.SQL_ERR);
        }
    }
}
