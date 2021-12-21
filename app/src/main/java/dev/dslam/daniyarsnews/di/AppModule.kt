package dev.dslam.daniyarsnews.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dslam.daniyarsnews.db.AppDatabase
import dev.dslam.daniyarsnews.db.LocalNewsDao
import dev.dslam.daniyarsnews.network.NewsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val baseURL = "https://newsapi.org/v2"

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providedNewsService(retrofit: Retrofit) : NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Singleton
    @Provides
    fun getAppDB(context: Application) : AppDatabase {
        return AppDatabase.getAppDB(context)
    }

    @Singleton
    @Provides
    fun getDao(appDB: AppDatabase): LocalNewsDao {
        return appDB.getLocalNewsDao()
    }
}