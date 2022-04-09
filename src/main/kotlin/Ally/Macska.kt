package Ally

import Card
import Events
import Game
import Player
import Request
import Type
import house

class Macska : Card(house.None, 0, "Macska", Type.Ally) {
    var used = false
    var player: Player? = null
    var numOfSpellsPlayed = 0

    override fun play(): Request? {
        player = Game.current
        used = false
        numOfSpellsPlayed = 0
        Events.roundEndedEvents[this] = ::reset
        Events.spellPlayedEvents[this] = ::skill
        super.play()
        return null
    }

    override fun discard(p: Player) {
        try {
            Events.roundEndedEvents.remove(this)
        } catch (e: Exception) {
        }
        try {
            Events.spellPlayedEvents.remove(this)
        } catch (e: Exception) {
        }
        super.discard(p)
    }

    override fun destroy(p: Player) {
        try {
            Events.roundEndedEvents.remove(this)
        } catch (e: Exception) {
        }
        try {
            Events.spellPlayedEvents.remove(this)
        } catch (e: Exception) {
        }
        super.destroy(p)
    }

    fun reset() {
        used = false
        numOfSpellsPlayed = 0
    }

    fun skill() {
        numOfSpellsPlayed++
        if (!used && Game.current == player && !Events.csalan && numOfSpellsPlayed == 3) {
            used = true
            Game.current.apply {
                Attacks++
            }
        }
    }
}