package com.example.chrom.jsontest;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MessagesAPI {

    @GET("messages1.json")
    Call<List<Message>> messagesCall();
    //List<Message> messages();
}
