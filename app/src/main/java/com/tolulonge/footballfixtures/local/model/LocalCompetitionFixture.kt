package com.tolulonge.footballfixtures.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "competition_fixture")
data class LocalCompetitionFixture(
    @PrimaryKey
    @ColumnInfo(name = "fixture_id")
    val id: Int?,
    val date: String?,
    val status: String?,
    val homeTeamName: String?,
    val awayTeamName: String?,
    val homeTeamScore: Int?,
    val awayTeamScore: Int?,
    val homeTeamLogo: String?,
    val awayTeamLogo: String?,
    val countryOfFixture: String?,
    val competitionName: String?,
    val refereeName: String?,
    val matchDay: Int?,
    val competitionCode: String?,
    val competitionEmblem: String?,
    val countryFlag: String?
)