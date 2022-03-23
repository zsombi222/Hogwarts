package Spell

import Card
import Events
import Game
import Request
import Type
import house

class Descendo : Card(house.Gryffindor, 5, "Descendo", Type.Spell) {
    override fun play(): Request? {
        Game.current.apply {
            Attacks += 2
            Hearts++
            if (House == this.House) {
                Hand.cards.addAll(DrawPile.draw(1))
            }
        }
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}