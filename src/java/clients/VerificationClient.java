package clients;

/**
 * A client to the VerificationService located at:
 * http://trustcare.itu.dk/DCRGraphVerificationServices/VerificationService.svc
 *
 * Generated and then modified a bit in Netbeans using the WSDL provided at:
 * http://trustcare.itu.dk/DCRGraphVerificationServices/VerificationService.svc?wsdl
 */
public class VerificationClient {

    private static org.tempuri.IVerificationService port;

    static {
        org.tempuri.VerificationService service = new org.tempuri.VerificationService();
        port = service.getBasicHttpBindingIVerificationService();
    }

    /**
     * Verify a DCR Graph.
     *
     * @param dcrgraphXml The DCR Graph serialized into a String of XML.
     * @return The result of the verification.
     */
    public static String verifyDCRGraph(java.lang.String dcrgraphXml) {
        return port.verifyDCRGraph(dcrgraphXml);
    }

    /**
     * Verify a DCR Graph, while also checking whether it fulfills a given
     * property.
     *
     * @param dcrgraphXml The DCR Graph serialized into a String of XML.
     * @param propertyXml The DCR Graph of the property, serialized into a
     * String of XML.
     * @return The result of the verification.
     */
    public static String verifyProperty(java.lang.String dcrgraphXml, java.lang.String propertyXml) {
        return port.verifyProperty(dcrgraphXml, propertyXml);
    }
}
