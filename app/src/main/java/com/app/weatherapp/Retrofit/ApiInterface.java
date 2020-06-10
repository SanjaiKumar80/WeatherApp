package com.app.weatherapp.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * CREATED BY SANJAIKUMAR On 09-06-2020
 */
public interface ApiInterface {


   //      ?92756c24107bc39dd0a7541f66ba55c5&units=metric"




    @GET("weather")
    Call<Example> getWeatherData(@Query("appid")String api ,@Query("q") String name);
}
