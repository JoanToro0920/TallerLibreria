class Book {
    private String title="";
    private String author="";
    private int publicationYear=0;
    private int numberOfPages=0;
    private int id=0;
    private boolean borrowed = false;
    

    public Book  (int id, String title, String author, int publicationYear, int numberOfPages) {
        
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
        this.id=id;
        
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
    public int getId() {
        return id;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isBorrowed() { 
        return borrowed;
    }
    public void setBorrowed(boolean borrowed) { 
        this.borrowed = borrowed;
    }
    
    
}