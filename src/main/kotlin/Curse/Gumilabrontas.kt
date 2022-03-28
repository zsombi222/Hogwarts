package Curse

import Card
import Events
import Game
import Player
import Request
import Type
import house

class Gumilabrontas : Card(house.None, 0, "Gumilábrontás", Type.Curse) {
    override fun play(): Request? {
        Game.current.apply {
            try {
                Game.current.DrawPile.cards[0].destroy()
            } catch (e: Exception) {
                println("Üres a húzópaklid (nagy szerencse)")
            }
        }
        Events.cursePlayedEvent()
        super.play()
        return null
    }

    override fun discard(p: Player) {
        super.destroy(p)
    }
}