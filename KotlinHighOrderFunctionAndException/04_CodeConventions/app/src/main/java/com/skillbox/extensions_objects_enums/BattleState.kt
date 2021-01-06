package com.skillbox.extensions_objects_enums

sealed class BattleState(private val stateMessage: String) {

    open fun print() = println(stateMessage)

    class Progress(
        private val firstTeamHp: Int,
        private val secondTeamHp: Int
    ) : BattleState("HP of 1'st team: $firstTeamHp\nHP of 2'nd team: $secondTeamHp\n")

    object FirstTeamWin : BattleState("First team is winner")

    object SecondTeamWin : BattleState("Second team is winner")

    object TheDraw : BattleState("The draw")
}
