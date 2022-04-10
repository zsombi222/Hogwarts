package Item

import Card
import Events
import Game
import Request
import Type
import house

class Mandragora : Card(house.Hufflepuff, 4, "Mandragóra", Type.Item) {
    override fun play(): Request? {
        Game.current.apply {
            Attacks++
            Hearts++
        }
        if(Game.current.House == this.House || Game.current.hasAllyWithHouse(this.House)){
            Game.current.Coins += 2
        }
        Events.itemPlayedEvent()
        super.play()
        return null
    }
}