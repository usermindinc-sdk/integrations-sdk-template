package com.usermind.usermindsdk.baselib.metrics;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class MetricsConfiguration {
    private String clientType = "noOp";
    private List<String> tags = new ArrayList<>();
    private String prefix = "";
    private String host = "";
    private Integer port = 8125;
//    CLIENT_TYPE - "dataDogNonBlocking"
//    tags - "stack"
//    prefix - ""
//    host - "docker-prod.west.usermind.com"
//    port - 8125


    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        if (!StringUtils.isEmpty(clientType)) {
            this.clientType = clientType;
        }
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
