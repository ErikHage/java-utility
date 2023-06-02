package com.tfr.operation.audit;

public record StepResult(String stepName, boolean isSkipped, Throwable error) {
}
