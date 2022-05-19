import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class    Kopiec <T extends Comparable <T>>
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

    public static double[] generujListe(double[] lista)
    {
        Random generator = new Random();


        lista[0] = 0;       //pierwszy element w heapSortowaniu nie jest istotny
        for (int i = 1; i < lista.length; i++)
        {
           // lista[i] = (generator.nextDouble(100));
        }
        return lista;
    }


    public  void drukuj()
    {
        for (int i=1; i<lista.size(); i++)
            System.out.print(lista.get(i) + ", ");
        System.out.println();
    }


    public static void main(String[] args)
    {
        Kopiec<String> kopiec = new Kopiec<>();
        String listaA[] = {"", "B", "A", "C", "E", "D"};
        for(String s:listaA)     //??? s = zmienna podstawiana(?)
            kopiec.lista.add(s);
        kopiec.drukuj();
        System.out.println("kopiec " + kopiec.czyJestKopcem());

        kopiec.utworzKopiec();
        kopiec.drukuj();
        System.out.println("kopiec " + kopiec.czyJestKopcem());




        Kopiec<Double> kopiec1 = new Kopiec<>();
        double lista1[] = new double[30];
        generujListe(lista1);
        for(Double d:lista1)
            kopiec1.lista.add(d);
        kopiec1.drukuj();
        System.out.println("kopiec " + kopiec1.czyJestKopcem());

        kopiec1.utworzKopiec();
        kopiec1.drukuj();
        System.out.println("kopiec " + kopiec1.czyJestKopcem());

        //kopiec1.dopisz(7);

        kopiec1.utworzKopiec();
        kopiec1.drukuj();
        System.out.println("kopiec " + kopiec1.czyJestKopcem());

        kopiec1.sortowanie();
        kopiec1.drukuj();
    }
}

class Osoba implements Comparable <Osoba>
{
    public String nazwisko, imie, dataUr;
    @Override
    public int compareTo(Osoba o)
    {
        Collator c = Collator.getInstance(new Locale("pl", "PL"));
        int porownanieNazwisk = c.compare(this.nazwisko, o.nazwisko);
        int porownanieImion = c.compare(this.imie, o.imie);
        if(porownanieNazwisk == 0)
            return porownanieImion;
        return porownanieNazwisk;
    }
    public Osoba (String nazwisko, String imie, String dataUr)
    {
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.dataUr = dataUr;
    }
}




























/*
    public static void czyJestKopcemOdpowiedz(int[] lista)
    {
        if (czyJestKopcem(lista) == true)
            System.out.println("Lista jest kopcem");
        else
            System.out.println("Lista nie jest kopcem");
    }

    public static void wynurzanie(int[] lista, int k)
    {
        int pom;
        while(k>1 && lista[k/2]<lista[k])
        {
            pom = lista[k];
            lista[k] = lista[k/2];
            lista[k/2] = pom;
            k = k/2;
        }
    }

    public static void zanurzanie(int[] lista, int k)
    {
        int N = lista.length - 1;
        int j;
        while (2*k <= N) {
            j = 2 * k;
            if (j < N && lista[j] < lista[j + 1]) j++;
            if (!(lista[k] < lista[j])) break;
            int pom = lista[k];
            lista[k] = lista[j];
            lista[j] = pom;
            k = j;
        }
    }

    public static void zanurzanie(int[] lista, int k, int N)
    {
        int j;
        while (2*k <= N) {
            j = 2 * k;
            if (j < N && lista[j] < lista[j + 1]) j++;
            if (!(lista[k] < lista[j])) break;
            int pom = lista[k];
            lista[k] = lista[j];
            lista[j] = pom;
            k = j;
        }
    }

    public static void utworzKopiec(int[] abc)
    {
        int N = abc.length;
        for(int k=N/2; k>=1; k--) zanurzanie(abc, k);
    }

    public static void sortowanie(int[] abc)
    {
        int N = abc.length-1;
        for (int k=N/2; k >= 1; k--) zanurzanie(abc, k);
        while (N > 1)
        {
            int pom = abc[1];
            abc[1] = abc[N];
            abc[N--] = pom;
            zanurzanie(abc, 1, N);
        }
    }

    public static int[] generujListe(int[] lista)
    {
        Random generator = new Random();

        lista[0] = 0;       //pierwszy element w heapSortowaniu nie jest istotny
        for (int i = 1; i < lista.length; i++)
        {
            lista[i] = generator.nextInt(100);
        }
        return lista;
    }


    public static void drukuj(int[] lista)
    {
        for (int i=0; i<lista.length; i++)
            System.out.print(lista[i] + ", ");
        System.out.println();
    }

    public static void main(String[] args)
    {
        int[] lista = new int[30];
        generujListe(lista);
        drukuj(lista);
        czyJestKopcemOdpowiedz(lista);
        System.out.println();

        utworzKopiec(lista);
        drukuj(lista);
        czyJestKopcemOdpowiedz(lista);
        System.out.println();

        sortowanie(lista);
        drukuj(lista);
        System.out.println("posortowana lista");
        System.out.println();

    }
}
*/