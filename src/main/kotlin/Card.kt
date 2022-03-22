import kotlin.random.Random

enum class Type{
    Item,
    Spell,
    Ally,
    Curse
}


abstract class Card(val house: house,val value: Int, val name: String, val type: Type) {
    open fun play(): Request? {
        Game.current.Hand.cards.remove(this)
        Game.current.Played.cards.add(this)
        return null
    }
    open fun drop() {
        Game.current.Hand.cards.remove(this)
        Game.current.DiscardPile.cards.add(this)
    }
    open fun use(): Request? { return null }
    override fun toString(): String {
        return name
    }

}

class Aguamenti: Card(house.None, 3, "Aguamenti", Type.Spell){
    override fun play(): Request? {
        Game.current.apply{
            Coins += 2
            Hearts++
        }
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}

class Albus_Dumbledore: Card(house.None, 9, "Albus Dumbledore", Type.Ally){
    var used = false
    var player: Player? = null
    override fun play(): Request? {
        player = Game.current
        used = false
        Events.roundEndedEvents[this] = ::reset
        Game.current.Hand.cards.remove(this)
        Game.current.Allies.cards.add(this)
        return null
    }

    override fun drop() {
        Events.roundEndedEvents.remove(this)
    }

    override fun use(): Request? {
        if(!used && Game.current == player){
            used = true
            Game.current.apply{
                Attacks++
                Coins++
                Hearts++
                Hand.cards.addAll(DrawPile.draw(1))
            }
        }
        return null
    }

    fun reset(){
        used = false
    }

}

class Alohomora: Card(house.None, 0, "Alohomora", Type.Spell){
    override fun play(): Request? {
        Game.current.Coins += 1
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}

class Altatoital: Card(house.None, 7, "Altatóital", Type.Item){
    override fun play(): Request? {
        Game.current.apply {
            Attacks += 2
        }
        super.play()
        val r = Request(::discardAlly, "Válassz szövetségest: [0-${Game.opponent.Allies.cards.size-1}]")
        Events.itemPlayedEvent()
        return r
    }

    fun discardAlly(r: Response): Boolean{
        try{
            Game.opponent.DiscardPile.cards.add(Game.opponent.Allies.cards.removeAt(r.n))
            return true
        } catch (e: Exception) {
            println("Nincs ilyen szövetséges")
        }
        return false
    }
}

class Aranycikesz: Card(house.None, 8, "Aranycikesz", Type.Item){
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

class Arresto_momentum: Card(house.None, 3, "Arresto momentum", Type.Spell){
    override fun play(): Request? {
        Game.current.apply {
            Hearts++
            Hand.cards.addAll(DrawPile.draw(1))
        }
        Events.spellPlayedEvent()
        super.play()
        return null
    }

    override fun drop() {
        Game.current.apply {
            Hearts++
        }
        super.drop()
    }
}

class Ascendio: Card(house.None, 4, "Ascendio", Type.Spell){
    override fun play(): Request? {
        Game.current.apply {
            Coins += 2
            Hand.cards.addAll(DrawPile.draw(1))
        }
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}

class Bagoly: Card(house.None, 0, "Bagoly", Type.Ally){
    var coins = 0
    var used = false
    var player: Player? = null
    override fun play(): Request? {
        Events.roundEndedEvents[this] = ::reset
        Game.current.Hand.cards.remove(this)
        Game.current.Allies.cards.add(this)
        player = Game.current
        used = false
        return null
    }

    override fun use(): Request? {
        if(!used && Game.current == player) {
            return Request(::chooseFunction, "0 - rakj félre egy érmét\n1 - vedd el az összeset")
        }
        return null
    }

    override fun drop() {
        coins = 0
        Events.roundEndedEvents.remove(this)
        super.drop()
    }

    fun reset(){
        used = false
    }

