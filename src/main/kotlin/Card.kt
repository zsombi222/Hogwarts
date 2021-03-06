import javafx.geometry.Rectangle2D
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.Screen
import java.io.File
import java.text.Normalizer
import java.text.Normalizer.normalize
import java.util.*

enum class Type {
    Item,
    Spell,
    Ally,
    Curse
}

abstract class Card(val House: house, val value: Int, val name: String, val type: Type) {

    val sep = File.pathSeparatorChar

    open fun play(): Request? {
        Game.current.Hand.cards.remove(this)
        if (this.type == Type.Ally) {
            Game.current.Allies.cards.add(this)
        } else {
            Game.current.Played.cards.add(this)
        }
        println("$name kijátszása...")
        return null
    }

    open fun drop(p: Player = Game.current) {
        p.Hand.cards.remove(this)
        p.DiscardPile.cards.add(this)
        println("$name eldobása kézből...")
    }

    open fun discard(p: Player = Game.current) {
        p.Played.cards.remove(this)
        p.Allies.cards.remove(this)
        println("$name eltávolítása a kijátszott lapok közül...")
        p.DiscardPile.cards.add(this)
    }

    open fun destroy(p: Player = Game.current) {
        p.Hand.cards.remove(this)
        p.DiscardPile.cards.remove(this)
        p.Played.cards.remove(this)
        p.DrawPile.cards.remove(this)
        p.Allies.cards.remove(this)
        Game.ClassRoom4.cards.remove(this)
        Game.ClassRoom.cards.remove(this)

        Game.Destructed.cards.add(this)

        println("$name elpusztítása...")
    }

    open fun use(): Request? {
        return null
    }

    override fun toString(): String {
        return name
    }

    fun image(): ImageView {
        val screen: Rectangle2D = Screen.getPrimary().getBounds()
        val path = System.getProperty("user.dir")
        val img = Image(
            "file:$path${sep}hp_assets${sep}cards_small${sep}${convName()}",
            0.0,
            screen.height / 5 - 20,
            true,
            true
        )
        return ImageView(img)
    }

    fun image90(): ImageView {
        val screen: Rectangle2D = Screen.getPrimary().getBounds()
        val path = System.getProperty("user.dir")
        val img =
            Image(
                "file:$path${sep}hp_assets${sep}cards_small_rotate${sep}${convName()}",
                screen.height / 5 - 20,
                0.0,
                true,
                true
            )
        val imgView = ImageView(img)
        return imgView
    }

    fun convName(): String {
        var toreturn = name.lowercase(Locale.getDefault())
        toreturn = toreturn.replace(" ", "_")
        toreturn = normalize(toreturn, Normalizer.Form.NFD).replace("[^\\p{ASCII}]".toRegex(), "")
        return "$toreturn.png"
    }
}

class Locomotor : Card(house.Ravenclaw, 6, "Locomotor", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Nymphadora_Tonks : Card(house.Hufflepuff, 6, "Nymphadora Tonks", Type.Ally) {
    override fun play(): Request? {
        return null
    }
}

class Perui_instant_sotetsegpor : Card(house.Slytherin, 4, "Perui instant sötétségpor", Type.Item) {
    override fun play(): Request? {
        return null
    }
}

class Petrificus_totalus : Card(house.None, 2, "Petrificus totalus", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Pirocus : Card(house.None, 4, "Pirocus", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Piroinitio : Card(house.None, 4, "Piroinitio", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Priori_incantatem : Card(house.None, 4, "Priori incantatem", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Profix : Card(house.None, 6, "Profix", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Protego : Card(house.None, 4, "Protego", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Purlicerpenna : Card(house.None, 3, "Purlicerpenna", Type.Item) {
    override fun play(): Request? {
        return null
    }
}

class Relaxo : Card(house.Slytherin, 6, "Relaxo", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Remszem_Mordon : Card(house.None, 5, "Rémszem Mordon", Type.Ally) {
    override fun play(): Request? {
        return null
    }
}

class Remus_Lupin : Card(house.None, 7, "Remus Lupin", Type.Ally) {
    override fun play(): Request? {
        return null
    }
}

class Reparo : Card(house.None, 3, "Reparo", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Revelio : Card(house.None, 2, "Revelio", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Rictusempra : Card(house.None, 2, "Rictusempra", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Ron_Weasley : Card(house.Gryffindor, 5, "Ron Weasley", Type.Ally) {
    override fun play(): Request? {
        return null
    }
}

class Rubeus_Hagrid : Card(house.None, 5, "Rubeus Hagrid", Type.Ally) {
    override fun play(): Request? {
        return null
    }
}

class Sonorus : Card(house.Hufflepuff, 4, "Sonorus", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Stupor : Card(house.Gryffindor, 6, "Stupor", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Suvickus : Card(house.None, 4, "Suvickus", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Szazfule_fozet : Card(house.Slytherin, 3, "Százfűlé főzet", Type.Item) {
    override fun play(): Request? {
        return null
    }
}

class Tarantallegra : Card(house.None, 8, "Tarantallegra", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Vajsor : Card(house.None, 2, "Vajsör", Type.Item) {
    override fun play(): Request? {
        return null
    }
}

class Varangy : Card(house.None, 0, "Varangy", Type.Ally) {
    override fun play(): Request? {
        return null
    }
}

class Vincent_Crack : Card(house.Slytherin, 5, "Vincent Crack", Type.Ally) {
    override fun play(): Request? {
        return null
    }
}

class Vingardium_leviosa : Card(house.Ravenclaw, 4, "Vingardium leviosa", Type.Spell) {
    override fun play(): Request? {
        return null
    }
}

class Zacharias_Smith : Card(house.Hufflepuff, 5, "Zacharias Smith", Type.Ally) {
    override fun play(): Request? {
        return null
    }
}

class Zsugoritott_fej : Card(house.None, 8, "Zsugorított fej", Type.Item) {
    override fun play(): Request? {
        return null
    }
}

