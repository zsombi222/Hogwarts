package Spell

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Expecto_patronum : Card(house.None, 5, "Expecto patronum", Type.Spell) {
    override fun play(): Request? {
        Game.current.apply {
            Coins += 2
            Hearts++
        }

        Events.spellPlayedEvent()
        super.play()
        if (Game.current.DiscardPile.getCurseNum() == 0) {
            println("Nincs rontásod a dobópakilban")
            return null
        }
        return Request(
            ::choose,
            "Válassz egy rontás lapot, amit eldobsz [0-${Game.current.DiscardPile.getCurseNum() - 1}]\n${Game.current.DiscardPile.printCurses()}",
            0
        )
    }

    fun choose(r: Response): Boolean {
        try {
            if (Game.current.DiscardPile.cards[r.n].type != Type.Curse) {
                println("Ez nem egy rontás.\nNem lett rontás elpusztítva")
                return true
            }

            Game.current.DiscardPile.cards[r.n].destroy()
            println("Elpusztítottál egy rontást")
            Game.current.Hand.cards.addAll(Game.current.DrawPile.draw(2))
            return true
        } catch (e: Exception) {
            println("Nem lett rontás elpusztítva")
            return true
        }
    }
}