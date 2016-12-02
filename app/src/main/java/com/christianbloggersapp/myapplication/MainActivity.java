package com.christianbloggersapp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);


        // https://jeaniesjourneys.com/feed/?paged=2
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jeaniesjourneys.com/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        WordPressService service = retrofit.create(WordPressService.class);

        Call<ArticleResponse> articleResponseCall = service.getFeed("2");

        articleResponseCall.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                Log.e("NJW", "response");
                ArticleResponse articleResponse = response.body();
                mTextView.setText(articleResponse.channel.title+"\n  "
                + articleResponse.channel.items.get(0).title);
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.e("NJW", "fail:request=" + call.request().toString());
                Log.e("NJW", "fail:message=" + t.getMessage());
            }
        });

    }
}
