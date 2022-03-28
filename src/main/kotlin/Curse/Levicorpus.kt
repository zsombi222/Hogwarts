package Curse

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Levicorpus : Card(house.None, 0, "Levicorpus", Type.Curse) {
    override fun play(): Request? {
        Events.cursePlayedEvent()
        super.play()
        return if (Game.current.Allies.cards.size > 0) {
            Request(::choose, "Válassz szövetségest\n${Game.current.Allies}")
        } else
            null
    }

    fun choose(r: Response): Boolean {
        return try {
            Game.current.Allies.cards[r.n].discard()
            true
        } catch (e: Exception) {
            println("Nincs ilyen lap")
            false
        }
    }
}