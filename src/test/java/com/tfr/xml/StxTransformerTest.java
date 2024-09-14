package com.tfr.xml;

import org.junit.jupiter.api.Test;

import javax.xml.transform.TransformerException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StxTransformerTest {

    private final StxTransformer transformer = new StxTransformer();

    @Test
    void shouldAddANewNode() throws TransformerException {
        final String input =
"""
<?xml version="1.0" encoding="UTF-8"?>
<root><a>a</a></root>
""";
        final String expectedOutput =
"""
<?xml version="1.0" encoding="UTF-8"?>
<root><a>a</a><b>b</b></root>
""";

        final String stx = """
                <stx:transform version="1.0" xmlns:stx="http://stx.sourceforge.net/2002/ns">
                    <stx:template match="root/a">
                        <stx:copy>
                            <stx:process-attributes/>
                            <stx:process-children/>
                        </stx:copy>
                        <b>b</b>
                    </stx:template>
                </stx:transform>
                """;
        final String output = transformer.transform(stx, input);

        assertEquals(expectedOutput, output);
    }
}