# Hogwarts
---
## Futtatás:
Windows:
- Töltsd le a build/libs mappa tartalmát
- dupla klikk a Hogwarts.bat-ra

Windows/Linux terminal:
- java -jar .\Hogwarts-1.0-SNAPSHOT.jar

---

## CLI:

(csak tesztelésre használandó)

test [num]

- előre megírt tesztek lefuttatása
- num - a teszt sorszáma
- pl.: test 0

add [card] [num]

- az aktuális játékos kezébe tesz egy megadott lapot, ha megadunk számot, akkor annyit tesz bele
- card - a kártya neve, magyarul, kisbetűvel úgy ahogy a lapon rajta van, a szóköz helyett alulvonás van
- num - opcionális paraméter, az alapértelmezett az 1
- pl.: add albus_dumbledore
- pl.: add alohomora 7

print

- információt ír ki az adott játékosról
- <3 jelzés a kapott szív jelölőket számolja
- / jelzés a kapott villámokat
- $ jelzés a kapott érméket
- Élet: a még adott körben a játékos élete
- Pont: hány ájulása van még hátra

turn

- kör vége, a másik játékos jön

play [idx]

- egy lap kijátszása a kézből
- idx - a kézben tartott lap sorszáma amit ki szeretnénk játszani (ez a print parancsnál látható sorszám)
- pl.: play 0

drop [idx]

- egy lap eldobása a kézből
- idx - a kézben tartott lap sorszáma amit ki szeretnénk játszani (ez a print parancsnál látható sorszám)
- pl.: drop 1

use [idx]

- egy szövetséges képességének felhasználása
- idx - a kijátszott szövetségesek közül annak a sorszáma amit ki szeretnénk játszani (ez a print parancsnál látható
  sorszám)
- pl.: use 0

<3

- szívek felhasználása

/

- villámok felhasználása

buy [idx]

- vásárlás a tanteremből
- idx - a lap sorszáma a tanteremben
- ha nem adunk meg idx-et, akkor kiírja a tanterem lapjait
- pl.: buy
- pl.: buy 3

end

- kilépés a programból

Egyes lapok választást kínálnak fel, ilyenkor a kijátszás vagy használat után kell begépelni a kiírt kérdésre a választ.







