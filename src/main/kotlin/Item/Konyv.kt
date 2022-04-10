package Item

import Card
import Events
import Game
import Player
import Request
import Response
import Type
import house

class Konyv : Card(house.None, 3, "Könyvek", Type.Item) {
    override fun play(): Request? {
        Events.itemPlayedEvent()
        super.play()
        return Request(::choose, "0 - 2db érme\n1 - húzz egy lapot", 2)
    }

    override fun discard(p: Player) {
        //nem kell super.discard(p)
        p.Played.cards.remove(this)
        println("$name visszarakása a könyvtárba...")
        Game.Books.cards.add(this)
    }

    fun choose(r: Response): Boolean {
        return when (r.n) {
            0 -> {
                Game.current.apply {
                    Coins+=2
                }
                true
            }
            1 -> {
                Game.current.apply {
                   Hand.cards.addAll(DrawPile.draw(1))
                }
                true
            }
            else -> false
        }
    }
}