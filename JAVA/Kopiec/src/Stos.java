import java.util.Random;

public class Stos <Element>
{
    public ObiektStosu pierwszy = null;
    private int N;

    public class ObiektStosu
    {
        Element element;
        ObiektStosu elPonizej;
    }

    public boolean czyPusty() {return N == 0;}
    public int rozmiar() {return N;}

    public void polozNaStosie(Element el)
    {
        ObiektStosu sPierwszy = pierwszy;
        pierwszy = new ObiektStosu();
        pierwszy.element = el;
        pierwszy.elPonizej = sPierwszy;
        N++;
    }

    public Element zdejmijZeStosu()
    {
        Element pobrany = pierwszy.element;
        pierwszy = pierwszy.elPonizej;
        N--;
        return pobrany;
    }

    public static void main(String[] args)
    {
        Stos <Integer> s1 = new Stos<>();
        Random losowe = new Random();
        int ile = 20;
        for (int i = 0; i < ile; i++)
            s1.polozNaStosie(losowe.nextInt(100));
        for (int i = 0; i < ile; i++)
            System.out.println(s1.zdejmijZeStosu());
    }
}