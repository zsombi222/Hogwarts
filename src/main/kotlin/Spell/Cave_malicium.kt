package Spell

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Cave_malicium: Card(house.None, 6, "Cave malicium", Type.Spell){
    override fun play(): Request? {
        Game.current.apply {
            Hearts += 2
        }
        Events.spellPlayedEvent()
        super.play()
        return Request(
            ::choose,
            "Válassz elpusztítandó lapot: [hand 0-${Game.current.Hand.cards.size - 1} / discard 0-${Game.current.DiscardPile.cards.size - 1}]"
        )
    }

    fun choose(r: Response): Boolean{
        when(r.text){
            "hand" -> {
                try {
                    Game.current.Hand.cards[r.n].destroy()
                }catch (e:Exception){println("Nem lett elpusztítva lap")}
            }
            "discard" -> {
                try {
                    Game.current.DiscardPile.cards[r.n].destroy()
                }catch (e:Exception){println("Nem lett elpusztítva lap")}
            }
            else -> println("Nem lett elpusztítva lap")
        }
        return true
    }
}