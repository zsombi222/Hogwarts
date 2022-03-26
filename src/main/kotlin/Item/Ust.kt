package Item

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Ust : Card(house.None, 0, "Üst", Type.Item) {
    override fun play(): Request? {
        Events.itemPlayedEvent()
        super.play()
        return Request(
            ::choose,
            "Válassz:\n0 - 1db Érme\n1 - 1db Szív"
        )
    }

    fun choose(r: Response): Boolean {
        return when (r.n) {
            0 -> {
                Game.current.Coins++
                true
            }
            1 -> {
                Game.current.Hearts++
                true
            }
            else -> false
        }
    }
}