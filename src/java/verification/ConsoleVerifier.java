package verification;

import java.io.*;

/**
 * A console application to test out the TaskManagerVerifier.
 */
public class ConsoleVerifier implements Runnable {

    /**
     * Use this method to run the application.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        Thread t = new Thread(new ConsoleVerifier());
        t.start();
    }
    BufferedReader input;
    File taskmanagerFile = null;
    File propertyFile = null;

    /**
     * Initializes the console with a little help from the user.
     */
    public ConsoleVerifier() {
        input = new BufferedReader(new InputStreamReader(System.in));
        while (taskmanagerFile == null) {
            System.out.println("Which kind of taskmanager file would you like to verify?");
            System.out.println(" - [example]  - The example from the notes.");
            System.out.println(" - [extended] - The extended manager from the assignment");
            System.out.println(" - [improved] - The improved manager whith liveness and no deadlocks.");
            switch (readLine().toLowerCase()) {
                case "example":
                    taskmanagerFile = new File("./files/example/prescribe-medicine-example.xml");
                    break;
                case "extended":
                    taskmanagerFile = new File("./files/extended/prescribe-medicine-extended.xml");
                    break;
                case "improved":
                    taskmanagerFile = new File("./files/improved/prescribe-medicine-improved.xml");
                    break;
                default:
                    taskmanagerFile = null;
                    break;
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Which property would you like to verify on the taskmanager?");
            System.out.println(" - example-[a]  - Verify the property described as Fig.3(a) in the notes.");
            System.out.println(" - example-[b]  - Verify the property described as Fig.3(b) in the notes.");
            System.out.println(" - [sign]-first - Verify our custom sign-first property.");
            System.out.println(" - [none]       - Don't verify any other properties.");
            switch (readLine().toLowerCase()) {
                case "a":
                    propertyFile = new File("./files/properties/example-a.xml");
                    TaskManagerVerifier.verifyDCRGraph(taskmanagerFile, propertyFile);
                    return;
                case "b":
                    propertyFile = new File("./files/properties/example-b.xml");
                    TaskManagerVerifier.verifyDCRGraph(taskmanagerFile, propertyFile);
                    return;
                case "sign":
                    propertyFile = new File("./files/properties/sign-first.xml");
                    TaskManagerVerifier.verifyDCRGraph(taskmanagerFile, propertyFile);
                    return;
                case "none":
                    TaskManagerVerifier.verifyDCRGraph(taskmanagerFile);
                    return;
                default:
                    continue;
            }
        }
    }

    /**
     * Gets input from the user.
     *
     * @return The input.
     */
    private String readLine() {
        System.out.print("> ");
        System.out.flush();
        try {
            String line = input.readLine();
            System.out.println();
            if (line.equalsIgnoreCase("exit")) {
                System.out.println("Bye-bye!");
                System.exit(0);
            }
            return line;
        } catch (IOException ex) {
            return ex.toString();
        }
    }
}
