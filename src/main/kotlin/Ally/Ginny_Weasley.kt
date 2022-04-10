package Ally

import Card
import Events
import Game
import Player
import Request
import Type
import house

class Ginny_Weasley : Card(house.None, 4, "Ginny Weasley", Type.Ally) {
    var used = false
    var player: Player? = null
    override fun play(): Request? {
        player = Game.current
        used = false
        Events.roundEndedEvents[this] = ::reset
        super.play()
        return null
    }

    override fun discard(p: Player) {
        try {
            Events.roundEndedEvents.remove(this)
        } catch (e: Exception) {
        }
        super.discard(p)
    }

    override fun destroy(p: Player) {
        try {
            Events.roundEndedEvents.remove(this)
        } catch (e: Exception) {
        }
        super.destroy(p)
    }

    override fun use(): Request? {
        if (!used && Game.current == player) {
            used = true
            val card = Game.current.Hand.draw(1)
            if (card[0].value >= 1) {
                Game.current.apply {
                    Attacks++
                    Coins++
                    Hand.cards.add(0, card[0])
                }
            }
        }
        return null
    }

    fun reset() {
        used = false
    }

}