package Item

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Kritalygomb : Card(house.Ravenclaw, 3, "Kritálygömb", Type.Item) {
    override fun play(): Request? {
        Game.current.apply {
            Hand.cards.addAll(DrawPile.draw(1))
        }
        if(Game.current.House == this.House || Game.current.hasAllyWithHouse(this.House)){
            Game.current.Coins += 2
        }
        Events.itemPlayedEvent()
        super.play()
        return Request(::dropcard, "Drop a card [0-${Game.current.Hand.cards.size - 1}]\n${Game.current.Hand}")
    }

    fun dropcard(r: Response): Boolean {
        return try {
            Game.current.apply {
                Hand.cards[r.n].drop()
            }
            true
        } catch (e: Exception) {
            println("Nem létezik ilyen lap")
            false
        }
    }
}