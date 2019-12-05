package no.ecc.gdsclient.remote;

import java.io.IOException;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import no.ecc.gdsclient.utility.XMLUtils;

class FileInfo {

    private String path;
    private String crcs;
    private String cacheKey;
    private Integer size;
    private String certificateName;
    
    

    public FileInfo(String path, String crcs, String cacheKey, Integer size, String certificateName) {
        this.path = path;
        this.crcs = crcs;
        this.cacheKey = cacheKey;
        this.size = size;
        this.certificateName = certificateName;
    }

    public String getPath() {
        return path;
    }

    public String getCrcs() {
        return crcs;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public Integer getSize() {
        return size;
    }

    public String getCertificateName() {
        return certificateName;
    }

    FileInfo(XMLStreamReader streamReader)
            throws IOException, XMLStreamException {
        assert streamReader.getEventType() == XMLStreamConstants.START_ELEMENT;
        String rootElementName = streamReader.getLocalName();
        while (streamReader.hasNext()) {
            int e = streamReader.next();
            if (e == XMLStreamConstants.START_ELEMENT) {
                String localName = streamReader.getLocalName();
                if ("path".equals(localName)) {
                    this.path = XMLUtils.readCharacters(streamReader);
                } else if ("crcs".equals(localName)) {
                    this.crcs = XMLUtils.readCharacters(streamReader);
                } else if ("cachekey".equals(localName)) {
                    this.cacheKey = XMLUtils.readCharacters(streamReader);
                } else if ("size".equals(localName)) {
                    this.size = Integer.valueOf(XMLUtils.readCharacters(streamReader));
                } else if ("certificateName".equals(localName)) {
                    this.certificateName = XMLUtils.readCharacters(streamReader);
                }
            }
            if (e == XMLStreamConstants.END_ELEMENT
                    && rootElementName.equals(streamReader.getLocalName())) {
                break;
            }
        }
    }
}