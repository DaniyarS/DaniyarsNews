package dev.dslam.daniyarsnews.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dslam.daniyarsnews.models.Article
import dev.dslam.daniyarsnews.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class HeadlinesFragmentViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    private var headlineList: MutableLiveData<List<Article>> = MutableLiveData()

    fun getHeadlineObserver(): MutableLiveData<List<Article>> {
        return headlineList
    }

    fun loadHeadline(search: String, apiKey: String) {
        repository.retrieveHeadline(headlineList, search, apiKey)
    }

    fun saveFavoriteNews(article: Article) {
        repository.saveFavoriteNews(article)
    }
}