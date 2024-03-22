import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private final String FILE_PATH = "data/Books.csv";
    Library library = new Library();
    private CSVCoder<Book> booksCoder = null;

    public Main() {
        booksCoder = new CSVCoder<Book>(';') {
            @Override
            public String[] encode(Book book) {
                return new String[] {
                        String.valueOf(book.getId()),
                        book.getTitle(),
                        book.getAuthor(),
                        String.valueOf(book.getPublicationYear()),
                        String.valueOf(book.getNumberOfPages()),

                };
            };

            @Override
            public Book decode(String[] values) {
                var id = Integer.parseInt(values[0]);
                var title = values[1];
                var author = values[2];
                var publicationYear = Integer.parseInt(values[3]);
                var numberOfPages = Integer.parseInt(values[4]);

                return new Book(id, title, author, publicationYear, numberOfPages);
            }
        };

    }

    public void loadInfo() {
        List<Book> books = new ArrayList<>();
        try {
            booksCoder.readFromFile("data/Books.csv", books);
            library = new Library();
            for (Book book : books) {
                library.addBook(book);
            }
            Console.writeLine();
        } catch (IOException e) {
            Console.writeLine("Error al intentar leer el archivo");
        }
    }

    public void saveInfo() {
        try {
            booksCoder.writeToFile(FILE_PATH, library.getAllBooks());
        } catch (IOException e) {
            Console.writeLine("Error al intentar guardar el archivo");
        }
    }

    public void showMenu() {
        String option = "";
        do {
            Console.writeLine("Bienvenido a la Biblioteca, que desea hacer?...");
            Console.writeLine("1. Añadir un libro");
            Console.writeLine("2. prestar un libro");
            Console.writeLine("3. devolver un libro");
            Console.writeLine("4. Eliminar un libro");
            Console.writeLine("5. Mostrar libros disponibles");
            Console.writeLine("6. Cargar informacion");
            Console.writeLine("7. Guardar Informacion");
            Console.writeLine("9. Salir");
            Console.writeLine("Ingresa la opcion: ");

            option = Console.readLine();
            switch (option) {
                case "1":
                    var id = 0;
                    var title = "";
                    var author = "";
                    var book = (Book) null;
                    var publicationYear = 0;
                    var numberOfPages = 0;
                    Console.writeLine("Ingrese el id del libro");
                    id = Integer.parseInt(Console.readLine());
                    if (library.getBook(id) != null) {
                        Console.writeLine("Error el id %d ya existe", id);
                    } else {
                        Console.writeLine("Ingrese el titulo del libro");
                        title = Console.readLine();
                        Console.writeLine("Ingrese el autor del libro");
                        author = Console.readLine();
                        Console.writeLine("Ingrese el año de publicacion del libro");
                        publicationYear = Integer.parseInt(Console.readLine());
                        Console.writeLine("Ingrese el numero de paginas del libro");
                        numberOfPages = Integer.parseInt(Console.readLine());

                        book = new Book(id, title, author, publicationYear, numberOfPages);
                        library.addBook(book);
                        Console.writeLine("El libro ha sido adicionado correctamente");
                    }

                    break;
                case "2":
                    Console.writeLine("Ingrese El ID del Libro que va a pedir prestado");
                    int borrowId = Integer.parseInt(Console.readLine());
                    Book borrowedBook = library.getBook(borrowId);
                    if (borrowedBook != null) {
                        if (!borrowedBook.isBorrowed()) { // Verifica si el libro no está prestado
                            borrowedBook.setBorrowed(true); // Marca el libro como prestado
                            Console.writeLine("El libro ha sido prestado");
                        } else {
                            Console.writeLine("El libro con ID " + borrowId + " actualmente no lo tenemos ya que ha sido prestado antes.");
                        }
                    } else {
                        Console.writeLine("El libro con ID " + borrowId + " no está disponible en la biblioteca.");
                    }
                    break;
                case "3":

                    Console.writeLine("Ingrese El ID del Libro que va a regresar");
                    int returnId = Integer.parseInt(Console.readLine());
                    boolean returned = library.returnBook(returnId);
                    if (returned) {
                        Console.writeLine("Libro devuelto");
                    } else {
                        Console.writeLine("No se pudo devolver el libro. ");
                    }
                    break;

                case "4":
                    Console.writeLine("Ingrese el ID del libro que desea eliminar:");
                    int deleteId = Integer.parseInt(Console.readLine());
                    boolean deleted = library.removeBookbyId(deleteId);
                    if (deleted) {
                        Console.writeLine("El libro con ID " + deleteId + " fue borrado.");
                    } else {
                        Console.writeLine("No se pudo eliminar el libro con ID " + deleteId + " Por favor verifique si el libro existe");
                    }
                    break;

                case "5":
                    Console.writeLine(" CARGANDO LIBROS DISPONIBLES...");

                    List<Book> availableBooks = library.getAllBooks();
                    if (availableBooks.isEmpty()) {
                        Console.writeLine("No hay ningun libro en la biblioteca.");
                    } else {
                        Console.writeLine("Lista de libros :");
                        int index = 1;
                        for (Book availableBook : availableBooks) {
                            if (!availableBook.isBorrowed()) {
                                Console.writeLine("Libro #" + index + ":" + ", Título: " + availableBook.getTitle()
                                        + ", Año de publicación: " + availableBook.getPublicationYear()
                                        + ", Número de páginas: " + availableBook.getNumberOfPages() + ", ID: "
                                        + availableBook.getId());
                            }
                            index++;
                        }
                    }
                    break;
                case "6":
                    Console.writeLine("Cargando informacion");
                    loadInfo();
                    break;
                case "7":
                    Console.writeLine("Guardando informacion");
                    saveInfo();
                    break;

            }

        } while (!option.equals("9"));
        if (option.equals("9")) {
            Console.writeLine("Nos vemos, vuelva pronto");
        }

    }

    public static void main(String[] args) {
        new Main().showMenu();
    }

}
