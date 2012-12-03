package clients;

/**
 * A client to the TaskmanagerHelper located at:
 * http://trustcare.itu.dk/DCRGraphVerificationServices/TaskmanagerHelperService.svc
 *
 * Generated and then modified a bit in Netbeans using the WSDL provided at:
 * http://trustcare.itu.dk/DCRGraphVerificationServices/TaskmanagerHelperService.svc?wsdl
 */
public class TaskmanagerHelperClient {

    private static org.tempuri.ITaskmanagerHelperService port;

    static {
        org.tempuri.TaskmanagerHelperService service = new org.tempuri.TaskmanagerHelperService();
        port = service.getBasicHttpBindingITaskmanagerHelperService();
    }

    /**
     * Create a DCR Graph representation of a given task manager.
     *
     * @param taskmanagerXml The task manager serialized into a String of XML.
     * @return The DCR Graph representation, serialized into a String of XML.
     */
    public static String taskmanagerXmlToDCRGXml(java.lang.String taskmanagerXml) {

        return port.taskmanagerXmlToDCRGXml(taskmanagerXml);
    }
}
