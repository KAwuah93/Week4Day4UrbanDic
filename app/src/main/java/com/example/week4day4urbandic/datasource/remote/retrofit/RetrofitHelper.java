package com.example.week4day4urbandic.datasource.remote.retrofit;

import com.example.week4day4urbandic.model.WordResponse;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    //Constants
    public static final String BASE_URL = "https://mashape-community-urban-dictionary.p.rapidapi.com/";
    public static final String API_PATH = "define";
    public static final String QUERY_TERM = "term";
    public static final String HOST = "mashape-community-urban-dictionary.p.rapidapi.com";
    public static final String API_KEY = "5979840fccmshba9afde82378ea8p1ad9e2jsn2a0d8e666d6a";

    public static Retrofit getRetrofitInstance() {

        //Preparing the header we have to build an okhttp client in order to nest the key for the request
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                //This is where we attach the headers and the keys to each of the individual parts
                Request request = original.newBuilder()
                        .header("x-rapidapi-host", HOST)
                        .header("x-rapidapi-key", API_KEY)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        //Building a request out of our specifications that we created above.
        OkHttpClient okHttpClient = client.build();
        //Returning a built retrofit instance with our headers included
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
    public static WordResponseService getService(){
        return getRetrofitInstance().create(WordResponseService.class);
    }
}
