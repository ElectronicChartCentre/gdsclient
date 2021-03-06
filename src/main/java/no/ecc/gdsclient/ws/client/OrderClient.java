package no.ecc.gdsclient.ws.client;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import no.ecc.gdsclient.ws.impl.OrderProductReport;
import no.ecc.gdsclient.ws.impl.OrderProductRequest;
import no.ecc.gdsclient.ws.impl.OrderReport;
import no.ecc.gdsclient.ws.impl.OrderRequest;
import no.ecc.gdsclient.ws.impl.PermitEndDateRequest;

public class OrderClient extends CommonClient {

    public Integer placeOrder(OrderRequest order) throws RemoteException {

        Call call;
        try {
            call = getNewCall();
            call.setTimeout(Integer.valueOf(1000 * 60 * 10)); // millis
            call.setOperationName(new QName("OrderService", "placeOrder"));
            QName qn = new QName("urn:BeanService", "OrderRequest");
            call.registerTypeMapping(OrderRequest.class, qn, new BeanSerializerFactory(
                    OrderRequest.class, qn), new BeanDeserializerFactory(OrderRequest.class, qn));
            call.addParameter("order", qn, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_INT);
            qn = new QName("urn:BeanService", "OrderProductRequest");
            call.registerTypeMapping(OrderProductRequest.class, qn, new BeanSerializerFactory(
                    OrderProductRequest.class, qn), new BeanDeserializerFactory(
                    OrderProductRequest.class, qn));
            qn = new QName("urn:BeanService", "PermitEndDateRequest");
            call.registerTypeMapping(PermitEndDateRequest.class, qn, new BeanSerializerFactory(
                    PermitEndDateRequest.class, qn), new BeanDeserializerFactory(
                    PermitEndDateRequest.class, qn));
            Integer orderNumber = (Integer) call.invoke(new Object[] { order });
            return orderNumber;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * Transform an quotation into a real order.
     * 
     * @param orderId
     * @param sendPermitsByMail
     * @throws RemoteException
     */
    public Boolean activateOrder(Integer orderId, boolean sendPermitsByMail) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setTimeout(Integer.valueOf(1000 * 60 * 10)); // millis
            call.setOperationName(new QName("OrderService", "activateOrder"));
            call.addParameter("orderId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("sendPermitsByMail", XMLType.XSD_BOOLEAN, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_BOOLEAN);
            return (Boolean) call.invoke(new Object[] { Integer.valueOf(orderId), Boolean.valueOf(sendPermitsByMail) });
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * Cancel a order
     */
    public void cancelOrder(Integer orderId) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("OrderService", "cancelOrder"));
            call.addParameter("orderId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_BOOLEAN);
            call.invoke(new Object[] { Integer.valueOf(orderId) });
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }
    
    public OrderReport getOrderReport(int orderId) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setTimeout(Integer.valueOf(1000 * 60 * 3)); // millis
            call.setOperationName(new QName("OrderService", "getOrderReport"));
            QName qn = new QName("urn:BeanService", "OrderReport");
            call.registerTypeMapping(
                OrderReport.class,
                qn,
                new BeanSerializerFactory(OrderReport.class, qn),
                new BeanDeserializerFactory(OrderReport.class, qn));
            call.addParameter("orderId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.setReturnType(qn);
            qn = new QName("urn:BeanService", "OrderProductReport");
            call.registerTypeMapping(
                OrderProductReport.class,
                qn,
                new BeanSerializerFactory(OrderProductReport.class, qn),
                new BeanDeserializerFactory(OrderProductReport.class, qn));
            OrderReport orderReport =
                (OrderReport) call.invoke(new Object[] { Integer.valueOf(orderId)});
            return orderReport;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        } catch (RemoteException e) {
            throw new RemoteException(e.getMessage());
        }

    }


}
