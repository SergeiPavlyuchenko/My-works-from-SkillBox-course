package com.skillbox.lists_1

sealed class GameGenre {

    data class Shooters(
        val name: String,
        val avatarLink: String,
        val rate: Float,
        val isCoop: Boolean
    ): GameGenre() {
    }

    data class Strategy(
        val name: String,
        val avatarLink: String,
        val rate: Float,
        val isCoop: Boolean
    ): GameGenre() {
    }

//    Collectible Card Game, without coop
    data class Ccg(
    val name: String,
    val avatarLink: String,
    val rate: Float
    ): GameGenre() {
    }
}
