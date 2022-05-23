package com.tolulonge.footballfixtures.di

import com.tolulonge.footballfixtures.domain.repository.FootballFixturesRepository
import com.tolulonge.footballfixtures.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @ViewModelScoped
    @Provides
    fun provideFootballFixturesUseCases(
        repository: FootballFixturesRepository,
    ): FootballFixturesUseCases {
        return FootballFixturesUseCases(
            getFootballFixtures = GetTodayFixtures(repository),
            getCompetitionsList = GetCompetitionsList(repository),
            getCompetitionFixturesList = GetCompetitionsFixturesList(repository)
        )
    }
}
