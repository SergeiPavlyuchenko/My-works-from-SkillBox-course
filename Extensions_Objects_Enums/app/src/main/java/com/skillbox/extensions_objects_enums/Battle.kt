package com.skillbox.extensions_objects_enums

class Battle(
        private val firstTeam: Team,
        private val secondTeam: Team
) {


    fun getBattleState(): BattleState {
        return when {
            firstTeam.getHpOfTeam() != 0 && secondTeam.getHpOfTeam() != 0 -> BattleState.Progress(firstTeam.getHpOfTeam(), secondTeam.getHpOfTeam())
            firstTeam.getHpOfTeam() != 0 && secondTeam.getHpOfTeam() == 0 -> BattleState.FirstTeamWin
            secondTeam.getHpOfTeam() != 0 && firstTeam.getHpOfTeam() == 0 -> BattleState.SecondTeamWin
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