package com.skillbox.extensions_objects_enums



fun main() {

    print("Enter a numbers of warrior for every team: ")
    val numbersOfWarriors = inputInt()
    val firstTeam = Team(numbersOfWarriors)
    val secondTeam = Team(numbersOfWarriors)
    val battle = Battle(firstTeam, secondTeam)
    val battleIsFinished: Boolean = battle.getBattleState() == BattleState.Progress(firstTeam, secondTeam)
    Battle(firstTeam, secondTeam)
    while (battleIsFinished) {
        battle.doBattleTurn()
        println("HP of first team: ${BattleState.HpOfTeam.getInfo(firstTeam)}")
        println("HP of second team: ${BattleState.HpOfTeam.getInfo(secondTeam)}")
    }

}

fun inputInt(): Int = readLine()?.toIntOrNull() ?: inputInt()