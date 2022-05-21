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
import com.tolulonge.footballfixtures.domain.mapper.DataTodayFixtureToDomainTodayFixtureMapper
import com.tolulonge.footballfixtures.domain.repository.FootballFixturesRepository
import com.tolulonge.footballfixtures.local.database.FootballFixturesDatabase
import com.tolulonge.footballfixtures.local.mapper.LocalDataTodayFixtureListMapper
import com.tolulonge.footballfixtures.local.source.LocalDataSourceImpl
import com.tolulonge.footballfixtures.presentation.mapper.DomainTodayFixtureToPresentationTodayFixtureMapper
import com.tolulonge.footballfixtures.remote.api.FootballFixturesApi
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
            dataTodayFixtureToDomainTodayFixtureMapper = DataTodayFixtureToDomainTodayFixtureMapper()
        )
    }


    @Provides
    @Singleton
    fun providesLocalDataSource(
        db: FootballFixturesDatabase,
    ): LocalDataSource {
        return LocalDataSourceImpl(
            localDataTodayFixtureListMapper = LocalDataTodayFixtureListMapper(),
            fixturesDao = db.todayFixturesDao
        )
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        fixturesApi: FootballFixturesApi,
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            footballFixturesApi = fixturesApi,
            remoteTodayFixtureToDataTodayFixtureMapper = RemoteTodayFixtureToDataTodayFixtureMapper()
        )
    }

    @Provides
    @Singleton
    fun provideDomainTodayFixtureToPresentationTodayFixtureMapper(): DomainTodayFixtureToPresentationTodayFixtureMapper {
        return DomainTodayFixtureToPresentationTodayFixtureMapper()
    }

    @Provides
    @Singleton
    fun providesApiKey(): String{
        return API_KEY
    }
}
