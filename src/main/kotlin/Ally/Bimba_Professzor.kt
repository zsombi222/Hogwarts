package Ally

import Card
import Events
import Game
import Player
import Request
import Type
import house

class Bimba_Professzor : Card(house.Hufflepuff, 7, "Bimba Professzor", Type.Ally) {
    var used = false
    var player: Player? = null
    override fun play(): Request? {
        player = Game.current
        used = false
        Events.roundEndedEvents[this] = ::reset
        Game.current.Hand.cards.remove(this)
        Game.current.Allies.cards.add(this)
        println("$name kijátszása...")
        return null
    }

    override fun discard() {
        try {
            Events.roundEndedEvents.remove(this)
        } catch (e: Exception) {
        }
        super.discard()
    }

    override fun destroy() {
        try {
            Events.roundEndedEvents.remove(this)
        } catch (e: Exception) {
        }
        super.destroy()
    }

    override fun use(): Request? {
        if (!used && Game.current == player) {
            used = true
            Game.current.apply {
                Coins++
                Hearts += 2
            }
        }
        return null
    }

    fun reset() {
        used = false
    }
}