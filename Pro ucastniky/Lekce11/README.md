Lekce 11
========

Další události
--------------

Úkol - Seriózní desktopová aplikace - Mandala
---------------------------------------------

Cílem domácího úkolu je naprogramovat desktopovou aplikaci pro vymalovávání mandal.

### Část 1 - Vyplňování místo kreslení

 Založte novou okenní aplikaci, která bude podobná Kreslení z hodiny. Nahrajte do labelu obrázek mandaly a naprogramujte
 do ní stejnou funkcionalitu jako jsem měl já na konci hodiny. Inspirujte se videem z lekce.

Obrázek se dá nahrát pomocí následující metody.

~~~Java
    private void nahrajObrazek(File soubor) {
        try {
            obrazek = ImageIO.read(soubor);
        } catch (IOException ex) {
            throw new ApplicationPublicException(ex, "Nepodařilo se nahrát obrázek mandaly ze souboru " + soubor.getAbsolutePath());
        }
        labObrazek.setIcon(new ImageIcon(obrazek));
        labObrazek.setMinimumSize(new Dimension(obrazek.getWidth(), obrazek.getHeight()));
        pack();
        setMinimumSize(getSize());
    }
~~~

Z metody obsluhující otevření okna (na hodině **priOtevreniOkna**) ji zavolejte například takto:

~~~Java
        File soubor = new File("obrazek1.png");
        nahrajObrazek(soubor);
~~~

Tím říkáme, že se má použít soubor **obrazek1.png** a že ten se nachází ve složce projektu (tedy té, kde se nachází
mimo jiné soubor pom.xml); například to může být složka **C:\Java-Training\Projects\Java1\Lekce11\Ukol10-Mandala**.

V materiálech k lekci jsem vám dodal zdrojový text metody na vyplnění **BufferedImage** (a pár dalších, pomocných
metod). Jedná se o soubor [Vyplnovani.txt](Vyplnovani.txt).

*Poznámka:* Dejte pozor, aby **labObrazek** měl nastavené zarovnání **horizontalAlignment** na **LEFT** a
**verticalAlignment** na **TOP**. Tedy aby obrázek mandaly byl vlevo nahoře. Jinak nebudou souřadnice **X** a **Y** z
událostního objektu **MouseEvent e** správně odpovídat souřadnicím bodů na **BufferedImage obrazek** a po kliknutí na
mandalu se vám vyplní "předem těžko odhadnutelná oblast".

Ukázkový program by mohl vypadat takto:

![](ukol10-program.png)

### Část 2 - Volba barev

Přidejte na formulář ještě čtvercové labely na výběr barvy malování. Labely udělejte pevně velké (např. 32x32), nastavte
jim **opaque** na **true** a **background** na zvolenou barvu. Když uživatel klikne na nějakou barvu, mělo by se
vyplňovat touto barvou.

#### Rady na cestu

