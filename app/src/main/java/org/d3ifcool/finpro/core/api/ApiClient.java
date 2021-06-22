package org.d3ifcool.finpro.core.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.d3ifcool.finpro.core.helpers.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.BASE_URL;

public class ApiClient {

    private static Retrofit retrofit;

    private static OkHttpClient okHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();

                    Request request = originalRequest.newBuilder()
                            .header("Content-type", "application/json")
                            .header("Accept", "application/json")
                            .header("Connection", "close")
//                            .addHeader("Accept-Encoding", "identity")
                            .build();

                    return chain.proceed(request);
                })
                .connectTimeout(Constant.ObjectConstanta.API_TIMEOUT, TimeUnit.MINUTES)
                .readTimeout(Constant.ObjectConstanta.API_TIMEOUT, TimeUnit.MINUTES)
                .writeTimeout(Constant.ObjectConstanta.API_TIMEOUT, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .build();

        return httpClient;
    }

    public static Retrofit getApiClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient())
                .build();

        return retrofit;
    }

}
