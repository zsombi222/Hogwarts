package Curse

import Card
import Events
import Player
import Request
import Type
import house

class Sectumsempra : Card(house.None, 0, "Sectumsempra", Type.Curse) {
    override fun play(): Request? {
        Events.sectumsempraN++
        Events.sectumsempra = true
        Events.cursePlayedEvent()
        super.play()
        return null
    }

    override fun discard(p: Player) {
        Events.sectumsempraN--
        if (Events.sectumsempraN == 0) {
            Events.sectumsempra = false
        }
        super.discard(p)
    }
}