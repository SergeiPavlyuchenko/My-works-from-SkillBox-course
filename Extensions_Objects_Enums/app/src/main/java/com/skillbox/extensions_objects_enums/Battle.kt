package com.skillbox.extensions_objects_enums

class Battle(
        private val firstTeam: Team,
        private val secondTeam: Team
) {

    private val firstTeamHp = firstTeam.getHpOfTeam()
    private val secondTeamHp = secondTeam.getHpOfTeam()

    fun getBattleState(): BattleState {
        return when {
            firstTeamHp != 0 && secondTeamHp != 0 -> BattleState.Progress(firstTeam, secondTeam)
            firstTeamHp != 0 && secondTeamHp == 0 -> BattleState.FirstTeamWin
            secondTeamHp != 0 && firstTeamHp == 0 -> BattleState.SecondTeamWin
            else ->  BattleState.TheDraw
        }
    }

    private fun haveHp(warrior: Warrior): Boolean = !warrior.isKilled

    fun doBattleTurn() {
        firstTeam.warriors.shuffle()
        secondTeam.warriors.shuffle()
        for(firstWarrior in firstTeam.warriors) {
            for(secondWarrior in secondTeam.warriors) {
                if(haveHp(firstWarrior) && haveHp(secondWarrior)) {
                    firstWarrior.attack(secondWarrior)
                    if(haveHp(secondWarrior)) secondWarrior.attack(firstWarrior) else break
                } else continue
            }
        }
    }

}