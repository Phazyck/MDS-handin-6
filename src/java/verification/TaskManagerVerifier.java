package verification;

import clients.TaskmanagerHelperClient;
import clients.VerificationClient;
import java.io.File;
import util.FileOrganizer;
import static xml.Serializer.*;
import xml.verification.*;

/**
 * This Class is capable of reading a task manager XML-file and verifying
 * whether certain properties holds for that particular task manager.
 */
public class TaskManagerVerifier {

    /**
     * Verify the DCR Graph which can be made from the given file.
     *
     * @param taskmanagerFile The file containing the information to be
     * verified.
     */
    public static void verifyDCRGraph(File taskmanagerFile) {
        try {
            // Prepare a FileManager to read/write files.
            FileOrganizer taskFiles = new FileOrganizer(taskmanagerFile);
            // Read the contents of the given file.
            String taskmanagerXml = taskFiles.readFile(".xml");
            // Send the taskmanagerXml to the service in order to get back its DCRGraph representation.
            String dcrgraphXml = TaskmanagerHelperClient.taskmanagerXmlToDCRGXml(taskmanagerXml);
            // Save the graph to file.
            taskFiles.writeFile("-graph.xml", dcrgraphXml);
            // Send the dcrgraphXml to the other service in order to get back its verification.
            String verificationXml = VerificationClient.verifyDCRGraph(dcrgraphXml);
            // Save the verification to file.
            taskFiles.writeFile("-verification.xml", verificationXml);
            // Deserialize the verification result.
            DCRGVerificationResult result = deSerialize(verificationXml, DCRGVerificationResult.class);
            // Print the results...
            System.out.println("=== " + result.model.name + " properties ===");
            for (PropertyResult r : result.model.results) {
                // ...of each propery check.
                System.out.println(r.type + ":\n  " + r.isValid + (r.isValid ? "" : " - " + r.message));
            }
            // Write the statespace to a .dot file.
            taskFiles.writeFile("-statespace.dot", result.model.statespace);
        } catch (Exception ex) {
            // In case an Exception is thrown, throw it as a RuntimeException.
            throw new RuntimeException(ex);
        }
    }

    /**
     * Verify the DCR Graph which can be made from the given file. Also check
     * whether a given property applies to the constructed DCR Graph.
     *
     * @param taskmanagerFile The file containing the information to be
     * verified.
     * @param propertyFile The file containing the property information.
     */
    public static void verifyDCRGraph(File taskmanagerFile, File propertyFile) {
        try {
            // Prepare a FileManager to read/write files.
            FileOrganizer taskFiles = new FileOrganizer(taskmanagerFile);
            // Read the contents of the given file.
            String taskmanagerXml = taskFiles.readFile(".xml");
            // Send the taskmanagerXml to the service in order to get back its DCRGraph representation.
            String dcrgraphXml = TaskmanagerHelperClient.taskmanagerXmlToDCRGXml(taskmanagerXml);
            // Save the graph to file.
            taskFiles.writeFile("-graph.xml", dcrgraphXml);
            // Prepare a FileManager for the property.
            FileOrganizer propertyFiles = new FileOrganizer(propertyFile);
            // Read the contents of the property file.
            String propertyXml = propertyFiles.readFile(".xml");
            // Send the propertyXml to the service in order to get back its DCRGraph representation.
            String propertyGraphXml = TaskmanagerHelperClient.taskmanagerXmlToDCRGXml(propertyXml);
            // Save the graph to file.
            propertyFiles.writeFile("-graph.xml", propertyGraphXml);
            // Send both graphs to the other service in order to get back the verification.
            String verificationXml = VerificationClient.verifyProperty(dcrgraphXml, propertyGraphXml);
            // Save the verification to file.
            taskFiles.writeFile("-verification.xml", verificationXml);
            // Deserialize the verification result.
            DCRGVerificationResult result = deSerialize(verificationXml, DCRGVerificationResult.class);
            // Display all the different results.
            printModel(result.model);
            printModel(result.property);
            printModel(result.composite);
            // Write the different statespaces to file.
            taskFiles.writeFile("-model.dot", result.model.statespace);
            taskFiles.writeFile("-property.dot", result.property.statespace);
            taskFiles.writeFile("-composite.dot", result.composite.statespace);
        } catch (Exception ex) {
            // In case an Exception is thrown, throw it as a RuntimeException.
            throw new RuntimeException(ex);
        }
    }

    /**
     * Prints out the results of a given <dcrgModel>.
     *
     * @param model The Model.
     */
    private static void printModel(DCRGModel model) {
        // Print the results...
        System.out.println("=== " + model.name + " properties ===");
        for (PropertyResult result : model.results) {
            // ...of each propery check.
            System.out.println(result.type + ":\n  " + result.isValid + (result.isValid ? "" : " - " + result.message));
        }
    }
}
