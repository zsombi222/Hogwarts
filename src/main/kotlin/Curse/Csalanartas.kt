package Curse

import Card
import Events
import Request
import Type
import house

class Csalanartas: Card(house.None, 0, "Csalánártás", Type.Curse){
    override fun play(): Request? {
        Events.csalanN++
        Events.csalan = true
        Events.cursePlayedEvent()
        super.play()
        return null
    }

    override fun discard() {
        Events.csalanN--
        if(Events.csalanN == 0) {
            Events.csalan = false
        }
        super.discard()
    }
}