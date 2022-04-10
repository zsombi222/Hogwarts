package Item

import Card
import Events
import Game
import Player
import Request
import Type
import house

class Magifix_ragasztoszalag : Card(house.Ravenclaw, 4, "Magifix ragasztószalag", Type.Item) {
    override fun play(): Request? {
        Game.current.apply {
            Coins += 2
        }
        if(Game.current.House == this.House || Game.current.hasAllyWithHouse(this.House)){
            Game.current.Hearts += 2
        }
        Events.newSpellToTopN++
        Events.newSpellToTop = true
        Events.itemPlayedEvent()
        super.play()
        return null
    }

    override fun discard(p: Player) {
        Events.newSpellToTopN--
        if (Events.newSpellToTopN == 0) {
            Events.newSpellToTop = false
        }
        super.discard(p)
    }
}