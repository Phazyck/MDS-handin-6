package clients;

public class VerificationClient {

    private static org.tempuri.IVerificationService port;

    static {
        org.tempuri.VerificationService service = new org.tempuri.VerificationService();
        port = service.getBasicHttpBindingIVerificationService();
    }

    public static String verify(java.lang.String dcrgraphXml, java.lang.String propertyXml, org.datacontract.schemas._2004._07.itu_dk_dcrs_commontypes.VerificationSettings generationSettings) {
        return port.verify(dcrgraphXml, propertyXml, generationSettings);
    }

    public static String verifyDCRGraph(java.lang.String dcrgraphXml) {
        return port.verifyDCRGraph(dcrgraphXml);
    }

    public static String verifyProperty(java.lang.String dcrgraphXml, java.lang.String propertyXml) {
        return port.verifyProperty(dcrgraphXml, propertyXml);
    }
}
