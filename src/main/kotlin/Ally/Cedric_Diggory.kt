package Ally

import Card
import Events
import Game
import Player
import Request
import Type
import house

class Cedric_Diggory : Card(house.Hufflepuff, 6, "Cedric Diggory", Type.Ally) {
    var used = false
    var player: Player? = null
    override fun play(): Request? {
        player = Game.current
        used = false
        Events.roundEndedEvents[this] = ::reset
        Events.healthIncreased[this] = ::skill
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
        try {
            Events.healthIncreased.remove(this)
        } catch (e: Exception) {
        }
        super.discard()
    }

    override fun destroy() {
        try {
            Events.roundEndedEvents.remove(this)
        } catch (e: Exception) {
        }
        try {
            Events.healthIncreased.remove(this)
        } catch (e: Exception) {
        }

        super.destroy()
    }

    fun reset() {
        used = false
    }

    fun skill() {
        if (!used && Game.current == player) {
            used = true
            Game.current.apply {
                Attacks++
                Hearts++
            }
        }
    }
}