package com.tfr.operation.audit;

import java.util.LinkedList;
import java.util.List;

public class AuditTrail {

    private final List<StepResult> audits;

    public AuditTrail() {
        this.audits = new LinkedList<>();
    }

    public void addAudit(String stepName, boolean isSkipped) {
        audits.add(new StepResult(stepName, isSkipped, null));
    }

    public void addAudit(String stepName, boolean isSkipped, Throwable error) {
        audits.add(new StepResult(stepName, isSkipped, error));
    }

    public List<StepResult> getAudits() {
        return audits;
    }
}
