import java.util.Random;

public class Stosy
{
    public static void main(String[] args)
    {
        /*Stos<Integer> stosWart = new Stos<>();
        Stos<String> stosOper = new Stos<>();
        String onp = "22 8 + 2 /";
        String[] podzielonyONP = onp.split(" ");
        int k = 0;
        String a;
        while(k < podzielonyONP.length)
        {
            a = podzielonyONP[k];
            if(a.matches("[0-9]*")) System.out.println(a);

            k++;
        }
        Stos<Integer> stosA = new Stos<>();
        int wynik = 0, x, y;
        for (String symbol: podzielonyONP)
        {
            if(symbol.matches("[0-9]*"))
            {
                stosA.polozNaStosie(Integer.parseInt(symbol));
                continue;
            }
            x = stosA.zdejmijZeStosu();
            y = stosA.zdejmijZeStosu();
            switch (symbol)
            {
                case "+":
                    wynik = y + x;
                    break;
                case "-":
                    wynik = y - x;
                    break;
                case "/":
                    wynik = y / x;
                    break;
                case "*":
                    wynik = y * x;
                    break;
            }
            stosA.polozNaStosie(wynik);
        }
        System.out.println(stosA.zdejmijZeStosu());*/

        Stos<String> stosA = new Stos<>();
        String[] listaNazwisk = {"Nowak", "Kowalski", "Isalov", "Kania"};
        String[] listaNazwiskOdwrocna = new String [listaNazwisk.length];
        String x;

        for(int i = 0; i < listaNazwisk.length; i++)
            stosA.polozNaStosie(listaNazwisk[i]);

        for(int i = 0; i < listaNazwisk.length; i++)
            listaNazwiskOdwrocna[i] = stosA.zdejmijZeStosu();

        for (int i = 0 ; i < listaNazwiskOdwrocna.length; i++)
        System.out.print(listaNazwiskOdwrocna[i] + " ");

        Random losowe = new Random();
        Stos<Dwojka> stosDwojek = new Stos<>();
        int ile = 20;
        int sumaX = 0, sumaY = 0;
        for(int i=0; i < ile; i++)
            stosDwojek.polozNaStosie(new Dwojka(losowe.nextInt(100), losowe.nextInt(100)));
        Dwojka para;
        for(int i=0; i < ile; i++)
        {
            para = stosDwojek.zdejmijZeStosu();
            sumaX += para.x;
            sumaY += para.y;
        }
        System.out.println();
        System.out.println(sumaX + " " + sumaY);
    }
}

class Dwojka
{
    int x;
    int y;
    public Dwojka(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}