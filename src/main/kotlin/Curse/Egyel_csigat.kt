package Curse

import Card
import Events
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

    override fun discard() {
        Events.egyelcsigatN--
        if (Events.egyelcsigatN == 0) {
            Events.egyelcsigat = false
        }
        super.discard()
    }
}