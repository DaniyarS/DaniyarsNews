package dev.dslam.daniyarsnews.network

import dev.dslam.daniyarsnews.models.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {
    @GET("/top-headlines?q=bitcoin&apiKey=e65ee0938a2a43ebb15923b48faed18d")
    fun getHeadlineNewses(): Call<ApiResponse>

    @GET("/top-headlines?q=bitcoin&apiKey=e65ee0938a2a43ebb15923b48faed18d")
    fun getEverything(): Call<ApiResponse>
}