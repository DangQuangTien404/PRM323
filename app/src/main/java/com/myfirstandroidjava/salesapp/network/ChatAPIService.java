package com.myfirstandroidjava.salesapp.network;

import com.myfirstandroidjava.salesapp.models.ChatMessage;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ChatAPIService {
    @GET("api/chat/history")
    Call<List<ChatMessage>> getChatHistory();
}
