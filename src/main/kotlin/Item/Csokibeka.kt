package Item

import Card
import Events
import Game
import Request
import Type
import house

class Csokibeka : Card(house.Gryffindor, 3, "Csokibéka", Type.Item) {
    override fun play(): Request? {
        Game.current.apply {
            Coins += 2
        }
        if (Game.current.House == this.House || Game.current.hasAllyWithHouse(this.House)) {
            Game.current.apply {
                Hearts += Allies.cards.size
            }
        }
        Events.itemPlayedEvent()
        super.play()
        return null
    }
}