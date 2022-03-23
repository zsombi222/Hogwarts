package Curse

import Card
import Events
import Request
import Type
import house

class Carbunculus: Card(house.None, 0, "Carbunculus", Type.Curse){
    override fun play(): Request? {
        Events.carbunculusN++
        Events.carbunculus = true
        Events.cursePlayedEvent()
        super.play()
        return null
    }

    override fun discard() {
        Events.carbunculusN--
        if(Events.carbunculusN == 0) {
            Events.carbunculus = false
        }
        super.discard()
    }
}