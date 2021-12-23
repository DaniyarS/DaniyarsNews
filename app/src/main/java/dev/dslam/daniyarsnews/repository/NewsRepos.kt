package dev.dslam.daniyarsnews.repository

import androidx.lifecycle.MutableLiveData
import dev.dslam.daniyarsnews.models.Article

interface NewsRepos {
    fun retrieveHeadline(topHeadlinesList: MutableLiveData<List<Article>>, search: String, apiKey: String)
    fun retrieveAll(allNewsList: MutableLiveData<List<Article>>, search: String, apiKey: String)
    fun saveFavoriteNews(article: Article)
    fun getFavoriteNews(): List<Article>?
}