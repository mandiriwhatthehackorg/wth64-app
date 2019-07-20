package xyz.kmbmicro.monica;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;


public class ChatActivity extends AppCompatActivity {
    ChatView chatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatView = (ChatView) findViewById(R.id.chat_view);


        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener(){
            @Override
            public boolean sendMessage(ChatMessage chatMessage){
                // perform actual message sending
                chatView.addMessage(chatMessage);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        chatView.addMessage(new ChatMessage("balasan", System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
                    }
                }, 1500);

                return false;
            }
        });
    }
}
