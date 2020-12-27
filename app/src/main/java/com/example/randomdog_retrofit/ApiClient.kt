package com.example.randomdog_retrofit

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiClient {

    @GET("api/breeds/image/random")
    suspend fun getRandomImage(): Response<DogImageModel>


    object ApiAdapter {
        val apiClient: ApiClient = Retrofit.Builder()
            .baseUrl("https://dog.ceo")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }

}