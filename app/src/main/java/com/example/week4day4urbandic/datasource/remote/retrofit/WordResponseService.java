package com.example.week4day4urbandic.datasource.remote.retrofit;

import com.example.week4day4urbandic.model.WordResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.week4day4urbandic.datasource.remote.retrofit.RetrofitHelper.API_PATH;
import static com.example.week4day4urbandic.datasource.remote.retrofit.RetrofitHelper.QUERY_TERM;

public interface WordResponseService {
    //Could make header in this area as well
    //issa static
    @GET(API_PATH)
    //Calling back observable to set up the threading scheme
    Observable<WordResponse> getWordResponse(@Query(QUERY_TERM)String term);
}
