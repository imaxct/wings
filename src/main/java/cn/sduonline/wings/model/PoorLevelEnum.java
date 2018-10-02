package cn.sduonline.wings.model;

/**
 * Created by imaxct on 18-9-29.
 */
public enum PoorLevelEnum {

    /**
     * 非困难
     */
    NOT_POOR("非困难"),

    /**
     * 困难
     */
    NORMAL("困难"),

    /**
     * 特殊困难
     */
    SPECIAL("特殊困难");

    private String desc;

    PoorLevelEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
