package com.library.app.api;

import com.library.app.model.Book;
import com.library.app.model.BookResponse;
import com.library.app.model.BorrowBook;
import com.library.app.model.BorrowBookResponse;
import com.library.app.model.BorrowResponse;
import com.library.app.model.ReturnBook;
import com.library.app.model.UserLogin;
import com.library.app.model.UserMD;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiBook {
    private static final String BASE_URL = "https://librarybackend-production.up.railway.app/";
    private String token;

    public ApiBook(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public interface MyApi {
            @GET("lib/v1/book?page=0&size=5&sort=id,asc")
            Call<BookResponse> getData();

            @GET("lib/v1/book/{id}")
            Call<Book> getBookDetail(@Path("id") String id);
            @GET("lib/v1/auth/user")
            Call<UserMD> getUser();

            @POST("lib/v1/book-return/borrow")
            Call<BorrowResponse> borrowBook(@Body BorrowBook borrowBook);

            @GET("lib/v1/book-return/borrow-history")
            Call<BorrowBookResponse> getBorrowHistory();

            @POST("lib/v1/return-book")
            Call<BorrowResponse> returnBook(@Body ReturnBook returnBook);
    }

    public Retrofit getRetrofitInstance() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", "Bearer " + token)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
