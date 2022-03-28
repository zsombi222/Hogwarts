package Spell

import Card
import Events
import Game
import Request
import Type
import house
import kotlin.random.Random

class Capitulatus : Card(house.Gryffindor, 4, "Capitulatus", Type.Spell) {
    override fun play(): Request? {
        Game.opponent.apply {
            try {
                Hand.cards[Random.nextInt(0, Hand.cards.size)].drop()
            } catch (e: Exception) {
                println("Nincs már kártya az ellenfél kezében")
            }
        }
        if (Game.current.House == this.House || Game.current.hasAllyWithHouse(this.House)) {
            Game.current.apply {
                Attacks++
            }
        }

        Events.spellPlayedEvent()
        super.play()
        return null
    }
}