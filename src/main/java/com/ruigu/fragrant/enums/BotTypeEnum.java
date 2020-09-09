package com.ruigu.fragrant.enums;

/**
 * @author HuangHuiWang
 * @date 2020/9/8 16:33
 */
public enum BotTypeEnum {

    /**
     * 文本类型
     */
    TEXT("text"),

    MARKDOWN("markdown"),

    IMAGE("image"),

    FILE("file");

    private String typeName;

    public String getTypeName() {
        return this.typeName;
    }

    BotTypeEnum(String typeName) {
        this.typeName = typeName;
    }

}
