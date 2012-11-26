package clients;

public class VerificationClient {

    public static String verify(java.lang.String dcrgraphXml, java.lang.String propertyXml, org.datacontract.schemas._2004._07.itu_dk_dcrs_commontypes.VerificationSettings generationSettings) {
        org.tempuri.VerificationService service = new org.tempuri.VerificationService();
        org.tempuri.IVerificationService port = service.getBasicHttpBindingIVerificationService();
        return port.verify(dcrgraphXml, propertyXml, generationSettings);
    }

    public static String verifyDCRGraph(java.lang.String dcrgraphXml) {
        org.tempuri.VerificationService service = new org.tempuri.VerificationService();
        org.tempuri.IVerificationService port = service.getBasicHttpBindingIVerificationService();
        return port.verifyDCRGraph(dcrgraphXml);
    }

    public static String verifyProperty(java.lang.String dcrgraphXml, java.lang.String propertyXml) {
        org.tempuri.VerificationService service = new org.tempuri.VerificationService();
        org.tempuri.IVerificationService port = service.getBasicHttpBindingIVerificationService();
        return port.verifyProperty(dcrgraphXml, propertyXml);
    }
    
}
