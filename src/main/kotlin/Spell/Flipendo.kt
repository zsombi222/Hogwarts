package Spell

import Card
import Events
import Game
import Request
import Type
import house

class Flipendo : Card(house.None, 8, "Flipendo", Type.Spell) {
    override fun play(): Request? {
        Game.current.apply {
            Attacks += 3
            Hand.cards.addAll(DrawPile.draw(1))
        }
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}