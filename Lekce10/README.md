Lekce 10
========

JKeyboard, Seznam objektů
-------------------------

### Osnova

1. Poznámky k domácímu úkolu
    * JKeyboard
    * Seznam objektů

### Videozáznam

Na YouTube se můžete podívat na [záznam z lekce](https://www.youtube.com/watch?v=0OHTx6GPtzs),
případně si prohlédnout [celý playlist](https://www.youtube.com/playlist?list=PLUVJxzuCt9AROpKl3Hu-DvdgQV-xHaoQY).

Úkol 08 - Ping Pong
------------------------------

V hodině jsme programovali Pong (naši verzi první počítačové hry na světě, 1972, wikipedia).

Dokončete Pong tak, aby uměl např. tyto věci: (toto je pouze seznam návrhů, který je doporučený).

* Hráči se ovládají pomocí W/S a šipky nahoru/dolů.
* Míček se odráží od horní a dolní stěny, ale ne od bočních změn.
* Míček se odráží od hráčů (s použitím kolize objektů).
* Pokud hráč nezachytí míček a uteče mu za okraj, protihráč dostane bod.
* Pokud se míček pohybuje s úhlem a rychlostí (ne napevno 45°), můžete složit hráče z více labelů (horní část, střední
  část a spodní část) a měnit odrazový úhel podle části, do které míček narazil.
* S přibývajícími body můžete míček zrychlovat.

Poznámka: Klávesy mají čísla, ale místo konkrétních čísel je doporučeno používat předpřipravené konstanty:

    if (kodKlavesy == 38) {
        // pohyb nahoru
    }

    // Lepší verze:
    if (kodKlavesy == KeyEvent.VK_UP) {
        // pohyb nahoru
    }

### Odevzdání domácího úkolu

Domácí úkol (složky s projekty) zabalte pomocí 7-Zipu pod jménem **Ukol08-Vase_Jmeno.7z**. (Případně lze použít prostý
zip, například na Macu). Takto vytvořený archív nahrajte na Google Drive do složky
[Úkol 08](https://drive.google.com/drive/u/0/folders/1-iuBrEqSQuFNBb7GbGN71YtNDe21gC06).

Vytvořte snímek obrazovky spuštěného programu a pochlubte se s ním
[v galerii na Facebooku](https://www.facebook.com/media/set/?set=oa.250221688983745&type=3).

Pokud byste chtěli odevzdat revizi úkolu (např. po opravě), zabalte ji a nahrajte ji na stejný Google Drive znovu, jen
tentokrát se jménem **Ukol08-Vase_Jmeno-verze2.7z**.
