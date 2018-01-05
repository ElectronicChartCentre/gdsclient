package no.ecc.gdsclient.ws.impl;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 */
public class FileDownloadWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _path;
    private byte[] _data;
    private int _percentComplete = 0;
    private String _cacheKey;
    
    public FileDownloadWrapper() {

    }

    public FileDownloadWrapper(String path, byte[] data) {
        _path = path;
        _data = data;
    }

    public void setPath(String p) {
        _path = p;
    }

    public String getPath() {
        return _path;
    }

    public void setData(byte[] d) {
        _data = d;
    }

    public byte[] getData() throws RemoteException {
        return _data;
    }

    public void setPercentComplete(int p) {
        _percentComplete = p;
    }

    public int getPercentComplete() {
        return _percentComplete;
    }

    public void setCacheKey(String k) {
        _cacheKey = k;
    }

    public String getCacheKey() {
        return _cacheKey;
    }

    @Override
    public int hashCode() {
        return getPath().hashCode();
    }

}
