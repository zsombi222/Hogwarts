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
        Events.itemPlayedEvent()
        super.play()
        if (Game.opponent.Allies.cards.size > 0)
            return Request(this::discardAlly, "Válassz szövetségest: [0-${Game.opponent.Allies.cards.size - 1}]")
        return null
    }

    fun discardAlly(r: Response): Boolean {
        return try {
            Game.opponent.Allies.cards[r.n].discard()
            true
        } catch (e: Exception) {
            println("Nincs ilyen szövetséges")
            false
        }
    }
}