package no.ecc.gdsclient.ws.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;

import no.ecc.gdsclient.utility.UrlPrefix;

/**
 * A common abstract superclass of all web services clients supposed to speak to
 * a other GDS instance. It is not supposed to work with non-GDS web services.
 */
abstract class CommonClient {
    
    private Logger log;
    private Service _service;
    private UrlPrefix urlPrefix;

    private String username;
    private String password;

    protected Logger getLogger() {
        if (log == null) {
            log = Logger.getLogger(getClass());
        }
        return log;
    }

    protected Call getNewCall() throws ServiceException, MalformedURLException {
        if (_service == null) {
            _service = new Service();
            _service.setMaintainSession(true);
        }
        Call call = (Call) _service.createCall();
        call.setMaintainSession(true);
        call.setTargetEndpointAddress(getUrlPrefix().getUrlPrefix() + "/services_basic");

        if ((username != null) && (password != null)) {
            call.setUsername(username);
            call.setPassword(password);
        }

        return call;
    }

    public UrlPrefix getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(UrlPrefix urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public void setUrlPrefix(String url) throws MalformedURLException {
        URL u = new URL(url);
        setUrlPrefix(new UrlPrefix(u));
        String userInfo = u.getUserInfo();
        if (userInfo != null) {
            StringTokenizer st = new StringTokenizer(userInfo, ":");
            username = st.nextToken();
            if (st.hasMoreElements()) {
                password = st.nextToken();
            }
        }
    }

}
