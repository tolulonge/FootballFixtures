package com.tolulonge.footballfixtures.remote.api

import com.tolulonge.footballfixtures.remote.model.competitionfixtures.RemoteCompetitionFixturesResponse
import com.tolulonge.footballfixtures.remote.model.competitions.RemoteCompetitionsListResponse
import com.tolulonge.footballfixtures.remote.model.todayfixture.RemoteTodayFixturesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FootballFixturesApi {



    @GET("matches")
    suspend fun getTodayFixtures(): RemoteTodayFixturesResponse

    @GET("competitions")
    suspend fun getCompetitionsList(): RemoteCompetitionsListResponse

    @GET("competitions/{competitionCode}/matches")
    suspend fun getCompetitionFixtures(
        @Path("competitionCode") competitionCode: String,
        @Query("matchday") matchDay: Int
    ): RemoteCompetitionFixturesResponse


    companion object {
        const val BASE_URL = "https://api.football-data.org/v4/"
    }
}
