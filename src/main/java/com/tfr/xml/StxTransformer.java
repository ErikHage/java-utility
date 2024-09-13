package com.tfr.xml;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

public class StxTransformer {

    private final TransformerFactory factory;

    public StxTransformer() {
        factory = TransformerFactory.newInstance("net.sf.joost.trax.TransformerFactoryImpl", null);
    }

    public String transform(final String stx, final String xmlInput) throws TransformerException {
        Source stxSource = new StreamSource(new StringReader(stx));
        Transformer transformer = factory.newTransformer(stxSource);

        Source xmlSource = new StreamSource(new StringReader(xmlInput));
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);

        transformer.transform(xmlSource, result);

        return writer.toString();
    }

}
