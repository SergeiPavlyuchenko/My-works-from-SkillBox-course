package com.skillbox.extensions_objects_enums


class Team(
        private val number: Int
)  {

    val warriors: MutableList<Warrior>
        get() = create()

    private fun create(): MutableList<Warrior> {
        var currentIndex = 0
        while(currentIndex <= number) {
            when {
                10.toBoolean() -> warriors.add(General())
                15.toBoolean() -> warriors.add(Sniper())
                30.toBoolean() -> warriors.add(Captain())
                else -> warriors.add(Solder())
            }
            currentIndex++
        }
        return warriors
    }

    fun getHpOfTeam(): Int {
        var sumHpOfTeam = 0
        warriors.onEach {
            sumHpOfTeam += it.currentHP
            println("$it have ${it.currentHP} HP")
        }
        return sumHpOfTeam
    }


}