package com.tolulonge.footballfixtures.remote.api

import com.tolulonge.footballfixtures.remote.model.todayfixture.RemoteTodayFixturesResponse
import retrofit2.http.GET


interface FootballFixturesApi {



    @GET("matches")
    suspend fun getTodayFixtures(): RemoteTodayFixturesResponse


    companion object {
        const val BASE_URL = "https://api.football-data.org/v4/"
    }
}