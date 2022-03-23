package Spell

import Card
import Events
import Game
import Request
import Type
import house

class Disaudio : Card(house.None, 7, "Disaudio", Type.Spell) {
    override fun play(): Request? {
        Game.current.apply {
            Attacks++
            Hearts += 2
        }
        Game.opponent.DrawPile.cards.addAll(Game.Curses.draw(1))
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}