import java.util.*;

public class InputValidator {
    public static int getIntegerInput(String promptText) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                System.out.println(promptText);
                userInput = Integer.parseInt(scanner.nextLine());
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        }
        return userInput;
    }

    // Method to validate user input row letter.
    // This validation will prevent throwing IllegalStateException at converting row letter to number.
    public static String getValidRow(String promptText) {
        Scanner scanner = new Scanner(System.in);
        String[] validInputs = {"A", "B", "C", "D"};

        String userInput = "";

        while (true) {
            System.out.println(promptText);
            userInput = scanner.nextLine().toUpperCase();

            boolean isValid = false;
            for (String validInput : validInputs) {
                if (userInput.equals(validInput)) {
                    isValid = true;
                    break;
                }
            }
            if (isValid) {
                break;
            } else {
                System.out.println("Error: Please enter one of the following options: A, B, C, D");
            }
        }
        return userInput;
    }
}
