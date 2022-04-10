package Item

import Card
import Events
import Game
import Player
import Request
import Type
import house

class Penna : Card(house.Hufflepuff, 3, "Penna", Type.Item) {
    override fun play(): Request? {
        Game.current.apply {
            Coins++
            Hand.cards.addAll(DrawPile.draw(1))
        }

        Events.itemPlayedEvent()
        super.play()
        return null
    }

    override fun drop(p: Player) {
        if(Game.current.House == this.House || Game.current.hasAllyWithHouse(this.House)){
            Game.current.apply {
                Hearts += 2
                Hand.cards.addAll(DrawPile.draw(1))
            }
        }
        super.drop(p)
    }
}