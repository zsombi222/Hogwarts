import Ally.Macska
import javafx.application.Application
import javafx.collections.FXCollections
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.stage.Screen
import javafx.stage.Stage
import javafx.stage.StageStyle
import java.util.*
import kotlin.random.Random
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    Application.launch(App::class.java)

    //game.start()
}


class Settings(
    var comp: Boolean = false,
    var name1: String = "p1",
    var name2: String = "p2",
    var house1: Int = 0,
    var house2: Int = 0,
    var deck1: Int = 0,
    var deck2: Int = 0
)

class App() : Application() {

    val settingsDialog: Dialog<Settings> = Dialog()

    override fun start(s: Stage) {

        val houseChoices =
            FXCollections.observableArrayList("Ravenclaw", "Gryffindor", "Slytherin", "Hufflepuff")
        val starterPileChoices =
            FXCollections.observableArrayList("Bagoly", "Ally.Macska", "Varangy")

        val startBtn = ButtonType("Start", ButtonBar.ButtonData.OK_DONE)
        val cancelBtn = ButtonType("Kilépés", ButtonBar.ButtonData.CANCEL_CLOSE)
        val choiceBox1 = ComboBox(houseChoices)
        val choiceBox2 = ComboBox(houseChoices)
        val nameBox1 = TextField("jatekos_1")
        val nameBox2 = TextField("jatekos_2")
        val choiceBox3 = ComboBox(starterPileChoices)
        val choiceBox4 = ComboBox(starterPileChoices)
        val compBox = CheckBox("Gép")
        choiceBox1.apply {
            value = house.Ravenclaw.toString()
        }
        choiceBox2.apply {
            value = "Slytherin"
        }
        choiceBox3.apply {
            value = "Bagoly"
        }
        choiceBox4.apply {
            value = "Ally.Macska"
        }

        val settingsRoot = VBox().apply {
            alignment = Pos.CENTER
            children.add(nameBox1)
            children.add(choiceBox1)
            children.add(choiceBox3)
            children.add(nameBox2)
            children.add(compBox)
            children.add(choiceBox2)
            children.add(choiceBox4)
        }

        settingsDialog.apply {
            initStyle(StageStyle.UNDECORATED)
            title = "Hogwarts"
            headerText = "Hogwarts"
            dialogPane.buttonTypes.addAll(startBtn, cancelBtn)
            dialogPane.content = settingsRoot

            setResultConverter {
                return@setResultConverter Settings(
                    compBox.isSelected,
                    nameBox1.text,
                    nameBox2.text,
                    choiceBox1.selectionModel.selectedIndex,
                    choiceBox2.selectionModel.selectedIndex,
                    choiceBox3.selectionModel.selectedIndex,
                    choiceBox4.selectionModel.selectedIndex
                )
            }
        }
        try {
            val result = settingsDialog.showAndWait()
            Game(result.get())
        } catch (e: Exception) {
            exitProcess(0)
        }

        Game.current.Allies.cards.add(Macska())

        ///////////////////////////////////////////////////////start game

        val screenWidth = Screen.getPrimary().bounds.width
        val screenHeight = Screen.getPrimary().bounds.height

        val opponentHand = FXCollections.observableArrayList<ImageView>()
        opponentHand.addAll(Game.opponent.Hand.images())
        val opponentList = ListView<ImageView>().apply {
            items = opponentHand
            minWidth = screenWidth / 2;
            maxWidth = screenWidth / 2;
            maxHeight = screenHeight / 5
            minHeight = screenHeight / 5
            setOrientation(Orientation.HORIZONTAL);
        }

        val opponentAllies = FXCollections.observableArrayList<ImageView>()
        opponentAllies.addAll(Game.opponent.Allies.images90())
        val opponentAlliesList = ListView<ImageView>().apply {
            items = opponentAllies
            minWidth = screenHeight / 5;
            maxWidth = screenHeight / 5;
            maxHeight = screenHeight / 2.5
            minHeight = screenHeight / 2.5
            setOrientation(Orientation.VERTICAL);
        }

        val opponentPlayed = FXCollections.observableArrayList<ImageView>()
        opponentPlayed.addAll(Game.opponent.Played.images90())
        val opponentPlayedList = ListView<ImageView>().apply {
            items = opponentPlayed
            minWidth = screenWidth / 2;
            maxWidth = screenWidth / 2;
            maxHeight = screenHeight / 5
            minHeight = screenHeight / 5
            setOrientation(Orientation.HORIZONTAL);
        }

        val currentHand = FXCollections.observableArrayList<ImageView>()
        currentHand.addAll(Game.current.Hand.images())
        val currentList = ListView<ImageView>().apply {
            items = currentHand
            minWidth = screenWidth / 2;
            maxWidth = screenWidth / 2;
            maxHeight = screenHeight / 5
            minHeight = screenHeight / 5
            setOrientation(Orientation.HORIZONTAL);
        }

        val currentAllies = FXCollections.observableArrayList<ImageView>()
        currentAllies.addAll(Game.current.Allies.images90())
        val currentAliiesList = ListView<ImageView>().apply {
            items = currentAllies
            minWidth = screenHeight / 5;
            maxWidth = screenHeight / 5;
            maxHeight = screenHeight / 2.5
            minHeight = screenHeight / 2.5
            setOrientation(Orientation.VERTICAL);
        }

        val currentPlayed = FXCollections.observableArrayList<ImageView>()
        currentPlayed.addAll(Game.current.Played.images90())
        val currentPlayedList = ListView<ImageView>().apply {
            items = currentPlayed
            minWidth = screenWidth / 2;
            maxWidth = screenWidth / 2;
            maxHeight = screenHeight / 5
            minHeight = screenHeight / 5
            setOrientation(Orientation.VERTICAL);
        }

        val shop = FXCollections.observableArrayList<ImageView>()
        shop.addAll(Game.ClassRoom4.images())
        val shopList = ListView<ImageView>().apply {
            items = shop
            minWidth = screenWidth / 4;
            maxWidth = screenWidth / 4;
            maxHeight = screenHeight / 5
            minHeight = screenHeight / 5
            setOrientation(Orientation.HORIZONTAL);
        }
        

        val useAllyBtn = Button().apply {
            text = "Szövetséges használata"
            style = "-fx-font-size:${screenHeight / 50}"
        }

        val useAllyPane = StackPane().apply {
            alignment = Pos.BOTTOM_CENTER
            minWidth = screenWidth / 4;
            maxWidth = screenWidth / 4;
            maxHeight = screenHeight / 5
            minHeight = screenHeight / 5
            children.add(VBox().apply {
                children.add(VBox().apply {
                    maxHeight = screenHeight / 10
                    minHeight = screenHeight / 10
                })
                children.add(useAllyBtn)
                alignment = Pos.CENTER
            })
        }

        val playButton = Button("PLAY").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
        }
        val buyButton = Button("BUY").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
        }
        val dropButton = Button("DROP").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
        }
        val turnButton = Button("TURN").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
        }
        val healButton = Button("HEAL").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
        }
        val attackButton = Button("ATTACK").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
        }

        val coinButton = Button("COINS").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
            isDisable = true
        }

        val hpButton = Button("${Game.current.Health}/${Game.current.Max_health}").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
            isDisable = true
        }

        val stunButton = Button("${Game.current.Stuns}").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
            isDisable = true
        }

        val nameButton = Button(Game.current.name).apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
            isDisable = true
        }

        val ohealButton = Button("HEAL").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
            isDisable = true
        }
        val oattackButton = Button("ATTACK").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
            isDisable = true
        }

        val ocoinButton = Button("COINS").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
            isDisable = true
            isDisable = true
        }

        val ohpButton = Button("${Game.opponent.Health}/${Game.opponent.Max_health}").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
            isDisable = true
        }

        val ostunButton = Button("${Game.opponent.Stuns}").apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
            isDisable = true
        }

        val onameButton = Button(Game.opponent.name).apply {
            style = "-fx-font-size:${screenHeight / 50}"
            minWidth = screenWidth / 8
            isDisable = true
        }


        val discardSizeText = Label(Game.current.DiscardPile.cards.size.toString()).apply {
            style = "-fx-font-size:${screenHeight / 50}"
        }
        val drawSizeText = Label(Game.current.DrawPile.cards.size.toString()).apply {
            style = "-fx-font-size:${screenHeight / 50}"
        }

        val odiscardSizeText = Label(Game.opponent.DiscardPile.cards.size.toString()).apply {
            style = "-fx-font-size:${screenHeight / 50}"
        }
        val odrawSizeText = Label(Game.opponent.DrawPile.cards.size.toString()).apply {
            style = "-fx-font-size:${screenHeight / 50}"
        }

        val ctrlPane = StackPane().apply {
            children.add(GridPane().apply {
                add(playButton, 0, 0)
                add(buyButton, 1, 0)
                add(dropButton, 0, 1)
                add(turnButton, 1, 1)
                add(discardSizeText, 0, 2)
                add(drawSizeText, 1, 2)
                hgap = 10.0
                vgap = 10.0
            })
        }

        val actionPane = StackPane().apply {
            children.add(GridPane().apply {
                add(coinButton, 0, 0)
                add(attackButton, 1, 0)
                add(healButton, 0, 1)
                add(hpButton, 1, 1)
                add(stunButton, 0, 2)
                add(nameButton, 1, 2)
                hgap = 10.0
                vgap = 10.0
            })
        }

        val octrlPane = StackPane().apply {
            children.add(GridPane().apply {
                add(odiscardSizeText, 0, 2)
                add(odrawSizeText, 1, 2)
                hgap = 10.0
                vgap = 10.0
            })
        }

        val oactionPane = StackPane().apply {
            children.add(GridPane().apply {
                add(ocoinButton, 0, 0)
                add(oattackButton, 1, 0)
                add(ohealButton, 0, 1)
                add(ohpButton, 1, 1)
                add(ostunButton, 0, 2)
                add(onameButton, 1, 2)
                add(odiscardSizeText, 0, 3)
                add(odrawSizeText, 1, 3)
                hgap = 10.0
                vgap = 10.0
            })
        }


        val gamegrid = GridPane().apply {
            //alignment = Pos.TOP_LEFT
            add(VBox().apply {
                children.add(opponentAlliesList)
                alignment = Pos.CENTER
            }, 0, 0, 1, 2)
            add(opponentList, 1, 0, 2, 1)
            add(opponentPlayedList, 1, 1, 2, 1)

            add(useAllyPane, 0, 2, 1, 1)
            //isGridLinesVisible = true
            add(currentList, 1, 4, 2, 1)
            add(VBox().apply {
                children.add(currentAliiesList)
                alignment = Pos.CENTER
            }, 0, 3, 1, 2)
            add(currentPlayedList, 1, 3, 2, 1)
            add(ctrlPane, 3, 4, 1, 1)
            add(actionPane, 3, 3, 1, 1)
            add(oactionPane, 3, 0, 1, 1)
            add(octrlPane, 3, 1, 1, 1)
            add(shopList, 3, 2, 1, 1)
        }

        val root = VBox().apply {
            alignment = Pos.TOP_CENTER
            children.add(gamegrid)
        }

        s.apply {
            isFullScreen = true
            title = "Hogwarts"
            scene = Scene(root, screenHeight, screenHeight)
            show()
        }
    }
}

