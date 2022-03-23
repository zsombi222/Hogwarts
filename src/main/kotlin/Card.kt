enum class Type{
    Item,
    Spell,
    Ally,
    Curse
}


abstract class Card(val House: house, val value: Int, val name: String, val type: Type) {
    open fun play(): Request? {
        Game.current.Hand.cards.remove(this)
        Game.current.Played.cards.add(this)
        return null
    }
    open fun drop() {
        Game.current.Hand.cards.remove(this)
        Game.current.DiscardPile.cards.add(this)
    }
    open fun discard() {
        try {
            Game.current.Played.cards.remove(this)
        }
        catch (e: Exception){
            Game.current.Allies.cards.remove(this)
        }
        finally {
            Game.current.DiscardPile.cards.add(this)
        }
    }
    open fun destroy(){
        try {
            Game.current.Hand.cards.remove(this)
        }catch (e: Exception){}
        try {
            Game.current.DiscardPile.cards.remove(this)
        }catch (e: Exception){}
        try {
            Game.current.Played.cards.remove(this)
        }catch (e: Exception){}
        try {
            Game.current.DrawPile.cards.remove(this)
        }catch (e: Exception){}
        try {
            Game.current.Allies.cards.remove(this)
        }catch (e: Exception){}
        Game.Destructed.cards.add(this)
    }
    open fun use(): Request? { return null }
    override fun toString(): String {
        return name
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

