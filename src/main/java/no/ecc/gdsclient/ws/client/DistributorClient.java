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

import no.ecc.gdsclient.ws.impl.Country;
import no.ecc.gdsclient.ws.impl.Customer;
import no.ecc.gdsclient.ws.impl.CustomerInfo;
import no.ecc.gdsclient.ws.impl.CustomerType;
import no.ecc.gdsclient.ws.impl.DiscountGroup;
import no.ecc.gdsclient.ws.impl.Distributor;
import no.ecc.gdsclient.ws.impl.DistributorInfo;
import no.ecc.gdsclient.ws.impl.UserPermit;
import no.ecc.gdsclient.ws.impl.Vessel;
import no.ecc.gdsclient.ws.impl.VesselCategory;
import no.ecc.gdsclient.ws.impl.VesselInfo;
import no.ecc.gdsclient.ws.impl.VesselTonnage;

public class DistributorClient extends CommonClient {

    public Distributor[] getDistributors() throws RemoteException {
        try {
            Call call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getDistributors"));
            QName qn = new QName("http://stubs.ws.gds.ecc.no", "ArrayOf_tns3_Distributor");
            call.registerTypeMapping(Distributor[].class, qn, new ArraySerializerFactory(),
                    new ArrayDeserializerFactory());
            call.setReturnType(qn);
            qn = new QName("urn:BeanService", "Distributor");
            call.registerTypeMapping(Distributor.class, qn, new BeanSerializerFactory(
                    Distributor.class, qn), new BeanDeserializerFactory(Distributor.class, qn));
            qn = new QName("urn:BeanService", "Customer");
            call.registerTypeMapping(Customer.class, qn, new BeanSerializerFactory(Customer.class,
                    qn), new BeanDeserializerFactory(Customer.class, qn));
            qn = new QName("urn:BeanService", "Vessel");
            call.registerTypeMapping(Vessel.class, qn, new BeanSerializerFactory(Vessel.class, qn),
                    new BeanDeserializerFactory(Vessel.class, qn));
            Distributor[] d = (Distributor[]) call.invoke(new Object[] {});
            return d;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public DistributorInfo getDistributorInfo(int distributorId) throws RemoteException {
        try {
            Call call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getDistributorInfo"));
            QName qn = new QName("urn:BeanService", "DistributorInfo");
            call.registerTypeMapping(DistributorInfo.class, qn, new BeanSerializerFactory(
                    DistributorInfo.class, qn), new BeanDeserializerFactory(DistributorInfo.class,
                    qn));
            call.addParameter("distributorId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.setReturnType(qn);
            DistributorInfo di = (DistributorInfo) call.invoke(new Object[] { Integer
                    .valueOf(distributorId) });
            return di;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public void saveDistributorInfo(DistributorInfo di) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("DistributorService", "saveDistributorInfo"));
            QName qn = new QName("urn:BeanService", "DistributorInfo");
            call.registerTypeMapping(DistributorInfo.class, qn, new BeanSerializerFactory(
                    DistributorInfo.class, qn), new BeanDeserializerFactory(DistributorInfo.class,
                    qn));
            call.addParameter("distributor", qn, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_BOOLEAN);
            call.invoke(new Object[] { di });
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public CustomerInfo getCustomerInfo(int customerId) throws RemoteException {
        try {
            Call call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getCustomerInfo"));
            QName qn = new QName("urn:BeanService", "CustomerInfo");
            call.registerTypeMapping(CustomerInfo.class, qn, new BeanSerializerFactory(
                    CustomerInfo.class, qn), new BeanDeserializerFactory(CustomerInfo.class, qn));
            call.addParameter("customerId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.setReturnType(qn);
            CustomerInfo ci = (CustomerInfo) call
                    .invoke(new Object[] { Integer.valueOf(customerId) });
            return ci;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public void saveCustomerInfo(CustomerInfo ci) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("DistributorService", "saveCustomerInfo"));
            QName qn = new QName("urn:BeanService", "CustomerInfo");
            call.registerTypeMapping(CustomerInfo.class, qn, new BeanSerializerFactory(
                    CustomerInfo.class, qn), new BeanDeserializerFactory(CustomerInfo.class, qn));
            call.addParameter("customer", qn, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_BOOLEAN);
            call.invoke(new Object[] { ci });
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public VesselInfo getVesselInfo(int vesselId) throws RemoteException {
        try {
            Call call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getVesselInfo"));
            QName qn = new QName("urn:BeanService", "VesselInfo");
            call.registerTypeMapping(VesselInfo.class, qn, new BeanSerializerFactory(
                    VesselInfo.class, qn), new BeanDeserializerFactory(VesselInfo.class, qn));
            call.addParameter("vesselId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.setReturnType(qn);
            VesselInfo vi = (VesselInfo) call.invoke(new Object[] { Integer.valueOf(vesselId) });
            return vi;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public VesselInfo getVesselInfoByVesselNo(String vesselNo) throws RemoteException {
        try {
            Call call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getVesselInfoByVesselNo"));
            QName qn = new QName("urn:BeanService", "VesselInfo");
            call.registerTypeMapping(VesselInfo.class, qn, new BeanSerializerFactory(
                    VesselInfo.class, qn), new BeanDeserializerFactory(VesselInfo.class, qn));
            call.addParameter("vesselNo", XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(qn);
            VesselInfo vi = (VesselInfo) call.invoke(new Object[] { vesselNo });
            return vi;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public VesselInfo getRemoteVesselInfoByIMO(String imoNumber) throws RemoteException {
        try {
            Call call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getRemoteVesselInfoByIMO"));
            QName qn = new QName("urn:BeanService", "VesselInfo");
            call.registerTypeMapping(VesselInfo.class, qn, new BeanSerializerFactory(
                    VesselInfo.class, qn), new BeanDeserializerFactory(VesselInfo.class, qn));
            call.addParameter("imoNumber", XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(qn);
            VesselInfo vi = (VesselInfo) call.invoke(new Object[] { imoNumber });
            return vi;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public void saveVesselInfo(VesselInfo vi) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("DistributorService", "saveVesselInfo"));
            QName qn = new QName("urn:BeanService", "VesselInfo");
            call.registerTypeMapping(VesselInfo.class, qn, new BeanSerializerFactory(
                    VesselInfo.class, qn), new BeanDeserializerFactory(VesselInfo.class, qn));
            call.addParameter("customer", qn, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_BOOLEAN);
            call.invoke(new Object[] { vi });
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public VesselCategory[] getAllVesselCategories() throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getAllVesselCategories"));
            QName qn = new QName("http://stubs.ws.gds.ecc.no", "ArrayOf_tns3_VesselCategory");
            call.registerTypeMapping(VesselCategory[].class, qn, new ArraySerializerFactory(),
                    new ArrayDeserializerFactory());
            call.setReturnType(qn);
            qn = new QName("urn:BeanService", "VesselCategory");
            call.registerTypeMapping(VesselCategory.class, qn, new BeanSerializerFactory(
                    VesselCategory.class, qn),
                    new BeanDeserializerFactory(VesselCategory.class, qn));
            VesselCategory[] vc = (VesselCategory[]) call.invoke(new Object[] {});
            return vc;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public VesselTonnage[] getAllVesselTonnages() throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getAllVesselTonnages"));
            QName qn = new QName("http://stubs.ws.gds.ecc.no", "ArrayOf_tns3_VesselTonnage");
            call.registerTypeMapping(VesselTonnage[].class, qn, new ArraySerializerFactory(),
                    new ArrayDeserializerFactory());
            call.setReturnType(qn);
            qn = new QName("urn:BeanService", "VesselTonnage");
            call.registerTypeMapping(VesselTonnage.class, qn, new BeanSerializerFactory(
                    VesselTonnage.class, qn), new BeanDeserializerFactory(VesselTonnage.class, qn));
            VesselTonnage[] vt = (VesselTonnage[]) call.invoke(new Object[] {});
            return vt;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public Country[] getAllCountries() throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getAllCountries"));
            QName qn = new QName("http://stubs.ws.gds.ecc.no", "ArrayOf_tns3_Country");
            call.registerTypeMapping(Country[].class, qn, new ArraySerializerFactory(),
                    new ArrayDeserializerFactory());
            call.setReturnType(qn);
            qn = new QName("urn:BeanService", "Country");
            call.registerTypeMapping(Country.class, qn,
                    new BeanSerializerFactory(Country.class, qn), new BeanDeserializerFactory(
                            Country.class, qn));
            Country[] cs = (Country[]) call.invoke(new Object[] {});
            return cs;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public CustomerType[] getAllCustomerTypes() throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getAllCustomerTypes"));
            QName qn = new QName("http://stubs.ws.gds.ecc.no", "ArrayOf_tns3_CustomerType");
            call.registerTypeMapping(CustomerType[].class, qn, new ArraySerializerFactory(),
                    new ArrayDeserializerFactory());
            call.setReturnType(qn);
            qn = new QName("urn:BeanService", "CustomerType");
            call.registerTypeMapping(Country.class, qn, new BeanSerializerFactory(
                    CustomerType.class, qn), new BeanDeserializerFactory(CustomerType.class, qn));
            CustomerType[] ct = (CustomerType[]) call.invoke(new Object[] {});
            return ct;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public DiscountGroup[] getAllDiscountGroups() throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getAllDiscountGroups"));
            QName qn = new QName("http://stubs.ws.gds.ecc.no", "ArrayOf_tns3_DiscountGroup");
            call.registerTypeMapping(DiscountGroup[].class, qn, new ArraySerializerFactory(),
                    new ArrayDeserializerFactory());
            call.setReturnType(qn);
            qn = new QName("urn:BeanService", "DiscountGroup");
            call.registerTypeMapping(Country.class, qn, new BeanSerializerFactory(
                    DiscountGroup.class, qn), new BeanDeserializerFactory(DiscountGroup.class, qn));
            DiscountGroup[] dgs = (DiscountGroup[]) call.invoke(new Object[] {});
            return dgs;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public UserPermit[] getUserPermitsForVessel(int vesselId) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getUserPermitsForVessel"));
            QName qn = new QName("http://stubs.ws.gds.ecc.no", "ArrayOf_tns3_UserPermit");
            call.registerTypeMapping(UserPermit[].class, qn, new ArraySerializerFactory(),
                    new ArrayDeserializerFactory());
            call.setReturnType(qn);
            qn = new QName("urn:BeanService", "UserPermit");
            call.registerTypeMapping(UserPermit.class, qn, new BeanSerializerFactory(
                    UserPermit.class, qn), new BeanDeserializerFactory(UserPermit.class, qn));
            call.addParameter("vesselId", XMLType.XSD_INTEGER, ParameterMode.IN);
            UserPermit[] ups = (UserPermit[]) call
                    .invoke(new Object[] { Integer.valueOf(vesselId) });
            return ups;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public void deleteUserPermit(UserPermit userPermit) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("DistributorService", "deleteUserPermit"));
            QName qn = new QName("urn:BeanService", "UserPermit");
            call.registerTypeMapping(UserPermit.class, qn, new BeanSerializerFactory(
                    UserPermit.class, qn), new BeanDeserializerFactory(UserPermit.class, qn));
            call.addParameter("userPermit", qn, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_BOOLEAN);
            call.invoke(new Object[] { userPermit });
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public void saveUserPermit(UserPermit userPermit) throws RemoteException {
        Call call;
        try {
            call = getNewCall();
            call.setOperationName(new QName("DistributorService", "saveUserPermit"));
            QName qn = new QName("urn:BeanService", "UserPermit");
            call.registerTypeMapping(UserPermit.class, qn, new BeanSerializerFactory(
                    UserPermit.class, qn), new BeanDeserializerFactory(UserPermit.class, qn));
            call.addParameter("userPermit", qn, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_BOOLEAN);
            call.invoke(new Object[] { userPermit });
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public Integer getS63VersionId(Integer vesselId, String userPermit)
            throws RemoteException {
        
        try {
            Call call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getS63VersionId"));
            call.addParameter("vesselId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("userPermit", XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_INT);
            return (Integer) call.invoke(new Object[] { vesselId, userPermit });
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }
    
    public void saveS63VersionId(Integer vesselId, String userPermit, Integer s63VersionId)
            throws RemoteException {
        try {
            Call call = getNewCall();
            call.setOperationName(new QName("DistributorService", "saveS63VersionId"));
            call.addParameter("vesselId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.addParameter("userPermit", XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter("s63VersionId", XMLType.XSD_INTEGER, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_BOOLEAN);
            call.invoke(new Object[] { vesselId, userPermit, s63VersionId });
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }
    
    public VesselInfo[] getVesselInfosForUserPermit(String userPermit) throws RemoteException {
        try {
            Call call = getNewCall();
            call.setOperationName(new QName("DistributorService", "getVesselInfosForUserPermit"));
            
            QName qn = new QName("http://stubs.ws.gds.ecc.no", "ArrayOf_tns3_VesselInfo");
            call.registerTypeMapping(VesselInfo[].class, qn, new ArraySerializerFactory(),
                    new ArrayDeserializerFactory());
            call.setReturnType(qn);
            qn = new QName("urn:BeanService", "VesselInfo");
            call.registerTypeMapping(VesselInfo.class, qn, new BeanSerializerFactory(
                    UserPermit.class, qn), new BeanDeserializerFactory(VesselInfo.class, qn));
            call.addParameter("userPermit", XMLType.XSD_STRING, ParameterMode.IN);
            VesselInfo[] vis = (VesselInfo[]) call.invoke(new Object[] { userPermit });
            return vis;
        } catch (MalformedURLException e) {
            throw new RemoteException(e.getMessage());
        } catch (ServiceException e) {
            throw new RemoteException(e.getMessage());
        }
    }

}
