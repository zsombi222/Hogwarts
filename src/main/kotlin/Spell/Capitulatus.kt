package Spell

import Card
import Events
import Game
import Request
import Type
import house
import kotlin.random.Random

class Capitulatus: Card(house.Gryffindor, 4, "Capitulatus", Type.Spell){
    override fun play(): Request? {
        Game.opponent.apply {
            try {
                DiscardPile.cards.add(Hand.drawIdx(Random.nextInt(0, Hand.cards.size + 1)))
            }catch (e: Exception) {
                println("Nincs már kártya az ellenfél kezében")
            }
        }
        if (Game.current.House == House) {
            Game.current.apply {
                Attacks++
            }
        }

        Events.spellPlayedEvent()
        super.play()
        return null
    }
}