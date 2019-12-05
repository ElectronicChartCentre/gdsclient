package no.ecc.gdsclient.remote;

import java.io.IOException;
import java.io.InputStream;

public interface Receiver {
    
    public void receive(String fileName, InputStream in) throws IOException;
    
    public boolean shouldStop();

}
