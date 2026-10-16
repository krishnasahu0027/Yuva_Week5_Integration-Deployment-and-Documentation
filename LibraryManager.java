import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Business operations for the in-memory library inventory. */
public class LibraryManager {
    private final List<Book> books = new ArrayList<Book>();
    private int nextId = 1;

    public Book addBook(String title, String author, String isbn, int year) {
        ensureUniqueIsbn(isbn, -1);
        Book book = new Book(nextId, title, author, isbn, year);
        books.add(book);
        nextId++;
        return book;
    }

    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(new ArrayList<Book>(books));
    }

    public Book findById(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    public Book findByIsbn(String isbn) {
        if (isbn == null) return null;
        String key = isbn.trim();
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(key)) return book;
        }
        return null;
    }

    public boolean updateBook(int id, String title, String author, String isbn, int year) {
        Book existing = findById(id);
        if (existing == null) return false;
        ensureUniqueIsbn(isbn, id);
        Book validated = new Book(id, title, author, isbn, year);
        existing.setTitle(validated.getTitle());
        existing.setAuthor(validated.getAuthor());
        existing.setIsbn(validated.getIsbn());
        existing.setPublicationYear(validated.getPublicationYear());
        return true;
    }

    public boolean deleteBook(int id) {
        Book book = findById(id);
        return book != null && books.remove(book);
    }

    public int getBookCount() { return books.size(); }

    private void ensureUniqueIsbn(String isbn, int excludedId) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty.");
        }
        Book existing = findByIsbn(isbn);
        if (existing != null && existing.getId() != excludedId) {
            throw new IllegalArgumentException("A book with this ISBN already exists.");
        }
    }
}
