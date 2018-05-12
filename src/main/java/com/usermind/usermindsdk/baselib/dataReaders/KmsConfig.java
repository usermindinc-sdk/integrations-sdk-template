package com.usermind.usermindsdk.baselib.datareaders;

import static com.google.common.base.Preconditions.checkNotNull;

public class KmsConfig {

    private String ec2Role = "";
    private String endpoint = "";
    private Integer socketTimeout = 0;
    private String defaultKeyId = "";

    public String getEc2Role() {
        return ec2Role;
    }

    public void setEc2Role(String ec2Role) {
        this.ec2Role = checkNotNull(ec2Role);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = checkNotNull(endpoint);
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = checkNotNull(socketTimeout);
    }

    public String getDefaultKeyId() {
        return defaultKeyId;
    }

    public void setDefaultKeyId(String defaultKeyId) {
        this.defaultKeyId = checkNotNull(defaultKeyId);
    }
}
