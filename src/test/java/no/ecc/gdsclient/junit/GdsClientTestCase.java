package no.ecc.gdsclient.junit;

import junit.framework.TestCase;

public abstract class GdsClientTestCase extends TestCase {

    private static final String URL_PREFIX_KEY = "URL_PREFIX";

    protected String getUrlPrefix() {
        String urlPrefix = System.getProperty(URL_PREFIX_KEY);
        if (urlPrefix == null) {
            urlPrefix = System.getenv(URL_PREFIX_KEY);
        }
        if (urlPrefix == null) {
            fail("missing property " + URL_PREFIX_KEY
                    + " set to something like https://username:password@qaprimar.ecc.no/qaprimar");
        }
        return urlPrefix;
    }

}
