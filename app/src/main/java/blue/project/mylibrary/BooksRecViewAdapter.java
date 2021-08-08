package blue.project.mylibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class BooksRecViewAdapter extends RecyclerView.Adapter<BooksRecViewAdapter.ViewHolder> {
    public static final String BOOK_ID_KEY = "bookID";
    public static final String ALL_BOOKS_ACTIVITY = "allBooksActivity";
    public static final String CURRENTLY_READING_BOOK_ACTIVITY = "currentlyReadingBookActivity";
    public static final String ALREADY_READ_BOOK_ACTIVITY = "alreadyReadBookActivity";
    public static final String WANT_TO_READ_BOOK_ACTIVITY = "wantToReadBookActivity";
    public static final String FAVORITE_BOOK_ACTIVITY = "favoriteBookActivity";

    private final String TAG = "BooksRecViewAdapter";
    private String parentActivity;
    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;

    public BooksRecViewAdapter(String parentActivity, Context mContext) {
        this.parentActivity = parentActivity;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.textViewBookName.setText(books.get(position).getName());
        holder.textViewAuthorName.setText(books.get(position).getAuthor());
        Glide.with(mContext).asBitmap().load(books.get(position).getImageUrl()).into(holder.imageBook);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
                mContext.startActivity(intent);
            }
        });

        if (books.get(position).getExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.btnDownArrow.setVisibility(View.GONE);
            holder.shortDescription.setText(books.get(position).getShortDescription());

            switch (parentActivity) {
                case ALL_BOOKS_ACTIVITY:
                    holder.btnDelete.setVisibility(View.GONE);
                    break;
                case CURRENTLY_READING_BOOK_ACTIVITY:
                    holder.btnDelete.setVisibility(View.VISIBLE);
                    holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Book b = books.get(position);
                            String bookName = b.getName();

                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("Do you want to delete " + bookName + " from list?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (Utils.getInstance().removeFromCurrentlyReadingBooks(books.get(position))) {
                                        Toast.makeText(mContext, bookName + " removed..!", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(mContext, "Error..!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //do nothing
                                }
                            });
                            builder.create().show();
                        }
                    });
                    break;
                case ALREADY_READ_BOOK_ACTIVITY:
                    holder.btnDelete.setVisibility(View.VISIBLE);
                    holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Book b = books.get(position);
                            String bookName = b.getName();
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("Do you wanr to delete " + bookName + " from list?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (Utils.getInstance().removeFromAlreadyReadBooks(books.get(position))) {
                                        Toast.makeText(mContext, bookName + " removed..!", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(mContext, "Error..!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //do nothing
                                }
                            });
                            builder.create().show();
                        }
                    });
                    break;
                case WANT_TO_READ_BOOK_ACTIVITY:
                    holder.btnDelete.setVisibility(View.VISIBLE);
                    holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Book b = books.get(position);
                            String bookName = b.getName();
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("Do you want to delete " + bookName + " from list?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (Utils.getInstance().removeFromWantToReadBooks(books.get(position))) {
                                        Toast.makeText(mContext, bookName + " removed..!", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(mContext, "Error..!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //do nothing
                                }
                            });
                            builder.create().show();
                        }
                    });
                    break;
                case FAVORITE_BOOK_ACTIVITY:
                    holder.btnDelete.setVisibility(View.VISIBLE);
                    holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Book b = books.get(position);
                            String bookName = b.getName();
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("Do you want to delete " + bookName + " from list?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (Utils.getInstance().removeFromFavoriteBooks(books.get(position))) {
                                        Toast.makeText(mContext, bookName + " removed..!", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(mContext, "Error..!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //do nothing
                                }
                            });
                            builder.create().show();
                        }
                    });
                    break;
                default:
                    break;
            }
        } else {
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.btnDownArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialCardView parent;
        private RelativeLayout expandedRelLayout;
        private ImageView imageBook, btnUpArrow, btnDownArrow;
        private TextView textViewBookName, textViewAuthorName, shortDescription, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            imageBook = itemView.findViewById(R.id.image_book);
            textViewBookName = itemView.findViewById(R.id.textView_book_name);
            textViewAuthorName = itemView.findViewById(R.id.textView_author_name);
            shortDescription = itemView.findViewById(R.id.textView_short_description_label);
            expandedRelLayout = itemView.findViewById(R.id.expanded_rel_layout);
            btnUpArrow = itemView.findViewById(R.id.btn_up_arrow);
            btnDownArrow = itemView.findViewById(R.id.btn_down_arrow);
            btnDelete = itemView.findViewById(R.id.btn_delete);

            btnDownArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.getExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            btnUpArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.getExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
