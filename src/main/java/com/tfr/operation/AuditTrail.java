package com.tfr.operation;

import java.util.HashMap;
import java.util.Map;

public class AuditTrail {

    private final Map<String,String> audits;

    public AuditTrail() {
        this.audits = new HashMap<>();
    }

    public void addAudit(String key, String value) {
        audits.put(key, value);
    }

    public Map<String,String> getAudits() {
        return new HashMap<>(audits);
    }
}
