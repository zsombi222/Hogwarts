package Spell

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Exmamoriam : Card(house.Ravenclaw, 5, "Exmamoriam", Type.Spell) {
    override fun play(): Request? {
        Game.current.apply {
            Coins += 2
        }
        if (Game.current.House == this.House || Game.current.hasAllyWithHouse(this.House)) {
            Game.current.apply {
                Attacks++
            }
        }

        Events.spellPlayedEvent()
        super.play()
        if (Game.opponent.Hand.hasOnlyCurses()) {
            println("Már csak rontások maradtak")
            return null
        }
        return Request(
            ::choose,
            "${Game.opponent.name}, válassz egy nem rontás lapot, amit eldobsz [0-${Game.opponent.Hand.cards.size - 1}]."
        )
    }

    fun choose(r: Response): Boolean {
        if (Game.opponent.Hand.hasOnlyCurses()) {
            println("Már csak rontások maradtak")
            return true
        }
        try {
            if (Game.opponent.Hand.cards[r.n].type == Type.Curse) {
                println("NEM rontás lapot!")
                return false
            }
            Game.opponent.DiscardPile.cards.add(Game.opponent.Hand.drawIdx(r.n))
            println("Eldobott egy nem rontás lapot")
            return true
        } catch (e: Exception) {
            println("Nincs ilyen lap")
            return false
        }
    }
}