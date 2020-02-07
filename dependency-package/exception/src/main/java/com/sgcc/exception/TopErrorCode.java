package com.sgcc.exception;

//import lombok.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum TopErrorCode {
    SUCCESS( 0 , "成功" ),
    FAILURE(12,"失败"),
    NULL_OBJ( 1 , "对象为空" ),
    INVALID_OBJ( 2 , "无效的数据" ),
    SQL_ERR( 3 , "数据库操作失败" ),
    SYSCFG_ERR( 4 , "系统配置错误" ),
    SAVE_OBJ_ERR(5 , "保存数据失败"),
    ZERO_OBJ( 6 , "没有数据" ),
    INVALID_PARAMS(7,"参数错误"),
    GET_META_ERR(8 , "获取Meta信息失败"),
    EXEC_PROC_ERR(9 , "启动流程失败"),
    PARENT_NOT_EXIST(10 , "父节点不存在"),
    EXEC_PUSH_DOWN_ERR(11 , "下推失败"),
    GENERAL_ERR( 100 , "通用错误" ),
    PREBOOK_FULL( 10001 , "该时间段预约已满" ),
    EXCEEDING_LIMIT( 10002 , "超过最大预约次数" ),
    PARAMETER_ERR( 101 , "参数错误" ),
    NO_DATAS( 102 , "未找到数据" ),
    HOUSEHOLD_BIND_NUM_EXCEED(20001,"用户绑定户号超过5个，无法绑定"),
    USER_BIND_NUM_EXCEED(20002,"该户号已被超过5个用户绑定，无法被绑定"),
    HOUSEHOLD_PWD_ERR(20003,"户号密码错误"),
    NONE_HOUSEHOLD_INFO(20004,"请先绑定户号"),
    HOUSEHOLD_BIND_REPEAT(20005,"用户已绑定该户号，无法绑定"),
    DECRYPTION_FAILED(20006,"解密失败"),
    HOUSEHOLD_NUM_ERR(20007,"户号长度错误"),
    DEVICE_EXCEPTION(20008,"设备正在维护"),
    LINE_UP(30001,"排号次数已达上限"),
    IS_EXIST_CHECKER(30002,"该营业厅审核人已存在"),
    IS_EXIST_STATE(30003,"该状态已存在已存在");

    //错误码
    private Integer errcode;
    //错误描述
    private String desc;

//    TopErrorCode(int errcode, String desc) {
//        this.errcode = errcode;
//        this.desc = desc;
//    }


    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


//    public Integer getErrcode() {
//        return errcode;
//    }
//
//    public String getDesc() {
//        return desc;
//    }

    @Override
    public String toString() {
        return "[" + this.errcode + "]" + this.desc;
    }

}
