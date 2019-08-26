package com.example.graphqlproject;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MyApolloClient {
    private static final String BASE_URL="https://api.graph.cool/simple/v1/cjzjzchq22e3n0162i2mhj17o";
    private static ApolloClient myApolloCLient;

    public static ApolloClient getMyApolloCLient(){
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        myApolloCLient=ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();

        return myApolloCLient;
    }
}
