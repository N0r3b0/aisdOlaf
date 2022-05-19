import java.util.Iterator;

public class Graf
{
    private int V;
    private int E;
    public Worek<Integer>[] sasiedzi;
    public int[] krawedzDo;
    public boolean[] oznakowane;
    private int s = 0;

    public Graf (int V)
    {
        this.V = V;
        this.E = 0;
        krawedzDo = new int[V];
        oznakowane = new boolean[V];
        sasiedzi = (Worek<Integer>[]) new Worek[V];
        for (int v = 0; v < V; v++)
            sasiedzi[v] = new Worek<Integer>();
    }

    public void dodajKrawedz (int v, int w)
    {
        sasiedzi[v].dodaj(w);
        sasiedzi[w].dodaj(v);
        E++;
    }

    public void dfs(Graf g, int v) //dfs --> depth first search
    {
        oznakowane[v] = true;
        for (int w : g.sasiedzi[v])
            if (!oznakowane[w])
            {
                System.out.println("badanie dsf " + w);
                krawedzDo[w] = v;
                dfs (g, w);
            }
    }

    public boolean jestSciezkaDo (int v) {return oznakowane[v];}
    public Iterable<Integer> sciezkaDo(int v)
    {
        if(!(jestSciezkaDo(v))) return null;
        Worek<Integer> sciezka = new Worek<>();
        for (int x = v; x != s; x = krawedzDo[x])
        sciezka.dodaj(s);
        return sciezka;
    }

    public static void main(String[] args)
    {
        Graf graf = new Graf(6);
        graf.dodajKrawedz(0, 5);
        graf.dodajKrawedz(2,4);
        graf.dodajKrawedz(2,3);
        graf.dodajKrawedz(1,2);
        graf.dodajKrawedz(0,2);
        graf.dodajKrawedz(3,4);
        graf.dodajKrawedz(0,5);

        /*for (Integer i: graf.sasiedzi[0])
        {
            System.out.println(" " + i);
        }
         */

        graf.dfs(graf, 5);

        System.out.println(graf.sciezkaDo(2));

        /*
        Iterator<Integer> it;
        for (int i = 0; i < graf.sasiedzi.length; i++)
        {
            it = graf.sasiedzi[i].iterator();
            System.out.println("Nastepniki wierzcholka " + i + "   ");
            while (it.hasNext())
                System.out.println(it.next() + "   ");
            System.out.println();
        }
         */


    }
}
