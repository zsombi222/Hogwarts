package Item

import Card
import Events
import Game
import Request
import Type
import house

class Boszorkanyfukivonat: Card(house.None, 6, "Boszorkányfűkivonat", Type.Item){
    override fun play(): Request? {
        Game.current.apply {
            Coins++
            Hearts++
        }
        Events.newAllyToTopN++
        Events.newAllyToTop = true
        Events.itemPlayedEvent()
        super.play()
        return null
    }

    override fun discard() {
        Events.newAllyToTopN--
        if(Events.newAllyToTopN == 0) {
            Events.newAllyToTop = false
        }
        super.discard()
    }
}