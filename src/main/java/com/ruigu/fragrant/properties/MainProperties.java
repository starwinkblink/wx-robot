package com.ruigu.fragrant.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author HuangHuiWang
 * @date 2020/9/9 9:57
 */
@ConfigurationProperties(prefix = "fragrant.bot")
public class MainProperties {

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
