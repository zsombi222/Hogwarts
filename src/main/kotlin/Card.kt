abstract class Card(val house: house,val value: Int, val name: String, val type: Type) {
    abstract fun play()
    fun drop() {}
    override fun toString(): String {
        return name
    }
}

class Aguamenti: Card(house.None, 3, "aguamenti", Type.Spell){
    override fun play() {

    }
}

class Albus_Dumbledore: Card(house.None, 9, "Albus Dumbledore", Type.Companion){
    override fun play() {

    }
}

class Alohomora: Card(house.None, 0, "Alohomora", Type.Spell){
    override fun play() {

    }
}

class Altatoital: Card(house.None, 7, "Altatóital", Type.Item){
    override fun play() {

    }
}

class Aranycikesz: Card(house.None, 8, "Aranycikesz", Type.Item){
    override fun play() {

    }
}

class Arresto_momentum: Card(house.None, 3, "Arresto momentum", Type.Spell){
    override fun play() {

    }
}

class Ascendio: Card(house.None, 4, "Ascendio", Type.Spell){
    override fun play() {

    }
}

class Bagoly: Card(house.None, 0, "Bagoly", Type.Companion){
    override fun play() {

    }
}

class Baziteo: Card(house.Hufflepuff, 6, "Baziteo", Type.Spell){
    override fun play() {

    }
}

class Bimba_Professzor: Card(house.Hufflepuff, 7, "Bimba Professzor", Type.Companion){
    override fun play() {

    }
}

class Bombarda: Card(house.None, 2, "Bombarda", Type.Spell){
    override fun play() {

    }
}

class Boszorkanyfukivonat: Card(house.None, 6, "Boszorkányfűkivonat", Type.Item){
    override fun play() {

    }
}

class Buvos_bizsere: Card(house.None, 7, "Bűvös bizsere", Type.Item){
    override fun play() {

    }
}

class Capitulatus: Card(house.Gryffindor, 4, "Capitulatus", Type.Spell){
    override fun play() {

    }
}

class Carbunculus: Card(house.None, 0, "Carbunculus", Type.Curse){
    override fun play() {

    }
}

class Cave_malcium: Card(house.None, 6, "Cave malcium", Type.Spell){
    override fun play() {

    }
}

class Cedric_Diggory: Card(house.Hufflepuff, 6, "Cedric Diggory", Type.Companion){
    override fun play() {

    }
}

class Cho_Chang: Card(house.Ravenclaw, 6, "Cho Chang", Type.Companion){
    override fun play() {

    }
}

class Confundo: Card(house.None, 7, "Confundo", Type.Spell){
    override fun play() {

    }
}

class Confundo_curse: Card(house.None, 0, "Confundo_curse", Type.Curse){
    override fun play() {

    }
}

class Conjuctivitis: Card(house.None, 0, "Conjuctivitis", Type.Curse){
    override fun play() {

    }
}

class Crucio: Card(house.None, 8, "Crucio", Type.Spell){
    override fun play() {

    }
}

class Csalanartas: Card(house.None, 0, "Csalánártás", Type.Curse){
    override fun play() {

    }
}

class Cukrozott_lepkeszarnyak: Card(house.Gryffindor, 4, "Cukrozott lepkeszárnyak", Type.Item){
    override fun play() {

    }
}

class Csokibeka: Card(house.Gryffindor, 3, "Csokibéka", Type.Item){
    override fun play() {

    }
}

class Densaugeo: Card(house.Slytherin, 5, "Densaugeo", Type.Spell){
    override fun play() {

    }
}

class Descendo: Card(house.Gryffindor, 5, "Descendo", Type.Spell){
    override fun play() {

    }
}

class Diffindo: Card(house.Hufflepuff, 5, "Diffindo", Type.Spell){
    override fun play() {

    }
}

class Disaudio: Card(house.None, 7, "Disaudio", Type.Spell){
    override fun play() {

    }
}

class Dobby: Card(house.None, 4, "Dobby a házimanó", Type.Companion){
    override fun play() {

    }
}

class Draco_Malfoy: Card(house.Slytherin, 6, "Draco Malfoy", Type.Companion){
    override fun play() {

    }
}

