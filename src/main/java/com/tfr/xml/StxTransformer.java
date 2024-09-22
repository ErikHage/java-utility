package com.tfr.xml;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Contains methods to transform XML files using the Streaming Transformations for XML (STX) protocol
 */
public class StxTransformer {

    private final TransformerFactory factory;

    public StxTransformer() {
        factory = TransformerFactory.newInstance("net.sf.joost.trax.TransformerFactoryImpl", null);
    }

    /**
     * Transform a given xml string using a given STX file
     * @param stx - stx transformation
     * @param xmlInput - input xml for transformation
     * @return String - transformed xml
     * @throws TransformerException if transformer not on classpath
     */
    public String transform(final String stx, final String xmlInput) throws TransformerException {
        Source stxSource = new StreamSource(new StringReader(stx));
        Transformer transformer = factory.newTransformer(stxSource);

        Source xmlSource = new StreamSource(new StringReader(xmlInput));
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);

        transformer.transform(xmlSource, result);

        return writer.toString();
    }

    public static void main(String[] args) throws TransformerException {
        final StxTransformer stxTransformer = new StxTransformer();

        final String input = """
                <orders type="collection">
                    <size class="int">1</size>
                    <element class="com.tfr.UserInfo">
                        <name>Erik</name>
                        <address>
                            <street>100 West Street</street>
                            <zip>12345</zip>
                        </address>
                    </element>
                </orders>
                """;

        final String stx = """
                <stx:transform version="1.0" pass-through="none" xmlns:stx="http://stx.sourceforge.net/2002/ns">
                    <stx:template match="*[@class='com.tfr.UserInfo']/address">
                        <stx:copy>some whole new content</stx:copy>
                    </stx:template>
                </stx:transform>
                """;


        System.out.println(stxTransformer.transform(stx, input));
    }
}
