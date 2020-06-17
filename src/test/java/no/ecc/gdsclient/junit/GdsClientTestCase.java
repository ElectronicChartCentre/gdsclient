package no.ecc.gdsclient.junit;

import java.util.logging.Logger;

import junit.framework.TestCase;
import no.ecc.gdsclient.utility.UrlPrefix;

public abstract class GdsClientTestCase extends TestCase {

    private Logger log;

    protected String getUrlPrefix() {
        String urlPrefix = System.getProperty(UrlPrefix.URL_PREFIX_KEY);
        if (urlPrefix == null) {
            urlPrefix = System.getenv(UrlPrefix.URL_PREFIX_KEY);
        }
        if (urlPrefix == null) {
            fail("missing property " + UrlPrefix.URL_PREFIX_KEY
                    + " set to something like https://username:password@qaprimar.ecc.no/qaprimar");
        }
        return urlPrefix;
    }

    protected String getAnonUrlPrefix() {
        String urlPrefix = System.getProperty(UrlPrefix.URL_PREFIX_KEY);
        if (urlPrefix == null) {
            urlPrefix = System.getenv(UrlPrefix.URL_PREFIX_KEY);
        }
        if (urlPrefix == null) {
            String defaultAnonUrlPrefix = "https://qaprimar.ecc.no/qaprimar";
            System.out.println("missing property " + UrlPrefix.URL_PREFIX_KEY + ". fallback to " + defaultAnonUrlPrefix
                    + " for anonymous use");
            return defaultAnonUrlPrefix;
        }
        return urlPrefix;
    }

    protected Logger getLogger() {
        if (log == null) {
            log = Logger.getLogger(getClass().getName());
        }
        return log;
    }

}
