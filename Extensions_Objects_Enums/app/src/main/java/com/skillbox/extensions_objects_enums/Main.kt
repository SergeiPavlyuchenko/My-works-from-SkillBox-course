package com.skillbox.extensions_objects_enums



fun main() {

    print("Enter a numbers of warrior for every team: ")
    val numbersOfWarriors = inputInt()
    val firstTeam = Team(numbersOfWarriors)
    val secondTeam = Team(numbersOfWarriors)
    val battle = Battle(firstTeam, secondTeam)
    val battleIsFinished: Boolean =
            battle.getBattleState() == BattleState.FirstTeamWin ||
            battle.getBattleState() == BattleState.SecondTeamWin ||
            battle.getBattleState() == BattleState.TheDraw
    Battle(firstTeam, secondTeam)
    while (!battleIsFinished) {
        battle.doBattleTurn()
        BattleState.Progress(firstTeam.getHpOfTeam(), secondTeam.getHpOfTeam()).print()
    }

}

fun inputInt(): Int = readLine()?.toIntOrNull() ?: inputInt()