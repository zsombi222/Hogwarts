package Ally

import Card
import Events
import Game
import Player
import Request
import Type
import house

class Cedric_Diggory: Card(house.Hufflepuff, 6, "Cedric Diggory", Type.Ally){
    var used = false
    var player: Player? = null
    override fun play(): Request? {
        player = Game.current
        used = false
        Events.roundEndedEvents[this] = ::reset
        Events.healthIncreased[this] = ::skill
        Game.current.Hand.cards.remove(this)
        Game.current.Allies.cards.add(this)
        return null
    }

    override fun discard() {
        Events.roundEndedEvents.remove(this)
        Events.healthIncreased.remove(this)
        super.discard()
    }

    override fun destroy() {
        Events.roundEndedEvents.remove(this)
        Events.healthIncreased.remove(this)
        super.destroy()
    }

    override fun use(): Request? {
        if(!used && Game.current == player){
            used = true
            Game.current.apply{
                Coins++
                Hearts += 2
            }
        }
        return null
    }

    fun reset(){
        used = false
    }

    fun skill(){
        if(!used && Game.current == player){
            used = true
            Game.current.apply {
                Attacks++
                Hearts++
            }
        }
    }
}