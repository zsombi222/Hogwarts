package Spell

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Imperio : Card(house.Slytherin, 4, "Imperio", Type.Spell) {
    override fun play(): Request? {
        Game.current.apply {
            Attacks += Game.opponent.Allies.cards.size
        }

        Events.spellPlayedEvent()
        super.play()

        if (Game.current.House == this.House || Game.current.hasAllyWithHouse(this.House)) {
            if(!Game.opponent.Hand.hasOnlyCurses())
                return Request(
                    ::dropcard,
                    "${Game.opponent.name}, dobj el egy lapot ami nem rontás\n${Game.opponent.Hand}",
                    1
                )
        }
        return null
    }

    fun dropcard(r: Response): Boolean {
        if (Game.opponent.Hand.hasOnlyCurses()) {
            println("Már csak rontások maradtak")
            return true
        }
        try {
            if (Game.opponent.Hand.cards[r.n].type == Type.Curse) {
                println("NEM rontás lapot!")
                return false
            }
            Game.opponent.Hand.drawIdx(r.n).drop()
            println("Eldobott egy nem rontás lapot")
            return true
        } catch (e: Exception) {
            println("Nincs ilyen lap")
            return false
        }
    }
}