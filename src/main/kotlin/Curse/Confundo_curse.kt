package Curse

import Card
import Events
import Player
import Request
import Type
import house

class Confundo_curse : Card(house.None, 0, "Confundo rontás", Type.Curse) {
    override fun play(): Request? {
        Events.confundoCurse = true
        Events.confundoCurseN++
        Events.cursePlayedEvent()
        super.play()
        return null
    }

    override fun discard(p: Player) {
        Events.confundoCurseN--
        if (Events.confundoCurseN == 0) {
            Events.confundoCurse = false
        }
        super.discard(p)
    }
}