    fun chooseFunction(r: Response): Boolean{
        return when (r.n){
            0 -> {
                when (Game.current.Coins){
                    0 -> { false }
                    else -> {
                        Game.current.Coins--
                        coins++
                        used = true
                        true
                    }
                }
            }
            1 -> {
                Game.current.Coins += coins
                coins = 0
                used = true
                true
            }
            else -> { false }
        }
    }

}

class Baziteo: Card(house.Hufflepuff, 6, "Baziteo", Type.Spell){
    override fun play(): Request? {
        Game.current.apply {
            Coins++
            Hearts++
            Hand.cards.addAll(DrawPile.draw(1))
        }
        if(Game.current.House == this.house || Game.current.hasAllyWithHouse(this.house)){
            Game.current.apply {
                Attacks += 2
            }
        }
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}

class Bimba_Professzor: Card(house.Hufflepuff, 7, "Bimba Professzor", Type.Ally){
    var used = false
    var player: Player? = null
    override fun play(): Request? {
        player = Game.current
        used = false
        Events.roundEndedEvents[this] = ::reset
        Game.current.Hand.cards.remove(this)
        Game.current.Allies.cards.add(this)
        return null
    }

    override fun drop() {
        Events.roundEndedEvents.remove(this)
    }

    override fun use(): Request? {
        if(!used && Game.current == player){
            used = true
            Game.current.apply{
                Coins++
                Hearts += 2
            }
        }
        return null
    }

    fun reset(){
        used = false
    }
}

class Bombarda: Card(house.None, 2, "Bombarda", Type.Spell){
    override fun play(): Request? {
        Game.current.apply{
            Attacks++
        }
        Events.spellPlayedEvent()
        super.play()
        return Request(::destroy, "Válassz egy elpusztítani kívánt lapot a tanteremből [0,1,2,3, ]:", 5)
    }

    fun destroy(r: Response): Boolean{
        return try {
            Game.Destructed.cards.add(Game.ClassRoom4.drawIdx(r.n))
            true
        } catch (e: Exception){
            println("Nem lett kártya elpusztítva")
            true
        }
    }
}

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

    override fun drop() {
        Events.newAllyToTopN--
        if(Events.newAllyToTopN == 0) {
            Events.newAllyToTop = false
        }
        super.drop()
    }
}

class Buvos_bizsere: Card(house.None, 7, "Bűvös bizsere", Type.Item){
    override fun play(): Request? {
        Game.current.apply {
            Attacks += 2
            Hearts += 2
            Hand.cards.addAll(DrawPile.draw(1))
        }
        Events.itemPlayedEvent()
        super.play()
        return null
    }
}

class Capitulatus: Card(house.Gryffindor, 4, "Capitulatus", Type.Spell){
    override fun play(): Request? {
        Game.opponent.apply {
            try {
                DiscardPile.cards.add(Hand.drawIdx(Random.nextInt(0, Hand.cards.size+1)))
            }catch (e: Exception) {
                println("Nincs már kártya az ellenfél kezében")
            }
        }
        if (Game.current.House == house) {
            Game.current.apply {
                Attacks++
            }
        }

        Events.spellPlayedEvent()
        super.play()
        return null
    }
}

class Carbunculus: Card(house.None, 0, "Carbunculus", Type.Curse){
    override fun play(): Request? {
        Events.carbunculusN++
        Events.carbunculus = true
        Events.cursePlayedEvent()
        super.play()
        return null
    }

