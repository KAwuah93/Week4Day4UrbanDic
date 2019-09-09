package com.example.week4day4urbandic;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week4day4urbandic.model.ListItem;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WordsRVAdapter extends RecyclerView.Adapter<WordsRVAdapter.ViewHolder> {
    private ArrayList<ListItem> listItems;

    public WordsRVAdapter(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

    public void setListItems(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
        // TODO change this after we're done playing around with it
        this.orderThumbsUp();
        notifyDataSetChanged();
    }

    public void orderThumbsUp(){
        if (this.getItemCount() > 0) {
            Collections.sort(listItems, new SortByThumbsUp());
        }
        notifyDataSetChanged();
    }

    public void orderThumbsDown(){
        if (this.getItemCount() > 0) {
            Collections.sort(listItems, new SortByThumbsDown());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflated = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item, parent, false);
        return new ViewHolder(inflated);
    }

    @Override
    public void onBindViewHolder(@NonNull WordsRVAdapter.ViewHolder holder, int position) {
        ListItem currentItem = listItems.get(position);
        holder.populate(currentItem);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitle;
        TextView tvExample;
        TextView tvDesc;
        TextView UpVote;
        TextView DownVote;
        ListItem item;

        public ViewHolder(View itemView){
            super(itemView);

            //Bind using the the ItemView instead of directly
            tvTitle = itemView.findViewById(R.id.tvRVTitle);
            tvExample = itemView.findViewById(R.id.tvRVExample);
            tvDesc = itemView.findViewById(R.id.tvRVDescib);
            UpVote = itemView.findViewById(R.id.tvUpVote);
            DownVote = itemView.findViewById(R.id.tvDownVote);

            itemView.setOnClickListener(this);
        }
        public void setItem(ListItem item) {
            this.item = item;
        }
        public void populate(ListItem item){
            tvTitle.setText(item.getWord());
            tvExample.setText(item.getExample());
            tvDesc.setText(item.getDefinition());
            UpVote.setText(item.getThumbsUp() + "");
            DownVote.setText(item.getThumbsDown() + "");
        }
        @Override
        public void onClick(View view){

        }
    }
}
//our sortbythumbs up function
class SortByThumbsUp implements Comparator<ListItem> {
    @Override
    public int compare(ListItem listItem, ListItem t1) {
        return  t1.getThumbsUp() - listItem.getThumbsUp();
    }
}

//our sort by thumbsdown function
class SortByThumbsDown implements Comparator<ListItem>{
    @Override
    public int compare(ListItem listItem, ListItem t1) {
        return listItem.getThumbsDown() - t1.getThumbsDown();
    }
}