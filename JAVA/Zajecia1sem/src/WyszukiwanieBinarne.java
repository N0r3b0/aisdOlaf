import java.util.Random;

public class WyszukiwanieBinarne
{
    static int wyszukaj(int[] lista, int wartosc)
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
    }

    public static void main (String[]args)
    {
       /* int[] lista = new int[30];
        generujListe(lista);
        drukuj(lista);
        int wynik = wyszukaj(lista, 1);
        System.out.println("Wyszukiwana wartość znajduje się na pozycji " + wynik);*/
        int[] lista = {1,2,3,4,5, 5, 7, 8};
        System.out.println(wyszukaj(lista, 7));

    }
}