class Game(settings: Settings) {

    var p1: Player
    var p2: Player

    companion object {
        lateinit var ClassRoom: Deck
        lateinit var Curses: Deck
        lateinit var Books: Deck
        lateinit var Destructed: Deck
        lateinit var ClassRoom4: Deck
        lateinit var current: Player
        lateinit var opponent: Player
    }


    init {
        ClassRoom = Deck(decktype.Classroom)
        Curses = Deck(decktype.Curses)
        Books = Deck(decktype.Books)
        Destructed = Deck(decktype.Empty)
        ClassRoom4 = Deck(decktype.Empty)

        ClassRoom.shuffle()
        Curses.shuffle()
        ClassRoom4.cards.addAll(ClassRoom.draw(4))

        if (settings.comp) {
            p1 = Human(settings.name1, convertHouse(settings.house1))
            p2 = Computer(settings.name2, convertHouse(settings.house2))
        } else {
            p1 = Human(settings.name1, convertHouse(settings.house1))
            p2 = Human(settings.name2, convertHouse(settings.house2))
        }

        val r = Random.nextBoolean()
        if (r) {
            current = p1
            opponent = p2
        } else {
            current = p2
            opponent = p1
        }

        p1.createStarterPile(convertDeck(settings.deck1))
        p2.createStarterPile(convertDeck(settings.deck2))

        Tests.newRound()

        ////////// TEST SECTION

        val scanner = Scanner(System.`in`)

        Game.current.Coins = 100

        testing@ while (true) {
            while (scanner.hasNextLine()) {
                val cmd = scanner.nextLine().split(' ')
                when (cmd[0]) {
                    "test" -> {
                        try {
                            Tests.run(cmd[1].toInt())
                        } catch (e: Exception) {
                            println("Hibás parancs")
                        }

                    }
                    "add" -> {
                        try {
                            val idx = cmd[1].toInt()
                            var n = 1
                            if (cmd.size > 2)
                                n = cmd[2].toInt()
                            Tests.add(idx, n)
                        } catch (e: Exception) {
                            try {
                                var n = 1
                                if (cmd.size > 2)
                                    n = cmd[2].toInt()
                                Tests.add(cmd[1], n)
                            } catch (e: Exception) {
                                println("Hibás parancs")
                            }
                        }
                    }
                    "print" -> {
                        Tests.print()
                    }
                    "turn" -> {
                        Tests.turn()
                    }
                    "play" -> {
                        try {
                            val R = CurseController.checkPlay(current.Hand.cards[cmd[1].toInt()])
                            if (R != null) {
                                while (true) {
                                    println(R.text)
                                    val l = readLine()
                                    val Re = Response()
                                    val lsplit = l?.split(' ')
                                    try {
                                        if (lsplit?.size == 1)
                                            Re.n = lsplit[0].toInt()
                                        else if (lsplit?.size == 2) {
                                            Re.n = lsplit[1].toInt()
                                            Re.text = lsplit[0]
                                        }
                                        if (R.req(Re)) {
                                            break
                                        }
                                    } catch (e: Exception) {
                                        println("hibás bemenet")
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            println("Hibás parancs")
                        }
                    }
                    "drop" -> {
                        try {
                            CurseController.checkDrop(current.Hand.cards[cmd[1].toInt()])
                        } catch (e: Exception) {
                            println("Hibás parancs")
                        }
                    }
                    "use" -> {
                        try {
                            val R = CurseController.checkUse(current.Allies.cards[cmd[1].toInt()])
                            if (R != null) {
                                while (true) {
                                    println(R.text)
                                    val l = readLine()
                                    val Re = Response()
                                    val lsplit = l?.split(' ')
                                    try {
                                        if (lsplit?.size == 1)
                                            Re.n = lsplit[0].toInt()
                                        else if (lsplit?.size == 2) {
                                            Re.n = lsplit[1].toInt()
                                            Re.text = lsplit[0]
                                        }
                                        if (R.req(Re)) {
                                            break
                                        }
                                    } catch (e: Exception) {
                                        println("hibás bemenet")
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            println("Hibás parancs")
                        }
                    }
                    "<3" -> {
                        CurseController.checkHeal()
                    }
                    "/" -> {
                        if (CurseController.checkAttack()) {
                            Tests.newRound()
                        }
                    }
                    "buy" -> {
                        try {
                            current.buy(cmd[1].toInt())
                        } catch (e: Exception) {
                            println(ClassRoom4.valPrint())
                            if(Game.Books.cards.size > 0){
                                println("\t4. Könyv (3)")
                            }
                        }
                    }
                    "end" -> {
                        break@testing
                    }
                    "ravenclaw" -> {
                        current.House = house.Ravenclaw
                    }
                    "gryffindor" -> {
                        current.House = house.Gryffindor
                    }
                    "slytherin" -> {
                        current.House = house.Slytherin
                    }
                    "hufflepuff" -> {
                        current.House = house.Hufflepuff
                    }
                    else -> {
                        println("Ismeretlen parancs")
                    }
                }
            }

        }

        scanner.close()

        ///////// END OF TEST SECTION


    }

    fun convertHouse(n: Int): house {
        return when (n) {
            0 -> house.Ravenclaw
            1 -> house.Gryffindor
            2 -> house.Slytherin
            3 -> house.Hufflepuff
            else -> house.None
        }
    }

    fun convertDeck(n: Int): decktype {
        return when (n) {
            0 -> decktype.StarterOwl
            1 -> decktype.StarterCat
            2 -> decktype.StarterFrog
            else -> decktype.Empty
        }
    }

    fun start(s: Stage?) {
        while (p1.Stuns > 0 && p2.Stuns > 0) {
            while (p1.Health > 0 && p2.Health > 0) {

                println("\n\n${current.name}:\n")

                while (true) {
                    val line = readLine()!!
                    if (line == "end") {
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