package cn.santeamo.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BizResultCode implements IResultCode {
    /**
     * 状态码
     */
    NO_REPLAY(999, "没有收到回复数据"),
    DEVICE_NOT_EXISTS(1000, "设备在本地服务表中不存在"),
    DEVICE_TYPE_UNKNOWN(1001, "设备类型未知"),
    DEVICE_TYPE_ERROR(1002, "设备类型未知"),
    DEVICE_NOT_LOGIN(1003, "设备未登录"),
    DEVICE_MANUFACTURERS_NOT_SUPPORT(1004, "设备厂商不支持"),
    DEVICE_MANUFACTURERS_NOT_AJHUA(1005, "设备不是大华设备"),
    DEVICE_MANUFACTURERS_NOT_HIKVISION(1006, "设备不是海康设备"),
    DEVICE_SERVICE_DEVICE_TYPE_NOT_SUPPORT(1007, "deviceService 不支持的设备类型"),
    DEVICE_TYPE_NOT_SUPPORT(1008, "连接设备失败，不支持的设备类型"),
    DEVICE_LOGIN_FAILED(1009, "设备登录失败"),
    DEVICE_MANUFACTURERS_NOT_UNIVIEW(1010, "设备不是宇视设备"),
    DEVICE_CONTROL_MSG_TYPE_NOT_SUPPORT(2000, "设备控制操作失败，不支持的消息类型"),
    DEVICE_CONTROL_DEVICE_TYPE_NOT_SUPPORT(2001, "设备控制操作失败，不支持的设备类型"),
    ;

    final int code;
    final String message;
}
