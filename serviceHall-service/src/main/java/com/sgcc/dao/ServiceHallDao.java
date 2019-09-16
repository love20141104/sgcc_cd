package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 营业网点
 */

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class ServiceHallDao {

    private String id;

    // 营业厅id
    private String serviceHallId;

    // 营业厅名
    private String serviceHallName;

    // 营业厅地址
    private String serviceHallAddr;

    // 营业时间
    private String serviceHallOpenTime;

    // 联系电话
    private String serviceHallTel;

    // 营业厅位置纬度
    private Double serviceHallLatitude;

    // 营业厅位置经度
    private Double serviceHallLongitude;

    // 营业厅所属行政区域
    private String serviceHallDistrict;

    // 是否可用
    private Boolean serviceHallAvailable;

    // 业务范围
    private String serviceHallBusinessDesc;

    // 交通方式
    private String serviceHallTraffic;

    // 标志性建筑
    private String serviceHallLandmarkBuilding;

    // 营业厅所属机构
    private String serviceHallOwner;

    // 覆盖行政区域
    private String serviceHallBusinessDistrict;

    // 营业厅等级
    private String serviceHallRank;

    // 营业厅可否代收
    private Boolean serviceHallCollect;

}
