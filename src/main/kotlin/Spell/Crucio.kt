package Spell

import Card
import Game
import Request
import Type
import house

class Crucio: Card(house.None, 8, "Crucio", Type.Spell){
    override fun play(): Request? {
        Game.opponent.apply {
            DiscardPile.cards.addAll(Game.Curses.draw(1))
            Game.current.Attacks++
            Game.current.Attacks += DiscardPile.getCurseNum()
        }
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}