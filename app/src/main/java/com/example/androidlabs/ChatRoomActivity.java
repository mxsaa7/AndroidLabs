package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        ListView lView = (ListView) findViewById(R.id.listview);
        EditText editMessage = (EditText) findViewById(R.id.editMessageText);

        Button btnSend = (Button) findViewById(R.id.btnSend);
        Button btnReceive = (Button) findViewById(R.id.btnReceive);

        btnSend.setOnClickListener(c -> {
            String message = editMessage.getText().toString();
            List<MessageList> chatList = new ArrayList<>();
            MessageList msgList = new MessageList(message,true);
            chatList.add(msgList);
            editMessage.setText("");
            ChatAdapter adapt = new ChatAdapter(chatList, getApplicationContext());
            lView.setAdapter(adapt);
        });

        btnReceive.setOnClickListener(c -> {
            String message = editMessage.getText().toString();
            List<MessageList> chatList = new ArrayList<>();
            MessageList msgList = new MessageList(message,true);
            chatList.add(msgList);
            editMessage.setText("");
            ChatAdapter adapt = new ChatAdapter(chatList, getApplicationContext());
            lView.setAdapter(adapt);
        });

        Log.e("ChatRoomActivity", "onCreate");
    }

}
class MessageList {
    public String message;
    public boolean isSend;

    public MessageList(String message, boolean isSend){
        this.message = message;
        this.isSend = isSend;
    }
    public MessageList(){

    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }

    public boolean isSend() {
        return isSend;
    }
    public void setSend(boolean send){
        isSend = send;
    }
}

class ChatAdapter extends BaseAdapter {
    private List<MessageList> chatList1;
    private Context context;
    private LayoutInflater inflater;

    public ChatAdapter(List<MessageList> messageLists, Context context){
        chatList1 = messageLists;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return chatList1.size();
    }

    @Override
    public Object getItem(int position) {
        return chatList1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null){
            if (chatList1.get(position).isSend()){
                view = inflater.inflate(R.layout.activity_main_send, null);

            }else {
                view = inflater.inflate(R.layout.activity_main_receive, null);
            }
            TextView textMessage = (TextView)view.findViewById(R.id.editMessageText);
            textMessage.setText(chatList1.get(position).message);
        }
        return view;
    }
}