package no.ecc.gdsclient.ws.client;

import no.ecc.gdsclient.catalogue.CatalogueParser;
import no.ecc.gdsclient.catalogue.CatalogueParser.Product;
import no.ecc.gdsclient.junit.GdsClientTestCase;
import no.ecc.gdsclient.ws.impl.CustomerInfo;
import no.ecc.gdsclient.ws.impl.IdName;
import no.ecc.gdsclient.ws.impl.OrderProductRequest;
import no.ecc.gdsclient.ws.impl.OrderReport;
import no.ecc.gdsclient.ws.impl.OrderRequest;
import no.ecc.gdsclient.ws.impl.VesselInfo;

public class OrderClientTest extends GdsClientTestCase {

    public void testOrder() throws Exception {
        DistributorClient dc = new DistributorClient();
        dc.setUrlPrefix(getUrlPrefix());

        OrderClient oc = new OrderClient();
        oc.setUrlPrefix(getUrlPrefix());

        CatalogueParser cp = CatalogueParser.create(getUrlPrefix());
        Product product = cp.getProducts().iterator().next();

        IdName[] dists = dc.getDistributorIdNames();
        assertTrue(dists.length > 0);
        int distId = dists[0].getId();

        CustomerInfo ci = new CustomerInfo();
        ci.setDistributorId(distId);
        ci.setEnabled(true);
        ci.setName("cust gdsclient junit " + System.currentTimeMillis());
        ci.setCountryId("NO");
        int custId = dc.saveCustomerInfoAndReturnId(ci);
        assertTrue(custId > 0);

        VesselInfo vi = new VesselInfo();
        vi.setDistributorId(distId);
        vi.setCustomerId(custId);
        vi.setName("vessel gdsclient junit " + System.currentTimeMillis());
        vi.setEnabled(true);
        vi.setCategoryId(dc.getAllVesselCategories()[0].getCategoryId());
        vi.setTonnageId(dc.getAllVesselTonnages()[0].getCode());
        vi.setFlagId(dc.getAllCountries()[0].getCode());
        int vesselId = dc.saveVesselInfoAndReturnId(vi);
        assertTrue(vesselId > 0);
        vi = dc.getVesselInfo(vesselId);

        OrderProductRequest opr = new OrderProductRequest();
        opr.setProductId(product.getProductId());
        opr.setQuantity(1);
        opr.setSubscriptionTypeId(1);

        OrderRequest or = new OrderRequest();
        or.setOrderProducts(new OrderProductRequest[] { opr });
        or.setUserId(vi.getUserId());

        int orderId = oc.placeOrder(or);
        assertTrue(orderId > 0);

        OrderReport orderReport = oc.getOrderReport(orderId);
        assertNotNull(orderReport);
        assertEquals(1, orderReport.getOrderProducts().length);

        // to activate the order
        // oc.activateOrder(orderId, false);
        
    }

}
