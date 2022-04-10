package Spell

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Obstructo : Card(house.None, 7, "Obstructo", Type.Spell) {
    override fun play(): Request? {

        Game.opponent.DiscardPile.cards.addAll(Game.Curses.draw(1))

        Events.spellPlayedEvent()
        super.play()
        if (Game.opponent.Hand.hasOnlyCurses()) {
            println("Már csak rontások maradtak")
            return null
        }
        return Request(
            ::choose,
            "${Game.opponent.name}, válassz egy nem rontás lapot, amit eldobsz [0-${Game.opponent.Hand.cards.size - 1}]\n${Game.opponent.Hand}."
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
            Game.opponent.Hand.drawIdx(r.n).drop()
            println("Eldobott egy nem rontás lapot")
            return true
        } catch (e: Exception) {
            println("Nincs ilyen lap")
            return false
        }
    }
}