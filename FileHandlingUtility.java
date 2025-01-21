import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

/**
 * FileHandlingUtility: A utility program to perform basic file operations such as
 * reading, writing, and modifying text files.
 */
public class FileHandlingUtility {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("File Handling Utility");
        System.out.println("1. Read a file");
        System.out.println("2. Write to a file");
        System.out.println("3. Modify a file");
        System.out.print("Choose an option (1/2/3): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        switch (choice) {
            case 1:
                System.out.print("Enter the file path to read: ");
                String readFilePath = scanner.nextLine();
                readFile(readFilePath);
                break;

            case 2:
                System.out.print("Enter the file path to write to: ");
                String writeFilePath = scanner.nextLine();
                System.out.print("Enter the content to write: ");
                String contentToWrite = scanner.nextLine();
                writeFile(writeFilePath, contentToWrite);
                break;

            case 3:
                System.out.print("Enter the file path to modify: ");
                String modifyFilePath = scanner.nextLine();
                System.out.print("Enter the content to append: ");
                String contentToAppend = scanner.nextLine();
                modifyFile(modifyFilePath, contentToAppend);
                break;

            default:
                System.out.println("Invalid choice. Exiting.");
        }

        scanner.close();
    }

    /**
     * Reads and displays the content of a file.
     *
     * @param filePath Path to the file to be read.
     */
    public static void readFile(String filePath) {
        try {
            String content = Files.readString(Path.of(filePath));
            System.out.println("File Content:");
            System.out.println(content);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Writes content to a file. Overwrites the file if it exists.
     *
     * @param filePath Path to the file to be written.
     * @param content  Content to write to the file.
     */
    public static void writeFile(String filePath, String content) {
        try {
            Files.writeString(Path.of(filePath), content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Appends content to an existing file.
     *
     * @param filePath Path to the file to be modified.
     * @param content  Content to append to the file.
     */
    public static void modifyFile(String filePath, String content) {
        try {
            Files.writeString(Path.of(filePath), content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.err.println("Error modifying file: " + e.getMessage());
        }
    }
}
