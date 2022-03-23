package Ally

import Card
import Events
import Game
import Player
import Request
import Type
import house

class Albus_Dumbledore: Card(house.None, 9, "Albus Dumbledore", Type.Ally){
    var used = false
    var player: Player? = null
    override fun play(): Request? {
        player = Game.current
        used = false
        Events.roundEndedEvents[this] = ::reset
        Game.current.Hand.cards.remove(this)
        Game.current.Allies.cards.add(this)
        super.play()
        return null
    }

    override fun discard() {
        super.discard()
        Events.roundEndedEvents.remove(this)
    }

    override fun destroy() {
        Events.roundEndedEvents.remove(this)
        super.destroy()
    }

    override fun use(): Request? {
        if(!used && Game.current == player){
            used = true
            Game.current.apply{
                Attacks++
                Coins++
                Hearts++
                Hand.cards.addAll(DrawPile.draw(1))
            }
        }
        return null
    }

    fun reset(){
        used = false
    }

}