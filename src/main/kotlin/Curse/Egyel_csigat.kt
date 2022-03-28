package Curse

import Card
import Events
import Player
import Request
import Type
import house

class Egyel_csigat : Card(house.None, 0, "Egyél csigát", Type.Curse) {
    override fun play(): Request? {
        Events.egyelcsigatN++
        Events.egyelcsigat = true
        Events.cursePlayedEvent()
        super.play()
        return null
    }

    override fun discard(p: Player) {
        Events.egyelcsigatN--
        if (Events.egyelcsigatN == 0) {
            Events.egyelcsigat = false
        }
        super.discard(p)
    }
}