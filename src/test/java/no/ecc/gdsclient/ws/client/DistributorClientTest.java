package no.ecc.gdsclient.ws.client;

import java.io.IOException;

import no.ecc.gdsclient.junit.GdsClientTestCase;
import no.ecc.gdsclient.ws.impl.Customer;
import no.ecc.gdsclient.ws.impl.Distributor;
import no.ecc.gdsclient.ws.impl.UserPermit;
import no.ecc.gdsclient.ws.impl.Vessel;

public class DistributorClientTest extends GdsClientTestCase {

    public void testGetVesselInfosForUserPermit() throws IOException {
        DistributorClient dc = new DistributorClient();
        dc.setUrlPrefix(getUrlPrefix());

        // need a user permit to test with. sorry.
        String userPermit = findUserPermit(dc);
        assertNotNull(userPermit);

        assertEquals(0, dc.getVesselInfosForUserPermit("whatever").length);
        assertEquals(1, dc.getVesselInfosForUserPermit(userPermit).length);
    }

    private String findUserPermit(DistributorClient dc) throws IOException {
        for (Distributor d : dc.getDistributors()) {
            for (Customer c : d.getCustomerArray()) {
                for (Vessel v : c.getVesselArray()) {
                    for (UserPermit up : dc.getUserPermitsForVessel(v.getVesselId())) {
                        return up.getUserPermitString();
                    }
                }
            }
        }
        return null;
    }

}
