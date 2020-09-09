package com.ruigu.fragrant.bot;

import com.ruigu.fragrant.template.MsgFormatType;

/**
 * 标识其是一个消息机器人
 *
 * @author HuangHuiWang
 * @date 2020/9/9 16:07
 */
public interface Bot extends MsgFormatType {

    /**
     * 机器人发送消息
     */
    void sendMessage();
}
