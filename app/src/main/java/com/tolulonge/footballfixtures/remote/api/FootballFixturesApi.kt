package com.tolulonge.footballfixtures.remote.api

import com.tolulonge.footballfixtures.remote.model.todayfixture.RemoteTodayFixturesResponse


interface FootballFixturesApi {



    fun getTodayFixtures(): RemoteTodayFixturesResponse


    companion object {
        const val BASE_URL = "https://www.vtaar.com/api/"
    }
}
