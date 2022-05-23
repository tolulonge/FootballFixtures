package com.tolulonge.footballfixtures.remote

import com.tolulonge.footballfixtures.data.model.DataCompetitionFixture
import com.tolulonge.footballfixtures.data.model.DataCompetitionX
import com.tolulonge.footballfixtures.data.model.DataTodayFixture
import com.tolulonge.footballfixtures.presentation.state.MatchStatus


object TestRemoteData {

    val listOfDataTodayFixture = listOf(
        DataTodayFixture(
            id = 999888,
            date = "2022-07-23T00:00:00Z",
            status = MatchStatus.FINISHED,
            homeTeamName = "Besiktas",
            awayTeamName = "Galatasaray",
            homeTeamScore = 3,
            awayTeamScore = 2,
            homeTeamLogo = "https://crests.football-data.org/328.png",
            awayTeamLogo = "https://crests.football-data.org/67.png",
            countryOfFixture = "Europe",
            competitionName = "Europa League",
            refereeName = "Mike Patterson",
            competitionEmblem = "https://crests.football-data.org/EL.png",
            countryFlag = "https://crests.football-data.org/770.svg"
        ),
        DataTodayFixture(
            id = 888999,
            date = "2022-06-23T00:00:00Z",
            status = MatchStatus.FINISHED,
            homeTeamName = "Ac Milan",
            awayTeamName = "Liverpool",
            homeTeamScore = 4,
            awayTeamScore = 4,
            homeTeamLogo = "https://crests.football-data.org/328.png",
            awayTeamLogo = "https://crests.football-data.org/67.png",
            countryOfFixture = "England",
            competitionName = "Premier League",
            refereeName = "Matt Preria",
            competitionEmblem = "https://crests.football-data.org/PL.png",
            countryFlag = "https://crests.football-data.org/770.svg"
        ),
        DataTodayFixture(
            id = 88776,
            date = "2022-05-23T00:00:00Z",
            status = MatchStatus.IN_PLAY,
            homeTeamName = "Manchester City",
            awayTeamName = "Everton FC",
            homeTeamScore = 1,
            awayTeamScore = 1,
            homeTeamLogo = "https://crests.football-data.org/328.png",
            awayTeamLogo = "https://crests.football-data.org/67.png",
            countryOfFixture = "England",
            competitionName = "Premier League",
            refereeName = "Bale Pawson",
            competitionEmblem = "https://crests.football-data.org/PL.png",
            countryFlag = "https://crests.football-data.org/770.svg"
        )
    )

    val listOfDataCompetitionFixture = listOf(
        DataCompetitionFixture(
            id = 887745,
            date = "2022-07-22T00:00:00Z",
            status = MatchStatus.FINISHED,
            homeTeamName = "Dortmund FC",
            awayTeamName = "Freiburg Fc",
            homeTeamScore = 1,
            awayTeamScore = 2,
            homeTeamLogo = "https://crests.football-data.org/328.png",
            awayTeamLogo = "https://crests.football-data.org/67.png",
            countryOfFixture = "Germany",
            competitionName = "Bundesliga",
            refereeName = "Craig Pawson",
            competitionEmblem = "https://crests.football-data.org/BL1.png",
            countryFlag = "https://crests.football-data.org/770.svg",
            matchDay = 38,
            competitionCode = "BL1"
        ),
        DataCompetitionFixture(
            id = 77775,
            date = "2022-03-22T00:00:00Z",
            status = MatchStatus.IN_PLAY,
            homeTeamName = "Hertha Berlin",
            awayTeamName = "Union Berlin",
            homeTeamScore = 4,
            awayTeamScore = 2,
            homeTeamLogo = "https://crests.football-data.org/328.png",
            awayTeamLogo = "https://crests.football-data.org/67.png",
            countryOfFixture = "Germany",
            competitionName = "Bundesliga",
            refereeName = "Matt Preria",
            competitionEmblem = "https://crests.football-data.org/PL.png",
            countryFlag = "https://crests.football-data.org/770.svg",
            matchDay = 38,
            competitionCode = "BL1"
        ),
        DataCompetitionFixture(
            id = 33345698,
            date = "2022-01-22T00:00:00Z",
            status = MatchStatus.IN_PLAY,
            homeTeamName = "Norwich FC",
            awayTeamName = "Southhampton FC",
            homeTeamScore = 2,
            awayTeamScore = 5,
            homeTeamLogo = "https://crests.football-data.org/328.png",
            awayTeamLogo = "https://crests.football-data.org/67.png",
            countryOfFixture = "England",
            competitionName = "Premier League",
            refereeName = "Bale Pawson",
            competitionEmblem = "https://crests.football-data.org/PL.png",
            countryFlag = "https://crests.football-data.org/770.svg",
            matchDay = 38,
            competitionCode = "PL"
        )
    )

    val listOfDataCompetitionX = listOf(
        DataCompetitionX(
            id = 2345639,
            competitionName = "Scottish Premier League",
            competitionEmblem = "https://crests.football-data.org/SL.png",
            competitionCode = "SL",
            currentMatchDay = 38,
            competitionCountryName = "Scotland",
            competitionCountryEmblem = "https://crests.football-data.org/370.svg",
            nextMatchDay = 39
        ),
        DataCompetitionX(
            id = 409395,
            competitionName = "La Liga",
            competitionEmblem = "https://crests.football-data.org/LLG.png",
            competitionCode = "LLG",
            currentMatchDay = 38,
            competitionCountryName = "Spain",
            competitionCountryEmblem = "https://crests.football-data.org/7080.svg",
            nextMatchDay = 39
        ),
        DataCompetitionX(
            id = 94494456,
            competitionName = "Serie A",
            competitionEmblem = "https://crests.football-data.org/ITL.png",
            competitionCode = "ITL",
            currentMatchDay = 38,
            competitionCountryName = "Italy",
            competitionCountryEmblem = "https://crests.football-data.org/72372.svg",
            nextMatchDay = 39
        ),
    )
}
