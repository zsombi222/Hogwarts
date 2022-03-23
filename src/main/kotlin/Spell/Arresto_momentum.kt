package Spell

import Card
import Events
import Game
import Request
import Type
import house

class Arresto_momentum: Card(house.None, 3, "Arresto momentum", Type.Spell){
    override fun play(): Request? {
        Game.current.apply {
            Hearts++
            Hand.cards.addAll(DrawPile.draw(1))
        }
        Events.spellPlayedEvent()
        super.play()
        return null
    }

    override fun discard() {
        Game.current.apply {
            Hearts++
        }
        super.discard()
    }
}