package com.tfr.operation.audit;

import org.junit.jupiter.api.Test;

public class AuditTrailTest {

    @Test
    public void testPrintAudits_ExpectSuccess() {
        AuditTrail auditTrail = new AuditTrail();

        auditTrail.addAudit("step 1", false);
        auditTrail.addAudit("step 2", false);

        auditTrail.printAudits();
    }
}
