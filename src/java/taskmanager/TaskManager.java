package taskmanager;

import java.io.*;
import javax.xml.bind.JAXBException;
import static xml.Serializer.*;
import xml.tasks.*;

public class TaskManager {

    public static void main(String[] args) {
        TaskManager tm = new TaskManager();

        try {
            System.out.println(serialize(tm.cal.tasks));
        } catch (JAXBException ex) {
            System.out.println(ex);
        }

    }
    private static final String load = "./tasks/load/prescribe-medicine-extended.xml";
    private static final String save = "./tasks/save/prescribe-medicine-extended.xml";
    private static final boolean resume = false;
    public Cal cal;

    public TaskManager() {
        loadCal();
        saveCal();
    }

    private void loadCal() {        
        try (FileInputStream stream = new FileInputStream(resume ? save : load)) {
            javax.xml.bind.JAXBContext context = javax.xml.bind.JAXBContext.newInstance(Cal.class);
            cal = (Cal) context.createUnmarshaller().unmarshal(stream);
        } catch (JAXBException | IOException ex) {
            cal = new Cal();
        }
    }

    private void saveCal() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(save)))) {
            writer.write(serialize(cal));
        } catch (JAXBException | IOException ex) {
            System.out.println(ex);
        }
    }

    public void printCal() {
        try {
            System.out.println(serialize(cal));
        } catch (JAXBException ex) {
            System.out.println(ex);
        }
    }
}
