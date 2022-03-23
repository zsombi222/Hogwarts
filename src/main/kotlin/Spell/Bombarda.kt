package Spell

import Card
import Events
import Game
import Request
import Response
import Type
import house

class Bombarda: Card(house.None, 2, "Bombarda", Type.Spell){
    override fun play(): Request? {
        Game.current.apply{
            Attacks++
        }
        Events.spellPlayedEvent()
        super.play()
        return Request(::destroy, "Válassz egy elpusztítani kívánt lapot a tanteremből [0,1,2,3, ]:", 5)
    }

    fun destroy(r: Response): Boolean{
        return try {
            Game.Destructed.cards.add(Game.ClassRoom4.drawIdx(r.n))
            true
        } catch (e: Exception){
            println("Nem lett kártya elpusztítva")
            true
        }
    }
}