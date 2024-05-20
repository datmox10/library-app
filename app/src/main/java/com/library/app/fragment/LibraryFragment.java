package com.library.app.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.library.app.R;
import com.library.app.adapter.ClassifyAdapter;
import com.library.app.adapter.MenuSachAdapter;
import com.library.app.api.ApiBook;
import com.library.app.model.Book;
import com.library.app.model.BookResponse;
import com.library.app.model.PhanLoai;
import com.library.app.model.Sach;
import com.library.app.model.TokenManager;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryFragment extends Fragment {

    private View view;
    private RecyclerView rcycLibrary;

    private ClassifyAdapter classifyAdapter;

    private TokenManager tokenManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_library, container, false);
        tokenManager = TokenManager.getInstance(getActivity());
        AnhXa();
        phanLoais();
        return view;
    }
    private void AnhXa(){
        rcycLibrary = view.findViewById(R.id.rcyc_library);
    }


    private void phanLoais(){
        ArrayList<PhanLoai> phanLoais = new ArrayList<>();

        ApiBook apiBook = new ApiBook(tokenManager.getToken());
        ApiBook.MyApi myApi = apiBook.getRetrofitInstance().create(ApiBook.MyApi.class);
        Call<BookResponse> call = myApi.getData();
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {

                if(response.isSuccessful()){
                    Log.d( "onResponse: ", String.valueOf(response.body().getBooksEntityList().size()));
                    ArrayList<Book> books = response.body().getBooksEntityList();
                    phanLoais.add(new PhanLoai("loai1","Văn hóa, xã hội", books));
                    phanLoais.add(new PhanLoai("loai2","Thiếu nhi", books));
                    phanLoais.add(new PhanLoai("loai3","Kinh tế", books));
                    phanLoais.add(new PhanLoai("loai4","Công nghệ thông tin", books));

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
                    rcycLibrary.setLayoutManager(linearLayoutManager);

                    classifyAdapter = new ClassifyAdapter(phanLoais,getActivity());
                    rcycLibrary.setAdapter(classifyAdapter);
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