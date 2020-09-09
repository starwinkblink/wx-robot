package com.ruigu.fragrant.bot;

import com.ruigu.fragrant.properties.MainProperties;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

/**
 * bot需要的基本属性注入
 *
 * @author HuangHuiWang
 * @date 2020/9/9 11:27
 */
public class BaseBot implements ApplicationContextAware, EnvironmentAware {

    protected static final String URL_PREFIX = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=";
    protected static final String LOCAL_ACTIVE_PROFILE = "local";

    protected ApplicationContext applicationContext;

    protected MainProperties mainProperties;

    protected List<String> profile;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext == null) {
            throw new RuntimeException("application can't be null");
        }
        this.applicationContext = applicationContext;
        mainProperties = applicationContext.getBean(MainProperties.class);
        if (mainProperties == null) {
            throw new RuntimeException("mainProperties can't be null");
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        if (environment == null) {
            throw new RuntimeException("environment can't be null");
        }
        this.profile = Arrays.asList(environment.getActiveProfiles());
    }
}
