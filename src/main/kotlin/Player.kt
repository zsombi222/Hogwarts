open class Player {
    val Hand = Deck(decktype.Empty)
    val DrawPile = Deck(decktype.Starter)
    val DiscardPile = Deck(decktype.Empty)
    val Companions = Deck(decktype.Empty)

    var Health = 7

    var Hearts = 0
    var Lightnings = 0;
    var Coins = 0;
}

class Human: Player() {
}

class Computer: Player() {
}