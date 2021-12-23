package dev.dslam.daniyarsnews.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.dslam.daniyarsnews.repository.NewsRepoImpl
import dev.dslam.daniyarsnews.repository.NewsRepos

@InstallIn(ViewModelComponent::class)
@Module
abstract class NewsModule {
    @Binds
    abstract fun getNewsSource(repo: NewsRepoImpl) : NewsRepos
}