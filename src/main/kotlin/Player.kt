abstract class Player(open val name: String, open val House: house, val dtype: decktype = decktype.StarterOwl) {
    val Hand = Deck(decktype.Empty)
    var DrawPile = Deck(decktype.Empty)
    val DiscardPile = Deck(decktype.Empty)
    val Allies = Deck(decktype.Empty)
    val Played = Deck(decktype.Empty)

    var Max_health = 7
    var Health = 7
    var Stuns = 3

    var Hearts = 0
    var Attacks = 0;
    var Coins = 0;

    init {
        createStarterPile(dtype)
        DrawPile.shuffle()
        Events.reshuffle[DrawPile] = ::resetDrawPile
    }

    fun resetDrawPile(to: Deck) {
        if (to == this.DrawPile) {
            DrawPile.cards.addAll(DiscardPile.drawAll())
            DrawPile.shuffle()
            println("$name újrakeveri a húzópakliját...")
        }
    }

    fun createStarterPile(dtype: decktype) {
        DrawPile = Deck(dtype)
    }

    fun hasAllyWithHouse(h: house): Boolean {
        for (c in Allies.cards) {
            if (c.House == h) {
                return true
            }
        }
        return false
    }

    fun buy(idx: Int) {
        val temp = Game.ClassRoom4.cards[idx]
        if (Coins >= Game.ClassRoom4.cards[idx].value) {
            Coins -= Game.ClassRoom4.cards[idx].value
            when (Game.ClassRoom4.cards[idx].type) {
                Type.Item -> {
                    if (Events.newItemToTop) {
                        DrawPile.cards.add(Game.ClassRoom4.drawIdx(idx))
                    } else {
                        DiscardPile.cards.add(Game.ClassRoom4.drawIdx(idx))
                    }
                }
                Type.Spell -> {
                    if (Events.newSpellToTop) {
                        DrawPile.cards.add(Game.ClassRoom4.drawIdx(idx))
                    } else {
                        DiscardPile.cards.add(Game.ClassRoom4.drawIdx(idx))
                    }
                }
                Type.Ally -> {
                    if (Events.newAllyToTop) {
                        DrawPile.cards.add(Game.ClassRoom4.drawIdx(idx))
                    } else {
                        DiscardPile.cards.add(Game.ClassRoom4.drawIdx(idx))
                    }
                }
                else -> DiscardPile.cards.add(Game.ClassRoom4.drawIdx(idx))
            }
            Game.ClassRoom4.cards.addAll(Game.ClassRoom.draw(1))
            println("Megvetted a ${temp.name} lapot ${temp.value} érméért\n${Coins}db érméd maradt")
        } else {
            println("Nincs elég pénzed (${Coins}/${temp.value})")
        }
    }

    /*
    returns true if opponent died
     */
    fun attack(): Boolean {
        Game.opponent.Health -= Attacks
        println("${Game.opponent.Health}/${Game.opponent.Max_health} élete maradt ${Game.opponent.name} játékosnak")
        Attacks = 0
        if (Game.opponent.Health <= 0) {
            Game.opponent.Stuns--
            println("${Game.opponent.name} elájult")
            if (Game.opponent.Stuns == 0) {
                println("Vége a játéknak, (de folytathatjátok ha szeretnétek)")
            }
            val tmp = Game.current
            Game.current = Game.opponent
            Game.opponent = tmp
            return true
        }
        return false
    }

    fun heal() {
        val tmp = minOf(Max_health - Health, Hearts)
        Health = minOf(Max_health, Health + Hearts)
        Hearts -= tmp
        println("${Health}/${Max_health} életed van")
    }

    override fun toString(): String {
        return """
$name: ($House)
Kézben: 
${Hand}
Kijátszott: 
${Played}
Szövetségesek: 
${Allies}
Húzópakli: 
${DrawPile}
Dobópakli: 
${DiscardPile}
Élet: $Health
Pont: $Stuns
<3 : $Hearts
/  : $Attacks
$  : $Coins
"""

    }
}

data class Human(override val name: String = "human", override val House: house = house.None) : Player(name, House) {
    override fun toString(): String {
        return super.toString()
    }
}

data class Computer(override val name: String = "computer", override val House: house = house.None) :
    Player(name, House) {
    override fun toString(): String {
        return super.toString()
    }
}