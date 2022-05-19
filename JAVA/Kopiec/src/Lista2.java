public class Lista2
{
    private static class ObiektListy
    {
        Osoba element;
        ObiektListy nastepny = null;
        ObiektListy poprzedni = null;
    }
    ObiektListy pierwszy, ostatni;

    boolean jestPusta() {return N==0;}
    int N=0;


    public void dodajDoListy(Osoba o)
    {
        ObiektListy staryOstatni = ostatni;
        ostatni = new ObiektListy();               //tworzenie nowego obiektu i umieszczenie go na koncu listy
        ostatni.element = o;
        ostatni.poprzedni = staryOstatni;
        if (jestPusta()) pierwszy = ostatni;
        else
            staryOstatni.nastepny = ostatni;       //przy tworzeniu nowego obiektu z klasy ObiektLista ma on domyslnie wartość null
        N++;
        wynurzanie(); //tworzy strukture kopca przy dodawaniu nowych elementow
    }

    public Osoba pobierzPierwszego()
    {
        if(jestPusta()) return null;
        Osoba o = pierwszy.element;
        pierwszy = pierwszy.nastepny;
        if(pierwszy != null)
            pierwszy.poprzedni = null;
        N--;
        if(jestPusta()) ostatni = null;
        return o;
    }

    public ObiektListy nrPozycji(int nrIndeksu)
    {
        if (nrIndeksu >= N) return null;
        Lista2.ObiektListy biezacy = pierwszy;
        for(int i = 0; i < nrIndeksu; i++)
        {
            biezacy = biezacy.nastepny;
        }
        return biezacy;
    }

    public Osoba pobierzItego(int nrIndeksu)
    {
        if (nrIndeksu >= N) return null;
        Lista2.ObiektListy biezacy = pierwszy;
        biezacy = nrPozycji(nrIndeksu);
        return biezacy.element;
    }

    public void wstaw(Osoba o, int nrIndeksu)
    {
        ObiektListy staryIty = nrPozycji(nrIndeksu);
        ObiektListy poprzedni = staryIty.poprzedni;
        ObiektListy nowyIty = new ObiektListy();

        nowyIty.element = o;
        nowyIty.nastepny = staryIty;
        nowyIty.poprzedni = poprzedni;

        poprzedni.nastepny = nowyIty;
        staryIty.poprzedni = nowyIty;
    }

    public void zamien(int nrIndeksu1, int nrIndeksu2)
    {
        if (nrIndeksu1 >= N || nrIndeksu2 >= N)
        {
            System.out.println("Przekroczony zakres dla zamiany");
            return;
        }
        Osoba o1, o2;
        o1 = pobierzItego(nrIndeksu1);
        //System.out.println(o1);
        o2 = pobierzItego(nrIndeksu2);
        //System.out.println(o2);
        nrPozycji(nrIndeksu1).element = o2;
        nrPozycji(nrIndeksu2).element = o1;
    }

    public boolean czyJestKopcem()
    {
        int dlugosc = N;
        boolean jestKopcem = true;
        for (int k = 1; k < N; k++)
        {
            if (2 * k >= dlugosc) return true;
            jestKopcem = pobierzItego(k).compareTo(pobierzItego(2 * k)) >= 0;
            if (2 * k + 1 >= dlugosc) return jestKopcem;
            jestKopcem = jestKopcem && pobierzItego(k).compareTo(pobierzItego(2 * k + 1)) >= 0;
            if (!jestKopcem) return false;
        }
        return true;
    }

    public void wynurzanie()
    {
        int k = N-1;
        ObiektListy pierwszyD = nrPozycji(k/2);
        ObiektListy drugiD = nrPozycji(k);
        boolean warunek = pierwszyD.element.compareTo(drugiD.element) < 0;
        while(k>1 && warunek) //lista (k/2) < lista k
        {
            zamien(k/2, k);
            k=k/2;
            if(k==1) break;
            pierwszyD = nrPozycji(k/2);
            drugiD = nrPozycji(k);
            warunek = pierwszyD.element.compareTo(drugiD.element) < 0;
        }
    }

    void drukuj()
    {
        Lista2.ObiektListy biezacy = pierwszy;
        do
        {
            System.out.println(biezacy.element);
            biezacy = biezacy.nastepny;
        }
        while (biezacy.nastepny != null);
        System.out.println(biezacy.element);
    }

    void drukujOdwrotnie()
    {
        Lista2.ObiektListy biezacy = ostatni;
        do
        {
            System.out.println(biezacy.element);
            biezacy = biezacy.poprzedni;
        }
        while (biezacy.poprzedni != null);
        System.out.println(biezacy.element);
    }

    public static void main(String[] args)
    {
        String[][] osoby = {{"element", "zerowy", "2000-01-11"},
                {"Bisniewski", "Kazimierz", "1998-01-11"},
                {"Aisniak", "Janusz", "1998-10-11"},
                {"Czyc", "Barbara", "1998-10-11"},
                {"Boruta", "Zygmunt", "1999-05-24"}};

        Lista2 obiekt = new Lista2();
        for (int i=0; i < osoby.length; i++)
        {
            obiekt.dodajDoListy(new Osoba(osoby[i][0], osoby[i][1], osoby[i][2]));
        }


        obiekt.drukuj();
        System.out.println("--------");
        Osoba o1 = new Osoba("Basdsadsadsadsauta", "Zygmunt", "1999-05-24");
        obiekt.wstaw(o1, 2);
        obiekt.drukuj();
    }
}
