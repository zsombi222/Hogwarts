
object Events {
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
}