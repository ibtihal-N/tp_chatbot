package ma.enset.tp_chatbot.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.enset.tp_chatbot.R;
import ma.enset.tp_chatbot.model.MessageModel;

public class ChatBotAdapter extends RecyclerView.Adapter {
    private List<MessageModel> messages;
    private Context context;

    public ChatBotAdapter() {
    }
    public ChatBotAdapter(List<MessageModel> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view=LayoutInflater.from(context).inflate(R.layout.user_msg,parent,false);
                return new UserViewHolder(view);
            case 1:
                view=LayoutInflater.from(context).inflate(R.layout.bot_msg,parent,false);
                return new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        String sender= messages.get(position).getSender();
        switch (sender){
            case "user":return 0;
            case "bol":return 1;
            default:return -1;
        }
    }
    
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel messageModel=messages.get(position);
            switch (messageModel.getSender()){
                case "user":((UserViewHolder)holder).tvUserMsg.setText(messageModel.getMessage());
                break;
                case "bot":((BotViewHolder)holder).tvBot.setText(messageModel.getMessage());
                break;
            }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView tvUserMsg;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvUserMsg=itemView.findViewById(R.id.tvMsgUser);
        }
    }

    public static class BotViewHolder extends RecyclerView.ViewHolder {
        TextView tvBot;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvBot=itemView.findViewById(R.id.tvMsgBot);
        }
    }

}
