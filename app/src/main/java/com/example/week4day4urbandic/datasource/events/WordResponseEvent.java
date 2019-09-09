package com.example.week4day4urbandic.datasource.events;

import com.example.week4day4urbandic.model.WordResponse;

public class WordResponseEvent {
    WordResponse wordResponse;

    public WordResponse getWordResponse() {
        return wordResponse;
    }

    public void setWordResponse(WordResponse wordResponse) {
        this.wordResponse = wordResponse;
    }

    public WordResponseEvent(WordResponse wordResponse) {
        this.wordResponse = wordResponse;
    }
}
