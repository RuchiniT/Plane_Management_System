public class Person {
    private String name;
    private String surname;
    private String email;

    // Constructor
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method to print information of Person
    public void printInfo() {
        System.out.println("\tName - " + name);
        System.out.println("\tSurname - " + surname);
        System.out.println("\tEmail - " + email);
    }
}