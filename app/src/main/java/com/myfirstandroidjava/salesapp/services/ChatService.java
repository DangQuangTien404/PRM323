package com.myfirstandroidjava.salesapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.myfirstandroidjava.salesapp.models.ChatMessage;
import com.myfirstandroidjava.salesapp.utils.Constants;
import java.util.Date;

public class ChatService extends Service {

    private HubConnection hubConnection;
    private final IBinder binder = new ChatBinder();

    public class ChatBinder extends Binder {
        public ChatService getService() {
            return ChatService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        hubConnection = HubConnectionBuilder.create(Constants.BASE_URL + "chathub").build();

        hubConnection.on("ReceiveMessage", (user, message) -> {
            Intent intent = new Intent("new-message");
            intent.putExtra("user", user);
            intent.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }, String.class, String.class);

        new Thread(() -> {
            try {
                hubConnection.start().blockingAwait();
            } catch (Exception e) {
                Log.e("ChatService", e.getMessage());
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hubConnection.stop();
    }

    public void sendMessage(String user, String message) {
        try {
            hubConnection.send("SendMessage", user, message);
        } catch (Exception e) {
            Log.e("ChatService", e.getMessage());
        }
    }
}
