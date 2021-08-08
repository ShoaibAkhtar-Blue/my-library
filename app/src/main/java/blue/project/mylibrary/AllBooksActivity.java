package blue.project.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridLayout;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {
    private BooksRecViewAdapter adapter;
    private RecyclerView booksRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        //a theme is defined in style.xml so this method is disabled
        //overridePendingTransition(R.anim.slide_in, R.anim.slide_out);   //applying animation

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //enabling action bar arrow button

        booksRecView = findViewById(R.id.booksRecView);

        adapter = new BooksRecViewAdapter(BooksRecViewAdapter.ALL_BOOKS_ACTIVITY, this);
        adapter.setBooks(Utils.getInstance().getAllBooks());
        booksRecView.setAdapter(adapter);
        //booksRecView.setLayoutManager(new GridLayoutManager(this, 2));
        booksRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * This method is called when activity is finished.
     * This method is disabled because a theme is defined in style.xml
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);   //applying animation
    }
    */

    /**
     * Implementing the functionality of action bar arrow button
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}