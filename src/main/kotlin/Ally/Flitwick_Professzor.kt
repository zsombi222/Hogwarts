package Ally

import Card
import Events
import Game
import Player
import Request
import Response
import Type
import house

class Flitwick_Professzor : Card(house.Ravenclaw, 7, "Flitwick Professzor", Type.Ally) {
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
            return Request(::choose, "Válassz egy eldobni kívánt varázslatot\n${Game.current.Hand}")
        }
        return null
    }

    fun choose(r: Response): Boolean {
        try {
            if (Game.current.Hand.cards[r.n].type != Type.Spell) {
                println("Varázslatot kell kiválasztanod")
                used = false
                return true
            }
            Game.current.Hand.cards[r.n].drop()
            println("Eldobtál egy varázslatot")
            Game.current.apply {
                Attacks++
                Hearts++
                Hand.cards.addAll(DrawPile.draw(1))
            }
            used = true
            return true
        } catch (e: Exception) {
            println("Nincs ilyen lap")
            used = false
            return true
        }
    }

    fun reset() {
        used = false
    }

}