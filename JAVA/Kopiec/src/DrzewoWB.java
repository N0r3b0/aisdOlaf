import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Locale;

public class DrzewoWB <K1, K2>
{
    private Wierzcholek korzen;
            class Wierzcholek
            {
                K1 klucz;
                K2 wartosc;
                Wierzcholek lewyPotomek;
                Wierzcholek prawyPotomek;
                int rozmiarP;

                public Wierzcholek(K1 a, K2 b, int N)
                {
                    this.klucz = a;
                    this.wartosc = b;
                    this.rozmiarP = N;
                }

                public String toString()
                {
                    String wynik = String.format("%-11s%-11s%02d", klucz, wartosc, rozmiarP);
                    return wynik;
                }
            }

            public Wierzcholek miejsceDla(K1 klucz, K2 wartosc)
            {
                String kierunek="";
                Wierzcholek w=korzen, wP=null;
                while (w!=null)
                {
                    wP=w;
                    int porownanie = compS.compare(klucz, w.klucz);
                    if(porownanie < 0)
                    {
                        kierunek = "lewy";
                        w = w.lewyPotomek;
                    }
                    else if (porownanie > 0)
                    {
                        kierunek = "prawy";
                        w=w.prawyPotomek;
                    }
                    else if (porownanie == 0)
                    {
                        kierunek = "trafiony"; break;
                    }
                }
                if(korzen == null) korzen = new Wierzcholek(klucz, wartosc, 1);
                    else
                    {
                        if (kierunek.equals("lewy")) wP.lewyPotomek = new Wierzcholek(klucz, wartosc, 1);
                        if (kierunek.equals("prawy")) wP.prawyPotomek = new Wierzcholek(klucz, wartosc, 1);
                        if (kierunek.equals("trafiony")) wP.wartosc = wartosc;
                    }
                    return wP;
            }

            Comparator <K1> compS = new Comparator<K1>()
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
            }; //???

    public void dopisz(K1 key, K2 value)
    {
        korzen = dopisz(korzen, key, value);
    }

    public int rozmiar(Wierzcholek x)
    {
        if(x == null) return 0;
        else return x.rozmiarP;
    }

    private Wierzcholek dopisz (Wierzcholek x, K1 key, K2 value)
    {
        if(x == null) return new Wierzcholek(key, value, 1);
        int porownanie = compS.compare(key, x.klucz);
        if (porownanie < 0) x.lewyPotomek = dopisz(x.lewyPotomek, key, value);
        else if (porownanie > 0) x.prawyPotomek = dopisz(x.prawyPotomek, key, value);
        else {x.wartosc = value; /*x.ile++*/};
        x.rozmiarP = rozmiar(x.prawyPotomek) + rozmiar(x.lewyPotomek)+1;
        return x;
    }

    public Wierzcholek minTR(Wierzcholek w)
    {
        if (w.lewyPotomek == null) return w;
        return minTR(w.lewyPotomek);
    }

    public Wierzcholek maxTR(Wierzcholek w)
    {
        if (w.prawyPotomek == null) return w;
        return maxTR(w.prawyPotomek);
    }

    public Wierzcholek znajdz(Wierzcholek x, K1 klucz)
    {
        if(x == null) return null;
        int porownanie = compS.compare(klucz, x.klucz);
        if (porownanie < 0) return znajdz(x.lewyPotomek, klucz);
        else if (porownanie > 0) return znajdz(x.prawyPotomek, klucz);
        else return x;
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

    public int zwrocPoziom(K1 klucz)
    {
        int poziom = 0;
        DrzewoWB.Wierzcholek w = korzen;
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

    ArrayList <Wierzcholek> kolejka = new ArrayList<>();

    public static void main(String[] args)
    {
       DrzewoWB<String,String> drzewo = new DrzewoWB<String, String>();

        drzewo.miejsceDla("Bratyslawa", "2017-01-23");
        drzewo.miejsceDla("Alabama", "2016-01-23");
        drzewo.miejsceDla("Aleksandria ", "2016-01-23");
        drzewo.miejsceDla("Czestochowa", "2018-05-23");
        drzewo.miejsceDla("Dubrownik", "2020-02-27");


        drzewo.przeszukajZakres(drzewo.korzen, "A", "B");

        for (int i =0; i < drzewo.kolejka.size(); i++)
            System.out.println(drzewo.kolejka.get(i));

        //System.out.println(drzewo.korzen.lewyPotomek);
        drzewo.kolejka.clear();

        drzewo.przeszukajZakres(drzewo.korzen, "A", "C");

        for (int i =0; i < drzewo.kolejka.size(); i++)
            System.out.println(drzewo.kolejka.get(i));

        System.out.println(drzewo.zwrocPoziom("Dubrownik"));

    }
}
