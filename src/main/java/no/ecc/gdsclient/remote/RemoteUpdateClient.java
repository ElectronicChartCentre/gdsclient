package no.ecc.gdsclient.remote;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.google.common.io.ByteStreams;

import no.ecc.gdsclient.exchangeset.CellUpdate;
import no.ecc.gdsclient.utility.FileUtils;

public class RemoteUpdateClient {

    private final String urlPrefix;
    private final String userPermit;
    private final List<CellUpdate> status;

    public RemoteUpdateClient(String urlPrefix, String userPermit, List<CellUpdate> status) {
        this.urlPrefix = urlPrefix;
        this.userPermit = userPermit;
        this.status = Collections.unmodifiableList(new ArrayList<>(status));
    }

    private Collection<CellUpdate> getStatus() {
        return status;
    }

    private String getUserPermit() {
        return userPermit;
    }

    private String getServer() {
        return urlPrefix + "/remoteupdate";
    }

    private void writeRequestTo(OutputStream out) throws IOException {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(out);
            writer.writeStartDocument("utf-8", "1.0");

            writer.writeStartElement("request");

            writer.writeStartElement("operation");
            writer.writeCharacters(Operation.OPERATION_UPDATE);
            writer.writeEndElement();

            writer.writeStartElement("exclude-cacheable-files");
            writer.writeEndElement();

            writer.writeStartElement("exclude-products-dot-txt");
            writer.writeEndElement();

            writer.writeStartElement("ecdis");
            writer.writeAttribute("type", "gdsclient");
            writer.writeAttribute("version", "");

            writer.writeStartElement("userpermit");
            writer.writeCharacters(getUserPermit());
            writer.writeEndElement();

            writer.writeStartElement("status");
            for (CellUpdate gcu : getStatus()) {
                writer.writeStartElement("cell");
                writer.writeAttribute("id", gcu.getCellId());
                writer.writeAttribute("edtn", gcu.getEdtn().toString());
                writer.writeAttribute("updn", gcu.getUpdn().toString());
                writer.writeEndElement();
            }

            writer.writeEndElement();

            writer.writeEndElement();

            writer.writeEndElement();

            writer.writeEndDocument();
            writer.close();
        } catch (XMLStreamException e) {
            throw new IOException(e);
        }
    }

    public void download(BiConsumer<String, InputStream> receiver) throws IOException {
        download(new Receiver() {

            @Override
            public boolean shouldStop() {
                return false;
            }

            @Override
            public void receive(String fileName, InputStream in) throws IOException {
                receiver.accept(fileName, in);
            }
        });
    }

    public void download(Receiver receiver) throws IOException {

        URL url = new URL(getServer());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        writeRequestTo(conn.getOutputStream());

        // only continue if response is 200 OK
        if (conn.getResponseCode() != 200) {
            throw new IOException("Got unexpected status " + conn.getResponseCode() + " " + conn.getResponseMessage());
        }

        ResponseMetadata responseMetadata = null;
        try (ZipInputStream zis = new ZipInputStream(conn.getInputStream())) {
            ZipEntry ze = null;
            while ((ze = zis.getNextEntry()) != null) {
                if (ze.isDirectory()) {
                    continue;
                }

                if (receiver.shouldStop()) {
                    return;
                }

                String fileName = ze.getName();

                // extract metadata to find extra files outside of the first zip stream
                if (fileName.equals(ResponseMetadata.FILENAME)) {
                    try {
                        // read to byte[] first as XMLStreamReader closes the underlying stream
                        byte[] data = ByteStreams.toByteArray(zis);
                        responseMetadata = new ResponseMetadata(new ByteArrayInputStream(data));
                    } catch (XMLStreamException e) {
                        throw new IOException("Could not parse remote metadata", e);
                    }
                    continue;
                }

                fileName = FileUtils.convertToNativePath(fileName);
                receiver.receive(fileName, zis);
            }
        }

        if ((responseMetadata == null) || responseMetadata.getExcludedFiles().isEmpty()) {
            return;
        }

        for (FileInfo file : responseMetadata.getExcludedFiles()) {

            if (receiver.shouldStop()) {
                return;
            }

            StringBuilder fileUrl = new StringBuilder();
            fileUrl.append(getServer());
            fileUrl.append("/");
            if (file.getCertificateName() != null && file.getCertificateName().length() > 0) {
                fileUrl.append(file.getCertificateName());
                fileUrl.append("/");
            }
            fileUrl.append(file.getPath());

            receiver.receive(file.getPath(), new URL(fileUrl.toString()).openStream());
        }

    }

}
