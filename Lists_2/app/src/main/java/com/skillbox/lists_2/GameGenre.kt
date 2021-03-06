package com.skillbox.lists_2

import android.graphics.drawable.Drawable

sealed class GameGenre {

    data class Shooters(
        val id: Long,
        val name: String,
        val avatarLink: String,
        val rate: Float,
        val genre: String,
        val isCoop: Boolean
    ): GameGenre() {
    }

    data class Strategy(
        val id: Long,
        val name: String,
        val avatarLink: String,
        val rate: Float,
        val genre: String,
        val isCoop: Boolean
    ): GameGenre() {
    }

//    Collectible Card Game, without coop
    data class Ccg(
    val id: Long,
    val name: String,
    val avatarLink: String,
    val rate: Float,
    val genre: String,
    ): GameGenre() {
    }

    data class KeepClear(
        val id: Long = 1,
        val name: String = "",
        val avatarLink: String = "",
        val rate: Float = 0f,
        val genre: String = "",
        val image: Drawable?
        ): GameGenre()
}
