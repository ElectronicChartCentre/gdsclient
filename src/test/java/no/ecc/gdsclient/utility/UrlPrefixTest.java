package no.ecc.gdsclient.utility;

import junit.framework.TestCase;
import no.ecc.gdsclient.utility.UrlPrefix;

public class UrlPrefixTest extends TestCase {

    public void testUrlPrefixStrangePassword() {
        String url = "http://a-user-name:aPassword@)!(@stuff.ecc.no/stuff";
        UrlPrefix up = new UrlPrefix(url);
        assertEquals("a-user-name:aPassword@)!(", up.getUserInfo());
        assertEquals("stuff.ecc.no", up.getHostName());
        assertEquals("http", up.getScheme());
        assertEquals(80, up.getServerPort());
        assertEquals("http://stuff.ecc.no/stuff", up.getUrlPrefix());
    }

    public void testUrlPrefixPort() {
        String url = "http://a-user-name:aPassword@)!(@stuff.ecc.no:8080/stuff";
        UrlPrefix up = new UrlPrefix(url);
        assertEquals("a-user-name:aPassword@)!(", up.getUserInfo());
        assertEquals("stuff.ecc.no", up.getHostName());
        assertEquals("http", up.getScheme());
        assertEquals(8080, up.getServerPort());
        assertEquals("http://stuff.ecc.no:8080/stuff", up.getUrlPrefix());
    }

    public void testUrlPrefixAnon() {
        String url = "http://stuff.ecc.no/stuff";
        UrlPrefix up = new UrlPrefix(url);
        assertNull(up.getUserInfo());
        assertEquals("stuff.ecc.no", up.getHostName());
        assertEquals("http", up.getScheme());
        assertEquals(80, up.getServerPort());
        assertEquals("http://stuff.ecc.no/stuff", up.getUrlPrefix());
    }

}
