
import java.io.*;

public class Serialization {

    public static void main(String[] args) throws IOException {
        String saveDirectory = "C:\\Users\\Jarrod Simner\\OneDrive - Queensland University of Technology\\CAB302_SharedFolder\\Temp Folder/employee.ser";

        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println(String.format("\nSave directory is:  %s\n", saveDirectory));

        System.out.println("Make Sure you've specifed a directory to save the serialized data (top of main function).\n" +
                "Then press s to serialise or d to deserialize. (You'll need to serialise first.)\n");

        // Reading data using readLine
        String userInput = reader.readLine();
        if (userInput.toLowerCase().contains("s")) {
            System.out.println("Serializing:\n");
            serialize(saveDirectory);
        } else if (userInput.toLowerCase().contains("d")) {
            System.out.println("Deserializing:\n");
            deserialize(saveDirectory);
        }
    }

    public static void serialize(String saveDirectory) {
        Employee e = new Employee();
        e.name = "Reyan Ali";
        e.address = "Phokka Kuan, Ambehta Peer";
        e.SSN = 11122333;
        e.number = 101;

        try {
            FileOutputStream fileOut =
                    new FileOutputStream(saveDirectory);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
            System.out.println(String.format("Serialized data is saved in %s", saveDirectory));
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void deserialize(String saveDirectory) {
        Employee e = null;
        try {
            FileInputStream fileIn = new FileInputStream(saveDirectory);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Employee) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }

        System.out.println("Deserialized Employee...");
        System.out.println("Name: " + e.name);
        System.out.println("Address: " + e.address);
        System.out.println("SSN: " + e.SSN);
        System.out.println("Number: " + e.number);
    }
}


