package com.library.app.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.activity.MainActivity;
import com.library.app.api.ApiBook;
import com.library.app.model.Book;
import com.library.app.model.BookResponse;
import com.library.app.model.BorrowBookMD;
import com.library.app.model.BorrowResponse;
import com.library.app.model.ReturnBook;
import com.library.app.model.TokenManager;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowAdapter extends RecyclerView.Adapter<BorrowAdapter.BorrowViewHolder> {

    private ArrayList<BorrowBookMD> borrowBookMDS;
    private Context context;

    private TokenManager tokenManager= TokenManager.getInstance(context);

    public BorrowAdapter(ArrayList<BorrowBookMD> borrowBookMDS, Context context) {
        this.borrowBookMDS = borrowBookMDS;
        this.context = context;
    }

    @NonNull
    @Override
    public BorrowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_borrow_book,parent,false);
        return new BorrowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BorrowViewHolder holder, int position) {
        BorrowBookMD borrowBookMD = borrowBookMDS.get(position);
        if (borrowBookMD == null) {
            return;
        }

        try {
            ArrayList<Book> book1 = getBookList();
            Book returnBook = null;
           for(Book book: book1){
                if(book.getId().equals(borrowBookMD.getBookId())){
                    returnBook = book;
                }
           }
            if(returnBook!=null){
                Picasso.get().load(returnBook.getImage()).into(holder.img);
                holder.txt_tensach.setText(returnBook.getTitle());
            }

            holder.txt_ngay_muon.setText(borrowBookMD.getBorrowDate());
            holder.txt_con_lai.setText(borrowBookMD.getDueDate());
            if (borrowBookMD.getStatus().equals("RETURN")) {
                holder.txt_tinh_trang.setText("Đã trả!");

            } else {
                holder.txt_tinh_trang.setText("Đang mượn!");
                holder.btn_return.setVisibility(View.VISIBLE);
            }

            ReturnBook returnBook1 = new ReturnBook();
            returnBook1.setReturnId(borrowBookMD.getId());
            holder.btn_return.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ApiBook apiBook = new ApiBook(tokenManager.getToken());
                    ApiBook.MyApi myApi = apiBook.getRetrofitInstance().create(ApiBook.MyApi.class);
                    Call<BorrowResponse> call = myApi.returnBook(returnBook1);
                    call.enqueue(new Callback<BorrowResponse>() {
                        @Override
                        public void onResponse(Call<BorrowResponse> call, Response<BorrowResponse> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(context, "Trả thành công!", Toast.LENGTH_SHORT).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(MainActivity.class);
                                    }
                                },2000);
                            }else{
                                Log.d( "onResponse: ",response.code()+"");
                            }
                        }

                        @Override
                        public void onFailure(Call<BorrowResponse> call, Throwable throwable) {
                            Log.d( "onResponse: ",throwable.toString());
                        }
                    });
                }
            });

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getItemCount() {
        if(borrowBookMDS!= null){
            return borrowBookMDS.size();
        }
        return 0;
    }

    public class BorrowViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView txt_tensach,txt_ngay_muon,txt_tinh_trang,txt_con_lai;

        private Button btn_return;
        public BorrowViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_item_borrow_book);
            txt_tensach = itemView.findViewById(R.id.txt_tensach);
            txt_ngay_muon = itemView.findViewById(R.id.txt_ngay_muon);
            txt_tinh_trang = itemView.findViewById(R.id.txt_tinh_trang);
            txt_con_lai = itemView.findViewById(R.id.txt_con_lai);
            btn_return = itemView.findViewById(R.id.btn_return);
        }
    }

        private ArrayList<Book> getBookList() throws ExecutionException, InterruptedException{
        ExecutorService executor = Executors.newSingleThreadExecutor();
        ApiBook apiBook = new ApiBook(tokenManager.getToken());
        ApiBook.MyApi myApi = apiBook.getRetrofitInstance().create(ApiBook.MyApi.class);
        Callable<ArrayList<Book>> callable = () -> {
            Call<BookResponse> call = myApi.getData();
            try{
                Response<BookResponse> response = call.execute();
                if(response.isSuccessful()){
                    return response.body().getBooksEntityList();
                }else{
                    throw new RuntimeException("Request not successful");
                }
            }catch (IOException o){
                throw new RuntimeException("Request not successful",o);
            }

        };
        Future<ArrayList<Book>> future = executor.submit(callable);
        ArrayList<Book> books = future.get();
        executor.shutdown();
        return books;
    }

    private void startActivity(Class<?> cls){
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
        ((Activity)context).finish();
    }
}
