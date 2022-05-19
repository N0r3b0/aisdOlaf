import java.util.Random;

public class SortowaniePrzezWstawianie
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

    static void sortujW (int[] lista)
    {
        int koniec = lista.length - 1;
        for (int i = 0; i <= koniec; i++)
        {
            for (int j = i; j > 0 && lista[j] > lista[j - 1]; j--)
            {
                int bufor = lista[j];
                lista[j] = lista[j - 1];
                lista[j - 1] = bufor;
            }
        }
    }

    public static void main(String[] args)
    {
        int tab1[] = new int[30];
        generujListe(tab1);
        drukuj(tab1);
        System.out.println();
        sortujW(tab1);
        drukuj(tab1);
    }
}