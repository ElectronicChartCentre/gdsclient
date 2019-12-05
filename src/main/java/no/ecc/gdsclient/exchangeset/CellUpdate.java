package no.ecc.gdsclient.exchangeset;

/**
 * A interface for the cellId/edtn/updn/reissue combination used to identify a
 * single EN/ER/Reissue.
 */
public interface CellUpdate {

    public String getCellId();

    public Integer getEdtn();

    public Integer getUpdn();

    public Integer getReissue();
    
    public String getIdString();

}
