package com.example.week4day4urbandic.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class WordResponse{

	@SerializedName("list")
	private List<ListItem> list;

	public void setList(List<ListItem> list){
		this.list = list;
	}

	public List<ListItem> getList(){
		return list;
	}

	@Override
 	public String toString(){
		return 
			"WordResponse{" + 
			"list = '" + list + '\'' + 
			"}";
		}
}