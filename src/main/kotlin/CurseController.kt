class CurseController {
    companion object {

        var spellcount = 0
        var itemcount = 0

        var attack = 1000
        var heal = 1000

        var attacked = false
        var healed = false

        fun checkPlay(c: Card): Request? {
            if (Game.current.Hand.getCurseNum() > 0 && c.type != Type.Curse) {
                println("Először a rontásokat kell kijátszanod")
                return null
            }

            return when (c.type) {
                Type.Ally -> {
                    return if (Events.egyelcsigat)
                        null
                    else
                        c.play()
                }
                Type.Spell -> {
                    return if (Events.conjuctivitis && spellcount >= 2) {
                        null
                    } else {
                        spellcount++
                        c.play()
                    }

                }
                Type.Item -> {
                    return if (Events.koromnovelo && itemcount >= 1) {
                        null
                    } else {
                        itemcount++
                        c.play()
                    }

                }
                Type.Curse -> {
                    c.play()
                }
            }
        }

        fun checkUse(c: Card): Request? {
            return if (Events.csalan) {
                null
            } else {
                c.use()
            }
        }

        fun checkAttack(): Boolean {
            return if (Events.confundoCurse) {
                return if (Events.gancsrontas) {
                    Game.current.attack(0)
                } else if (!attacked) {
                    attacked = true
                    Game.current.attack(1)
                } else {
                    Game.current.attack(0)
                }
            } else if (Events.gancsrontas) {
                Game.current.attack(0)
            } else {
                Game.current.attack()
            }
        }

        fun checkHeal() {
            if (Events.sectumsempra) {
                if (Events.gancsrontas) {
                    Game.current.heal(0)
                } else if (!healed) {
                    Game.current.heal(1)
                    healed = true
                } else {
                    Game.current.heal(0)
                }
            } else if (Events.gancsrontas) {
                Game.current.heal(0)
            } else {
                Game.current.heal()
            }
        }


        fun reset() {
            spellcount = 0
            itemcount = 0
            attack = 1000
            heal = 1000
            attacked = false
            healed = false
        }
    }
}