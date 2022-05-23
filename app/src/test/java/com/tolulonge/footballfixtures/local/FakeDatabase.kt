package com.tolulonge.footballfixtures.local


import com.tolulonge.footballfixtures.local.model.LocalCompetitionFixture
import com.tolulonge.footballfixtures.local.model.LocalCompetitionX
import com.tolulonge.footballfixtures.local.model.LocalTodayFixture

class FakeDatabase {

    var listOfLocalTodayFixture = hashSetOf(
        LocalTodayFixture(
            id = 373737,
            date = "2022-05-22T00:00:00Z",
            status = "FINISHED",
            homeTeamName = "Burnley FC",
            awayTeamName = "Newcastle United FC",
            homeTeamScore = 1,
            awayTeamScore = 2,
            homeTeamLogo = "https://crests.football-data.org/328.png",
            awayTeamLogo = "https://crests.football-data.org/67.png",
            countryOfFixture = "England",
            competitionName = "Premier League",
            refereeName = "Craig Pawson",
            competitionEmblem = "https://crests.football-data.org/PL.png",
            countryFlag = "https://crests.football-data.org/770.svg"
        ),
        LocalTodayFixture(
            id = 636744,
            date = "2022-05-22T00:00:00Z",
            status = "FINISHED",
            homeTeamName = "Arsenal FC",
            awayTeamName = "Chelsea FC",
            homeTeamScore = 4,
            awayTeamScore = 2,
            homeTeamLogo = "https://crests.football-data.org/328.png",
            awayTeamLogo = "https://crests.football-data.org/67.png",
            countryOfFixture = "England",
            competitionName = "Premier League",
            refereeName = "Matt Preria",
            competitionEmblem = "https://crests.football-data.org/PL.png",
            countryFlag = "https://crests.football-data.org/770.svg"
        ),
        LocalTodayFixture(
            id = 399947,
            date = "2022-05-22T00:00:00Z",
            status = "FINISHED",
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
            countryFlag = "https://crests.football-data.org/770.svg"
        )

    )

    var listOfLocalCompetitionFixture = hashSetOf(
        LocalCompetitionFixture(
            id = 89764,
            date = "2022-05-22T00:00:00Z",
            status = "FINISHED",
            homeTeamName = "Burnley FC",
            awayTeamName = "Newcastle United FC",
            homeTeamScore = 1,
            awayTeamScore = 2,
            homeTeamLogo = "https://crests.football-data.org/328.png",
            awayTeamLogo = "https://crests.football-data.org/67.png",
            countryOfFixture = "England",
            competitionName = "Premier League",
            refereeName = "Craig Pawson",
            competitionEmblem = "https://crests.football-data.org/PL.png",
            countryFlag = "https://crests.football-data.org/770.svg",
            matchDay = 38,
            competitionCode = "PL"
        ),
        LocalCompetitionFixture(
            id = 9824435,
            date = "2022-08-22T00:00:00Z",
            status = "FINISHED",
            homeTeamName = "Bayern Munich",
            awayTeamName = "Chelsea FC",
            homeTeamScore = 4,
            awayTeamScore = 2,
            homeTeamLogo = "https://crests.football-data.org/328.png",
            awayTeamLogo = "https://crests.football-data.org/67.png",
            countryOfFixture = "Germany",
            competitionName = "Bundesliga",
            refereeName = "Matt Preria",
            competitionEmblem = "https://crests.football-data.org/BL1.png",
            countryFlag = "https://crests.football-data.org/770.svg",
            matchDay = 38,
            competitionCode = "BL1"
        ),
        LocalCompetitionFixture(
            id = 56557,
            date = "2022-05-22T00:00:00Z",
            status = "FINISHED",
            homeTeamName = "Hamburger FC",
            awayTeamName = "Wolfsburg",
            homeTeamScore = 2,
            awayTeamScore = 5,
            homeTeamLogo = "https://crests.football-data.org/328.png",
            awayTeamLogo = "https://crests.football-data.org/67.png",
            countryOfFixture = "Germany",
            competitionName = "Bundesliga",
            refereeName = "Bale Pawson",
            competitionEmblem = "https://crests.football-data.org/BL1.png",
            countryFlag = "https://crests.football-data.org/770.svg",
            matchDay = 38,
            competitionCode = "BL1"
        )
    )

    var listOfLocalCompetitionX = hashSetOf(
        LocalCompetitionX(
            id = 987891,
            competitionName = "Premier League",
            competitionEmblem = "https://crests.football-data.org/PL.png",
            competitionCode = "PL",
            currentMatchDay = 38,
            competitionCountryName = "England",
            competitionCountryEmblem = "https://crests.football-data.org/770.svg",
            nextMatchDay = 39
        ),
        LocalCompetitionX(
            id = 123234,
            competitionName = "Bundesliga",
            competitionEmblem = "https://crests.football-data.org/BL1.png",
            competitionCode = "BL1",
            currentMatchDay = 38,
            competitionCountryName = "Germany",
            competitionCountryEmblem = "https://crests.football-data.org/7080.svg",
            nextMatchDay = 39
        ),
        LocalCompetitionX(
            id = 55667,
            competitionName = "Ligue 1",
            competitionEmblem = "https://crests.football-data.org/FR.png",
            competitionCode = "FR",
            currentMatchDay = 38,
            competitionCountryName = "France",
            competitionCountryEmblem = "https://crests.football-data.org/7372.svg",
            nextMatchDay = 39
        ),
    )

}