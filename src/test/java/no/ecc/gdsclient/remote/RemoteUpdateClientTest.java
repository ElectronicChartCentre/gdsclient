package no.ecc.gdsclient.remote;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.xml.stream.XMLStreamException;

import com.google.common.io.ByteStreams;

import no.ecc.gdsclient.exchangeset.CellUpdate;
import no.ecc.gdsclient.junit.GdsClientTestCase;
import no.ecc.s100.security.S100Crypt;
import no.ecc.s100.security.S100DataPermit;
import no.ecc.s100.security.S100Manufacturer;
import no.ecc.s100.security.S100ManufacturerLookup;
import no.ecc.s100.security.S100PermitFile;
import no.ecc.s100.security.S100UserPermit;
import no.ecc.s100.utility.FileUtils;
import no.ecc.s100.utility.Hex;

public class RemoteUpdateClientTest extends GdsClientTestCase {

    public void testS100() throws Exception {
        // example mId and mKey from S-100 documentation
        String mId = "859868";
        String mKey = "4D5A79677065774A7343705272664F72";
        S100Manufacturer manufacturer = new S100Manufacturer(mId, mKey);
        S100ManufacturerLookup manufacturerLookup = new S100ManufacturerLookup() {

            @Override
            public S100Manufacturer manufacturerForMId(String theMId) {
                return mId.equals(theMId) ? manufacturer : null;
            }
        };

        // test user permit with access to some test data
        String hwId = "C6387A65063319694A560F97B3AB19CC";
        String hwIdEncrypted = manufacturer.encrypt(hwId);
        assertEquals("B2BEC75A6831832259DB00A2B0A9FA7D", hwIdEncrypted);
        S100UserPermit userPermit = new S100UserPermit(hwIdEncrypted, mId);
        assertEquals("B2BEC75A6831832259DB00A2B0A9FA7D541B9954859868", userPermit.getUserPermitString());

        S100Crypt dataPermitCrypt = new S100Crypt.EmptyIVNoPadding(hwId);

        String urlPrefix = "https://primar.ecc.no/primar";
        List<CellUpdate> status = Collections.emptyList();
        Map<String, S100DataPermit> dataPermitByDataSetId = new HashMap<>();
        Map<String, String> fileStartAsStringByFileName = new HashMap<>();

        RemoteUpdateClient client = new RemoteUpdateClient(urlPrefix, userPermit.getUserPermitString(), status);
        client.download((path, in) -> {
            System.out.println(path);
            String fileName = FileUtils.getBaseName(path);
            try {
                byte[] data = ByteStreams.toByteArray(in);
                if (fileName.equals(S100PermitFile.PERMIT_DOT_XML)) {
                    // read to byte[] first as XMLStreamReader closes the underlying stream
                    S100PermitFile permitFile = new S100PermitFile(manufacturerLookup, new ByteArrayInputStream(data));
                    for (S100DataPermit dataPermit : permitFile.getDataPermits()) {
                        dataPermitByDataSetId.put(dataPermit.getDataSetId(), dataPermit);
                    }
                } else if (fileName.startsWith("10") || fileName.endsWith(".H5") || fileName.endsWith(".102")) {
                    String dataSetId = FileUtils.getFileNameWithoutSuffix(fileName);
                    S100DataPermit dataPermit = dataPermitByDataSetId.get(dataSetId);

                    // decrypt data key using HW_ID as key.
                    String dataKey = Hex
                            .toString(dataPermitCrypt.decrypt(Hex.fromString(dataPermit.getEncryptedDataKey())));

                    // decrypt data using data key
                    S100Crypt dataCrypt = new S100Crypt.RandomIV(dataKey);
                    byte[] decryptedData = dataCrypt.decrypt(data);

                    // unzip
                    byte[] decompressedData = null;
                    try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(decryptedData))) {
                        zis.getNextEntry();
                        decompressedData = ByteStreams.toByteArray(zis);
                    }

                    // collect text version of start of file for testing.
                    fileStartAsStringByFileName.put(fileName, new String(decompressedData, 0, 100, "UTF-8"));
                }

            } catch (IOException | XMLStreamException | GeneralSecurityException e) {
                e.printStackTrace();
            }
        });

        assertFalse(dataPermitByDataSetId.isEmpty());
        assertFalse(fileStartAsStringByFileName.isEmpty());

        for (Map.Entry<String, String> e : fileStartAsStringByFileName.entrySet()) {
            assertTrue(e.getValue().contains("DSSI") || e.getValue().contains("HDF"));
        }

    }

}
