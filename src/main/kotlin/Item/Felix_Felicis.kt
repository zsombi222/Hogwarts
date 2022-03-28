package Item

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Felix_Felicis : Card(house.None, 5, "Felix Felicis", Type.Item) {
    override fun play(): Request? {
        Game.current.apply {
            if (Allies.cards.size >= 2) {
                Coins += 3
                Hand.cards.addAll(DrawPile.draw(2))
            } else {
                Attacks++
                Hearts++
            }
        }
        Events.itemPlayedEvent()
        super.play()
        return Request(::choose, "0 - páros\n1 - páratlan", 2)
    }

    fun choose(r: Response): Boolean {
        return when (r.n) {
            0 -> {
                Game.current.apply {
                    if (Game.ClassRoom.cards[0].value % 2 == 0)
                        Attacks += 2
                    else
                        Hearts += 2
                }
                true
            }
            1 -> {
                Game.current.apply {
                    if (Game.ClassRoom.cards[0].value % 2 == 1)
                        Attacks += 2
                    else
                        Hearts += 2
                }
                true
            }
            else -> false
        }
    }
}