package com.ruigu.fragrant.bot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruigu.fragrant.message.BotMessage;
import com.ruigu.fragrant.template.MsgFormatType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

/**
 * Bot的共有功能
 *
 * @author HuangHuiWang
 * @date 2020/9/8 16:30
 */

public class CommonBot extends BaseBot implements Bot {

    private static final Logger log = LoggerFactory.getLogger(CommonBot.class);

    /**
     * 信息归属的模板类型
     */
    protected int msgFormat;

    /**
     * 需要发送的信息载体
     */
    protected BotMessage botMessage;

    public CommonBot() {
    }

    public CommonBot(int msgFormat) {
        this.msgFormat = msgFormat;
    }

    /**
     * 发送信息,主要异常信息的打印等
     */
    @Override
    public void sendMessage() {
        try {
            // 如果本地启动无须发送企业微信机器人消息
            if (!CollectionUtils.isEmpty(profile) && profile.contains(LOCAL_ACTIVE_PROFILE)) {
                return;
            }
            log.info("prepare send work-wechat message,{}", botMessage);
            Map<String, Object> responseMap = send();
            log.info("send work-wechat message!");
            // 消息消息发送成功
            if ("0".equals(responseMap.get("errcode").toString())) {
                log.info("send work-wechat message success!");
            } else {
                log.error("error! transfer hedwig has errorMessage: {}", responseMap.get("errmsg"));
            }
        } catch (Exception e) {
            log.error("BotClient：transfer BotMsg has error! errorMessage: " + e.getMessage(), e);
        } finally {
            this.botMessage = null;
        }
    }

    /**
     * 发送信息的工作
     *
     * @return
     * @throws IOException
     */
    private Map<String, Object> send() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        // 封装url和消息
        ObjectMapper mapper = new ObjectMapper();
        Map params = mapper.convertValue(this.botMessage, Map.class);
        String url = URL_PREFIX + mainProperties.getKey();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, params, String.class);
        return mapper.readValue(responseEntity.getBody(), Map.class);

    }
}
