package com.christianbloggersapp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    WebView mWebViewContent, mWebViewDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        mWebViewContent = (WebView) findViewById(R.id.webViewContent);
        mWebViewDescription = (WebView) findViewById(R.id.webViewDescription);



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
                mTextView.setText(articleResponse.channel.title
                        +"\nTitle0=  " + articleResponse.channel.items.get(0).title
                        +"\nImage0=  " + articleResponse.channel.items.get(0).contentList.get(0).url
                        +"\nDesc0=  " + articleResponse.channel.items.get(0).description

                );

                String description = articleResponse.channel.items.get(0).description;
                mWebViewDescription.loadData(description, "text/html; charset=utf-8", "UTF-8");

                String data = articleResponse.channel.items.get(0).encoded;
                mWebViewContent.loadData(data, "text/html; charset=utf-8", "UTF-8");
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.e("NJW", "fail:request=" + call.request().toString());
                Log.e("NJW", "fail:message=" + t.getMessage());
            }
        });

    }
}
