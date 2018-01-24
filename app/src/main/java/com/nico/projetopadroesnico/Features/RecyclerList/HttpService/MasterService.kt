package com.nico.projetopadroesnico.Features.RecyclerList.HttpService

import com.google.gson.GsonBuilder
import com.nico.projetopadroesnico.Features.RecyclerList.Model.RedditNewsResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MasterService {

    interface NewsAPI {
        @GET("/top.json")
        fun getNews(@Query("after") after: String,
                   @Query("limit") limit: String): Call<RedditNewsResponse>
    }

    private val service: NewsAPI

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder()
                .setLenient()
                .create()

        val baseUrl = "https://www.reddit.com"

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()

        service = retrofit.create<NewsAPI>(NewsAPI::class.java)
    }

    fun getNews(after: String, limit: String): Call<RedditNewsResponse> {
       return service.getNews(after, limit)
    }
}