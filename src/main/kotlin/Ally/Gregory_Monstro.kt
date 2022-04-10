package Ally

import Card
import Events
import Game
import Player
import Request
import Type
import house

class Gregory_Monstro : Card(house.Slytherin, 6, "Gregory Monstro", Type.Ally) {
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
                    Game.opponent.Hand.cards.addAll(Game.Curses.draw(1))
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