package Item

import Card
import Events
import Game
import Request
import Type
import house

class Fozetkeszlet : Card(house.None, 5, "Főzetkészlet", Type.Item) {
    override fun play(): Request? {
        Game.current.apply {
            Attacks++
            Coins++
            Hearts++
        }
        Events.itemPlayedEvent()
        super.play()
        return null
    }
}