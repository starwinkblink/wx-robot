package com.ruigu.fragrant.bot;

import com.ruigu.fragrant.constant.BotConstant;
import com.ruigu.fragrant.message.BotMessage;
import com.ruigu.fragrant.template.MsgFormatType;
import org.slf4j.helpers.MessageFormatter;

import java.util.stream.Stream;

/**
 * @author HuangHuiWang
 * @date 2020/9/8 16:42
 */
public class MarkdownBot extends CommonBot {

    public MarkdownBot(int msgFormat) {
        super(msgFormat);
    }

    public MarkdownBot loadMessage(Object... args) {
        switch (msgFormat) {
            case MsgFormatType.ENUM_TEMPLATE:
                // 将参数全部替换掉模板中的替换位
                loadMessage(MessageFormatter.arrayFormat(BotConstant.MarkDown.COMMON_INFO, args).getMessage());
                return this;
            case MsgFormatType.CUSTOM_TEMPLATE:
                // 使用第一个参数为模板，其余全部参数替换模板中的替换位
                loadMessage(MessageFormatter.arrayFormat(args[0].toString(), Stream.of(args).skip(1L).toArray()).getMessage());
                return this;
            default:
                // 默认走枚举类中默认的提示信息
                loadMessage("默认提示信息Markdown");
                return this;
        }
    }

    private void loadMessage(String message) {
        this.botMessage = BotMessage.markdownBuilder(message).build();
    }

}
