package Item

import Card
import Events
import Game
import Request
import Type
import house

class Aranycikesz : Card(house.None, 8, "Aranycikesz", Type.Item) {
    override fun play(): Request? {
        Game.current.apply {
            if (Allies.cards.size >= 2) {
                Coins += 3
                Hand.cards.addAll(DrawPile.draw(2))
            } else {
                Attacks++
                Hearts++
            }
        }
        Events.itemPlayedEvent()
        super.play()
        return null
    }
}