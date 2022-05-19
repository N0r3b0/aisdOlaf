import java.text.Collator;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Kopiec <T extends Comparable <T>>
{
    public boolean czyJestKopcem()
    {
        int dlugosc = lista.size();
        boolean jestKopcem = true;
        for (int k = 1; k < lista.size(); k++)
        {
            if (2 * k >= dlugosc) return true;
            jestKopcem = lista.get(k).compareTo(lista.get(2 * k)) >= 0;
            if (2 * k + 1 >= dlugosc) return jestKopcem;
            jestKopcem = jestKopcem && lista.get(k).compareTo(lista.get(2 * k + 1)) >= 0;
            if (!jestKopcem) return false;
        }
        return true;
    }

    public void wynurzanie(int k)
    {
        T pom;
        while(k>1 && lista.get(k/2).compareTo(lista.get(k)) <= 0) //lista (k/2) < lista k
        {
            pom = lista.get(k);
            lista.set(k, lista.get(k/2));
            lista.set(k/2, pom);
            k = k/2;
        }
    }

    public void zanurzanie(int k)
    {
        int N = lista.size() - 1;
        int j;
        while (2*k <= N) {
            j = 2 * k;
            if (j < N && lista.get(j).compareTo(lista.get(j + 1)) < 0) j++;
            if (!(lista.get(k).compareTo(lista.get(j)) < 0)) break;
            T pom = lista.get(k);
            lista.set(k, lista.get(j));
            lista.set(j, pom);
            k = j;
        }
    }

    public void zanurzanie(int k, int N)
    {
        int j;
        while (2*k <= N) {
            j = 2 * k;
            if (j < N && lista.get(j).compareTo(lista.get(j + 1)) < 0) j++;
            if (!(lista.get(k).compareTo(lista.get(j)) < 0)) break;
            T pom = lista.get(k);
            lista.set(k, lista.get(j));
            lista.set(j, pom);
            k = j;
        }
    }

    public void utworzKopiec()
    {
        int N = lista.size();
        for(int k=N/2; k>=1; k--) zanurzanie(k);
    }

    public void sortowanie()
    {
        int N = lista.size()-1;
        for (int k=N/2; k >= 1; k--) zanurzanie(k);
        while (N > 1)
        {
            T pom = lista.get(1);
            lista.set(1, lista.get(N));
            lista.set(N--, pom);
            zanurzanie(1, N);
        }
    }

    ArrayList<T> lista = new ArrayList<>();

    public void dopisz(T dopisywany)
    {
        lista.add(dopisywany);
        wynurzanie(lista.size()-1);
    }

    //public void dopisz(ArrayList<T> lista, T dopisywany){} //??

    public static Integer[] generujListe(Integer[] lista)
    {
        Random generator = new Random();


        lista[0] = 0;       //pierwszy element w heapSortowaniu nie jest istotny
        for (int i = 1; i < lista.length; i++)
        {
            lista[i] = (generator.nextInt(100));
        }
        return lista;
    }

    public void pobierzPierwszego()
    {
        int N = lista.size()-1;
        lista.set(1, lista.get(N));
        lista.remove(N);
    }

    public void drukuj()
    {
        for (int i=1; i<lista.size(); i++)
            System.out.print(lista.get(i));
        System.out.println();
    }


    public static void main(String[] args)
    {
        Kopiec<Osoba> kopiecO = new Kopiec<>();
        kopiecO.lista.add(new Osoba());
        kopiecO.dopisz(new Osoba());
        kopiecO.dopisz(new Osoba("Bowak", "Aazimierz", "2002-11-24" ));
        kopiecO.dopisz(new Osoba("Aowak", "Kazimierz", "2002-10-13" ));
        kopiecO.dopisz(new Osoba("Cowak", "Zazimierz", "2002-10-13" ));

        System.out.println(kopiecO.czyJestKopcem());
    }
}

class Osoba implements Comparable <Osoba>
{
    public String nazwisko = "DomyslneNazwisko";
    public String imie = "DomyslneImie";
    public String dataUr = "2000-12-12";
    static LocalDate dzisiaj = LocalDate.now();
    static int bRok = dzisiaj.getYear();
    static int bMiesiac = dzisiaj.getMonthValue();
    @Override
    public int compareTo(Osoba o)
    {
        /*Collator c = Collator.getInstance(new Locale("pl", "PL"));
        int porownanieNazwisk = c.compare(this.nazwisko, o.nazwisko);
        int porownanieImion = c.compare(this.imie, o.imie);
        if(porownanieNazwisk == 0)
            return porownanieImion;
        return porownanieNazwisk;*/


        double rW=this.wiek(this.dataUr) - o.wiek(o.dataUr);
        if(rW > 0) return 1;
        else if (rW < 0) return -1;
        else return 0;
    }

    public int porownanieWieku(Osoba o, double wiek)
    {
        Collator c = Collator.getInstance(new Locale("pl", "PL"));
        int porownanieWieku = c.compare(this.nazwisko, o.nazwisko);
        int porownanieImion = c.compare(this.imie, o.imie);


        return porownanieWieku;
    }

    public Osoba (String nazwisko, String imie, String dataUr)
    {
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.dataUr = dataUr;
    }

    public Osoba() {}

    public String toString()
    {
         return nazwisko + " " + imie + " " + dataUr + " wiek " + String.format("%5.2f\n", wiek(dataUr));
    }

    double wiek(String dataUr)
    {
        String[] data = dataUr.split("-");
        LocalDate urodzenie = LocalDate.of(Integer.parseInt(data[0]),
                Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        Period per = Period.between(urodzenie, dzisiaj);
        return per.getYears() + per.getMonths()/12.0 + per.getDays()/365.0;
    }
}
