package Ally

import Card
import Events
import Game
import Player
import Request
import Type
import house

class Luna_Lovegood : Card(house.Ravenclaw, 6, "Luna Lovegood", Type.Ally) {
    var used = false
    var player: Player? = null
    override fun play(): Request? {
        player = Game.current
        used = false
        Events.roundEndedEvents[this] = ::reset
        Events.itemPlayedEvents[this] = ::skill
        super.play()
        return null
    }

    override fun discard(p: Player) {
        try {
            Events.roundEndedEvents.remove(this)
        } catch (e: Exception) {
        }
        try {
            Events.itemPlayedEvents.remove(this)
        } catch (e: Exception) {
        }
        super.discard(p)
    }

    override fun destroy(p: Player) {
        try {
            Events.roundEndedEvents.remove(this)
        } catch (e: Exception) {
        }
        try {
            Events.itemPlayedEvents.remove(this)
        } catch (e: Exception) {
        }
        super.destroy(p)
    }

    fun reset() {
        used = false
    }

    fun skill() {
        if (!used && Game.current == player && !Events.csalan) {
            used = true
            Game.current.apply {
                Hearts++
                Hand.cards.addAll(DrawPile.draw(1))
            }
        }
    }
}