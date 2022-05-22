package com.tolulonge.footballfixtures.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "competitions")
data class LocalCompetitionX(
    @PrimaryKey
    @ColumnInfo(name = "competition_id")
    val id: Int?,
    val competitionName: String?,
    val competitionEmblem: String?,
    val currentMatchDay: Int?,
    val nextMatchDay: Int?
)