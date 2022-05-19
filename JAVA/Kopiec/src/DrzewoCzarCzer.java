import com.sun.tools.javac.Main;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class DrzewoCzarCzer <K1, K2>
{
    static final boolean CZERWONY = true;
    static final boolean CZARNY = false;
    private DrzewoCzarCzer.Wierzcholek korzen;

    class Wierzcholek
    {
        K1 klucz;
        K2 wartosc;
        DrzewoCzarCzer.Wierzcholek lewyPotomek;
        DrzewoCzarCzer.Wierzcholek prawyPotomek;
        int N = 0;
        boolean kolor;

        public Wierzcholek(K1 a, K2 b, int N, boolean kolor)
        {
            this.klucz = a;
            this.wartosc = b;
            this.N = N;
            this.kolor = kolor;
        }

        public String toString()
        {
            String wynik = String.format("%12s %12s wielkosc %5s", klucz, wartosc, N);
            return wynik;
        }
    }

    public int rozmiar(DrzewoCzarCzer.Wierzcholek x)
    {
        if(x == null) return 0;
        else return x.N;
    }

    public Wierzcholek rotacjaLewa(Wierzcholek h)
    {
        //obracamy wokol wierzcholka h
        Wierzcholek x = h.prawyPotomek;
        h.prawyPotomek = x.lewyPotomek;
        x.lewyPotomek = h;
        x.kolor = h.kolor;
        h.kolor = CZERWONY;
        x.N = h.N;
        h.N = 1+rozmiar(h.lewyPotomek) + rozmiar(h.prawyPotomek);
        return x;
    }

    public Wierzcholek rotacjaPrawa(Wierzcholek h)
    {
        //obracamy wokol wierzcholka h
        Wierzcholek x = h.lewyPotomek;
        h.lewyPotomek = x.prawyPotomek;
        x.prawyPotomek = h;
        x.kolor = h.kolor;
        h.kolor = CZERWONY;
        x.N = h.N;
        h.N = 1+rozmiar(h.lewyPotomek) + rozmiar(h.prawyPotomek);
        return x;
    }

    public void zmienKolory(Wierzcholek h)
    {
        h.kolor = CZERWONY;
        h.lewyPotomek.kolor = CZARNY;
        h.prawyPotomek.kolor = CZARNY;
    }

    boolean jestCzerwony (Wierzcholek x)
    {
        if (x == null) return false;
        return x.kolor == CZERWONY;
    }

    Comparator<K1> compS = new Comparator<K1>()
    {
        Collator c = Collator.getInstance(new Locale("pl","PL"));
        @Override
        public int compare(K1 s1, K1 s2)
        {
            if (s1.getClass().getName().endsWith("Integer"))
            {
                System.out.println(" Tak " + s1.getClass().getName());
                return (int) Math.signum((int)s1-(int)s2);
            }
            return c.compare(s1, s2);
        }
    };
    public void dopisz(K1 key, K2 value)
    {
        korzen = dopisz(korzen, key, value);
        korzen.kolor = CZARNY;
    }
    private Wierzcholek dopisz (Wierzcholek wierz, K1 key, K2 value)
    {
        if(wierz == null) return new Wierzcholek(key, value, 1, CZERWONY);
        int porownanie = compS.compare(key, wierz.klucz);
        if (porownanie < 0) wierz.lewyPotomek = dopisz(wierz.lewyPotomek, key, value);
        else if (porownanie > 0) wierz.prawyPotomek = dopisz(wierz.prawyPotomek, key, value);
        else {wierz.wartosc = value; /*x.ile++*/};
        if (jestCzerwony(wierz.prawyPotomek) && !jestCzerwony(wierz.lewyPotomek))
            wierz = rotacjaLewa(wierz);
        if (jestCzerwony(wierz.lewyPotomek) && jestCzerwony(wierz.lewyPotomek.lewyPotomek))
            wierz = rotacjaPrawa(wierz);
        if (jestCzerwony(wierz.prawyPotomek) && jestCzerwony(wierz.lewyPotomek))
            zmienKolory(wierz);

        wierz.N = rozmiar(wierz.lewyPotomek) + rozmiar(wierz.prawyPotomek)+1;
        return wierz;
    }

    public int zwrocPoziom(K1 klucz)
    {
        int poziom = 0;
        DrzewoCzarCzer.Wierzcholek w = korzen;
        while (w != null)
        {
            poziom++;
            int porownanie = compS.compare(klucz, (K1) w.klucz);
            if (porownanie < 0)
                w = w.lewyPotomek;
            else
                return poziom;
        }
        return -1;
    }

    public void przeszukajZakres(Wierzcholek x, K1 dolny, K1 gorny)
    {
        if (x == null) return;
        int dZDanym = compS.compare(dolny, x.klucz);
        int gZDanym = compS.compare(gorny, x.klucz);
        if (dZDanym < 0) przeszukajZakres(x.lewyPotomek, dolny, gorny);
        if (dZDanym <= 0 && gZDanym >= 0) kolejka.add(x);
        if (gZDanym > 0) przeszukajZakres(x.prawyPotomek, dolny, gorny);
    }
    ArrayList<DrzewoCzarCzer.Wierzcholek> kolejka = new ArrayList<>();

    public static void main(String[] args)
    {
        DrzewoCzarCzer<String,String> drzewo = new DrzewoCzarCzer<String, String>();

        drzewo.dopisz("Bratyslawa", "2017-01-23");
        drzewo.dopisz("aw", "2017-01-22");
        drzewo.dopisz("Alabama", "2016-01-23");
        drzewo.dopisz("Aleksandria ", "2016-01-23");
        drzewo.dopisz("Czestochowa", "2018-05-23");
        drzewo.dopisz("Dubrownik", "2020-02-27");
        drzewo.dopisz("E", "2017-02-11");
        drzewo.dopisz("F", "2017-01-12");
        drzewo.dopisz("G", "2017-01-13");
        drzewo.dopisz("K", "2016-01-14");
        drzewo.dopisz("O ", "2016-01-15");
        drzewo.dopisz("P", "2018-05-16");
        drzewo.dopisz("I", "2020-02-17");
        drzewo.dopisz("J", "2017-02-18");


        drzewo.przeszukajZakres(drzewo.korzen, " ", "Ż");
        for (int i =0; i < drzewo.kolejka.size(); i++)
            System.out.println(drzewo.kolejka.get(i));


    }
}
