package bookstore;

import java.util.Scanner;

public class Book {
    private static int numberOfCreatedBooks = 0;

    private String title;
    private String author;
    private long ISBN;
    private double price;

    // Constructor
    public Book(String title, String author, long ISBN, double price) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.price = price;
        numberOfCreatedBooks++;
    }

    // Getter methods
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

    // Setter methods
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

    // Override equals() method for object comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book otherBook = (Book) obj;
        return ISBN == otherBook.ISBN && Double.compare(otherBook.price, price) == 0;
    }

    // Override toString() method for displaying Book information
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN=" + ISBN +
                ", price=" + price +
                '}';
    }

    public static void main(String[] args) {
        // Example usage
        BookstoreManager.runBookstoreManager();
    }
}