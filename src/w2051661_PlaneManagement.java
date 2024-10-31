import java.util.*;
public class w2051661_PlaneManagement {
    private static int[][] seatAvailability;
    private static double[][] seatPrices;
    private static Ticket[][] ticketBookingInfo;

    public w2051661_PlaneManagement() {

        // Initialize seat availability array
        seatAvailability = new int[4][];
        seatAvailability[0] = new int[14];
        seatAvailability[1] = new int[12];
        seatAvailability[2] = new int[12];
        seatAvailability[3] = new int[14];

        // Initialize seat prices array
        seatPrices = new double[4][];
        seatPrices[0] = new double[14];
        seatPrices[1] = new double[12];
        seatPrices[2] = new double[12];
        seatPrices[3] = new double[14];

        // Initialize ticket booking array
        ticketBookingInfo = new Ticket[4][];
        ticketBookingInfo[0] = new Ticket[14];
        ticketBookingInfo[1] = new Ticket[12];
        ticketBookingInfo[2] = new Ticket[12];
        ticketBookingInfo[3] = new Ticket[14];

        // Initialize all seats as available (0) and set prices
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < seatAvailability[row].length; col++) {
                seatAvailability[row][col] = 0;
                // Set prices based on rows and columns
                if (col < 5) {
                    seatPrices[row][col] = 200.0; // Price for 1 to 5 columns
                } else if (col < 9) {
                    seatPrices[row][col] = 150.0; // Price for 6 to 9 columns
                } else {
                    seatPrices[row][col] = 180.0; // Price for 10 to 14 columns
                }
            }
        }
    }

    public void buy_seat(int row, int col,String name, String surname, String email) {
        if (row >= 0 && row < 4 && col >= 0 && col < seatAvailability[row].length) {
            if (seatAvailability[row][col] == 0) {
                seatAvailability[row][col] = 1;
                double price = seatPrices[row][col];
                System.out.println("Success seat booking.");
                Person bookPerson = new Person(name,surname,email);
                Ticket bookTicket = new Ticket(row,col + 1,price,bookPerson);
                // Here col + 1 to adjust column number to real seat number.
                ticketBookingInfo[row][col] = bookTicket;
                bookTicket.save();
            } else {
                System.out.println("Fail seat booking.");
            }
        } else {
            System.out.println("Invalid user input. Seat does not exist.");
        }
    }

    public void cancel_seat(int row, int col) {
        if (row >= 0 && row < 4 && col >= 0 && col < seatAvailability[row].length) {
            if (seatAvailability[row][col] == 1) {
                Ticket cancleTicket = ticketBookingInfo[row][col];
                cancleTicket.delete();
                seatAvailability[row][col] = 0;
                ticketBookingInfo[row][col] = null;
                System.out.println("Success: Seat cancelled.");
            } else {
                System.out.println("This seat is already not booked.");
            }
        } else {
            System.out.println("Invalid user input. Seat does not exist.");
        }
    }

    public void find_first_available() {
        boolean found_available_seat = false;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < seatAvailability[row].length; col++) {
                if (seatAvailability[row][col] == 0) {
                    String display_row = switch (row) {
                        case 0 -> "A";
                        case 1 -> "B";
                        case 2 -> "C";
                        case 3 -> "D";
                        default -> throw new IllegalStateException("Unexpected value: " + row);
                    };
                    System.out.println("Available First Seat: " +display_row+(col+1));
                    found_available_seat = true;
                    break;
                }
            }
            if (found_available_seat) {
                break;
            }
        }
        if (!found_available_seat) {
            System.out.println("No seat available.");
        }
    }

    public void show_seating_plan() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < seatAvailability[row].length; col++) {
                System.out.print((seatAvailability[row][col] == 0 ? "O" : "X"));
            }
            System.out.println();
        }
    }

    public void print_ticket_info() {
        double totalPrice = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < ticketBookingInfo[row].length; col++) {
                if (ticketBookingInfo[row][col] != null) {
                    Ticket ticketInfo = ticketBookingInfo[row][col];
                    totalPrice += ticketInfo.getPrice();
                    ticketInfo.printInfo();
                    System.out.println();
                }
            }
        }
        System.out.println("Total sales: " + totalPrice);
    }

    public void search_ticket(int row, int col) {
        if (row >= 0 && row < 4 && col >= 0 && col < seatAvailability[row].length) {
            if (seatAvailability[row][col] == 1) {
                Ticket ticketInfo = ticketBookingInfo[row][col];
                ticketInfo.printInfo();
            } else {
                System.out.println("This seat is available.");
            }
        } else {
            System.out.println("Invalid user input. Seat does not exist.");
        }
    }

    public static void main(String[] args) {
        w2051661_PlaneManagement airplane = new w2051661_PlaneManagement();

        label:
        while (true) {
            System.out.println("\nWelcome to the Plane Management application");
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n*******************************************************" +
                    "\n*\t\t\t\t\tMENU OPTIONS\t\t\t\t\t" + "  " + "*" +
                    "\n*******************************************************" +
                    "\n\t1. Buy a seat." +
                    "\n\t2. Cancel a seat." +
                    "\n\t3. Find first available seat." +
                    "\n\t4. Show seating plan." +
                    "\n\t5. Print tickets information and total sales." +
                    "\n\t6. Search ticket." +
                    "\n\t0. Quit." +
                    "\n*******************************************************");
            System.out.println("Please select an option : ");
            String option = scanner.next();

            switch (option) {
                case "1": {
                    System.out.println("What is your name? ");
                    String userName = scanner.next();
                    System.out.println("What is your surname? ");
                    String userSurname = scanner.next();
                    System.out.println("What is your email address? ");
                    String userEmailAddress = scanner.next();
                    String rowLetter = InputValidator.getValidRow("Input a row letter : ");
                    int rowNumber = switch (rowLetter) {
                        case "A" -> 0;
                        case "B" -> 1;
                        case "C" -> 2;
                        case "D" -> 3;
                        default -> throw new IllegalStateException("Unexpected value: " + rowLetter);
                    };
                    int seat_number = InputValidator.getIntegerInput("Input a seat number : ");

                    airplane.buy_seat(rowNumber, seat_number - 1, userName, userSurname, userEmailAddress);

                    break;
                }
                case "2": {
                    String row_letter = InputValidator.getValidRow("Input a row letter : ");
                    int row_number = switch (row_letter) {
                        case "A" -> 0;
                        case "B" -> 1;
                        case "C" -> 2;
                        case "D" -> 3;
                        default -> throw new IllegalStateException("Unexpected value: " + row_letter);
                    };
                    int seat_number = InputValidator.getIntegerInput("Input a seat number : ");
                    airplane.cancel_seat(row_number, seat_number - 1);

                    break;
                }
                case "3":
                    airplane.find_first_available();

                    break;
                case "4":
                    airplane.show_seating_plan();

                    break;
                case "5":
                    airplane.print_ticket_info();

                    break;
                case "6": {
                    String rowLetter = InputValidator.getValidRow("Input a row letter : ");
                    int rowNumber = switch (rowLetter) {
                        case "A" -> 0;
                        case "B" -> 1;
                        case "C" -> 2;
                        case "D" -> 3;
                        default -> throw new IllegalStateException("Unexpected value: " + rowLetter);
                    };
                    int seat_number = InputValidator.getIntegerInput("Input a seat number : ");

                    airplane.search_ticket(rowNumber, seat_number - 1);

                    break;
                }
                case "0":
                    break label;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
