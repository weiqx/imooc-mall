package com.imooc.mall.exception;

/**
 * 异常枚举
 * 非常方便未来开发
 */
public enum ImoocMallExceptionEnum {

    NEED_USER_NAME(10001, "用户名不能为空"),
    NEED_PASSWORD(10002,"密码不能为空"),
    PASSWORD_TOO_SHORT(10003, "密码长度不能小于8位"),
    NAME_EXISTED(10004, "不允许重名"),
    INSERT_FAILED(10005, "插入失败，请重试"),
    WRONG_PASSWORD(10006, "密码错误"),
    NEED_LOGIN(10007, "用户未登录"),
    UPDATE_FAILED(10008, "更新失败"),
    NEED_ADMIN(10009, "无管理员权限"),
    NAME_NOT_NULL(100010, "参数不能为空"),
    CREATE_FAILED(100011, "新增失败"),
    REQUEST_PARAM_ERROR(100012, "参数错误"),
    DELETE_FAILED(100013, "删除失败"),
    MKDIR_FAILED(100014, "文件夹创建失败"),
    UPLOAD_FAILED(100015, "图片上传失败"),
    NOT_SALE(100016, "商品状态不可售"),
    NOT_ENOUGH(10017, "商品库存不足"),
    CART_EMPTY(10018, "购物车已勾选的商品为空"),
    NO_ENUM(10019, "未找到对应的枚举类"),
    NO_ORDER(10020, "订单不存在"),
    NOT_YOUR_ORDER(10021, "订单不属于你"),

    SYSTEM_ERROR(20000, "系统异常");

    Integer code;

    String msg;

    ImoocMallExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
