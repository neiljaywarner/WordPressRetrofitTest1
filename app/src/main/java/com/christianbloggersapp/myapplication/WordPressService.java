package com.christianbloggersapp.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by neil on 12/1/16.
 */
public interface WordPressService {

        @GET("/feed/")
        Call<ArticleResponse> getFeed(@Query("paged") String pageNumber);
        //e.g yields /feed?paged=2 if called with getFeed("2");
        // e.g https://jeaniesjourneys.com/feed/?paged=2

}
