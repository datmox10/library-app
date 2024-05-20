package com.library.app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.library.app.R;
import com.library.app.adapter.DichVuAdapter;
import com.library.app.adapter.MenuSachAdapter;
import com.library.app.api.ApiBook;
import com.library.app.api.HandlerBookTraining;
import com.library.app.model.Book;
import com.library.app.model.BookResponse;
import com.library.app.model.DichVu;
import com.library.app.model.TokenManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private View view;
    private RecyclerView recyclerMenu, recyclerNewBook, recyclerHotBook;

    private DichVuAdapter dichVuAdapter;
    private MenuSachAdapter menuSachAdapter;
    private Toolbar toolbar;

    private TokenManager tokenManager = TokenManager.getInstance(getActivity());

    HandlerBookTraining handlerBookTraining = new HandlerBookTraining();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.d( "onCreate-homey: ",tokenManager.getToken());
        AnhXa();
        getDichVu();
        getNewBook();
        getHotBook();

        handlerBookTraining.getAllBookTraining();
        return view;
    }

    private void AnhXa(){
        recyclerMenu = view.findViewById(R.id.recycler_menu);
        recyclerNewBook = view.findViewById(R.id.recycler_new_book);
        recyclerHotBook = view.findViewById(R.id.recycler_hot_book);
    }

    private void getDichVu(){
        ArrayList<DichVu> dichVus = new ArrayList<>();
        dichVus.add(new DichVu(1,"Đặt phòng", getResources().getDrawable(R.drawable.datphong)));
        dichVus.add(new DichVu(2,"Yêu thích",getResources().getDrawable(R.drawable.yeuthich)));
        dichVus.add(new DichVu(3,"Khóa luận",getResources().getDrawable(R.drawable.khoaluan)));
        dichVus.add(new DichVu(4,"Hỏi đáp AI",getResources().getDrawable(R.drawable.khoaluan)));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerMenu.setLayoutManager(linearLayoutManager);

        dichVuAdapter = new DichVuAdapter(dichVus,getActivity());
        recyclerMenu.setAdapter(dichVuAdapter);
    }
    private void getNewBook(){

        ApiBook apiBook = new ApiBook(tokenManager.getToken());
        ApiBook.MyApi myApi = apiBook.getRetrofitInstance().create(ApiBook.MyApi.class);
        Call<BookResponse> call = myApi.getData();
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {

                if(response.isSuccessful()){
                    Log.d( "onResponse: ", String.valueOf(response.body().getBooksEntityList().size()));
                    ArrayList<Book> books = response.body().getBooksEntityList();

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
                    recyclerNewBook.setLayoutManager(linearLayoutManager);

                    menuSachAdapter = new MenuSachAdapter(books,getActivity());
                    recyclerNewBook.setAdapter(menuSachAdapter);
                }else{
                    Log.d( "onResponse: ", String.valueOf(response.code()));
                }

            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable throwable) {
                Log.d( "onResponse: ", throwable.toString());
            }
        });


    }

    private void getHotBook(){

        ApiBook apiBook = new ApiBook(tokenManager.getToken());
        ApiBook.MyApi myApi = apiBook.getRetrofitInstance().create(ApiBook.MyApi.class);
        Call<BookResponse> call = myApi.getData();
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {

                if(response.isSuccessful()){
                    Log.d( "onResponse: ", String.valueOf(response.body().getBooksEntityList().size()));
                    ArrayList<Book> books = response.body().getBooksEntityList();

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
                    recyclerHotBook.setLayoutManager(linearLayoutManager);

                    menuSachAdapter = new MenuSachAdapter(books,getActivity());
                    recyclerHotBook.setAdapter(menuSachAdapter);
                }else{
                    Log.d( "onResponse: ", String.valueOf(response.code()));
                }

            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable throwable) {
                Log.d( "onResponse: ", throwable.toString());
            }
        });





    }
}