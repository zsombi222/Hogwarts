package Curse

import Card
import Events
import Game
import Player
import Request
import Type
import house

class Geminio : Card(house.None, 0, "Geminio", Type.Curse) {
    override fun play(): Request? {
        Game.current.apply {
            DiscardPile.cards.addAll(Game.Curses.draw(2))
        }
        Events.cursePlayedEvent()
        super.play()
        return null
    }

    override fun discard(p: Player) {
        super.destroy(p)
    }
}