package no.ecc.gdsclient.ws.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import no.ecc.gdsclient.junit.GdsClientTestCase;
import no.ecc.gdsclient.ws.impl.FileDownloadWrapper;

public class CellDownloadClientTest extends GdsClientTestCase {

    public void testDownload() throws IOException {

        List<String> cellIds = new ArrayList<>(Arrays.asList("US2EC02M", "US5MI88M"));

        CellDownloadClient c = new CellDownloadClient();
        c.setUrlPrefix(getUrlPrefix());

        try {
            c.download(true, null, new Date(), cellIds, Collections.emptyList());

            int i = 0;
            while (true) {
                FileDownloadWrapper f = c.get(i++);
                if (f == null) {
                    break;
                }
                System.out.println(f.getPath());
            }
            assertTrue(i > 5);

        } finally {
            c.finish();
        }
    }

    public void testDownloadS102() throws IOException {

        List<String> cellIds = new ArrayList<>(Arrays.asList("102CA0024100N04700W"));

        CellDownloadClient c = new CellDownloadClient();
        c.setUrlPrefix(getUrlPrefix());

        try {
            assertTrue(c.download(true, null, new Date(), cellIds, Collections.emptyList()));

            int i = 0;
            while (true) {
                FileDownloadWrapper f = c.get(i++);
                if (f == null) {
                    break;
                }
                System.out.println(f.getPath());
            }
            assertTrue(i > 5);

        } finally {
            c.finish();
        }
    }

    public void testDownloadForVessel() throws IOException {
        
        CellDownloadClient c = new CellDownloadClient();
        c.setUrlPrefix(getUrlPrefix());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);

        try {
            assertTrue(c.downloadForVessel(getVesselId(), true, calendar.getTime(), null, true));

            int i = 0;
            while (true) {
                FileDownloadWrapper f = c.get(i++);
                if (f == null) {
                    break;
                }
                System.out.println(f.getPath());
                //break to not potentially download thousands of exchange nodes
                if(i > 5) break;
            }
            assertTrue(i > 5);

        } finally {
            c.finish();
        }
    }
}
