import java.text.Collator;
import java.util.Locale;
import java.util.Random;

public class SortowanieStringow
{
    public static void drukuj(int[] lista)
    {
        for (int i = 0; i < lista.length; i++)
            System.out.print(lista[i] + " ");
        System.out.println();
    }

    public static int[] generujListe(int[] lista)
    {
        Random generator = new Random();

        lista[0] = 0;
        for (int i = 1; i < lista.length; i++)
        {
            lista[i] = generator.nextInt(100);
        }
        return lista;
    }

    static void sortuj (int[] lista)
    {
        int doPozycji = lista.length - 1;
        boolean posortowane;
        do
        {
            posortowane = true;
            for (int i = 0; i < doPozycji ; i++)
                if (lista[i] > lista[i + 1])
                {
                    int bufor = lista[i];
                    lista[i] = lista[i + 1];
                    lista[i + 1] = bufor;
                    posortowane = false;
                }
            doPozycji--;
        }
        while (!posortowane);
    }

    static Collator porownywacz = Collator.getInstance(new Locale("pi"));

    static void sortujStringi (String[] listaNazwisk)
    {
        String[] lista1 = {"Nowak", "Kania", "Adamski", "Trocki"};
        for(int j=0; j<lista1.length; j++)
        {
            porownywacz.compare(lista1[j], lista1[j+1]);
            System.out.println(lista1[j]);
        }
    }

    public static void main(String[] args)
    {
        int tab1[] = new int[30];
        generujListe(tab1);
        drukuj(tab1);
        System.out.println();
        sortuj(tab1);
        drukuj(tab1);
        System.out.println();
        String[] lista1 = {"Nowak", "Kania", "Adamski", "Trocki"};
        for(int i=0; i< lista1.length; i++)
            for(int j=0; j<lista1.length; j++)
            {
                if (porownywacz.compare(lista1[j], lista1[j + 1]) > 0) {

                }
                System.out.println(lista1[j]);
            }
    }
}