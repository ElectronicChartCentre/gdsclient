package no.ecc.gdsclient.ws.client;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.encoding.ser.ArrayDeserializerFactory;
import org.apache.axis.encoding.ser.ArraySerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import no.ecc.gdsclient.ws.impl.CellInfo;
import no.ecc.gdsclient.ws.impl.CellKey;
import no.ecc.gdsclient.ws.impl.FileDownloadWrapper;

public class CellDownloadClient extends CommonClient {

    public CellKey[] getCellKeys(Date exportDate) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setTimeout(Integer.valueOf(1000 * 60 * 3)); // millis
            call.setOperationName(new QName("CellDownloadService", "getCellKeys"));
            QName qn = new QName("http://stubs.ws.gds.ecc.no", "ArrayOf_tns1_CellKey");
            call.registerTypeMapping(CellKey[].class, qn, new ArraySerializerFactory(),
                    new ArrayDeserializerFactory());
            call.setReturnType(qn);
            qn = new QName("urn:BeanService", "CellKey");
            call.registerTypeMapping(CellKey.class, qn,
                    new BeanSerializerFactory(CellKey.class, qn), new BeanDeserializerFactory(
                            CellKey.class, qn));
            call.addParameter("exportDate", XMLType.XSD_DATETIME, ParameterMode.IN);
            CellKey[] cellKeys = (CellKey[]) call.invoke(new Object[] { exportDate });
            return cellKeys;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public boolean download(boolean encrypted, Date fromDate, Date exportDate,
            Collection<String> cellIds, Collection<CellInfo> status) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setTimeout(Integer.valueOf(1000 * 60 * 30)); // millis
            call.setOperationName(new QName("CellDownloadService", "download"));
            QName cellInfoArrayQn = new QName("http://stubs.ws.gds.ecc.no", "ArrayOf_tns1_CellInfo");
            call.registerTypeMapping(CellInfo[].class, cellInfoArrayQn,
                    new ArraySerializerFactory(), new ArrayDeserializerFactory());
            QName cellInfoQn = new QName("urn:BeanService", "CellInfo");
            call.registerTypeMapping(CellInfo.class, cellInfoQn, new BeanSerializerFactory(
                    CellInfo.class, cellInfoQn), new BeanDeserializerFactory(CellInfo.class,
                    cellInfoQn));

            call.addParameter("encrypted", XMLType.XSD_BOOLEAN, ParameterMode.IN);
            call.addParameter("fromDate", XMLType.XSD_DATETIME, ParameterMode.IN);
            call.addParameter("exportDate", XMLType.XSD_DATETIME, ParameterMode.IN);
            call.addParameter("cellIds", XMLType.XSD_ANY, ParameterMode.IN);
            call.addParameter("status", cellInfoArrayQn, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_BOOLEAN);

            Boolean r = (Boolean) call.invoke(new Object[] { Boolean.valueOf(encrypted), fromDate,
                    exportDate, cellIds.toArray(new String[cellIds.size()]),
                    status.toArray(new CellInfo[status.size()]) });
            return r.booleanValue();
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public FileDownloadWrapper get(int index) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            // using a relative short timeout. use retry instead.
            call.setTimeout(Integer.valueOf(1000 * 60)); // millis
            call.setOperationName(new QName("CellDownloadService", "get"));
            QName qn = new QName("urn:BeanService", "FileDownloadWrapper");
            call.registerTypeMapping(FileDownloadWrapper.class, qn, new BeanSerializerFactory(
                    FileDownloadWrapper.class, qn), new BeanDeserializerFactory(
                    FileDownloadWrapper.class, qn));
            call.addParameter("index", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.setReturnType(qn);
            return (FileDownloadWrapper) call.invoke(new Object[] { Integer.valueOf(index) });
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public boolean finish() throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("CellDownloadService", "finish"));
            call.setReturnType(XMLType.XSD_BOOLEAN);
            Boolean r = (Boolean) call.invoke(new Object[] {});
            return r.booleanValue();
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public FileDownloadWrapper getSignature(String cellId, int edtn, int updn, int reissue,
            String certificateFileName) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            // using a relative short timeout. use retry instead.
            call.setTimeout(Integer.valueOf(1000 * 60)); // millis
            call.setOperationName(new QName("CellDownloadService", "getSignature"));
            QName qn = new QName("urn:BeanService", "FileDownloadWrapper");
            call.registerTypeMapping(FileDownloadWrapper.class, qn, new BeanSerializerFactory(
                    FileDownloadWrapper.class, qn), new BeanDeserializerFactory(
                    FileDownloadWrapper.class, qn));
            call.addParameter("cellId", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("edtn", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("updn", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("reissue", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("certificateFileName", XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(qn);
            return (FileDownloadWrapper) call.invoke(new Object[] { cellId, edtn, updn, reissue, certificateFileName });
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        }
    }

}
