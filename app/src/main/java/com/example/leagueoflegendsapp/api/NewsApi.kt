package com.example.leagueoflegendsapp.api

import com.example.leagueoflegendsapp.BuildConfig
import com.example.leagueoflegendsapp.model.NewsResponseDto
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String = "tesla",
        @Query("from") from: String = "2026-05-18",
        @Query("sortBy") sortBy: String = "publishedAt",
    ): NewsResponseDto
}


fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor())
        .build()
}

object RetrofitInstance {

    private const val BASE_URL = "https://newsapi.org/v2/"

    val api: NewsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(NewsApiService::class.java)
    }
}

class ApiKeyInterceptor : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {

        val request = chain.request()

        val url = request.url.newBuilder()
            .addQueryParameter(
                "apiKey",
                BuildConfig.NEWS_API_KEY
            )
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(url)
                .build()
        )
    }
}