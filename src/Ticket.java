import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    // Constructor
    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Getters and setters
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // Method to print ticket information
    public void printInfo() {
        System.out.println("Person Information:");
        person.printInfo();
        String display_row = switch (row) {
            case 0 -> "A";
            case 1 -> "B";
            case 2 -> "C";
            case 3 -> "D";
            default -> throw new IllegalStateException("Unexpected value: " + row);
        };
        System.out.println("Row: " + display_row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: " + price);
    }

    public void save() {
        String display_row = switch (row) {
            case 0 -> "A";
            case 1 -> "B";
            case 2 -> "C";
            case 3 -> "D";
            default -> throw new IllegalStateException("Unexpected value: " + row);
        };

        // Specify the file path
        String filePath = display_row + seat + ".txt";

        try {
            // Create a FileWriter object
            FileWriter fileWriter = new FileWriter(filePath);

            // Create a BufferedWriter object
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write data to the file
            bufferedWriter.write("Person Information:\n");
            bufferedWriter.write("\tName - " + person.getName() + "\n");
            bufferedWriter.write("\tSurname - " + person.getSurname() + "\n");
            bufferedWriter.write("\tEmail - " + person.getEmail() + "\n");
            bufferedWriter.write("Row: " + display_row + "\n");
            bufferedWriter.write("Seat: " + seat + "\n");
            bufferedWriter.write("Price: " + price + "\n");

            // Close the BufferedWriter
            bufferedWriter.close();

            System.out.println("Data has been written to the file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void delete() {
        String display_row = switch (row) {
            case 0 -> "A";
            case 1 -> "B";
            case 2 -> "C";
            case 3 -> "D";
            default -> throw new IllegalStateException("Unexpected value: " + row);
        };

        // Specify the file path
        String filePath = display_row + seat + ".txt";

        // Create a File object representing the file to be deleted
        File file = new File(filePath);

        // Check if the file exists
        if (file.exists()) {
            // Attempt to delete the file
            boolean deleted = file.delete();

            // Check if the file was deleted successfully
            if (deleted) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("The file does not exist.");
        }
    }
}

