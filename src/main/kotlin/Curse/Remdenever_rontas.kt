package Curse

import Card
import Events
import Request
import Type
import house

class Remdenever_rontas : Card(house.None, 0, "Rémdenevér rontás", Type.Curse) {
    override fun play(): Request? {
        Events.remdeneverN++
        Events.remdenever = true
        Events.cursePlayedEvent()
        super.play()
        return null
    }

    override fun discard() {
        Events.remdeneverN--
        if (Events.remdeneverN == 0) {
            Events.remdenever = false
        }
        super.discard()
    }
}