package Ally

import Card
import Events
import Game
import Player
import Request
import Response
import Type
import house

class Perselus_Piton : Card(house.Slytherin, 7, "Perselus Piton", Type.Ally) {
    var used = false
    var player: Player? = null
    override fun play(): Request? {
        player = Game.current
        used = false
        Events.roundEndedEvents[this] = ::reset
        super.play()
        return null
    }

    override fun discard(p: Player) {
        try {
            Events.roundEndedEvents.remove(this)
        } catch (e: Exception) {
        }
        super.discard(p)
    }

    override fun destroy(p: Player) {
        try {
            Events.roundEndedEvents.remove(this)
        } catch (e: Exception) {
        }
        super.destroy(p)
    }

    override fun use(): Request? {
        if (!used && Game.current == player) {
            return Request(
                ::choose,
                "0 - 2db villám\n1 - 2db szív\n2 - [0-${Game.current.DiscardPile.cards.size - 1}]Válassz egy elpusztítani kívánt rontást\n${Game.current.Hand}"
            )
        }
        return null
    }

    fun choose(r: Response): Boolean {
        Game.current.apply {
            return when (r.n) {
                0 -> {
                    Attacks += 2
                    used = true
                    true
                }
                1 -> {
                    Hearts += 2
                    used = true
                    true
                }
                2 -> {
                    try {
                        if (Game.current.DiscardPile.cards[r.text.toInt()].type != Type.Curse) {
                            println("Ez nem egy rontás.\nNem lett rontás elpusztítva")
                            return true
                        }
                        Game.current.DiscardPile.cards[r.text.toInt()].destroy()
                        println("Elpusztítottál egy rontást")
                        used = true
                        return true
                    } catch (e: Exception) {
                        println("Nem lett rontás elpusztítva")
                        return true
                    }
                }
                else -> false
            }
        }



        try {
            if (Game.current.Hand.cards[r.n].type != Type.Spell) {
                println("Varázslatot kell kiválasztanod")
                used = false
                return true
            }
            Game.current.Hand.cards[r.n].drop()
            println("Eldobtál egy varázslatot")
            Game.current.apply {
                Attacks++
                Hearts++
                Hand.cards.addAll(DrawPile.draw(1))
            }
            used = true
            return true
        } catch (e: Exception) {
            println("Nincs ilyen lap")
            used = false
            return true
        }
    }

    fun reset() {
        used = false
    }
}