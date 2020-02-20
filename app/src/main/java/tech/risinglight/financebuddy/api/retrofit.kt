package tech.risinglight.financebuddy.api

import retrofit2.http.GET

public interface RetrofitClient {
    @GET("/v2/top-headlines?country=in&category=business&apiKey=0e8c9860ffbb41989cd90b17aa870b76")
    fun getArticle()


}