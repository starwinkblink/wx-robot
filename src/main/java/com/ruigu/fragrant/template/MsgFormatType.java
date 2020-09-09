package com.ruigu.fragrant.template;

/**
 * @author huanghuiwang
 * @date 2020/7/24
 */
public interface MsgFormatType {

    /**
     * 消息格式化的类型       ps: 可新增
     * STATIC_MESSAGE       1。 静态消息
     * ENUM_TEMPLATE        2. 枚举类里已经定义了响应模板，所有的可变参数都匹配到模板中的占位符
     * CUSTOM_TEMPLATE      3. 不使用枚举类的模板，手动自定义模板内容，可变参数首位为自定义模板内容，除首位所有的可变参数都匹配到模板中的占位符
     */
    int STATIC_MESSAGE = 1;
    int ENUM_TEMPLATE = 2;
    int CUSTOM_TEMPLATE = 3;

}