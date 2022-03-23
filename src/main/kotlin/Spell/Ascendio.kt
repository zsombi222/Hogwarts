package Spell

import Card
import Events
import Game
import Request
import Type
import house

class Ascendio : Card(house.None, 4, "Ascendio", Type.Spell) {
    override fun play(): Request? {
        Game.current.apply {
            Coins += 2
            Hand.cards.addAll(DrawPile.draw(1))
        }
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}