package com.skillbox.lists_2

import kotlin.random.Random.Default.nextLong

class AppData {
    companion object {
        val randomGames: List<GameGenre> = listOf(
            GameGenre.Shooters(
                id = nextLong(),
                name = "Apex Legends",
                avatarLink = "https://metarankings.ru/wp-content/uploads/Apex-Legends-boxart-cover.jpg",
                rate = 6.5f,
                genre = "Shooter",
                isCoop = true
            ),
            GameGenre.Shooters(
                id = nextLong(),
                name = "Cyberpunk 2077",
                avatarLink = "https://metarankings.ru/wp-content/uploads/cyberpunk-2077-box-art-cover.jpg",
                rate = 7.5f,
                genre = "Shooter",
                isCoop = false
            ),
            GameGenre.Strategy(
                id = nextLong(),
                name = "Anno 1800",
                avatarLink = "https://metarankings.ru/wp-content/uploads/Anno-1800-boxart-cover.png",
                rate = 7.0f,
                genre = "Strategy",
                isCoop = false
            ),
            GameGenre.Strategy(
                id = nextLong(),
                name = "Starcraft 2",
                avatarLink = "https://metarankings.ru/wp-content/uploads/Starcraft-2-Legacy-of-the-Void-boxart-cover.jpg",
                rate = 7.5f,
                genre = "Strategy",
                isCoop = true
            ),
            GameGenre.Ccg(
                id = nextLong(),
                name = "Hearthstone",
                avatarLink = "https://static.metacritic.com/images/products/games/3/ad340607c7d5802161e053536ec796dd-98.jpg",
                rate = 5.5f,
                genre = "CCG"
            ),
            GameGenre.Ccg(
                id = nextLong(),
                name = "Gwent",
                avatarLink = "https://static.metacritic.com/images/products/games/1/25c89fe2d9f71555e3f93ce2263d3c3c-98.jpg",
                rate = 7.0f,
                genre = "CCG"
            )
        )
    }
}