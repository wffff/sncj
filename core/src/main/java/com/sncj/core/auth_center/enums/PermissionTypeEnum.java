package com.sncj.core.auth_center.enums;

/**
 * Created by Danny on 2018/7/18.
 */
public enum PermissionTypeEnum {
    MENU(0, "菜单"),
    TABS(1, "选项卡"),
    FUNC(2, "功能"),
    ;

    public Integer code;
    public String des;

    PermissionTypeEnum(Integer code, String des) {
        this.code = code;
        this.des = des;
    }

    public static PermissionTypeEnum get(Integer code) {
        for (PermissionTypeEnum c : PermissionTypeEnum.values()) {
            if (c.code.toString().equals(code.toString())) {
                return c;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "code:" + code + ", des:" + des;
    }
}