class Egyel_csigat: Card(house.None, 0, "Egyél csigát", Type.Curse){
    override fun play() {

    }
}

class Exmamoriam: Card(house.Ravenclaw, 5, "Exmamoriam", Type.Spell){
    override fun play() {

    }
}

class Expecto_patronum: Card(house.None, 5, "Expecto patronum", Type.Spell){
    override fun play() {

    }
}

class Felix_Felicis: Card(house.None, 5, "Felix Felicis", Type.Item){
    override fun play() {

    }
}

class Flipendo: Card(house.None, 8, "Flipendo", Type.Spell){
    override fun play() {

    }
}

class Flitwick_Professzor: Card(house.Ravenclaw, 7, "Flitwick Professzor", Type.Companion){
    override fun play() {

    }
}

class Fozetkeszlet: Card(house.None, 5, "Főzetkészlet", Type.Item){
    override fun play() {

    }
}
class Gancsrontas: Card(house.None, 0, "Gáncsrontás", Type.Curse){
    override fun play() {

    }
}
class Geminio: Card(house.None, 0, "Geminio", Type.Curse){
    override fun play() {

    }
}

class Gildroy_Lockhart: Card(house.Ravenclaw, 5, "Gildroy Lockhart", Type.Companion){
    override fun play() {

    }
}
class Ginny_Weasley: Card(house.None, 4, "Ginny Weasley", Type.Companion){
    override fun play() {

    }
}
class Gregory_Monstro: Card(house.Slytherin, 6, "Gregory Monstro", Type.Companion){
    override fun play() {

    }
}
class Gumilabrontas: Card(house.None, 0, "Gumilábrontás", Type.Curse){
    override fun play() {

    }
}

class Harry_Potter: Card(house.Gryffindor, 6, "Harry Potter", Type.Companion){
    override fun play() {

    }
}

class Hermione_Granger: Card(house.Gryffindor, 6, "Hermione Granger", Type.Companion){
    override fun play() {

    }
}

class Imperio: Card(house.Slytherin, 4, "Imperio", Type.Spell){
    override fun play() {

    }
}

class Invito: Card(house.None, 3, "Invito", Type.Spell){
    override fun play() {

    }
}
class Konyv: Card(house.None, 3, "Könyv", Type.Item){
    override fun play() {

    }
}
class Koromnovelo_rontas: Card(house.None, 0, "Körömnövelő rontás", Type.Curse){
    override fun play() {

    }
}

class Kritalygomb: Card(house.Ravenclaw, 3, "Kritálygömb", Type.Item){
    override fun play() {

    }
}
class Levicorpus: Card(house.None, 0, "Levicorpus", Type.Curse){
    override fun play() {

    }
}
class Locomotor: Card(house.Ravenclaw, 6, "Locomotor", Type.Spell){
    override fun play() {

    }
}
class Lumos: Card(house.None, 6, "Lumos", Type.Spell){
    override fun play() {

    }
}

class Luna_Lovegood: Card(house.Ravenclaw, 6, "Luna Lovegood", Type.Companion){
    override fun play() {

    }
}

class Macska: Card(house.None, 0, "Macska", Type.Companion){
    override fun play() {

    }
}

class Magifi_ragasztoszalag: Card(house.Ravenclaw, 4, "Magifix ragasztószalag", Type.Item){
    override fun play() {

    }
}

class Mandragora: Card(house.Hufflepuff, 4, "Mandragóra", Type.Item){
    override fun play() {

    }
}
class Mcgalacony_Professzor: Card(house.Gryffindor, 7, "McGalacony Professzor", Type.Companion){
    override fun play() {

    }
}
class Neville_Longbottom: Card(house.None, 4, "Neville Longbottom", Type.Companion){
    override fun play() {

    }
}

class Nymphadora_Tonks: Card(house.Hufflepuff, 6, "Nymphadora Tonks", Type.Companion){
    override fun play() {

    }
}
class Obstructo: Card(house.None, 7, "Obstructo", Type.Spell){
    override fun play() {

    }
}
class Oppugno: Card(house.None, 5, "Oppugno", Type.Spell){
    override fun play() {

    }
}
class Palca: Card(house.None, 0, "Pálca", Type.Item){
    override fun play() {

    }
}

