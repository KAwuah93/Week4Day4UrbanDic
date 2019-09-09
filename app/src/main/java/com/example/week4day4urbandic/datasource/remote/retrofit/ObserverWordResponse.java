package com.example.week4day4urbandic.datasource.remote.retrofit;

import android.util.Log;
import android.widget.Toast;

import com.example.week4day4urbandic.datasource.events.WordResponseEvent;
import com.example.week4day4urbandic.model.ListItem;
import com.example.week4day4urbandic.model.WordResponse;

import org.greenrobot.eventbus.EventBus;

import javax.security.auth.login.LoginException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ObserverWordResponse implements Observer<WordResponse> {
    //Local variable should be included so you can pass something back
    WordResponse wordResponse;
    @Override
    public void onSubscribe(Disposable d) {
        //Log this

    }

    @Override
    public void onNext(WordResponse wordResponse) {
        this.wordResponse = wordResponse;

    }

    @Override
    public void onError(Throwable e) {
        Log.e("CHAIN", "onError: ", e );

    }

    @Override
    public void onComplete() {
        EventBus.getDefault().post(new WordResponseEvent(wordResponse));
    }
}