package Curse

import Card
import Events
import Request
import Type
import house

class Gancsrontas : Card(house.None, 0, "Gáncsrontás", Type.Curse) {
    override fun play(): Request? {
        Events.gancsrontasN++
        Events.gancsrontas = true
        Events.cursePlayedEvent()
        super.play()
        return null
    }

    override fun discard() {
        Events.gancsrontasN--
        if (Events.gancsrontasN == 0) {
            Events.gancsrontas = false
        }
        super.destroy()
    }
}