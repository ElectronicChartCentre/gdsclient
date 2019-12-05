package no.ecc.gdsclient.remote;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.google.common.io.ByteStreams;

public class ResponseMetadata {

    public static final String FILENAME = "response.xml";

    private final List<FileInfo> excludedFiles = new ArrayList<>();

    public ResponseMetadata(InputStream in) throws IOException, XMLStreamException {
        
        
        byte[] data = ByteStreams.toByteArray(in);
        System.out.println("AA"+new String(data, "UTF-8"));
        in = new ByteArrayInputStream(data);
        
        
        
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        XMLStreamReader streamReader = null;
        try {
            streamReader = inputFactory.createXMLStreamReader(in);

            while (streamReader.hasNext()) {
                int e = streamReader.next();
                if (e == XMLStreamConstants.START_ELEMENT) {
                    String localName = streamReader.getLocalName();
                    if ("file".equals(localName)) {
                        // <file path="ENC_ROOT/S102/CA00/S102CA2_450006700/1/0/S102CA2_450006700.102" crcs="F3B7A974" cachekey="31182398a1613de8e7909b7769bccb79c13c6ba9" size="-1" certificateName="IHO.CRT"/>
                        String path = streamReader.getAttributeValue(null, "path");
                        String crcs = streamReader.getAttributeValue(null, "crcs");
                        String cacheKey = streamReader.getAttributeValue(null, "cachekey");
                        String size = streamReader.getAttributeValue(null, "size");
                        String certificateName = streamReader.getAttributeValue(null, "certificateName");
                        excludedFiles.add(new FileInfo(path, crcs, cacheKey, size == null ? null : Integer.valueOf(size), certificateName));
                    }
                }
            }

        } finally {
            if (streamReader != null) {
                streamReader.close();
            }
        }

    }

    public List<FileInfo> getExcludedFiles() {
        return excludedFiles;
    }

}