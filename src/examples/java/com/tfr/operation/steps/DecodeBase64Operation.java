package com.tfr.operation.steps;

import com.tfr.operation.exception.OperationException;
import com.tfr.operation.operation.Operation;

import java.util.Base64;

public class DecodeBase64Operation implements Operation<String, String> {
    @Override
    public String getName() {
        return "Decode Base64 Operation";
    }

    @Override
    public String execute(String input) throws OperationException {
        try {
            byte[] decodedValue = Base64.getUrlDecoder().decode(input);
            return new String(decodedValue);
        } catch (Exception ex) {
            throw new OperationException(ex);
        }
    }
}
