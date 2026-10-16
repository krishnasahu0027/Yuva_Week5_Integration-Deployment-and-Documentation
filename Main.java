import java.util.List;
import java.util.Scanner;

/** Command-line interface for the Library Book Inventory application. */
public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final LibraryManager LIBRARY = new LibraryManager();

    public static void main(String[] args) {
        seedDemoBooks();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1: addBook(); break;
                    case 2: listBooks(); break;
                    case 3: updateBook(); break;
                    case 4: deleteBook(); break;
                    case 5: searchBook(); break;
                    case 0: running = false; System.out.println("Goodbye!"); break;
                    default: System.out.println("Please choose a valid menu option.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("===== LIBRARY BOOK INVENTORY =====");
        System.out.println("1. Add a book");
        System.out.println("2. List all books");
        System.out.println("3. Update book information");
        System.out.println("4. Delete a book");
        System.out.println("5. Search by book ID");
        System.out.println("0. Exit");
    }

    private static void addBook() {
        Book book = LIBRARY.addBook(readText("Title: "), readText("Author: "),
            readText("ISBN: "), readInt("Publication year: "));
        System.out.println("Book added successfully. ID: " + book.getId());
    }

    private static void listBooks() {
        List<Book> books = LIBRARY.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book book : books) System.out.println(book);
        System.out.println("Total books: " + books.size());
    }

    private static void updateBook() {
        int id = readInt("Book ID to update: ");
        String title = readText("New title: ");
        String author = readText("New author: ");
        String isbn = readText("New ISBN: ");
        int year = readInt("New publication year: ");
        System.out.println(LIBRARY.updateBook(id, title, author, isbn, year)
            ? "Book updated successfully." : "No book found with ID " + id + ".");
    }

    private static void deleteBook() {
        int id = readInt("Book ID to delete: ");
        System.out.println(LIBRARY.deleteBook(id) ? "Book deleted successfully."
            : "No book found with ID " + id + ".");
    }

    private static void searchBook() {
        Book book = LIBRARY.findById(readInt("Book ID to search: "));
        System.out.println(book == null ? "No book found." : book);
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        String value = INPUT.nextLine().trim();
        if (value.isEmpty()) throw new IllegalArgumentException("Input cannot be empty.");
        return value;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(INPUT.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static void seedDemoBooks() {
        LIBRARY.addBook("Atomic Habits", "James Clear", "9780735211292", 2018);
        LIBRARY.addBook("The Alchemist", "Paulo Coelho", "9780061122415", 1988);
        LIBRARY.addBook("Wings of Fire", "A. P. J. Abdul Kalam", "9788173711466", 1999);
    }
}
