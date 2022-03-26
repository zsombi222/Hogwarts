package Ally

import Card
import Events
import Game
import Player
import Request
import Response
import Type
import house

class Dobby : Card(house.None, 4, "Dobby a házimanó", Type.Ally) {
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
            return Request(::dropcard, "Drop a card [0,1,2,3}]")
        }
        return null
    }

    fun dropcard(r: Response): Boolean {
        return try {
            Game.current.apply {
                Game.ClassRoom4.cards[r.n].destroy()
            }
            true
        } catch (e: Exception) {
            println("Nem lett elpusztítva lap")
            true
        }
    }

    fun reset() {
        used = false
    }
}