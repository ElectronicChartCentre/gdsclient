package no.ecc.gdsclient.ws.client;

import java.io.IOException;

import no.ecc.gdsclient.junit.GdsClientTestCase;
import no.ecc.gdsclient.ws.impl.Customer;
import no.ecc.gdsclient.ws.impl.Distributor;
import no.ecc.gdsclient.ws.impl.UserPermit;
import no.ecc.gdsclient.ws.impl.Vessel;

public class ProductClientTest extends GdsClientTestCase {

    public void testCreatePermitFile() throws IOException {
        DistributorClient dc = new DistributorClient();
        dc.setUrlPrefix(getUrlPrefix());

        ProductClient pc = new ProductClient();
        pc.setUrlPrefix(getUrlPrefix());

        Integer vesselId = null;
        String userPermit = null;

        // looking for a vessel with a user permit on the server. for most use cases,
        // the client knows this.
        for (Distributor d : dc.getDistributors()) {
            if (vesselId != null) {
                break;
            }
            for (Customer c : d.getCustomerArray()) {
                if (vesselId != null) {
                    break;
                }
                for (Vessel v : c.getVesselArray()) {
                    for (UserPermit up : dc.getUserPermitsForVessel(v.getVesselId())) {
                        userPermit = up.getUserPermitString();
                        vesselId = v.getVesselId();
                        break;
                    }
                }
            }
        }

        assertNotNull(vesselId);
        assertNotNull(userPermit);

        String permitFileContent = pc.createPermitFile(vesselId, userPermit, false);
        assertNotNull(permitFileContent);
        assertTrue(permitFileContent.startsWith(":DATE 2"));

    }

}
