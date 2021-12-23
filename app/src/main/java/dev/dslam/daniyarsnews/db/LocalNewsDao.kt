package dev.dslam.daniyarsnews.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.dslam.daniyarsnews.models.Article

@Dao
interface LocalNewsDao {
    @Query("SELECT * FROM articles")
    fun getFavorite(): List<Article>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOnArticle(article: Article)

    /*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllArticles(articles: List<Article>)
    */
}