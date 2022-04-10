package Spell

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Lumos : Card(house.None, 6, "Lumos", Type.Spell) {
    override fun play(): Request? {
        Game.current.apply {
            Coins += 2
            Attacks++
        }
        val card = Game.current.Hand.draw(1)[0]
        Game.current.Hand.cards.add(0, card)
        Events.spellPlayedEvent()
        super.play()
        return Request(::choose, "0 - ${card.name} kézbe vétele\n1 - ${card.name} eldobása")
    }

    fun choose(r: Response): Boolean{
        Game.current.apply {
            Hand.cards.addAll(DrawPile.draw(1))
            return when (r.n) {
                0 -> {
                    true
                }
                1 -> {
                    Hand.cards[Hand.cards.size-1].drop()
                    true
                }
                else -> false
            }
        }
    }
}