package Item

import Card
import Events
import Game
import Request
import Type
import house

class Palca : Card(house.None, 0, "Pálca", Type.Item) {
    override fun play(): Request? {
        Game.current.apply {
            Attacks++
        }
        Events.itemPlayedEvent()
        super.play()
        return null
    }
}