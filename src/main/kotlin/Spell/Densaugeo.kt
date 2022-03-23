package Spell

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Densaugeo : Card(house.Slytherin, 5, "Densaugeo", Type.Spell) {
    override fun play(): Request? {
        if (Game.current.House == this.House) {
            Game.current.Attacks++
        }
        Events.spellPlayedEvent()
        super.play()
        return Request(::choose, "Válassz:\n 0 - 2db szív\n 1 - 1db rontás")
    }

    fun choose(r: Response): Boolean {
        return when (r.n) {
            0 -> {
                Game.current.Hearts += 2
                true
            }
            1 -> {
                Game.opponent.DiscardPile.cards.addAll(Game.Curses.draw(1))
                true
            }
            else -> false
        }
    }
}