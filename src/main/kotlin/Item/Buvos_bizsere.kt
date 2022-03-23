package Item

import Card
import Events
import Game
import Request
import Type
import house

class Buvos_bizsere: Card(house.None, 7, "Bűvös bizsere", Type.Item){
    override fun play(): Request? {
        Game.current.apply {
            Attacks += 2
            Hearts += 2
            Hand.cards.addAll(DrawPile.draw(1))
        }
        Events.itemPlayedEvent()
        super.play()
        return null
    }
}