class Penna: Card(house.Hufflepuff, 3, "Penna", Type.Item){
    override fun play() {

    }
}

class Perselus_Piton: Card(house.Slytherin, 7, "Perselus Piton", Type.Companion){
    override fun play() {

    }
}

class Perui_instant_sotetsegpor: Card(house.Slytherin, 4, "Perui instant sötétségpor", Type.Item){
    override fun play() {

    }
}

class Petrificus_totalus: Card(house.None, 2, "Petrificus totalus", Type.Spell){
    override fun play() {

    }
}
class Pirocus: Card(house.None, 4, "Pirocus", Type.Spell){
    override fun play() {

    }
}
class Piroinitio: Card(house.None, 4, "Piroinitio", Type.Spell){
    override fun play() {

    }
}

class Priori_incantatem: Card(house.None, 4, "Priori incantatem", Type.Spell){
    override fun play() {

    }
}
class Profix: Card(house.None, 6, "Profix", Type.Spell){
    override fun play() {

    }
}
class Protego: Card(house.None, 4, "Protego", Type.Spell){
    override fun play() {

    }
}
class Purlicerpenna: Card(house.None, 3, "Purlicerpenna", Type.Item){
    override fun play() {

    }
}

class Relaxo: Card(house.Slytherin, 6, "Relaxo", Type.Spell){
    override fun play() {

    }
}

class Remdenever_rontas: Card(house.None, 0, "Rémdenevér rontás", Type.Curse){
    override fun play() {

    }
}

class Remszem_Mordon: Card(house.None, 5, "Rémszem Mordon", Type.Companion){
    override fun play() {

    }
}

class Remus_Lupin: Card(house.None, 7, "Remus Lupin", Type.Companion){
    override fun play() {

    }
}
class Reparo: Card(house.None, 3, "Reparo", Type.Spell){
    override fun play() {

    }
}
class Revelio: Card(house.None, 2, "Revelio", Type.Spell){
    override fun play() {

    }
}

class Rictusempra: Card(house.None, 2, "Rictusempra", Type.Spell){
    override fun play() {

    }
}

class Ron_Weasley: Card(house.Gryffindor, 5, "Ron Weasley", Type.Companion){
    override fun play() {

    }
}

class Rubeus_Hagrid: Card(house.None, 5, "Rubeus Hagrid", Type.Companion){
    override fun play() {

    }
}

class Sectumsempra: Card(house.None, 0, "Sectumsempra", Type.Curse){
    override fun play() {

    }
}

class Sonorus: Card(house.Hufflepuff, 4, "Sonorus", Type.Spell){
    override fun play() {

    }
}

class Stupor: Card(house.Gryffindor, 6, "Stupor", Type.Spell){
    override fun play() {

    }
}

class Suvickus: Card(house.None, 4, "Suvickus", Type.Spell){
    override fun play() {

    }
}

class Szazfule_fozet: Card(house.Slytherin, 3, "Százfűlé főzet", Type.Item){
    override fun play() {

    }
}

class Tarantallegra: Card(house.None, 8, "Tarantallegra", Type.Spell){
    override fun play() {

    }
}

class Vajsor: Card(house.None, 2, "Vajsör", Type.Item){
    override fun play() {

    }
}

class Ust: Card(house.None, 0, "Üst", Type.Item){
    override fun play() {

    }
}

class Varangy: Card(house.None, 0, "Varangy", Type.Companion){
    override fun play() {

    }
}

class Vincent_Crack: Card(house.Slytherin, 5, "Vincent Crack", Type.Companion){
    override fun play() {

    }
}

class Vingardium_leviosa: Card(house.Ravenclaw, 4, "Vingardium leviosa", Type.Spell){
    override fun play() {

    }
}

class Zacharias_Smith: Card(house.Hufflepuff, 5, "Zacharias Smith", Type.Companion){
    override fun play() {

    }
}

class Zsugoritott_fej: Card(house.None, 8, "Zsugorított fej", Type.Item){
    override fun play() {

    }
}

enum class Type{
    Item,
    Spell,
    Companion,
    Curse
}