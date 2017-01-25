package com.wondersgroup.tpa.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.wondersgroup.tpa.conf
 * @Description:
 * @Author: xifeng chenxifeng@wondersgroup.com
 * @Date: 2017-01-25
 * @Time: 13:55
 */
@ConfigurationProperties(prefix = "tpa")
public class TpaProperties {
    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    private String defaultPassword;

}
