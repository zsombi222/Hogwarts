import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.NumberFormatException
import java.lang.module.ModuleDescriptor.read
import kotlin.random.Random

fun main(args: Array<String>) {


    val game = Game(false)
    //game.start()
}

class Game(comp: Boolean){

    lateinit var p1: Player
    lateinit var p2: Player

    companion object{

        lateinit var ClassRoom: Deck
        lateinit var Curses: Deck
        lateinit var Books: Deck
        lateinit var Destructed: Deck
        lateinit var ClassRoom4: Deck
        var current: Player = Human()
        var opponent: Player = Human()
    }


    init {
        ClassRoom = Deck(decktype.Classroom)
        Curses = Deck(decktype.Curses)
        Books = Deck(decktype.Books)
        Destructed = Deck(decktype.Empty)
        ClassRoom4 = Deck(decktype.Empty)

        current = Human()
        opponent = Human()

        if(comp){
            p1 = Human("Vivi", house.Slytherin)
            p2 = Computer("A Gép", house.Gryffindor)
        }
        else{
            p1 = Human("Vivi", house.Slytherin)
            p2 = Human("Zsombi", house.Ravenclaw)
        }

        val r = Random.nextBoolean()
        if(r){
            current = p1
            opponent = p2
        }
        else{
            current = p2
            opponent = p1
        }

        ////////// TEST SECTION



        current = p1
        opponent = p2

        val reader = BufferedReader(InputStreamReader(System.`in`))

        while(true){
            reader.lineSequence().forEach {
                //println("a sor: "+it)
                val cmd = it.split(' ')
                when (cmd[0]){
                    "test"->{
                        Tests.run(cmd[1].toInt())
                    }
                    "add"->{
                        try {
                            val idx = cmd[1].toInt()
                            var n = 1
                            if(cmd.size > 2)
                                n = cmd[2].toInt()
                            Tests.add(idx,n)
                        } catch (e: NumberFormatException){
                            var n = 1
                            if(cmd.size > 2)
                                n = cmd[2].toInt()
                            Tests.add(cmd[1],n)
                        }
                    }
                    "print" ->{
                        Tests.print()
                    }
                    "turn" ->{
                        Tests.turn()
                    }
                    "play" ->{
                        Game.current.Hand.cards[cmd[1].toInt()].play()
                    }
                    else ->{
                        println("Ismeretlen parancs")
                    }
                }
            }

        }



/*

        """
add macska 1
add bagoly 1
        """
        p1.creasteStarterPile(decktype.StarterCat)
        p1.DrawPile.shuffle()
        //p1.Hand.cards.addAll(p1.DrawPile.draw(3))
        p1.Hand.cards.add(Arresto_momentum())
        p1.Hand.cards.add(Aguamenti())
        p1.Hand.cards.add(Albus_Dumbledore())
        p1.Hand.cards.add(Alohomora())
        p1.Hand.cards.add(Altatoital())
        p1.Hand.cards.add(Aranycikesz())
        p1.Hand.cards.add(Ascendio())
        p1.Hand.cards.add(Bagoly())
        p1.Hand.cards.add(Baziteo())

        println(p1)

        p1.Hand.cards[0].play()

        println(p1)

        p1.Hand.cards[0].play()

        println(p1)

        p1.Hand.cards[0].play()

        println(p1)

        p1.Allies.cards[0].use()

        println(p1)

        p1.Hand.cards[0].play()

        println(p1)

        var ru = p1.Hand.cards[0].play()
        if (ru?.req != null) {
            ru.req(Response(0))
        }

        println(p1)

        p1.Hand.cards[0].play()

        println(p1)

        p1.Hand.cards[0].play()

        println(p1)

        println("------------------")

        p1.Hand.cards[0].play()

        println(p1)

        ru = p1.Allies.cards[1].use()
        if (ru?.req != null) {
            ru.req(Response(0))
        }

        println(p1)

        p1.Hand.cards[0].play()

        println(p1)


        ///////// END OF TEST SECTION
        */
    }


    fun start(){
        while(p1.Stuns > 0 && p2.Stuns > 0){
            while (p1.Health > 0 && p2.Health > 0){

                println("\n\n${current.name}:\n")

                while(true){
                    val line = readLine()!!
                    if(line == "end"){
                        break
                    }
                }
                val temp = current
                current = opponent
                opponent = temp
            }
            p1.Health = 7
            p2.Health = 7
        }
    }
}