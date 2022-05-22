package com.tolulonge.footballfixtures.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.Gson
import com.tolulonge.footballfixtures.core.util.API_KEY
import com.tolulonge.footballfixtures.data.repository.FootballFixturesRepositoryImpl
import com.tolulonge.footballfixtures.data.repository.LocalDataSource
import com.tolulonge.footballfixtures.data.repository.RemoteDataSource
import com.tolulonge.footballfixtures.domain.mapper.AllDomainMappers
import com.tolulonge.footballfixtures.domain.mapper.DataCompetitionXToDomainCompetitionXMapper
import com.tolulonge.footballfixtures.domain.mapper.DataTodayFixtureToDomainTodayFixtureMapper
import com.tolulonge.footballfixtures.domain.repository.FootballFixturesRepository
import com.tolulonge.footballfixtures.local.database.FootballFixturesDatabase
import com.tolulonge.footballfixtures.local.mapper.AllLocalMappers
import com.tolulonge.footballfixtures.local.mapper.LocalDataCompetitionXListMapper
import com.tolulonge.footballfixtures.local.mapper.LocalDataTodayFixtureListMapper
import com.tolulonge.footballfixtures.local.source.LocalDataSourceImpl
import com.tolulonge.footballfixtures.presentation.mapper.AllPresentationMappers
import com.tolulonge.footballfixtures.presentation.mapper.DomainCompetitionXToPresentationCompetitionXMapper
import com.tolulonge.footballfixtures.presentation.mapper.DomainTodayFixtureToPresentationTodayFixtureMapper
import com.tolulonge.footballfixtures.remote.api.FootballFixturesApi
import com.tolulonge.footballfixtures.remote.mapper.AllRemoteMappers
import com.tolulonge.footballfixtures.remote.mapper.RemoteCompetitionToDataCompetitionMapper
import com.tolulonge.footballfixtures.remote.mapper.RemoteTodayFixtureToDataTodayFixtureMapper
import com.tolulonge.footballfixtures.remote.source.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideFootballFixtureApi(
        apiKey: String
    ): FootballFixturesApi {

        val headerAuthorization = Interceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("X-Auth-Token", apiKey)
            chain.proceed(request.build())
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(headerAuthorization)
            .build()
        return Retrofit.Builder()
            .baseUrl(FootballFixturesApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideAttendanceDatabase(app: Application): FootballFixturesDatabase{
        return Room.databaseBuilder(
            app,
            FootballFixturesDatabase::class.java,
            FootballFixturesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesFootballFixturesRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
    ): FootballFixturesRepository {
        return FootballFixturesRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            allDomainMappers = AllDomainMappers(
                dataTodayFixtureToDomainTodayFixtureMapper = DataTodayFixtureToDomainTodayFixtureMapper(),
                dataCompetitionXToDomainCompetitionXMapper = DataCompetitionXToDomainCompetitionXMapper()
            )
        )
    }


    @Provides
    @Singleton
    fun providesLocalDataSource(
        db: FootballFixturesDatabase,
    ): LocalDataSource {
        return LocalDataSourceImpl(
            allLocalMappers = AllLocalMappers(
                localDataTodayFixtureListMapper = LocalDataTodayFixtureListMapper(),
                localDataCompetitionXListMapper = LocalDataCompetitionXListMapper()
            ),
            fixturesDao = db.todayFixturesDao,
            competitionsDao = db.competitionsDao
        )
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        fixturesApi: FootballFixturesApi,
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            footballFixturesApi = fixturesApi,
            allRemoteMappers = AllRemoteMappers(
                remoteTodayFixtureToDataTodayFixtureMapper = RemoteTodayFixtureToDataTodayFixtureMapper(),
                remoteCompetitionToDataCompetitionMapper = RemoteCompetitionToDataCompetitionMapper()
            )
        )
    }

    @Provides
    @Singleton
    fun provideAllPresentationMappers(): AllPresentationMappers {
        return AllPresentationMappers(
            domainTodayFixtureToPresentationTodayFixtureMapper = DomainTodayFixtureToPresentationTodayFixtureMapper(),
            domainCompetitionXToPresentationCompetitionXMapper = DomainCompetitionXToPresentationCompetitionXMapper()
        )
    }

    @Provides
    @Singleton
    fun providesApiKey(): String{
        return API_KEY
    }
}
