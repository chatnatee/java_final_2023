package bookstore;

import java.util.Scanner;

// BookstoreManager class handles the management of a bookstore system
class BookstoreManager {
    // Constants for authentication
    private static final String PASSWORD = "pargol";
    private static final int MAX_ATTEMPTS = 3;
    private static final int MAX_FAILED_ATTEMPTS = 12;

    // Method to run the BookstoreManager
    public static void runBookstoreManager() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bookstore Management System!");

        // Part I: Prompt for the maximum number of books
        System.out.print("Enter the maximum number of books the bookstore can contain: ");
        int maxBooks = scanner.nextInt();
        Book[] inventory = new Book[maxBooks];

        int failedAttempts = 0;

        // Part II: Display main menu
        while (true) {
            System.out.println("\nMain Menu:");
            // Display menu options
            System.out.println("1. Enter new books (password required)");
            System.out.println("2. Change book information (password required)");
            System.out.println("3. Display all books by a specific author");
            System.out.println("4. Display all books under a certain price");
            System.out.println("5. Quit");
            System.out.println("6. Display all books");
            System.out.print("Please enter your choice > ");

            // Get user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Handle user choice using a switch statement
            switch (choice) {
                case 1:
                    // Option 1: Enter new books
                    if (!authenticateUser(scanner)) {
                        failedAttempts++;
                        handleFailedAttempts(failedAttempts);
                        break;
                    }
                    addBooksToInventory(inventory, maxBooks);
                    break;

                case 2:
                    // Option 2: Change information of a book
                    if (!authenticateUser(scanner)) {
                        failedAttempts++;
                        handleFailedAttempts(failedAttempts);
                        break;
                    }
                    updateBookInformation(inventory);
                    break;

                case 3:
                    // Option 3: Display all books by a specific author
                    displayBooksByAuthor(inventory);
                    break;

                case 4:
                    // Option 4: Display all books under a certain price
                    displayBooksUnderPrice(inventory);
                    break;

                case 5:
                    // Option 5: Quit
                    System.out.println("Closing the program. Goodbye!");
                    System.exit(0);

                case 6:
                    // Option 6: Display all books
                    displayAllBooks(inventory);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    // Method to authenticate the user with a password
    private static boolean authenticateUser(Scanner scanner) {
        System.out.print("Enter your password: ");
        String enteredPassword = scanner.nextLine();
        return enteredPassword.equals(PASSWORD);
    }

    // Method to handle multiple failed password attempts
    private static void handleFailedAttempts(int failedAttempts) {
        if (failedAttempts % MAX_ATTEMPTS == 0) {
            System.out.println("Program detected suspicious activities and will terminate immediately!");
            System.exit(0);
        } else {
            System.out.println("Incorrect password. Please try again.");
        }
    }

    // Method to add books to the inventory
    private static void addBooksToInventory(Book[] inventory, int maxBooks) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of books to add: ");
        int numBooksToAdd = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Check if there is enough space for additional books
        if (numBooksToAdd > (maxBooks - Book.findNumberOfCreatedBooks())) {
            System.out.println("Not enough space for additional books. Remaining spaces: " +
                    (maxBooks - Book.findNumberOfCreatedBooks()));
            return;
        }

        // Loop to input details for each book
        for (int i = 0; i < numBooksToAdd; i++) {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();

            System.out.print("Enter author name: ");
            String author = scanner.nextLine();

            System.out.print("Enter ISBN: ");
            long ISBN = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter price: $");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            Book newBook = new Book(title, author, ISBN, price);
            inventory[Book.findNumberOfCreatedBooks() - 1] = newBook;
            System.out.println("Book added successfully!");
        }
    }

    // Method to update information of a book in the inventory
    private static void updateBookInformation(Book[] inventory) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the book number to update: ");
        int bookNumberToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Check if the book number is valid
        if (bookNumberToUpdate < 0 || bookNumberToUpdate >= Book.findNumberOfCreatedBooks()
                || inventory[bookNumberToUpdate] == null) {
            System.out.println("Invalid book number or no book found at the specified index.");
            return;
        }

        // Display current book information
        displayBookInformation(inventory[bookNumberToUpdate]);

        int updateChoice;
        do {
            // Display options for updating information
            System.out.println("\nWhat information would you like to change?");
            System.out.println("1. Author");
            System.out.println("2. Title");
            System.out.println("3. ISBN");
            System.out.println("4. Price");
            System.out.println("5. Quit");
            System.out.print("Enter your choice > ");

            // Get user choice for updating
            updateChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Switch statement to handle different update choices
            switch (updateChoice) {
                case 1:
                    // Update author
                    System.out.print("Enter new author name: ");
                    String newAuthor = scanner.nextLine();
                    inventory[bookNumberToUpdate].setAuthor(newAuthor);
                    break;
                case 2:
                    // Update title
                    System.out.print("Enter new book title: ");
                    String newTitle = scanner.nextLine();
                    inventory[bookNumberToUpdate].setTitle(newTitle);
                    break;
                case 3:
                    // Update ISBN
                    System.out.print("Enter new ISBN: ");
                    long newISBN = scanner.nextLong();
                    scanner.nextLine(); // Consume newline
                    inventory[bookNumberToUpdate].setISBN(newISBN);
                    break;
                case 4:
                    // Update price
                    System.out.print("Enter new price: $");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    inventory[bookNumberToUpdate].setPrice(newPrice);
                    break;
                case 5:
                    // Quit updating
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }

            // If the user chose to update and not quit, display updated information
            if (updateChoice != 5) {
                displayBookInformation(inventory[bookNumberToUpdate]);
            }
        } while (updateChoice != 5);
    }

    // Method to display information of a book
    private static void displayBookInformation(Book book) {
        System.out.println("\nBook #" + (book.getISBN() % 1000)); // Just an example, you can customize the format
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Title: " + book.getTitle());
        System.out.println("ISBN: " + book.getISBN());
        System.out.println("Price: $" + book.getPrice());
    }

    // Method to display books by a specific author
    private static void displayBooksByAuthor(Book[] inventory) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the author name: ");
        String authorToSearch = scanner.nextLine();

        // Loop through the inventory and display books by the specified author
        for (Book book : inventory) {
            if (book != null && book.getAuthor().equalsIgnoreCase(authorToSearch)) {
                displayBookInformation(book);
            }
        }
    }

    // Method to display books under a certain price
    private static void displayBooksUnderPrice(Book[] inventory) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the maximum price: $");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        // Loop through the inventory and display books under the specified price
        for (Book book : inventory) {
            if (book != null && book.getPrice() < maxPrice) {
                displayBookInformation(book);
            }
        }
    }

    // Method to display information for all books in the inventory
    private static void displayAllBooks(Book[] inventory) {
        System.out.println("\nAll Books in Inventory:");
        for (Book book : inventory) {
            if (book != null) {
                displayBookInformation(book);
                System.out.println("-----------------------------");
            }
        }
    }
}
