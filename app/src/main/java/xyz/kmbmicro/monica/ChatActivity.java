package xyz.kmbmicro.monica;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.StringTokenizer;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;


public class ChatActivity extends AppCompatActivity {
    ChatView chatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatView = (ChatView) findViewById(R.id.chat_view);
        chatView.addMessage(new ChatMessage("Halo, dengan Monica, ada yang bisa dibantu?", System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener(){
            @Override
            public boolean sendMessage(ChatMessage chatMessage){
                // perform actual message sending
                chatView.addMessage(chatMessage);
                String message = chatMessage.getMessage();
                final String replyList[] = ReplyEngine.getReply(message);

                processReplyList(chatView, replyList, 0);
                chatView.getInputEditText().setText("");
                return false;
            }
        });
    }

    public void processReplyList(final ChatView chatView, final String[] replyList, final int current) {

        System.out.println(replyList.length);
        System.out.println(current);

        if(current>=replyList.length) {
            return;
        } else {
            if(replyList[current].startsWith("[DELAY]")) {
                final Handler handler = new Handler();
                int delayNumber = getDelayNumber(replyList[current]);
                System.out.println(delayNumber);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    processReplyList(chatView, replyList, current+1);
                    }
                }, delayNumber);
            } else if(replyList[current].startsWith("[API_SALDO]")) {
                new HttpGetRequest().execute("http://appwth46-wth-46.apps.openshift.mandiriwhatthehack.com/get_balance.php");
            }  else {
                chatView.addMessage(new ChatMessage(replyList[current], System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
                processReplyList(chatView, replyList, current+1);
            }
        }
    }

    public int getDelayNumber(String delayStr) {
        StringTokenizer token = new StringTokenizer(delayStr);
        token.nextToken();
        return Integer.parseInt(token.nextToken());
    }

    class HttpGetRequest extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;   @Override
        protected String doInBackground(String... params){
            String stringUrl = params[0];
            String result = null;
            String inputLine;      try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);         //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();         //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();         //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());         //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();         //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }         //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();         //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        protected void onPostExecute(String result){
            super.onPostExecute(result);
//            System.out.println(result);

            try {
                JSONObject jObj = new JSONObject(result);
                JSONObject response = jObj.getJSONObject("Response");
                JSONObject cif = response.getJSONObject("cif");
                String amount = cif.getString("codeAmount1");
                System.out.println(amount);
                chatView.addMessage(new ChatMessage("Saldo Anda saat ini sebesar Rp. "+amount, System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
                chatView.addMessage(new ChatMessage("Apakah ada pertanyaan lain?", System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
