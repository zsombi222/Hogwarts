package Spell

import Card
import Events
import Game
import Request
import Type
import house
import kotlin.random.Random

class Confundo: Card(house.None, 7, "Confundo", Type.Spell){
    override fun play(): Request? {
        Game.opponent.apply {
            try {
                val c = Hand.drawIdx(Random.nextInt(0, Hand.cards.size + 1))
                DiscardPile.cards.add(c)
                if(c.type == Type.Curse || c.type == Type.Spell){
                    Game.current.Coins++
                }
                else if (c.type == Type.Ally || c.type == Type.Item){
                    Game.current.Attacks++
                    Game.current.Hearts++
                }
            }catch (e: Exception) {
                println("Nincs már kártya az ellenfél kezében")
            }
        }
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}