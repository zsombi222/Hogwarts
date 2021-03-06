package Ally

import Card
import Events
import Game
import Player
import Request
import Response
import Type
import house

class Cho_Chang : Card(house.Ravenclaw, 6, "Cho Chang", Type.Ally) {
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
            Game.current.apply {
                Hand.cards.addAll(DrawPile.draw(1))
            }
            return Request(::dropcard, "Drop a card [0-${Game.current.Hand.cards.size - 1}]\n${Game.current.Hand}")
        }
        return null
    }

    fun dropcard(r: Response): Boolean {
        return try {
            Game.current.apply {
                Hand.cards[r.n].drop()
            }
            true
        } catch (e: Exception) {
            println("Nem létezik ilyen lap")
            false
        }
    }

    fun reset() {
        used = false
    }
}