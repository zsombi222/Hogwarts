package Ally

import Card
import Events
import Game
import Player
import Request
import Response
import Type
import house

class Bagoly : Card(house.None, 0, "Bagoly", Type.Ally) {
    var coins = 0
    var used = false
    var player: Player? = null
    override fun play(): Request? {
        Events.roundEndedEvents[this] = ::reset
        player = Game.current
        used = false
        super.play()
        return null
    }

    override fun use(): Request? {
        if (!used && Game.current == player) {
            return Request(::chooseFunction, "0 - rakj félre egy érmét\n1 - vedd el az összeset")
        }
        return null
    }

    override fun discard() {
        coins = 0
        try {
            Events.roundEndedEvents.remove(this)
        } catch (e: Exception) {
        }
        super.discard()
    }

    override fun destroy() {
        coins = 0
        try {
            Events.roundEndedEvents.remove(this)
        } catch (e: Exception) {
        }
        super.destroy()
    }

    fun reset() {
        used = false
    }

    fun chooseFunction(r: Response): Boolean {
        return when (r.n) {
            0 -> {
                when (Game.current.Coins) {
                    0 -> {
                        true
                    }
                    else -> {
                        Game.current.Coins--
                        coins++
                        used = true
                        true
                    }
                }
            }
            1 -> {
                if (coins != 0) {
                    Game.current.Coins += coins
                    coins = 0
                    used = true
                }
                true
            }
            else -> {
                false
            }
        }
    }

    override fun toString(): String {
        return "${super.toString()} ($coins)"
    }
}