import java.time.Year;

/** Domain model for a book in the inventory. */
public class Book {
    private final int id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;

    public Book(int id, String title, String author, String isbn, int publicationYear) {
        if (id <= 0) throw new IllegalArgumentException("Book ID must be positive.");
        this.id = id;
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);
        setPublicationYear(publicationYear);
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }

    public void setTitle(String value) { title = required(value, "Title"); }
    public void setAuthor(String value) { author = required(value, "Author"); }
    public void setIsbn(String value) { isbn = required(value, "ISBN"); }

    public void setPublicationYear(int value) {
        int currentYear = Year.now().getValue();
        if (value < 1 || value > currentYear) {
            throw new IllegalArgumentException("Publication year must be between 1 and " + currentYear + ".");
        }
        publicationYear = value;
    }

    private String required(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be empty.");
        }
        return value.trim();
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Title: " + title + " | Author: " + author
            + " | ISBN: " + isbn + " | Year: " + publicationYear;
    }
}
