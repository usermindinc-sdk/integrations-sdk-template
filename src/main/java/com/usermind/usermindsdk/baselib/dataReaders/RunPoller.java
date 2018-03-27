package com.usermind.usermindsdk.baselib.dataReaders;

import org.springframework.stereotype.Component;

import java.time.ZoneOffset;

@Component
public class RunPoller {
    private String runId = "1020926111074";
    private String connectionId = "907c3155-0036-4d5e-a0f1-d0fab6d61d95";
    private String orgId = "100";
    private String legacyId = "101";

    private String apiKey = "nM_bPyV4sfbVBz8Po28g";
    private String accountName = "ragi-test";
    private long waveTimestamp = 1;

//    public static long getWaveTimestamp(IntegrationApiConnector connector) {
//        Configuration run = connector.getCachedRun();
//        if (run.hasPath("output.waveTimestamp")) {
//            return run.getLong("output.waveTimestamp");
//        }
//        return LocalDateTime.parse(run.getString("created_at"))
//                .toInstant(ZoneOffset.UTC).toEpochMilli();
//    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getLegacyId() {
        return legacyId;
    }

    public void setLegacyId(String legacyId) {
        this.legacyId = legacyId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public long getWaveTimestamp() {
        return waveTimestamp;
    }

    public void setWaveTimestamp(long waveTimestamp) {
        this.waveTimestamp = waveTimestamp;
    }
}
