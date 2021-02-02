package no.ecc.gdsclient.ws.client;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.encoding.ser.ArrayDeserializerFactory;
import org.apache.axis.encoding.ser.ArraySerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import no.ecc.gdsclient.ws.impl.ProductPermit;

public class ProductClient extends CommonClient {

    public ProductPermit[] getProductPermits(String userId) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setTimeout(Integer.valueOf(1000 * 60 * 3)); // millis
            call.setOperationName(new QName("ProductService", "getProductPermits"));
            QName qn = new QName("http://stubs.ws.gds.ecc.no", "ArrayOf_tns3_ProductPermit");
            call.registerTypeMapping(ProductPermit[].class, qn, new ArraySerializerFactory(),
                    new ArrayDeserializerFactory());
            call.setReturnType(qn);
            qn = new QName("urn:BeanService", "ProductPermit");
            call.registerTypeMapping(ProductPermit.class, qn, new BeanSerializerFactory(
                    ProductPermit.class, qn), new BeanDeserializerFactory(ProductPermit.class, qn));
            call.addParameter("userId", XMLType.XSD_STRING, ParameterMode.IN);
            ProductPermit[] pp = (ProductPermit[]) call.invoke(new Object[] { userId });
            return pp;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }
    
    public String createPermitFile(Integer vesselId, String userPermit, boolean usePreviousEdition) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("ProductService", "createPermitFile"));
            call.setReturnType(XMLType.XSD_STRING);
            call.addParameter("vesselId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("userPermit", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("usePreviousEdition", XMLType.XSD_BOOLEAN, ParameterMode.IN);
            String permitFile = (String) call.invoke(new Object[] { vesselId, userPermit, usePreviousEdition });
            return permitFile;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }
    
    public String createPermitFile(Integer vesselId, String userPermit, int year, int week) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("ProductService", "createPermitFile"));
            call.setReturnType(XMLType.XSD_STRING);
            call.addParameter("vesselId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("userPermit", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("year", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("week", XMLType.XSD_INTEGER, ParameterMode.IN);
            String permitFile = (String) call.invoke(new Object[] { vesselId, userPermit, year, week });
            return permitFile;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

}
