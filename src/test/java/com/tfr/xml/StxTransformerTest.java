package com.tfr.xml;

import org.junit.jupiter.api.Test;

import javax.xml.transform.TransformerException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StxTransformerTest {

    private final StxTransformer transformer = new StxTransformer();

    @Test
    void shouldAddANewNode() throws TransformerException {
        final String input = """
                <?xml version="1.0" encoding="UTF-8"?>
                <root><a>a</a></root>
                """;
        final String expectedOutput = """
                <?xml version="1.0" encoding="UTF-8"?>
                <root><a>a</a><b>b</b></root>
                """;
        final String stx = """
                <stx:transform version="1.0" pass-through="all" xmlns:stx="http://stx.sourceforge.net/2002/ns">
                    <stx:template match="root">
                        <stx:copy>
                            <stx:process-attributes/>
                            <stx:process-children/>
                            <b>b</b>
                        </stx:copy>
                    </stx:template>
                </stx:transform>
                """;

        final String output = transformer.transform(stx, input);

        assertEquals(expectedOutput, output);
    }

    @Test
    void shouldRemoveANode() throws TransformerException {
        final String input = """
                <?xml version="1.0" encoding="UTF-8"?>
                <root><a>a</a><b>b</b></root>
                """;
        final String expectedOutput = """
                <?xml version="1.0" encoding="UTF-8"?>
                <root><a>a</a></root>
                """;
        final String stx = """
                <stx:transform version="1.0" pass-through="all" xmlns:stx="http://stx.sourceforge.net/2002/ns">
                    <stx:template match="root/b">
                    </stx:template>
                </stx:transform>
                """;

        final String output = transformer.transform(stx, input);

        assertEquals(expectedOutput, output);
    }

    @Test
    void shouldHandleConditionalIf() throws TransformerException {
        final String input1 = """
                <?xml version="1.0" encoding="UTF-8"?>
                <root><a>a</a></root>
                """;
        final String expectedOutput1 = """
                <?xml version="1.0" encoding="UTF-8"?>
                <root><a>a</a><c>1</c></root>
                """;
        final String input2 = """
                <?xml version="1.0" encoding="UTF-8"?>
                <root><a>b</a></root>
                """;
        final String expectedOutput2 = """
                <?xml version="1.0" encoding="UTF-8"?>
                <root><a>b</a><c>c</c></root>
                """;
        final String stx = """
                <stx:transform version="1.0" pass-through="all" xmlns:stx="http://stx.sourceforge.net/2002/ns">
                    <stx:template match="root/a">
                        <stx:copy>
                            <stx:process-attributes/>
                            <stx:process-children/>
                        </stx:copy>
                        <stx:if test=". = 'b'">
                            <c>c</c>
                        </stx:if>
                        <stx:else>
                            <c>1</c>
                        </stx:else>
                    </stx:template>
                </stx:transform>
                """;

        final String output1 = transformer.transform(stx, input1);
        final String output2 = transformer.transform(stx, input2);

        assertEquals(expectedOutput1, output1);
        assertEquals(expectedOutput2, output2);
    }
}