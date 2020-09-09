package com.ruigu.fragrant.bot;

import com.ruigu.fragrant.constant.BotConstant;
import com.ruigu.fragrant.message.BotMessage;
import com.ruigu.fragrant.template.MsgFormatType;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author HuangHuiWang
 * @date 2020/9/8 16:42
 */
public class TextBot extends CommonBot {

    public TextBot(int msgFormat) {
        super(msgFormat);
    }

    /**
     * 装载信息对象
     *
     * @param mentionedList
     * @param mentionedMobileList
     * @param args                可变参数
     * @return {@link TextBot}
     */
    public TextBot loadMessage(List<String> mentionedList, List<String> mentionedMobileList, Object... args) {
        switch (msgFormat) {
            case MsgFormatType.ENUM_TEMPLATE:
                // 将参数全部替换掉模板中的替换位
                loadMessage(MessageFormatter.arrayFormat(BotConstant.Text.TEMPLATE_TEXT, args).getMessage(), mentionedList, mentionedMobileList);
                return this;
            case MsgFormatType.CUSTOM_TEMPLATE:
                // 使用第一个参数为模板，其余全部参数替换模板中的替换位
                loadMessage(MessageFormatter.arrayFormat(args[0].toString(), Stream.of(args).skip(1L).toArray()).getMessage(), mentionedList, mentionedMobileList);
                return this;
            default:
                // 默认走枚举类中默认的提示信息
                loadMessage("默认信息Text", mentionedList, mentionedMobileList);
                return this;
        }
    }

    private void loadMessage(String message, List<String> mentionedList, List<String> mentionedMobileList) {
        BotMessage.TextBuilder textBuilder = BotMessage.textBuilder(message);
        if (CollectionUtils.isEmpty(mentionedList) && CollectionUtils.isEmpty(mentionedMobileList)) {
            textBuilder.atAll();
        } else {
            textBuilder.addMobileForAt(mentionedMobileList.toArray(new String[mentionedList.size()]))
                    .addUserIdForAt(mentionedList.toArray(new String[mentionedList.size()]));
        }
        this.botMessage = textBuilder.build();
    }

}
