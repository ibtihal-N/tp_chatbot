package ma.enset.tp_chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import ma.enset.tp_chatbot.adapters.ChatBotAdapter;
import ma.enset.tp_chatbot.api.BrainShopApi;
import ma.enset.tp_chatbot.model.BrainShopeResponse;
import ma.enset.tp_chatbot.model.MessageModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<MessageModel> messages =new ArrayList<>();
    private ImageButton buttonSend;
    private EditText editTextUser;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUser=findViewById(R.id.editMsg);
        buttonSend=findViewById(R.id.buttonSend);
        recyclerView=findViewById(R.id.rvChBot);
        Gson gson=new GsonBuilder().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://api.brainshop.ai/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        BrainShopApi brainShopApi=retrofit.create(BrainShopApi.class);
        ChatBotAdapter chatBotAdapter=new ChatBotAdapter(messages,this);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setAdapter(chatBotAdapter);
        recyclerView.setLayoutManager(manager);

        buttonSend.setOnClickListener(view -> {
            String msg=editTextUser.getText().toString();
            messages.add(new MessageModel(msg,"user"));
            chatBotAdapter.notifyDataSetChanged();
            String url=""+msg;
            Call<BrainShopeResponse> response=brainShopApi.getMessage(url);
            editTextUser.setText("");
            response.enqueue(new Callback<BrainShopeResponse>() {
                @Override
                public void onResponse(Call<BrainShopeResponse> call, Response<BrainShopeResponse> response) {
                    Log.i("info",response.body().getCnt());
                    messages.add(new MessageModel(response.body().getCnt(),"bot"));
                    chatBotAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<BrainShopeResponse> call, Throwable t) {
                    Log.i("info",t.getMessage());
                }
            });
        });
    }
}