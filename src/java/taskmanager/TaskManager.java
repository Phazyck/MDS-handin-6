package taskmanager;

import clients.*;
import java.io.*;
import javax.xml.bind.JAXBException;
import static xml.Serializer.*;
import xml.tasks.*;

public final class TaskManager implements Runnable {

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new TaskManager());
        t.start();
    }
    private static final String dir = "./files/";
    private static final String tmXml = "prescribe-medicine-extended.xml";
    private Cal cal;

    public TaskManager() throws IOException, JAXBException {
        try (FileInputStream stream = new FileInputStream(dir + tmXml)) {
            javax.xml.bind.JAXBContext context = javax.xml.bind.JAXBContext.newInstance(Cal.class);
            cal = (Cal) context.createUnmarshaller().unmarshal(stream);
        }
    }

    private void writeOut(String file, String contents) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(dir + file)))) {
            writer.write(contents);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public String verifyDCRGraph() {
        try {
            String taskmanagerXml = serialize(cal);
            String dcrgraphXml = TaskmanagerHelperClient.taskmanagerXmlToDCRGXml(taskmanagerXml);
            return VerificationClient.verifyDCRGraph(dcrgraphXml);
        } catch (JAXBException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public void run() {
        writeOut("out.txt", verifyDCRGraph());
    }
}
