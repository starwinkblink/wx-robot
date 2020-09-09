package com.ruigu.fragrant;

import com.ruigu.fragrant.bot.MarkdownBot;
import com.ruigu.fragrant.bot.TextBot;
import com.ruigu.fragrant.enums.BotTypeEnum;
import com.ruigu.fragrant.factory.BotFactory;
import com.ruigu.fragrant.properties.MainProperties;
import com.ruigu.fragrant.template.MsgFormatType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HuangHuiWang
 * @date 2020/9/8 18:19
 */
@EnableConfigurationProperties(value = MainProperties.class)
@Configuration
@ConditionalOnProperty(
        prefix = "fragrant.bot",
        name = "key"
)
public class MainConfiguration {

    @Bean
    public BotFactory botFactory() {
        return new BotFactory();
    }

    @Bean
    @ConditionalOnBean(value = BotFactory.class)
    public TextBot textBot(BotFactory botFactory) {
        return (TextBot) botFactory.getBot(BotTypeEnum.TEXT.getTypeName(), MsgFormatType.CUSTOM_TEMPLATE);
    }

    @Bean
    @ConditionalOnBean(value = BotFactory.class)
    public MarkdownBot markdownBot(BotFactory botFactory) {
        return (MarkdownBot) botFactory.getBot(BotTypeEnum.MARKDOWN.getTypeName(), MsgFormatType.CUSTOM_TEMPLATE);

    }

}
