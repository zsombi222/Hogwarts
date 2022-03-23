
object Events {


    var newAllyToTop = false
    var newAllyToTopN = 0
    var newItemToTop = false
    var newItemToTopN = 0
    var newSpellToTop = false
    var newSpellToTopN = 0

    var carbunculus = false
    var carbunculusN = 0

    var confundoCurse = false
    var confundoCurseN = 0

    var conjuctivitis = false
    var conjuctivitisN = 0

    var csalan = false
    var csalanN = 0


    val coinEvents = hashMapOf<Card, () -> Unit>()
    fun coinEvent(){
        for (event in coinEvents){
            event.value()
        }
    }

    val spellPlayedEvents = hashMapOf<Card, () -> Unit>()
    fun spellPlayedEvent() {
        for (event in spellPlayedEvents){
            event.value()
        }
    }

    val itemPlayedEvents = hashMapOf<Card, () -> Unit>()
    fun itemPlayedEvent() {
        for (event in itemPlayedEvents){
            event.value()
        }
    }

    val cardDroppedEvents = hashMapOf<Card, () -> Unit>()
    fun cardDroppedEvent() {
        for (event in cardDroppedEvents){
            event.value()
        }
    }

    val spellDroppedEvents = hashMapOf<Card, () -> Unit>()
    fun spellDroppedEvent() {
        for (event in spellDroppedEvents){
            event.value()
        }
    }

    val cursePlayedEvents = hashMapOf<Card, () -> Unit>()
    fun cursePlayedEvent() {
        for (event in cursePlayedEvents){
            event.value()
        }
    }

    val roundEndedEvents = hashMapOf<Card, () -> Unit>()
    fun roundEndedEvent(){
        for (event in roundEndedEvents){
            event.value()
        }
    }

    val drawPileEmpty = hashMapOf<Card, () -> Unit>()
    fun drawPileEmptyEvent(){
        for (event in drawPileEmpty){
            event.value()
        }
    }

    val healthIncreased = hashMapOf<Card, () -> Unit>()
    fun healthIncreasedEvent(){
        for (event in healthIncreased){
            event.value()
        }
    }
}