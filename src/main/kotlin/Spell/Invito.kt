package Spell

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Invito : Card(house.None, 3, "Invito", Type.Spell) {
    override fun play(): Request? {
        Events.spellPlayedEvent()
        super.play()
        return Request(
            ::choose,
            "coins - 2 érme\nVagy válassz egy tárgyat amit a kezedbe veszel [0-${Game.current.DiscardPile.cards.size - 1}].\n${Game.current.DiscardPile}"
        )
    }

    fun choose(r: Response): Boolean {
        if (r.text == "coins"){
            Game.current.Coins += 2
            return true
        }
        if (Game.current.DiscardPile.cards.size == 0) {
            println("Nincs a dobópakliban lap")
            return false
        }
        if (!Game.current.DiscardPile.hasItems()){
            println("Nincs tárgy a dobópakliban")
            return false
        }
        try {
            if (Game.current.Hand.cards[r.n].type != Type.Item) {
                println("Tárgyat válassz!")
                return false
            }
            Game.current.Hand.cards.add(Game.current.DiscardPile.drawIdx(r.n))
            println("Kezedbe vettél egy tárgyat")
            return true
        } catch (e: Exception) {
            println("Nincs ilyen lap")
            return false
        }
    }
}