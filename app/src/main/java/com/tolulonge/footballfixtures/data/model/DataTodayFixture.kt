package com.tolulonge.footballfixtures.data.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.tolulonge.footballfixtures.presentation.state.MatchStatus

data class DataTodayFixture(
    val id: Int?,
    val date: String?,
    val status: MatchStatus?,
    val homeTeamName: String?,
    val awayTeamName: String?,
    val homeTeamScore: Int?,
    val awayTeamScore: Int?,
    val homeTeamLogo: String?,
    val awayTeamLogo: String?,
    val countryOfFixture: String?,
    val competitionName: String?,
    val refereeName: String?,
    val competitionEmblem: String?,
    val countryFlag: String?
)
