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
        }
        if (Game.current.House == this.House || Game.current.hasAllyWithHouse(this.House)) {
            Game.current.Hand.cards.addAll(Game.current.DrawPile.draw(1))
        }
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}