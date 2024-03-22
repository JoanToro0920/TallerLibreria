import java.util.ArrayList;
import java.util.List;

public class Library {
    private ArrayList<Book> books = null;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book newbook) {
        Book book = getBook(newbook.getId());
        if (book == null) {
            books.add(newbook);
        }

        

    }

    public Book getBook(int anId) {
        for (Book book : books) {
            if (book.getId() == anId)
                return book;
        }
        return null;
    }

    public boolean removeBookbyId(int anId) {
        Book book = getBook(anId);
        return books.remove(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }
    public boolean returnBook(int id) {
        Book book = getBook(id);
        if (book != null && book.isBorrowed()) {
            book.setBorrowed(false); 
            return true;
        }
        return false;
    }

}
