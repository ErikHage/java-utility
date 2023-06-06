package com.tfr.operation;

import com.tfr.operation.exception.OperationException;
import com.tfr.operation.models.ExampleResult;
import com.tfr.operation.steps.IsValidMessageFormatValidation;
import com.tfr.operation.steps.ParseMessageOperation;

import java.util.Base64;

public class Example {

    public static void main(String ...args) {
        Example example = new Example();

        // valid message
        String message = "OjpCRUdJTiBNRVNTQUdFOjoKc2VuZGVyOkVyaWsKSGVsbG8gV29ybGQhCjo6RU5EIE1FU1NBR0U6Og==";

        // message missing sender
        // String message = "OjpCRUdJTiBNRVNTQUdFOjoKbWlzc2luZyBzZW5kZXIKOjpFTkQgTUVTU0FHRTo6";

        example.runForMessage(message);
    }

    private void runForMessage(String encodedMessage) {
        OperationChain<ExampleResult> resultStep = BasicOperationChain.of(encodedMessage)
//                .transform(new DecodeBase64Operation())
                .transform("lambdaDecodeBase64", this::decodeBase64)
                .validate(new IsValidMessageFormatValidation())
                .transform(new ParseMessageOperation());

        resultStep.getAuditTrail().printAudits();

        ExampleResult result = resultStep.getState();

        System.out.println(result);
    }

    private String decodeBase64(String input) {
        try {
            byte[] decodedValue = Base64.getUrlDecoder().decode(input);
            return new String(decodedValue);
        } catch (Exception ex) {
            throw new OperationException(ex);
        }
    }
}
