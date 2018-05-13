package no.ecc.gdsclient.ws.client;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
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

import no.ecc.gdsclient.ws.impl.OrderHeadReport;

public class ReportClient extends CommonClient {

    /**
     * Get orderheaders for the given limits.
     * 
     * @param distributorId
     *            the id of the distributor. null if all allowed distributors
     * @param customerId
     *            the id og the customer. null if all customers
     * @param vesselId
     *            the id of the vessel. null if all vessels
     * @param from
     *            the minimum date of the order. null if no from date
     * @param to
     *            the maximum date of the order. null if no to date
     */
    public OrderHeadReport[] getOrderHeaders(Integer distributorId, Integer customerId, Integer VesselInfoId, Date from,
            Date to) throws RemoteException {

        try {
            Call call = getNewCall();
            call.setOperationName(new QName("ReportService", "getOrderHeaders"));
            QName qn = new QName("http://stubs.ws.gds.ecc.no", "ArrayOf_tns3_OrderHeadReport");
            call.registerTypeMapping(OrderHeadReport[].class, qn, new ArraySerializerFactory(),
                    new ArrayDeserializerFactory());
            call.setReturnType(qn);
            qn = new QName("urn:BeanService", "OrderHeadReport");
            call.registerTypeMapping(OrderHeadReport.class, qn, new BeanSerializerFactory(OrderHeadReport.class, qn),
                    new BeanDeserializerFactory(OrderHeadReport.class, qn));
            call.addParameter("distributorId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("customerId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("VesselInfoId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("from", XMLType.XSD_DATETIME, ParameterMode.IN);
            call.addParameter("to", XMLType.XSD_DATETIME, ParameterMode.IN);
            OrderHeadReport[] ohrs = (OrderHeadReport[]) call
                    .invoke(new Object[] { distributorId, customerId, VesselInfoId, from, to });
            return ohrs;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public byte[] getQuotationReportAsPdf(Integer orderId) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("ReportService", "getQuotationReportAsPdf"));
            call.addParameter("orderId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_BASE64);
            byte[] pdfFile = (byte[]) call.invoke(new Object[] { orderId });
            return pdfFile;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

}
