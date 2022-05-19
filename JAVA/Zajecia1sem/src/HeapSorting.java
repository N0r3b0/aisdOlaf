import java.util.Random;

public class HeapSorting
{
    public static boolean czyJestKopcem (int[] abc)
    {
        int dlugosc = abc.length;
        boolean jestKopcem = true;
        for (int k = 1; k<abc.length; k++)
        {
            if (2*k >= dlugosc) return true;
            jestKopcem = abc[k] >= abc[2*k];
            if (2*k+1 >= dlugosc) return jestKopcem;
            jestKopcem = jestKopcem && abc[k] >= abc[2*k+1];
            if (!jestKopcem) return false;
        }
        return true;
    }

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

    /*static int wyszukaj(int[] lista, int wartosc)
    {
        int srodek = 0;
        int odPoz = 0;
        int doPoz = lista.length-1;
        while(!(odPoz > doPoz))
        {
            srodek = odPoz+(doPoz-odPoz)/2;
            if(wartosc < lista[srodek])
                doPoz = srodek-1;
            else if (wartosc > lista[srodek])
                odPoz = srodek+1;
            else return srodek;
        }
        return odPoz;
    }*/

    public static void drukuj(int[] lista)
    {
        for (int i=0; i<lista.length; i++)
            System.out.print(lista[i] + ", ");
        System.out.println();
    }

    public static void main(String[] args)
    {
        int[] lista = {0,6,7,4};
        czyJestKopcemOdpowiedz(lista);
        /*int szukana = 6;
        int wynik = wyszukaj(lista, szukana);
        System.out.println("Liczba " + szukana + " znajduje się na pozycji " + wynik);*/
    }
}

