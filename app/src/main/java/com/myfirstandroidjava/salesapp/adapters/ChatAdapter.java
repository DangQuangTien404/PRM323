package com.myfirstandroidjava.salesapp.adapters;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.myfirstandroidjava.salesapp.R;
import com.myfirstandroidjava.salesapp.models.ChatMessage;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<ChatMessage> chatMessages;

    public ChatAdapter(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessages.get(position);
        holder.bind(chatMessage);
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public void addMessage(ChatMessage message) {
        chatMessages.add(message);
        notifyItemInserted(chatMessages.size() - 1);
    }

    public void setMessages(List<ChatMessage> messages) {
        this.chatMessages = messages;
        notifyDataSetChanged();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewUser;
        private TextView textViewMessage;
        private TextView textViewTimestamp;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUser = itemView.findViewById(R.id.textViewUser);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
            textViewTimestamp = itemView.findViewById(R.id.textViewTimestamp);
        }

        public void bind(ChatMessage chatMessage) {
            String message = chatMessage.getMessage();
            if (message != null && message.endsWith(" has joined the chat")) {
                textViewUser.setVisibility(View.GONE);
                String username = chatMessage.getUser();
                SpannableString spannableMessage = new SpannableString(message);
                int startIndex = message.indexOf(username);
                int endIndex = startIndex + username.length();

                if (startIndex != -1) {
                    spannableMessage.setSpan(new ForegroundColorSpan(Color.GREEN), startIndex, endIndex, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                textViewMessage.setText(spannableMessage);
            } else {
                textViewUser.setVisibility(View.VISIBLE);
                textViewUser.setText(chatMessage.getUser());
                textViewMessage.setText(chatMessage.getMessage());
            }

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
            textViewTimestamp.setText(sdf.format(chatMessage.getTimestamp()));
        }
    }
}
