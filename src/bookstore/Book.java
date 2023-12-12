package bookstore;

import java.util.Scanner;

// Book class represents a book with various properties
public class Book {
    // Static variable to keep track of the number of created Book objects
    private static int numberOfCreatedBooks = 0;

    // Instance variables representing the properties of a book
    private String title;
    private String author;
    private long ISBN;
    private double price;

    // Constructor to initialize a Book object with specified values
    public Book(String title, String author, long ISBN, double price) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.price = price;
        numberOfCreatedBooks++; // Increment the count of created books
    }

    // Getter methods to access the private instance variables
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public long getISBN() {
        return ISBN;
    }

    public double getPrice() {
        return price;
    }

    // Setter methods to modify the values of the private instance variables
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Static method to find the number of created Book objects
    public static int findNumberOfCreatedBooks() {
        return numberOfCreatedBooks;
    }

    // Override equals() method for object comparison based on ISBN and price
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book otherBook = (Book) obj;
        return ISBN == otherBook.ISBN && Double.compare(otherBook.price, price) == 0;
    }

    // Override toString() method for a string representation of the Book object
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN=" + ISBN +
                ", price=" + price +
                '}';
    }

    // Main method for example usage (instantiates a BookstoreManager and runs it)
    public static void main(String[] args) {
        BookstoreManager.runBookstoreManager();
    }
}
