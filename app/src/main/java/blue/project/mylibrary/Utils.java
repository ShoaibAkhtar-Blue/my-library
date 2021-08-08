package blue.project.mylibrary;

import java.util.ArrayList;

public class Utils {
    private static Utils instance;
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> favoriteBooks;

    private Utils() {
        if (null == allBooks) {
            allBooks = new ArrayList<>();
            initData();
        }

        if (null == currentlyReadingBooks) {
            currentlyReadingBooks = new ArrayList<>();
        }

        if (null == alreadyReadBooks) {
            alreadyReadBooks = new ArrayList<>();
        }

        if (null == wantToReadBooks) {
            wantToReadBooks = new ArrayList<>();
        }

        if (null == favoriteBooks) {
            favoriteBooks = new ArrayList<>();
        }
    }

    public static synchronized Utils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    private void initData() {
        //TODO: add initial data

        allBooks.add(new Book(1, "CS506", "Umair Javed", 471, "https://image.jimcdn.com/app/cms/image/transf/none/path/s209dea8884e066e7/image/i3b511e2ec6437098/version/1573473565/web-design-development-company-new-york.jpg", "Web Design and Development by using Java", "Long Description"));
        allBooks.add(new Book(2, "AI101", "Dr. Zafar Alvi", 500, "https://static.businessworld.in/article/article_extra_large_image/1588319167_Cp7vRv_Artificial_Intelligence.png", "Artificial Intelligence", "Long Description"));
        allBooks.add(new Book(3, "The Myth of Sisyphus", "Albert Camus", 250, "https://miro.medium.com/max/500/1*DDsOx6D3oe8ZxcA-OTfIDA.jpeg", "The Myth of Sisyphus is a 1942 philosophical essay by Albert Camus. The English translation by Justin O'Brien was first published in 1955.", "Long Description"));
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public Book getBookById(int bookID) {
        for (Book b :
                allBooks) {
            if (b.getId() == bookID) {
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadyReadBooks(Book book) {
        for (Book b: alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                return true;
            }
        }
        return alreadyReadBooks.add(book);
    }

    public boolean removeFromAlreadyReadBooks(Book book) {
        for (Book b: alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                return alreadyReadBooks.remove(book);
            }
        }
        return false;
    }

    public boolean addToWantToReadBooks(Book book) {
        for (Book b: wantToReadBooks) {
            if (b.getId() == book.getId()) {
                return true;
            }
        }
        return wantToReadBooks.add(book);
    }

    public boolean removeFromWantToReadBooks(Book book) {
        for (Book b: wantToReadBooks) {
            if (b.getId() == book.getId()) {
                return wantToReadBooks.remove(book);
            }
        }
        return false;
    }

    public boolean addToCurrentlyReadingBooks(Book book) {
        for (Book b: currentlyReadingBooks) {
            if (b.getId() == book.getId()) {
                return true;
            }
        }
        return currentlyReadingBooks.add(book);
    }

    public boolean removeFromCurrentlyReadingBooks(Book book) {
        for (Book b: currentlyReadingBooks) {
            if (b.getId() == book.getId()) {
                return currentlyReadingBooks.remove(book);
            }
        }
        return false;
    }

    public boolean addToFavoriteBooks(Book book) {
        for (Book b: favoriteBooks) {
            if (b.getId() == book.getId()) {
                return true;
            }
        }
        return favoriteBooks.add(book);
    }

    public boolean removeFromFavoriteBooks(Book book) {
        for (Book b: favoriteBooks) {
            if (b.getId() == book.getId()) {
                return favoriteBooks.remove(book);
            }
        }
        return false;
    }
}
