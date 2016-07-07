package com.adu.cookbook.constants;

/**
 * @author yunjie.du
 * @date 2016/7/7 11:30
 */
public enum CookDegreeEnum {
    DEGREE1(1, "小菜一碟"),
    DEGREE2(2, "游刃有余"),
    DEGREE3(3, "家常快餐"),
    DEGREE4(4, "小试牛刀"),
    DEGREE5(5, "九牛二虎");

    private final int code;
    private final String desc;

    CookDegreeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CookDegreeEnum codeOf(int code) {
        for (CookDegreeEnum cookDegreeEnum : CookDegreeEnum.values()) {
            if (cookDegreeEnum.code == code) {
                return cookDegreeEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
