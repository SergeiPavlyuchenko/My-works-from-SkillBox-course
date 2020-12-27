package com.skillbox.extensions_objects_enums



fun main() {

    print("Enter a numbers of warrior for every team: ")
    val numbersOfWarriors = inputInt()
    val firstTeam = Team(numbersOfWarriors)
    val secondTeam = Team(numbersOfWarriors)
    val battle = Battle(firstTeam, secondTeam)
    Battle(firstTeam, secondTeam)
    while (true) {
        battle.doBattleTurn()
        BattleState.Progress(firstTeam.getHpOfTeam(), secondTeam.getHpOfTeam()).print()
        if(firstTeam.getHpOfTeam() == 0) break
        if(secondTeam.getHpOfTeam() == 0) break
    }
    battle.getBattleState().print()
    return

}

fun inputInt(): Int = readLine()?.toIntOrNull() ?: inputInt()