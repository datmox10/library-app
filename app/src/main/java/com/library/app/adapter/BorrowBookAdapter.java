package com.library.app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.api.ApiBook;
import com.library.app.model.Book;
import com.library.app.model.BookResponse;
import com.library.app.model.BorrowBookMD;
import com.library.app.model.BorrowBookResponse;
import com.library.app.model.BorrowResponse;
import com.library.app.model.TokenManager;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowBookAdapter extends RecyclerView.Adapter<BorrowBookAdapter.BorrowBookViewHolder> {

    private ArrayList<BorrowBookMD> books;
    private Context context;

//    private TokenManager tokenManager = TokenManager.getInstance(context);

    public BorrowBookAdapter(ArrayList<BorrowBookMD> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public BorrowBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_borrow_book,parent,false);
        return new BorrowBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BorrowBookViewHolder holder, int position) {
        BorrowBookMD book = books.get(position);
        if(book==null){
            return;
        }

//        ArrayList<Book> book1 = null;
//        try {
//            book1 = getBookList();
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Log.d( "onBindViewHolder: ",book1.size()+"");
        holder.txt_ngay_muon.setText(book.getBorrowDate());
        holder.txt_con_lai.setText(book.getOverdue());
        holder.txt_tinh_trang.setText("Đã trả!");
        Log.d("onBindViewHolder1: ",book.getBorrowDate());
//        for(Book book2: book1){
//            if(book.getBookId() == book2.getId()){
//                Picasso.get().load(book2.getImage()).into(holder.img);
//                holder.txt_tensach.setText(book2.getTitle());
//                holder.txt_ngay_muon.setText(book.getBorrowDate());
//                holder.txt_con_lai.setText(book.getOverdue());
//                if(book.getStatus()=="RETURN"){
//                    holder.txt_tinh_trang.setText("Đã trả!");
//                }else{
//                    holder.txt_tinh_trang.setText("Đang mượn!");
//                }
//            }
//        }

    }

    @Override
    public int getItemCount() {
        if(books!=null){
            return books.size();
        }
        return 0;
    }

    public class BorrowBookViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView txt_tensach,txt_ngay_muon,txt_tinh_trang,txt_con_lai;
        private Button btn_return;
        public BorrowBookViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_item_borrow_book);
            txt_tensach = itemView.findViewById(R.id.txt_tensach);
            txt_ngay_muon = itemView.findViewById(R.id.txt_ngay_muon);
            txt_tinh_trang = itemView.findViewById(R.id.txt_tinh_trang);
            txt_con_lai = itemView.findViewById(R.id.txt_con_lai);
            btn_return = itemView.findViewById(R.id.btn_return);
        }
    }

//    private ArrayList<Book> getBookList() throws ExecutionException, InterruptedException{
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        ApiBook apiBook = new ApiBook(tokenManager.getToken());
//        ApiBook.MyApi myApi = apiBook.getRetrofitInstance().create(ApiBook.MyApi.class);
//        Callable<ArrayList<Book>> callable = () -> {
//            Call<BookResponse> call = myApi.getData();
//            try{
//                Response<BookResponse> response = call.execute();
//                if(response.isSuccessful()){
//                    return response.body().getBooksEntityList();
//                }else{
//                    throw new RuntimeException("Request not successful");
//                }
//            }catch (IOException o){
//                throw new RuntimeException("Request not successful",o);
//            }
//
//        };
//        Future<ArrayList<Book>> future = executor.submit(callable);
//        ArrayList<Book> books = future.get();
//        executor.shutdown();
//        return books;
//    }

}
