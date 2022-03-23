package Spell

import Card
import Events
import Game
import Request
import Type
import house

class Aguamenti: Card(house.None, 3, "Aguamenti", Type.Spell){
    override fun play(): Request? {
        Game.current.apply{
            Coins += 2
            Hearts++
        }
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}