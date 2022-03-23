package Spell

import Card
import Events
import Game
import Request
import Type
import house

class Alohomora: Card(house.None, 0, "Alohomora", Type.Spell){
    override fun play(): Request? {
        Game.current.Coins += 1
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}