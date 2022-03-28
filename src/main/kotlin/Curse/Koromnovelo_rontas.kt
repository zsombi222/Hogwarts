package Curse

import Card
import Events
import Request
import Type
import house

class Koromnovelo_rontas : Card(house.None, 0, "Körömnövelő rontás", Type.Curse) {
    override fun play(): Request? {
        Events.koromnoveloN++
        Events.koromnovelo = true
        Events.cursePlayedEvent()
        super.play()
        return null
    }

    override fun discard() {
        Events.koromnoveloN--
        if (Events.koromnoveloN == 0) {
            Events.koromnovelo = false
        }
        super.discard()
    }
}