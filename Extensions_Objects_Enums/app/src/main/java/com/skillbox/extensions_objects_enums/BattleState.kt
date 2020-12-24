package com.skillbox.extensions_objects_enums

sealed class BattleState {

    class Progress(
            private val firstTeam: Team,
            private val secondTeam: Team,
    ): BattleState()

    object HpOfTeam: BattleState() {
        fun getInfo(team: Team): Int = team.getHpOfTeam()
    }

    object FirstTeamWin: BattleState() {
        fun print() = println("First team is winner")
    }

    object SecondTeamWin: BattleState() {
        fun print() = println("Second team is winner")
    }

    object TheDraw: BattleState() {
        fun print() = println("The draw")
    }



}
