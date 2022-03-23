package Item

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Altatoital : Card(house.None, 7, "Altatóital", Type.Item) {
    override fun play(): Request? {
        Game.current.apply {
            Attacks += 2
        }
        val r = Request(::discardAlly, "Válassz szövetségest: [0-${Game.opponent.Allies.cards.size - 1}]")
        Events.itemPlayedEvent()
        super.play()
        return r
    }

    fun discardAlly(r: Response): Boolean {
        try {
            Game.opponent.Allies.cards[r.n].discard()
            return true
        } catch (e: Exception) {
            println("Nincs ilyen szövetséges")
        }
        return false
    }
}