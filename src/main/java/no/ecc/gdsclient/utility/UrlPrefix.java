package no.ecc.gdsclient.utility;

import java.io.Serializable;

/**
 * A class representing the url prefix of a java web application.
 */
public final class UrlPrefix implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final String URL_PREFIX_KEY = "URL_PREFIX";

    private final String schema;
    private final String name;
    private final int serverPort;
    private final String context;
    private final String userInfo;

    private transient String urlPrefix;

    public UrlPrefix(String url) {
        int schemaEndIndex = url.indexOf("://");
        this.schema = url.substring(0, schemaEndIndex);
        int lastAlphaIndex = url.lastIndexOf('@');
        int pathStart = 0;
        String hostName = null;
        if (lastAlphaIndex >= 0) {
            // has user info
            pathStart = url.indexOf('/', lastAlphaIndex);
            this.userInfo = url.substring(schemaEndIndex + 3, lastAlphaIndex);
            hostName = url.substring(lastAlphaIndex + 1, pathStart);
        } else {
            // no user info
            pathStart = url.indexOf('/', schemaEndIndex + 3);
            hostName = url.substring(schemaEndIndex + 3, pathStart);
            this.userInfo = null;
        }
        int nameColonPos = hostName.indexOf(':');
        if (nameColonPos > 0) {
            // has port
            serverPort = Integer.parseInt(hostName.substring(nameColonPos + 1));
            hostName = hostName.substring(0, nameColonPos);
        } else {
            serverPort = "http".equals(schema) ? 80 : 443;
        }
        this.name = hostName;
        this.context = url.substring(pathStart);
    }

    private boolean isCustomPort() {
        // HttpServletRequest.getScheme apparently might return null if using
        // jboss,
        // so check this and return false if null
        if (schema == null) {
            return false;
        }
        return (schema.equals("http") && (serverPort != 80)) || (schema.equals("https") && (serverPort != 443));
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

    public String getUserInfo() {
        return userInfo;
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

    @Override
    public String toString() {
        return getUrlPrefix();
    }

}
