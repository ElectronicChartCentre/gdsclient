package no.ecc.gdsclient.cli;

import java.util.Arrays;

import no.ecc.gdsclient.utility.UrlPrefix;
import no.ecc.gdsclient.ws.client.DistributorClient;
import no.ecc.gdsclient.ws.impl.VesselInfo;

public class UserPermit {

    public static void main(String[] args) throws Exception {
        String urlPrefix = System.getProperty(UrlPrefix.URL_PREFIX_KEY);
        if (urlPrefix == null) {
            urlPrefix = System.getenv(UrlPrefix.URL_PREFIX_KEY);
        }
        if (urlPrefix == null) {
            System.err.println("missing property " + UrlPrefix.URL_PREFIX_KEY
                    + " set to something like https://username:password@qaprimar.ecc.no/qaprimar");
            System.exit(-1);
        }

        DistributorClient dc = new DistributorClient();
        dc.setUrlPrefix(urlPrefix);

        if (args.length >= 2) {
            String command = args[0];
            int vesselId = Integer.parseInt(args[1]);
            if (command.equals("list")) {
                no.ecc.gdsclient.ws.impl.UserPermit[] ups = dc.getUserPermitsForVessel(vesselId);
                for (no.ecc.gdsclient.ws.impl.UserPermit up : ups) {
                    System.out.println(up.getUserPermitString());
                }
                return;
            } else if (args.length == 3) {
                String userPermitString = args[2];
                if (command.equals("save")) {

                    no.ecc.gdsclient.ws.impl.UserPermit[] ups = dc.getUserPermitsForVessel(vesselId);
                    for (no.ecc.gdsclient.ws.impl.UserPermit up : ups) {
                        if (userPermitString.equals(up.getUserPermitString())) {
                            // no way to change any parameters here yet, so no need to save
                            return;
                        }
                    }

                    VesselInfo vesselInfo = dc.getVesselInfo(vesselId);

                    no.ecc.gdsclient.ws.impl.UserPermit up = new no.ecc.gdsclient.ws.impl.UserPermit();
                    up.setUserPermitString(userPermitString);
                    up.setUserId(vesselInfo.getUserId());
                    up.setName("test");
                    dc.saveUserPermit(up);

                    return;
                } else if (command.equals("delete")) {
                    no.ecc.gdsclient.ws.impl.UserPermit[] ups = dc.getUserPermitsForVessel(vesselId);
                    for (no.ecc.gdsclient.ws.impl.UserPermit up : ups) {
                        if (userPermitString.equals(up.getUserPermitString())) {
                            dc.deleteUserPermit(up);
                        }
                    }
                    return;
                }
            }
        }

        System.err.println(
                "Usage: ..UserPermit [list vesselId|save vesselId THE_USER_PERMIT|delete vesselId THE_USER_PERMIT] "
                        + UrlPrefix.URL_PREFIX_KEY
                        + " set to something like https://username:password@qaprimar.ecc.no/qaprimar");
        System.exit(-1);

    }

}
