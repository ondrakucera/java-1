package cz.czechitas.pocitac;

public class Pocitac {

    boolean jeZapnuty;
    Procesor cpu;
    Pamet ram;
    Disk pevnyDisk;

    public String toString() {
        String result;
        if (jeZapnuty) {
            result = "Zapnuty pocitac [";
        } else {
            result = "Vypnuty pocitac [";
        }
        if (cpu != null) {
            result = result + cpu.toString();
            result = result + ", ";
        }
        if (ram != null) {
            result = result + ram.toString();
            result = result + ", ";
        }
        if (pevnyDisk != null) {
            result = result + pevnyDisk.toString();
        }
        result = result + "]";
        return result;
    }

    public void zapniSe() {
        if (jeZapnuty) {
            System.out.println("Pocitac je jiz zapnuty. Nelze ho znovu zapnout.");
        } else if (cpu == null) {
            System.out.println("Pocitaci chybi procesor. Nelze ho zapnout.");
        } else if (ram == null) {
            System.out.println("Pocitaci chybi pamet. Nelze ho zapnout.");
        } else if (pevnyDisk == null) {
            System.out.println("Pocitaci chybi pevny disk. Nelze ho zapnout.");
        } else {
            jeZapnuty = true;
            System.out.println("Pocitac se zapnul.");
        }
    }

    public void vypniSe() {
        if (jeZapnuty) {
            jeZapnuty = false;
            System.out.println("Pocitac se vypnul.");
        } else {
            System.out.println("Pocitac je jiz vypnuty.");
        }
    }

    public void vytvorSouborOVelikosti(long velikost) {
        if (!jeZapnuty) {
            System.out.println("Pocitac neni zapnuty. Nelze vytvaret soubory.");
        } else {
            if (pevnyDisk.vyuziteMisto + velikost < pevnyDisk.kapacita) {
                pevnyDisk.vyuziteMisto = pevnyDisk.vyuziteMisto + velikost;
                System.out.println("Vytvoril se soubor o velikosti " + velikost + " bajtu.");
                System.out.print("    ");
                System.out.println(pevnyDisk.toString());
                System.out.println();
            } else {
                System.out.println("Na disku neni dost mista pro vytvoreni souboru " + velikost + ".");
                System.out.print("    ");
                System.out.println(pevnyDisk.toString());
                System.out.println();
            }
        }
    }

    public void vymazSouboryOVelikosti(long velikost) {
        if (!jeZapnuty) {
            System.out.println("Pocitac neni zapnuty. Nelze mazat soubory.");
        } else {
            if (pevnyDisk.vyuziteMisto < velikost) {
                velikost = pevnyDisk.vyuziteMisto;
            }
            pevnyDisk.vyuziteMisto = pevnyDisk.vyuziteMisto - velikost;
            System.out.println("Vymazal se soubor o velikosti " + velikost + " bajtu.");
            System.out.print("    ");
            System.out.println(pevnyDisk.toString());
            System.out.println();
        }
    }
}
