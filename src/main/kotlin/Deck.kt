import java.lang.Exception

class Deck(val dtype: decktype) {
    var cards: MutableList<Card> = mutableListOf()

    fun shuffle(){
        cards.shuffle()
    }

    fun draw(n: Int): (List<Card>) {
        val list = mutableListOf<Card>()
        for (i in 0 until n){
            if(cards.size > 0)
                list.add(cards.removeAt(0))
            else{
                Events.drawPileEmptyEvent()
                break
            }
        }
        return list
    }

    fun drawIdx(n: Int): Card{
        return try {
            cards.removeAt(n)
        }
        catch (e: Exception){
            throw Exception("out of index")
        }
    }

    override fun toString(): String {
        var s = ""
        for(i in 0 until cards.size){
            s += "\t$i. ${cards[i]}"
            if (i < cards.size-1){
                s+="\n"
            }
        }
        return s
    }

    init {
        when (dtype) {
            decktype.StarterCat ->{
                invoke<Alohomora>(7)
                invoke<Ust>(1)
                invoke<Palca>(1)
                invoke<Macska>(1)
            }
            decktype.StarterFrog ->{
                invoke<Alohomora>(7)
                invoke<Ust>(1)
                invoke<Palca>(1)
                invoke<Varangy>(1)
            }
            decktype.StarterOwl ->{
                invoke<Alohomora>(7)
                invoke<Ust>(1)
                invoke<Palca>(1)
                invoke<Bagoly>(1)
            }
            decktype.Books -> {
                invoke<Konyv>(8)
            }
            decktype.Curses -> {
                invoke<Carbunculus>(3)
                invoke<Confundo_curse>(2)
                invoke<Conjuctivitis>(2)
                invoke<Csalanartas>(2)
                invoke<Egyel_csigat>(2)
                invoke<Gancsrontas>(4)
                invoke<Geminio>(2)
                invoke<Gumilabrontas>(3)
                invoke<Koromnovelo_rontas>(2)
                invoke<Levicorpus>(4)
                invoke<Remdenever_rontas>(3)
                invoke<Sectumsempra>(2)
            }
            decktype.Classroom -> {
                invoke<Aguamenti>(2)
                invoke<Albus_Dumbledore>(1)
                invoke<Altatoital>(2)
                invoke<Aranycikesz>(1)
                invoke<Arresto_momentum>(1)
                invoke<Ascendio>(2)
                invoke<Baziteo>(2)
                invoke<Bimba_Professzor>(1)
                invoke<Bombarda>(2)
                invoke<Boszorkanyfukivonat>(1)
                invoke<Buvos_bizsere>(1)
                invoke<Capitulatus>(3)
                invoke<Cave_malicium>(2)
                invoke<Cedric_Diggory>(1)
                invoke<Cho_Chang>(1)
                invoke<Confundo>(1)
                invoke<Crucio>(1)
                invoke<Cukrozott_lepkeszarnyak>(2)
                invoke<Csokibeka>(2)
                invoke<Densaugeo>(2)
                invoke<Descendo>(2)
                invoke<Diffindo>(2)
                invoke<Disaudio>(1)
                invoke<Dobby>(1)
                invoke<Draco_Malfoy>(1)
                invoke<Exmamoriam>(2)
                invoke<Expecto_patronum>(2)
                invoke<Felix_Felicis>(1)
                invoke<Flipendo>(1)
                invoke<Flitwick_Professzor>(1)
                invoke<Fozetkeszlet>(2)
                invoke<Gildroy_Lockhart>(1)
                invoke<Ginny_Weasley>(1)
                invoke<Gregory_Monstro>(1)
                invoke<Harry_Potter>(1)
                invoke<Hermione_Granger>(1)
                invoke<Imperio>(3)
                invoke<Invito>(2)
                invoke<Kritalygomb>(2)
                invoke<Locomotor>(2)
                invoke<Lumos>(1)
                invoke<Luna_Lovegood>(1)
                invoke<Magifix_ragasztoszalag>(2)
                invoke<Mandragora>(2)
                invoke<Mcgalacony_Professzor>(1)
                invoke<Neville_Longbottom>(1)
                invoke<Nymphadora_Tonks>(1)
                invoke<Obstructo>(1)
                invoke<Oppugno>(3)
                invoke<Penna>(2)
                invoke<Perselus_Piton>(1)
                invoke<Perui_instant_sotetsegpor>(2)
                invoke<Petrificus_totalus>(4)
                invoke<Pirocus>(2)
                invoke<Piroinitio>(2)
                invoke<Priori_incantatem>(1)
                invoke<Profix>(2)
                invoke<Protego>(3)
                invoke<Purlicerpenna>(2)
                invoke<Relaxo>(2)
                invoke<Remszem_Mordon>(1)
                invoke<Remus_Lupin>(1)
                invoke<Reparo>(2)
                invoke<Revelio>(2)
                invoke<Rictusempra>(2)
                invoke<Ron_Weasley>(1)
                invoke<Rubeus_Hagrid>(1)
                invoke<Sonorus>(3)
                invoke<Stupor>(2)
                invoke<Suvickus>(2)
                invoke<Szazfule_fozet>(2)
                invoke<Tarantallegra>(1)
                invoke<Vajsor>(2)
                invoke<Vincent_Crack>(1)
                invoke<Vingardium_leviosa>(3)
                invoke<Zacharias_Smith>(1)
                invoke<Zsugoritott_fej>(1)
            }
            decktype.Empty -> {}
        }
    }

    inline operator fun <reified T : Card> invoke(n: Int) {
        repeat(n) {
            cards.add(T::class.java.getDeclaredConstructor().newInstance())
        }
    }
}

enum class decktype{
    StarterCat,
    StarterFrog,
    StarterOwl,
    Classroom,
    Books,
    Curses,
    Empty
}