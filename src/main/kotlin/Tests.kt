import Ally.*
import Curse.*
import Item.*
import Spell.*

class Tests {
    companion object {
        fun run(n: Int) {
            println("Teszt $n futtatása...")
            when (n) {
                0 -> {
                    Tests.add("alohomora", 7)
                    add("macska")
                    add("ust")
                    add("palca")
                    turn()
                    add("alohomora", 7)
                    add("varangy")
                    add("ust")
                    add("palca")
                    print()
                }
            }
        }

        fun add(card: String, n: Int = 1) {
            val c = mutableListOf<Card>()

            try {
                val ca = ctrs[keys.indexOf(card)].getDeclaredConstructor().newInstance()
                repeat(n) {
                    c.add(ca)
                }
                println("${Game.current.name} kapott $n ${ca.name} lapot")
            } catch (e: Exception) {
                println("Nincs ilyen lap")
            }
            Game.current.Hand.cards.addAll(c)
        }

        fun add(idx: Int, n: Int = 1) {
            val c = mutableListOf<Card>()
            try {
                val ca = ctrs[idx].getDeclaredConstructor().newInstance()
                repeat(n) {
                    c.add(ca)
                }
                println("${Game.current.name} kapott $n ${ca.name} lapot")
            } catch (e: Exception) {
                println("Nincs ilyen lap")
            }
            Game.current.Hand.cards.addAll(c)
        }

        fun turn() {
            Game.current.apply {
                DiscardPile.cards.addAll(Game.current.Hand.drawAll())
                DiscardPile.cards.addAll(Game.current.Played.drawAll())
                Hand.cards.addAll((Game.current.DrawPile.draw(5)))
                Hearts = 0
                Coins = 0
                Attacks = 0
            }


            val temp = Game.current
            Game.current = Game.opponent
            Game.opponent = temp
            println(Game.current.name + " következik")
            CurseController.reset()
            Events.roundEndedEvent()
        }

        fun newRound() {
            for (p in listOf<Player>(Game.current, Game.opponent)) {
                p.apply {
                    Hearts = 0
                    Coins = 0
                    Attacks = 0
                    DiscardPile.cards.addAll(Hand.drawAll())
                    DiscardPile.cards.addAll(Played.drawAll())
                    DiscardPile.cards.addAll(Allies.drawAll())
                    Hand.cards.addAll((DrawPile.draw(5)))
                    Health = Max_health
                }
            }
            println("${Game.current.name} következik")
        }


        fun print() {
            println("_____________________")
            println("______Ellenfél_______")
            print(Game.opponent)
            println()
            println("_____________________")
            println("__Jelenlegi játékos__")
            print(Game.current)


        }

        val keys = setOf(
            "aguamenti",
            "albus_dumbledore",
            "altatoital",
            "aranycikesz",
            "arresto_momentum",
            "ascendio",
            "baziteo",
            "bimba_professzor",
            "bombarda",
            "boszorkanyfukivonat",
            "buvos_bizsere",
            "capitulatus",
            "cave_malicium",
            "cedric_diggory",
            "cho_chang",
            "confundo",
            "crucio",
            "cukrozott_lepkeszarnyak",
            "csokibeka",
            "densaugeo",
            "descendo",
            "diffindo",
            "disaudio",
            "dobby_a_hazimano",
            "draco_malfoy",
            "exmamoriam",
            "expecto_patronum",
            "felix_felicis",
            "flipendo",
            "flitwick_professzor",
            "fozetkeszlet",
            "gildroy_lockhart",
            "ginny_weasley",
            "gregory_monstro",
            "harry_potter",
            "hermione_granger",
            "imperio",
            "invito",
            "kritalygomb",
            "locomotor",
            "lumos",
            "luna_lovegood",
            "magifix_ragasztoszalag",
            "mandragora",
            "mcgalacony_professzor",
            "neville_longbottom",
            "nymphadora_tonks",
            "obstructo",
            "oppugno",
            "penna",
            "perselus_piton",
            "perui_instant_sotetsegpor",
            "petrificus_totalus",
            "pirocus",
            "piroinitio",
            "priori_incantatem",
            "profix",
            "protego",
            "purlicerpenna",
            "relaxo",
            "remszem_mordon",
            "remus_lupin",
            "reparo",
            "revelio",
            "rictusempra",
            "ron_weasley",
            "rubeus_hagrid",
            "sonorus",
            "stupor",
            "suvickus",
            "szazfule_fozet",
            "tarantallegra",
            "vajsor",
            "vincent_crack",
            "vingardium_leviosa",
            "zacharias_smith",
            "zsugoritott_fej",
            "carbunculus",
            "confundo_rontas",
            "conjuctivitis",
            "csalanartas",
            "egyel_csigat",
            "gancsrontas",
            "geminio",
            "gumilabrontas",
            "koromnovelo_rontas",
            "levicorpus",
            "remdenever_rontas",
            "sectumsempra",
            "konyv",
            "alohomora",
            "ust",
            "palca",
            "bagoly",
            "varangy",
            "macska"
        )

        val d = Deck(decktype.Empty)
        val ctrs = mutableListOf(
            Aguamenti::class.java,
            Albus_Dumbledore::class.java,
            Altatoital::class.java,
            Aranycikesz::class.java,
            Arresto_momentum::class.java,
            Ascendio::class.java,
            Baziteo::class.java,
            Bimba_Professzor::class.java,
            Bombarda::class.java,
            Boszorkanyfukivonat::class.java,
            Buvos_bizsere::class.java,
            Capitulatus::class.java,
            Cave_malicium::class.java,
            Cedric_Diggory::class.java,
            Cho_Chang::class.java,
            Confundo::class.java,
            Crucio::class.java,
            Cukrozott_lepkeszarnyak::class.java,
            Csokibeka::class.java,
            Densaugeo::class.java,
            Descendo::class.java,
            Diffindo::class.java,
            Disaudio::class.java,
            Dobby::class.java,
            Draco_Malfoy::class.java,
            Exmamoriam::class.java,
            Expecto_patronum::class.java,
            Felix_Felicis::class.java,
            Flipendo::class.java,
            Flitwick_Professzor::class.java,
            Fozetkeszlet::class.java,
            Gildroy_Lockhart::class.java,
            Ginny_Weasley::class.java,
            Gregory_Monstro::class.java,
            Harry_Potter::class.java,
            Hermione_Granger::class.java,
            Imperio::class.java,
            Invito::class.java,
            Kritalygomb::class.java,
            Locomotor::class.java,
            Lumos::class.java,
            Luna_Lovegood::class.java,
            Magifix_ragasztoszalag::class.java,
            Mandragora::class.java,
            Mcgalacony_Professzor::class.java,
            Neville_Longbottom::class.java,
            Nymphadora_Tonks::class.java,
            Obstructo::class.java,
            Oppugno::class.java,
            Penna::class.java,
            Perselus_Piton::class.java,
            Perui_instant_sotetsegpor::class.java,
            Petrificus_totalus::class.java,
            Pirocus::class.java,
            Piroinitio::class.java,
            Priori_incantatem::class.java,
            Profix::class.java,
            Protego::class.java,
            Purlicerpenna::class.java,
            Relaxo::class.java,
            Remszem_Mordon::class.java,
            Remus_Lupin::class.java,
            Reparo::class.java,
            Revelio::class.java,
            Rictusempra::class.java,
            Ron_Weasley::class.java,
            Rubeus_Hagrid::class.java,
            Sonorus::class.java,
            Stupor::class.java,
            Suvickus::class.java,
            Szazfule_fozet::class.java,
            Tarantallegra::class.java,
            Vajsor::class.java,
            Vincent_Crack::class.java,
            Vingardium_leviosa::class.java,
            Zacharias_Smith::class.java,
            Zsugoritott_fej::class.java,
            Carbunculus::class.java,
            Confundo_curse::class.java,
            Conjuctivitis::class.java,
            Csalanartas::class.java,
            Egyel_csigat::class.java,
            Gancsrontas::class.java,
            Geminio::class.java,
            Gumilabrontas::class.java,
            Koromnovelo_rontas::class.java,
            Levicorpus::class.java,
            Remdenever_rontas::class.java,
            Sectumsempra::class.java,
            Konyv::class.java,
            Alohomora::class.java,
            Ust::class.java,
            Palca::class.java,
            Bagoly::class.java,
            Varangy::class.java,
            Macska::class.java
        )
    }


}