public class Lista
{
    private static class ObiektListy
    {
        Osoba element;
        ObiektListy nastepny = null;
    }
        ObiektListy pierwszy;

    void drukuj()
    {
        ObiektListy biezacy = pierwszy;
        do
        {
            System.out.println(biezacy.element);
            biezacy = biezacy.nastepny;
        }
        while (biezacy.nastepny != null);
        System.out.println(biezacy.element);
    }

    void zamienNazwisko(int ktory, String nowe)
    {
        ObiektListy biezacy = pierwszy;
        for(int i=0; i<1000; i++)
        {
            if(i==ktory) break;
            if(biezacy == null) return; //zabezpieczenie przed operowaniem na obiekcie null
            biezacy = biezacy.nastepny;
        }
        biezacy.element.nazwisko = nowe;
    }

    public static void main(String[] args)
    {
        String[][] osoby = {{"Wisniewski", "Kazimierz", "1998-01-11"},
                            {"Wisniak", "Janusz", "1998-10-11"},
                            {"Szyc", "Barbara", "1998-10-11"},
                            {"Boruta", "Zygmunt", "1999-05-24"}};
        Lista obiekt = new Lista();
        obiekt.pierwszy = new ObiektListy();
        obiekt.pierwszy.element = new Osoba(osoby[0][0], osoby[0][1], osoby[0][2]);

        ObiektListy kolejny = obiekt.pierwszy;

        for (int i=1; i < osoby.length; i++)
        {
            kolejny.nastepny = new ObiektListy();
            kolejny.nastepny.element = new Osoba(osoby[i][0], osoby[i][1], osoby[i][2]);
            kolejny = kolejny.nastepny;
        }
        System.out.println();
        obiekt.drukuj(); System.out.println("\n" + "Nowa Linia" + "\n\n");
        obiekt.zamienNazwisko(5, "Fedde");
        obiekt.drukuj();


    }
}