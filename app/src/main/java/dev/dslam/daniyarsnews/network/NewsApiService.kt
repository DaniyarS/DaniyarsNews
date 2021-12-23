package dev.dslam.daniyarsnews.network

import dev.dslam.daniyarsnews.models.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("/top-headlines")
    fun getHeadlineNewses(
        @Query("q") topic: String,
        @Query("apiKey") apiKey: String
    ): Call<ApiResponse>

    @GET("/everything")
    fun getEverything(
        @Query("q") topic: String,
        @Query("apiKey") apiKey: String
    ): Call<ApiResponse>
}
