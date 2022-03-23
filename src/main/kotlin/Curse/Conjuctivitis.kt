package Curse

import Card
import Events
import Request
import Type
import house

class Conjuctivitis : Card(house.None, 0, "Conjuctivitis", Type.Curse) {
    override fun play(): Request? {
        Events.conjuctivitisN++
        Events.conjuctivitis = true
        Events.cursePlayedEvent()
        super.play()
        return null
    }

    override fun discard() {
        Events.conjuctivitisN--
        if (Events.conjuctivitisN == 0) {
            Events.conjuctivitis = false
        }
        super.discard()
    }
}