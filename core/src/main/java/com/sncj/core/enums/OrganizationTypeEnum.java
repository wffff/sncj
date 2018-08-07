package com.sncj.core.enums;

/**
 * Created by Danny on 2018/7/13.
 */
public enum OrganizationTypeEnum {
    GROUP(0, "集团"),
    COMPANY(1, "子公司"),
    SHOP(2, "车间"),
    DEVICE(3,"设备");

    public Number code;
    public String des;

    OrganizationTypeEnum(Number code, String des) {
        this.code = code;
        this.des = des;
    }

    public static OrganizationTypeEnum get(Integer code) {

        for (OrganizationTypeEnum c : OrganizationTypeEnum.values()) {
            if (c.code.toString().equals(code.toString())) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "code:" + code + ", des:" + des;
    }

}
