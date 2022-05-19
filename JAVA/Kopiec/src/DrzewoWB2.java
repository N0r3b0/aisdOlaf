import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Locale;

class DrzewoWB2 <K1>
{
    private Wierzcholek korzen;
    class Wierzcholek
    {
        K1 klucz;
        Wierzcholek lewyPotomek;
        Wierzcholek prawyPotomek;
        int rozmiarP;

        public Wierzcholek(K1 a, int N)
        {
            this.klucz = a;
            this.rozmiarP = N;
        }

        public String toString()
        {
            String wynik = String.format("%-11s%-11s%02d", klucz, rozmiarP);
            return wynik;
        }
    }

    public Wierzcholek miejsceDla(K1 klucz)
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
        if(korzen == null) korzen = new Wierzcholek(klucz, 1);
        else
        {
            if (kierunek.equals("lewy")) wP.lewyPotomek = new Wierzcholek(klucz, 1);
            if (kierunek.equals("prawy")) wP.prawyPotomek = new Wierzcholek(klucz, 1);
            //if (kierunek.equals("trafiony")) wP.wartosc = wartosc;
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
            if (s1.getClass().getName().endsWith("Character"))
                return c.compare(String.valueOf(s1), String.valueOf(s2));
            return c.compare(s1, s2);
        }
    }; //???

    public void dopisz(K1 key)
    {
        korzen = dopisz(korzen, key);
    }

    public int rozmiar(Wierzcholek x)
    {
        if(x == null) return 0;
        else return x.rozmiarP;
    }

    private Wierzcholek dopisz (Wierzcholek x, K1 key)
    {
        if(x == null) return new Wierzcholek(key, 1);
        int porownanie = compS.compare(key, x.klucz);
        if (porownanie < 0) x.lewyPotomek = dopisz(x.lewyPotomek, key);
        else if (porownanie > 0) x.prawyPotomek = dopisz(x.prawyPotomek, key);
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

    ArrayList <Wierzcholek> kolejka = new ArrayList<>();

    public static void main(String[] args)
    {

        DrzewoWB2<String> drzewo = new DrzewoWB2<String>();
        String tekst = "Podczas implementacji programu programista często " +
                "Ponieważ struktury danych są w programie rzeczą szczególnie ";

        System.out.println(tekst.charAt(1));
        System.out.println("click");

        //drzewo.przeszukajZakres();

    }
}