Připravte si vlastní sadu barev k vyplňování. Můžete použít [Adobe KULER](https://color.adobe.com/).

Zatímco v programu na hodině mělo okno mimo jiné vlastnost **stetec**, kterému jsme průběžně nastavovali různou barvu,
tady vůbec tuto vlastnost nepoužijeme (a tedy jí ani nebudeme nastavovat barvu). Místo toho bude lepší vytvořit
například vlastnost **zvolenaBarva** a měnit přímo tuto (a tu pak předávat metodě **vyplnObrazek**).

### Část 3 - Příprava vlastní mandaly

Mandal je na internetu spousta. Najděte nebo si nakreslete jednu vlastní. Mandala akorát musí být striktně
černo-bílá. Pouze dvě barvy! Pro příklad vyjděme třeba z této alternativní mandaly:
[ukol10-mandala2.png](ukol10-mandala2.png). Vypadá sice, že je černobílá, ale ve skutečnosti má vyhlazené čáry pomocí
stupňů šedi. Tyto stupně šedi je nutné oříznout na striktně černou a bílou. Například v IrfanView je menu Image ->
Decrease Color Depth... a zvolte 2 barvy.

Černo-bílá mandala pak vypadá takto (srovnejte s tou výše):
[ukol10-mandala2-blackwhite.png](ukol10-mandala2-blackwhite.png).

Dále je ale nutné opět obrázek mandaly povýšit na plnobarevný obrázek (16 milionů barev neboli 24 bitů na pixel). Proto
znovu zvolte Image -> Increase Color Depth... a zvolte 16,7 M barev.

Rozumná velikost mandaly je 640x640 bodů, ale můžete použít i jinak velké obrázky. IrfanView umí obrázky i zvětšovat a
zmenšovat. Vyzkoušejte a uvidíte samy.

V materiálech lekce jsou 2 hotové funkční mandaly.

### Nepovinná část 4 - Vylepšení

Program Mandal jakkoliv vylepšete. Napadá mě mnoho způsobů, co by ještě appka mohla umět.

#### Například nahrávání a ukládání obrázků

Bylo by fajn přidat klasické menu a do něj možnost uložit a nahrát libovolný obrázek. Inspirovat se můžete vzorovým
řešením.

Metoda obsluhující položku menu pro otevření obrázku může vypadat takto:

~~~Java
    private void priStiskuMenuOtevrit(ActionEvent e) {
        JFileChooser dialog;
        if (otevrenySoubor == null) {
            dialog = new JFileChooser(".");
        } else {
            dialog = new JFileChooser(otevrenySoubor.getParentFile());
        }
        dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dialog.setMultiSelectionEnabled(false);
        dialog.addChoosableFileFilter(new FileNameExtensionFilter("Obrázky (*.png)", "png"));
        int vysledek = dialog.showOpenDialog(this);
        if (vysledek != JFileChooser.APPROVE_OPTION) {
            return;
        }

        otevrenySoubor = dialog.getSelectedFile();
        nahrajObrazek(otevrenySoubor);
    }
~~~

Zde najdete metodu na uložení obrázku:

~~~Java
    private void ulozObrazek(File soubor) {
        try {
            ImageIO.write(obrazek, "png", soubor);
        } catch (IOException ex) {
            throw new ApplicationPublicException(ex, "Nepodařilo se uložit obrázek mandaly do souboru " + soubor.getAbsolutePath());
        }


    }
~~~

Zde je kód, který se uživatele zeptá na jméno souboru klasických ukládacím dialogem:

~~~Java
    private void ulozitJako() {
        JFileChooser dialog;
        dialog = new JFileChooser(".");

        int vysledek = dialog.showSaveDialog(this);
        if (vysledek != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File soubor = dialog.getSelectedFile();
        if (!soubor.getName().contains(".") && !soubor.exists()) {
            soubor = new File(soubor.getParentFile(), soubor.getName() + ".png");
        }
        if (soubor.exists()) {
            int potvrzeni = JOptionPane.showConfirmDialog(this, "Soubor " + soubor.getName() + " už existuje.\nChcete jej přepsat?", "Přepsat soubor?", JOptionPane.YES_NO_OPTION);
            if (potvrzeni == JOptionPane.NO_OPTION) {
                return;
            }
        }
        ulozObrazek(soubor);
    }
~~~

### Odevzdání domácího úkolu

Nejprve appku/appky zbavte přeložených spustitelných souborů.
Zařídíte to tak, že v IntelliJ IDEA vpravo zvolíte
Maven Projects -> Lifecycle -> Clean.
Úspěch se projeví tak, že v projektové složce zmizí
podsložka `target`.
Následně složku s projektem
zabalte pomocí 7-Zipu pod jménem `Ukol-CISLO-Vase_Jmeno.7z`.
(Případně lze použít prostý zip, například na Macu).
Takto vytvořený archív nahrajte na Google Drive do Odevzdávárny.

Pokud byste chtěli odevzdat revizi úkolu (např. po opravě),
zabalte ji a nahrajte ji na stejný Google Drive znovu,
jen tentokrát se jménem `Ukol-CISLO-Vase_Jmeno-verze2.7z`.

Termín odevzdání je dva dny před další lekcí, nejpozději 23:59.
Tedy pokud je další lekce ve čtvrtek, termín je úterý 23:59.
Pokud úkol nebo revizi odevzdáte později,
prosím pošlete svému opravujícímu kouči/lektorovi email nebo zprávu přes FB.
