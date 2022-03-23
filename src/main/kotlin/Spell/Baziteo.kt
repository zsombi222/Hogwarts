package Spell

import Card
import Events
import Game
import Request
import Type
import house

class Baziteo: Card(house.Hufflepuff, 6, "Baziteo", Type.Spell){
    override fun play(): Request? {
        Game.current.apply {
            Coins++
            Hearts++
            Hand.cards.addAll(DrawPile.draw(1))
        }
        if(Game.current.House == this.House || Game.current.hasAllyWithHouse(this.House)){
            Game.current.apply {
                Attacks += 2
            }
        }
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}