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