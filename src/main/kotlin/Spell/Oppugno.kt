package Spell

import Card
import Events
import Game
import Request
import Type
import house

class Oppugno : Card(house.None, 5, "Oppugno", Type.Spell) {
    override fun play(): Request? {
        Game.opponent.DiscardPile.cards.addAll(Game.Curses.draw(1))
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}