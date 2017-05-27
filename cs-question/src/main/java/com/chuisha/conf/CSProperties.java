package com.chuisha.conf;

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
@ConfigurationProperties(prefix = "cs")
public class CSProperties {
    private String fileSaveDir;

    public String getFileSaveDir() {
        return fileSaveDir;
    }

    public void setFileSaveDir(String fileSaveDir) {
        this.fileSaveDir = fileSaveDir;
    }
}
