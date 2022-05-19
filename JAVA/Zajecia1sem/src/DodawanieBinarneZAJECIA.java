public class DodawanieBinarneZAJECIA
{
    static int[] konwertuj(int x, int n)
    {
        int[] zm = new int[n];
        for(int j=0; j<x; j++)
        {
            x=x/2;
            zm[n-1-j]=x%2;
        }
        return zm;
    }

   /* static int konwertuj10(int[] lista)
    {
            int waga=1;
            int wartosc10=0;
            int koniec=lista.length-1;
            for(int j=0; j<=koniec; j++)
            {
                wartosc10 += lista[j] * waga;
                waga /= 2;
            }
            return wartosc10;

    }*/

    public static void drukuj(int[] liczby)
    {
        for(int j=0; j<liczby.length; j++)
            System.out.print(liczby[j] + " ");
            System.out.println();
    }


    public static void main (String args[])
    {
        int[] listaA=konwertuj(23,4);
        drukuj(listaA);
        int[] listaB = konwertuj(52, 6);
        drukuj(listaB);
        int[] listaW = {0,0,0,0,0,0,0};
        int przeniesienie = 0;
        int sumaBit=0;
        System.out.println("=======================");

        for(int j= listaA.length-1;j<0;j--)
        {
            sumaBit=listaA[j]=listaB[j] + przeniesienie;
            if (sumaBit == 0)
            {
                listaW[j+1] = 0;
                przeniesienie=0;
            }
            else if (sumaBit ==2);
        }
    }
}
