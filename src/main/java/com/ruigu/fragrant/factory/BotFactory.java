package com.ruigu.fragrant.factory;


import com.ruigu.fragrant.bot.CommonBot;
import com.ruigu.fragrant.bot.MarkdownBot;
import com.ruigu.fragrant.bot.TextBot;
import com.ruigu.fragrant.enums.BotTypeEnum;

/**
 * @author HuangHuiWang
 * @date 2020/9/8 16:25
 */

public class BotFactory {

    public CommonBot getBot(String botType, int msgFormat) {
        if (BotTypeEnum.TEXT.getTypeName().equals(botType)) {
            return new TextBot(msgFormat);
        } else if (BotTypeEnum.MARKDOWN.getTypeName().equals(botType)) {
            return new MarkdownBot(msgFormat);
        } else {
            throw new RuntimeException("create Bot failed !");
        }
    }
}
