package no.ecc.gdsclient.catalogue;

import java.io.IOException;

import org.dom4j.DocumentException;

import no.ecc.gdsclient.junit.GdsClientTestCase;

public class CatalogueParserTest extends GdsClientTestCase {

    public void testCatalogueParser() throws IOException, DocumentException {
        CatalogueParser cp = CatalogueParser.create(getAnonUrlPrefix());
        assertFalse(cp.getCells().isEmpty());
    }

}
