abstract class Player(open val name: String, open val House: house) {
    val Hand = Deck(decktype.Empty)
    var DrawPile = Deck(decktype.Empty)
    val DiscardPile = Deck(decktype.Empty)
    val Allies = Deck(decktype.Empty)
    val Played = Deck(decktype.Empty)

    var Health = 7
    var Stuns = 3

    var Hearts = 0
    var Attacks = 0;
    var Coins = 0;

    fun creasteStarterPile(dtype: decktype){
        DrawPile = Deck(dtype)
    }

    fun hasAllyWithHouse(h: house): Boolean{
        for (c in Allies.cards){
            if (c.House == h){
                return true
            }
        }
        return false
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
*  : $Attacks
¤  : $Coins
"""

    }
}

data class Human(override val name: String = "human", override val House: house = house.None): Player(name, House) {
    override fun toString(): String {
        return super.toString()
    }
}

data class Computer(override val name: String = "computer", override val House: house = house.None): Player(name, House) {
    override fun toString(): String {
        return super.toString()
    }
}