    override fun drop() {
        Events.carbunculusN--
        if(Events.carbunculusN == 0) {
            Events.carbunculus = false
        }
        super.drop()
    }
}

class Cave_malicium: Card(house.None, 6, "Cave malicium", Type.Spell){
    override fun play(): Request? {
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}

class Cedric_Diggory: Card(house.Hufflepuff, 6, "Cedric Diggory", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Cho_Chang: Card(house.Ravenclaw, 6, "Cho Chang", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Confundo: Card(house.None, 7, "Confundo", Type.Spell){
    override fun play(): Request? {
        Events.spellPlayedEvent()
        super.play()
        return null
    }
}

class Confundo_curse: Card(house.None, 0, "Confundo_curse", Type.Curse){
    override fun play(): Request? {
        return null
    }
}

class Conjuctivitis: Card(house.None, 0, "Conjuctivitis", Type.Curse){
    override fun play(): Request? {
        return null
    }
}

class Crucio: Card(house.None, 8, "Crucio", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Csalanartas: Card(house.None, 0, "Csalánártás", Type.Curse){
    override fun play(): Request? {
        return null
    }
}

class Cukrozott_lepkeszarnyak: Card(house.Gryffindor, 4, "Cukrozott lepkeszárnyak", Type.Item){
    override fun play(): Request? {
        return null
    }
}

class Csokibeka: Card(house.Gryffindor, 3, "Csokibéka", Type.Item){
    override fun play(): Request? {
        return null
    }
}

class Densaugeo: Card(house.Slytherin, 5, "Densaugeo", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Descendo: Card(house.Gryffindor, 5, "Descendo", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Diffindo: Card(house.Hufflepuff, 5, "Diffindo", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Disaudio: Card(house.None, 7, "Disaudio", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Dobby: Card(house.None, 4, "Dobby a házimanó", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Draco_Malfoy: Card(house.Slytherin, 6, "Draco Malfoy", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Egyel_csigat: Card(house.None, 0, "Egyél csigát", Type.Curse){
    override fun play(): Request? {
        return null
    }
}

class Exmamoriam: Card(house.Ravenclaw, 5, "Exmamoriam", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Expecto_patronum: Card(house.None, 5, "Expecto patronum", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Felix_Felicis: Card(house.None, 5, "Felix Felicis", Type.Item){
    override fun play(): Request? {
        return null
    }
}

class Flipendo: Card(house.None, 8, "Flipendo", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Flitwick_Professzor: Card(house.Ravenclaw, 7, "Flitwick Professzor", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Fozetkeszlet: Card(house.None, 5, "Főzetkészlet", Type.Item){
    override fun play(): Request? {
        return null
    }
}
class Gancsrontas: Card(house.None, 0, "Gáncsrontás", Type.Curse){
    override fun play(): Request? {
        return null
    }
}
class Geminio: Card(house.None, 0, "Geminio", Type.Curse){
    override fun play(): Request? {
        return null
    }
}

class Gildroy_Lockhart: Card(house.Ravenclaw, 5, "Gildroy Lockhart", Type.Ally){
    override fun play(): Request? {
        return null
    }
}
class Ginny_Weasley: Card(house.None, 4, "Ginny Weasley", Type.Ally){
    override fun play(): Request? {
        return null
    }
}
class Gregory_Monstro: Card(house.Slytherin, 6, "Gregory Monstro", Type.Ally){
    override fun play(): Request? {
        return null
    }
}
class Gumilabrontas: Card(house.None, 0, "Gumilábrontás", Type.Curse){
    override fun play(): Request? {
        return null
    }
}

class Harry_Potter: Card(house.Gryffindor, 6, "Harry Potter", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Hermione_Granger: Card(house.Gryffindor, 6, "Hermione Granger", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Imperio: Card(house.Slytherin, 4, "Imperio", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Invito: Card(house.None, 3, "Invito", Type.Spell){
    override fun play(): Request? {
        return null
    }
}
class Konyv: Card(house.None, 3, "Könyv", Type.Item){
    override fun play(): Request? {
        return null
    }
}
class Koromnovelo_rontas: Card(house.None, 0, "Körömnövelő rontás", Type.Curse){
    override fun play(): Request? {
        return null
    }
}

class Kritalygomb: Card(house.Ravenclaw, 3, "Kritálygömb", Type.Item){
    override fun play(): Request? {
        return null
    }
}
class Levicorpus: Card(house.None, 0, "Levicorpus", Type.Curse){
    override fun play(): Request? {
        return null
    }
}
class Locomotor: Card(house.Ravenclaw, 6, "Locomotor", Type.Spell){
    override fun play(): Request? {
        return null
    }
}
class Lumos: Card(house.None, 6, "Lumos", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Luna_Lovegood: Card(house.Ravenclaw, 6, "Luna Lovegood", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Macska: Card(house.None, 0, "Macska", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Magifix_ragasztoszalag: Card(house.Ravenclaw, 4, "Magifix ragasztószalag", Type.Item){
    override fun play(): Request? {
        return null
    }
}

class Mandragora: Card(house.Hufflepuff, 4, "Mandragóra", Type.Item){
    override fun play(): Request? {
        return null
    }
}
class Mcgalacony_Professzor: Card(house.Gryffindor, 7, "McGalacony Professzor", Type.Ally){
    override fun play(): Request? {
        return null
    }
}
class Neville_Longbottom: Card(house.None, 4, "Neville Longbottom", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Nymphadora_Tonks: Card(house.Hufflepuff, 6, "Nymphadora Tonks", Type.Ally){
    override fun play(): Request? {
        return null
    }
}
class Obstructo: Card(house.None, 7, "Obstructo", Type.Spell){
    override fun play(): Request? {
        return null
    }
}
class Oppugno: Card(house.None, 5, "Oppugno", Type.Spell){
    override fun play(): Request? {
        return null
    }
}
class Palca: Card(house.None, 0, "Pálca", Type.Item){
    override fun play(): Request? {
        return null
    }
}

class Penna: Card(house.Hufflepuff, 3, "Penna", Type.Item){
    override fun play(): Request? {
        return null
    }
}

class Perselus_Piton: Card(house.Slytherin, 7, "Perselus Piton", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Perui_instant_sotetsegpor: Card(house.Slytherin, 4, "Perui instant sötétségpor", Type.Item){
    override fun play(): Request? {
        return null
    }
}

class Petrificus_totalus: Card(house.None, 2, "Petrificus totalus", Type.Spell){
    override fun play(): Request? {
        return null
    }
}
class Pirocus: Card(house.None, 4, "Pirocus", Type.Spell){
    override fun play(): Request? {
        return null
    }
}
class Piroinitio: Card(house.None, 4, "Piroinitio", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Priori_incantatem: Card(house.None, 4, "Priori incantatem", Type.Spell){
    override fun play(): Request? {
        return null
    }
}
class Profix: Card(house.None, 6, "Profix", Type.Spell){
    override fun play(): Request? {
        return null
    }
}
class Protego: Card(house.None, 4, "Protego", Type.Spell){
    override fun play(): Request? {
        return null
    }
}
class Purlicerpenna: Card(house.None, 3, "Purlicerpenna", Type.Item){
    override fun play(): Request? {
        return null
    }
}

class Relaxo: Card(house.Slytherin, 6, "Relaxo", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Remdenever_rontas: Card(house.None, 0, "Rémdenevér rontás", Type.Curse){
    override fun play(): Request? {
        return null
    }
}

class Remszem_Mordon: Card(house.None, 5, "Rémszem Mordon", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Remus_Lupin: Card(house.None, 7, "Remus Lupin", Type.Ally){
    override fun play(): Request? {
        return null
    }
}
class Reparo: Card(house.None, 3, "Reparo", Type.Spell){
    override fun play(): Request? {
        return null
    }
}
class Revelio: Card(house.None, 2, "Revelio", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Rictusempra: Card(house.None, 2, "Rictusempra", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Ron_Weasley: Card(house.Gryffindor, 5, "Ron Weasley", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Rubeus_Hagrid: Card(house.None, 5, "Rubeus Hagrid", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Sectumsempra: Card(house.None, 0, "Sectumsempra", Type.Curse){
    override fun play(): Request? {
        return null
    }
}

class Sonorus: Card(house.Hufflepuff, 4, "Sonorus", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Stupor: Card(house.Gryffindor, 6, "Stupor", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Suvickus: Card(house.None, 4, "Suvickus", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Szazfule_fozet: Card(house.Slytherin, 3, "Százfűlé főzet", Type.Item){
    override fun play(): Request? {
        return null
    }
}

class Tarantallegra: Card(house.None, 8, "Tarantallegra", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Vajsor: Card(house.None, 2, "Vajsör", Type.Item){
    override fun play(): Request? {
        return null
    }
}

class Ust: Card(house.None, 0, "Üst", Type.Item){
    override fun play(): Request? {
        return null
    }
}

class Varangy: Card(house.None, 0, "Varangy", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Vincent_Crack: Card(house.Slytherin, 5, "Vincent Crack", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Vingardium_leviosa: Card(house.Ravenclaw, 4, "Vingardium leviosa", Type.Spell){
    override fun play(): Request? {
        return null
    }
}

class Zacharias_Smith: Card(house.Hufflepuff, 5, "Zacharias Smith", Type.Ally){
    override fun play(): Request? {
        return null
    }
}

class Zsugoritott_fej: Card(house.None, 8, "Zsugorított fej", Type.Item){
    override fun play(): Request? {
        return null
    }
}

