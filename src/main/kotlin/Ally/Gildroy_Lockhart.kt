package Ally

import Card
import Events
import Game
import Player
import Request
import Response
import Type
import house

class Gildroy_Lockhart : Card(house.Ravenclaw, 5, "Gildroy Lockhart", Type.Ally) {
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
            return Request(
                ::dropcard,
                "Drop a card [0-${Game.current.Hand.cards.size - 1}] [0-${Game.opponent.Hand.cards.size - 1}]\n${Game.current.Hand}\n---\n${Game.opponent.Hand}"
            )
        }
        return null
    }

    fun dropcard(r: Response): Boolean {
        return try {
            val c1 = Game.current.Hand.cards[r.n]
            val c2 = Game.opponent.Hand.cards[r.text.toInt()]
            c1.drop()
            c2.drop()
            used = true
            true
        } catch (e: Exception) {
            println("Nem sikerült")
            true
        }
    }

    fun reset() {
        used = false
    }
}