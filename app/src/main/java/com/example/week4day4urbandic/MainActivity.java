package com.example.week4day4urbandic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.week4day4urbandic.datasource.events.WordResponseEvent;
import com.example.week4day4urbandic.datasource.remote.retrofit.ObserverWordResponse;
import com.example.week4day4urbandic.datasource.remote.retrofit.RetrofitHelper;
import com.example.week4day4urbandic.model.ListItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    EditText input;
    RecyclerView recyclerView;
    WordsRVAdapter adapter;
    ArrayList<ListItem> defList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind
        defList = new ArrayList<>();
        input = findViewById(R.id.etDicSearch);
        recyclerView = findViewById(R.id.rvWordList);
        adapter = new WordsRVAdapter(defList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    //EventBus
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    //Unsub
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    //Subscribe
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWordEvent(WordResponseEvent event){
        if(event.getWordResponse() != null){
            defList = new ArrayList<ListItem>(event.getWordResponse().getList());
            Log.d("TAG_L", "onWordEvent() returned: " + defList);
            adapter.setListItems(defList);

        }
    }

    //call the system
    public void makeCall(String string){
        RetrofitHelper retrofitHelper = new RetrofitHelper();
        retrofitHelper.getService().getWordResponse(string)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ObserverWordResponse());
    }

    public void getFunc(View view) {

        String query = input.getText().toString();
        makeCall(query);
    }

    public void sortFuncClick(View view) {

        switch(view.getId()){
            case R.id.btnSortUpVote:
                adapter.orderThumbsUp();
                break;
            case R.id.btnSortDownVote:
                Toast.makeText(this, "Downsort Clicked", Toast.LENGTH_SHORT).show();
                adapter.orderThumbsDown();
                break;
        }
    }
}
