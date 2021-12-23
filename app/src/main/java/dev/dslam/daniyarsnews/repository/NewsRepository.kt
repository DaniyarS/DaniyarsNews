package dev.dslam.daniyarsnews.repository

import androidx.lifecycle.MutableLiveData
import dev.dslam.daniyarsnews.db.LocalNewsDao
import dev.dslam.daniyarsnews.models.ApiResponse
import dev.dslam.daniyarsnews.models.Article
import dev.dslam.daniyarsnews.network.NewsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService,
    private val localNewsDao: LocalNewsDao
) {

    fun retrieveHeadline(
        topHeadlinesList: MutableLiveData<List<Article>>,
        search: String,
        apiKey: String
    ) {
        val call: Call<ApiResponse> = newsApiService.getHeadlineNewses(topic = search, apiKey = apiKey)
        call.enqueue(object : Callback<ApiResponse> {

            //Сперва пытаемся получить данные из сети, если не получилось показываем лайкнутые
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.articles?.let { topHeadlinesList.postValue(it) }
                } else {
                    if (getFavoriteNews() != null) {
                        topHeadlinesList.postValue(getFavoriteNews())
                    } else {
                        topHeadlinesList.postValue(null)
                    }
                }
            }

            //Показываем лайкнутые новости
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                if (getFavoriteNews() != null) {
                    topHeadlinesList.postValue(getFavoriteNews())
                } else {
                    topHeadlinesList.postValue(null)
                }
            }

        })
    }

    fun retrieveAll(
        allNewsList: MutableLiveData<List<Article>>,
        search: String,
        apiKey: String
    ) {
        val call: Call<ApiResponse> = newsApiService.getEverything(topic = search, apiKey = apiKey)
        call.enqueue(object : Callback<ApiResponse> {
            //Сперва пытаемся получить данные из сети, если не получилось показываем лайкнутые
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.articles?.let { allNewsList.postValue(it) }
                } else {
                    if (getFavoriteNews() != null) {
                        allNewsList.postValue(getFavoriteNews())
                    } else {
                        allNewsList.postValue(null)
                    }
                }
            }

            //Показываем лайкнутые новости
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                if (getFavoriteNews() != null) {
                    allNewsList.postValue(getFavoriteNews())
                } else {
                    allNewsList.postValue(null)
                }
            }

        })
    }

    fun saveFavoriteNews(article: Article) {
        localNewsDao.insertOnArticle(article)
    }

    private fun getFavoriteNews(): List<Article>? {
        return localNewsDao.getFavorite()
    }
}
