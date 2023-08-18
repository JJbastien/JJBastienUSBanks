package com.example.usbanks.apiService

import com.example.usbanks.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    //https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=API_KEY
    @GET("v2/top-headlines/")
    suspend fun getNewshealines(
        @Query("country")
        country:String = "us",
      @Query("category")
      business:String = "business",
    @Query("apiKey")
    apiKey: String = "087b13e4468d4cb9a1aafdf8fdb1a350"
    ): Response<NewsApiResponse>
}