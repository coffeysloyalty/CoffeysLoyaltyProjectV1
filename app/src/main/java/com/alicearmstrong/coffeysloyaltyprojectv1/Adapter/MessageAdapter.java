package com.alicearmstrong.coffeysloyaltyprojectv1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alicearmstrong.coffeysloyaltyprojectv1.R;
import com.alicearmstrong.coffeysloyaltyprojectv1.database.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private  static  final int message_left = 0;
    private  static  final int message_right= 1;

    private Context context;
    private List<Chat> chatList;

    FirebaseUser firebaseUser;

    public MessageAdapter(Context context, List<Chat> chatList)
    {
        this.chatList = chatList;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == message_right) {
            View view = LayoutInflater.from( context ).inflate( R.layout.chat_item_right, parent, false );
            return new MessageAdapter.ViewHolder( view );
        }
        else
        {
            View view = LayoutInflater.from( context ).inflate( R.layout.chat_item_left, parent, false );
            return new MessageAdapter.ViewHolder( view );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Chat chat = chatList.get( position );

        holder.show_message.setText( chat.getMessage() );

    }


    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView show_message;

        public ViewHolder(View itemView) {
            super( itemView );

            show_message = itemView.findViewById( R.id.show_message );
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (chatList.get(position).getSender().equals( firebaseUser.getUid()))
        {
            return message_right;
        }
        else
        {
            return message_left;
        }
    }
}


