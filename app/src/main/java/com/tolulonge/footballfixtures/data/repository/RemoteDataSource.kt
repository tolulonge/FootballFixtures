package com.tolulonge.footballfixtures.data.repository

import com.tolulonge.footballfixtures.core.util.Resource
import com.tolulonge.footballfixtures.data.model.DataTodayFixture


interface RemoteDataSource {

    suspend fun getTodayFixtures(): Resource<List<DataTodayFixture>>

}
