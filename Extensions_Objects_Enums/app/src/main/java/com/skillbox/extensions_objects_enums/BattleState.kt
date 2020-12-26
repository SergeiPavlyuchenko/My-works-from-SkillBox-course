package com.skillbox.extensions_objects_enums

sealed class BattleState(private val stateMessage: String) {

    open fun print() = println(stateMessage)

    class Progress(
            private val firstTeamHp: Int,
            private val secondTeamHp: Int
    ): BattleState("HP of 1'st team: $firstTeamHp\nHP of 2'nd team: $secondTeamHp") {
        override fun print() = super.print()
    }

    object FirstTeamWin: BattleState("First team is winner") {
        override fun print() = super.print()
    }

    object SecondTeamWin: BattleState("Second team is winner") {
        override fun print() = super.print()
    }

    object TheDraw: BattleState("The draw") {
        override fun print() = super.print()
    }

}
