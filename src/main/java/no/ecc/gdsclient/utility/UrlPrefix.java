package no.ecc.gdsclient.utility;

import java.io.Serializable;
import java.net.URL;

/**
 * A class representing the url prefix of a java web application.
 */
public final class UrlPrefix implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String schema;
    private final String name;
    private final int serverPort;
    private final int localPort;
    private final String context;

    private transient String urlPrefix;

    public UrlPrefix(String schema, String name, int serverPort, int localPort, String context) {
        this.schema = schema;
        this.name = name;
        this.serverPort = serverPort;
        this.localPort = localPort;

        if (!context.startsWith("/")) {
            context = "/" + context;
        }

        this.context = context;
    }

    public UrlPrefix(URL url) {
        this.schema = url.getProtocol();
        this.name = url.getHost();
        if (url.getPort() > 0) {
            this.serverPort = url.getPort();
        } else {
            this.serverPort = url.getDefaultPort();
        }
        this.localPort = -1;
        String path = url.getPath();
        String[] pathElems = path.split("/");
        if (pathElems.length > 1) {
            this.context = '/' + pathElems[1];
        } else {
            this.context = path;
        }
    }

    private boolean isCustomPort() {
        // HttpServletRequest.getScheme apparently might return null if using
        // jboss,
        // so check this and return false if null
        if (schema == null) {
            return false;
        }
        return (schema.equals("http") && (serverPort != 80))
                || (schema.equals("https") && (serverPort != 443));
    }

    public int getLocalPort() {
        return localPort;
    }
    
    public String getHostName() {
        return name;
    }

    /**
     * Return the http/https url prefix used in the given request. The returned
     * value will be like "https://servername:portnumber/context
     * 
     * @return a String representation of the url prefix
     */
    public String getUrlPrefix() {
        if (urlPrefix == null) {
            StringBuilder urlPrefixBuilder = new StringBuilder();
            urlPrefixBuilder.append(schema);
            urlPrefixBuilder.append("://");
            urlPrefixBuilder.append(name);
            // handle non standard ports
            if (isCustomPort()) {
                urlPrefixBuilder.append(":");
                urlPrefixBuilder.append(serverPort);
            }
            urlPrefixBuilder.append(context);
            urlPrefix = urlPrefixBuilder.toString();
        }
        return urlPrefix;
    }
    
    public String getScheme() {
        return schema;
    }
    
    public int getServerPort() {
        return serverPort;
    }

    /**
     * Return the context like "/utvikling"
     * 
     * @return
     */
    public String getContext() {
        return context;
    }

    public UrlPrefix toSchemeServerPort(String requestedScheme, Integer requestedServerPort) {
        if (requestedScheme == null || requestedServerPort == null) {
            return this;
        }
        if (requestedScheme.equals(schema) && requestedServerPort.intValue() == serverPort) {
            return this;
        }
        return new UrlPrefix(requestedScheme, name, requestedServerPort.intValue(), localPort, context);
    }

    @Override
    public String toString() {
        return getUrlPrefix();
    }

}
