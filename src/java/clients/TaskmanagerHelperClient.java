package clients;

public class TaskmanagerHelperClient {

    private static org.tempuri.ITaskmanagerHelperService port;

    static {
        org.tempuri.TaskmanagerHelperService service = new org.tempuri.TaskmanagerHelperService();
        port = service.getBasicHttpBindingITaskmanagerHelperService();
    }

    public static String taskmanagerXmlToDCRGXml(java.lang.String taskmanagerXml) {

        return port.taskmanagerXmlToDCRGXml(taskmanagerXml);
    }
}
