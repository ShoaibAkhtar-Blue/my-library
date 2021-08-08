package blue.project.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private ImageView bookLogo;
    private Button btnCurrentlyReading, btnWantToRead, btnAlreadyRead, btnAddToFavorite;
    private TextView bookName, authorName, noOfPages, shortDescription, longDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);


        String longDes = "This handout is a traditional introduction to any language features. You might not be able to comprehend some of the features fully at this stage but do nott worry you will get to know about these as we move on with the course." +
                "\nThis handout is a traditional introduction to any language features. You might not be able to comprehend some of the features fully at this stage but do nott worry you will get to know about these as we move on with the course.";

        Book defaultBook = new Book(1, "CS506", "Umair Javed", 471, "https://image.jimcdn.com/app/cms/image/transf/none/path/s209dea8884e066e7/image/i3b511e2ec6437098/version/1573473565/web-design-development-company-new-york.jpg", "Web Design and Development by using Java", longDes);


        initViews();

        //TODO: set data to views received from recycler view
        Intent intent = getIntent();
        if (intent != null) {
            int bookId = intent.getIntExtra(BooksRecViewAdapter.BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book book = Utils.getInstance().getBookById(bookId);
                if (book != null) {
                    setData(book);
                    handleCurrentlyReading(book);
                    handleWantToRead(book);
                    handleAlreadyRead(book);
                    handleFavorites(book);
                }
            }
        }
    }

    /**
     * This method is used to initialize the views.
     */
    private void initViews() {
        bookLogo = findViewById(R.id.image_book_logo);

        btnCurrentlyReading = findViewById(R.id.button_add_to_currently_reading);
        btnWantToRead = findViewById(R.id.button_add_to_want_to_read);
        btnAlreadyRead = findViewById(R.id.button_add_to_already_read);
        btnAddToFavorite = findViewById(R.id.button_add_to_favorite);

        bookName = findViewById(R.id.textView_bookName);
        authorName = findViewById(R.id.textView_author);
        noOfPages = findViewById(R.id.textView_no_of_pages);
        shortDescription = findViewById(R.id.textView_short_des);
        longDescription = findViewById(R.id.textView_long_des);
    }

    private void setData(Book book) {
        Glide.with(this).asBitmap().load(book.getImageUrl()).into(bookLogo);
        bookName.setText(book.getName());
        authorName.setText(book.getAuthor());
        noOfPages.setText(String.valueOf(book.getPages()));
        shortDescription.setText(book.getShortDescription());
        longDescription.setText(book.getLongDescription());
    }

    /**
     * This menthod is used to enable or disable the Already Read button.
     * @param book
     */
    private void handleAlreadyRead(Book book) {
        Utils.getInstance();
        ArrayList<Book> books = Utils.getAlreadyReadBooks();
        boolean existInAlreadyRead = false;
        for (Book b : books) {
            if (b.getId() == book.getId()) {
                existInAlreadyRead = true;
                break;
            }
        }
        if (existInAlreadyRead) {
            btnAlreadyRead.setEnabled(false);   //disabling the button
        } else {
            btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToAlreadyReadBooks(book)) {  //adding book in already read books
                        Toast.makeText(BookActivity.this, "Book successfully added..!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Error...!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * This method is used to enable or disable the Want to Read button.
     * @param book
     */
    private void handleWantToRead(Book book) {
        ArrayList<Book> books = Utils.getWantToReadBooks();
        boolean existInWantToReadBooks = false;
        for (Book b: books) {
            if (b.getId() == book.getId()) {
                existInWantToReadBooks = true;
                break;
            }
        }
        if (existInWantToReadBooks) {
            btnWantToRead.setEnabled(false);    //disabling the button
        } else {
            btnWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToWantToReadBooks(book)) {  //adding book to Want to Read Books
                        Toast.makeText(BookActivity.this, "Book successfully added..!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Error..!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * This method is used to enable or disable the Currently Reading button
     * @param book
     */
    private void handleCurrentlyReading(Book book) {
        ArrayList<Book> books = Utils.getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;
        for (Book b: books) {
            if (b.getId() == book.getId()) {
                existInCurrentlyReadingBooks = true;
                break;
            }
        }
        if (existInCurrentlyReadingBooks) {
            btnCurrentlyReading.setEnabled(false);
        } else {
            btnCurrentlyReading.setOnClickListener(view -> {
                if (Utils.getInstance().addToCurrentlyReadingBooks(book)) { //add book to currently reading books
                    Toast.makeText(BookActivity.this, "Book successfully added..!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, CurrentlyReadingBookActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(BookActivity.this, "Error..!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * This method is used to enable or disable Add to Favorite button
     * @param book
     */
    private void handleFavorites(Book book) {
        ArrayList<Book> books = Utils.getFavoriteBooks();
        boolean existInFavoriteBooks = false;
        for (Book b: books) {
            if (b.getId() == book.getId()) {
                existInFavoriteBooks = true;
                break;
            }
        }
        if (existInFavoriteBooks) {
            btnAddToFavorite.setEnabled(false);
        } else {
            btnAddToFavorite.setOnClickListener(view -> {
                if (Utils.getInstance().addToFavoriteBooks(book)) {
                    Toast.makeText(BookActivity.this, "Book successfully added..!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, FavoriteBookActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(BookActivity.this, "Error..!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}