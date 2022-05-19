import java.util.Random;
public class sortowaniePrzezScalanie {
    static int lista[] = {4, 6, 47, 3, 7, 8, 10, 12, 50, 78};
    static int listaW[] = new int[lista.length];
    static void scal(int odPoz, int srodek, int doPoz){
        int i = odPoz;
           int j = srodek+1;
            for( int k=odPoz; k<=doPoz; k++){
                if (i>srodek) listaW[k]=lista[j++];
                else if (j>doPoz) listaW[k]=lista[i++];
                else{
                    if (lista[i]<lista[j]) listaW[k] = lista[i++];
                    else listaW[k] = lista[j++];
                }
            }
        }

        public static void drukuj(int[] lista) {
            for (int i = 0; i < lista.length; i++)
                System.out.print(lista[i] + " ");
            System.out.println();
        }

        public static void main(String[] args) {
            scal(0, 2, 9);
            drukuj (listaW);
            tworzenieTrojek(16);

    /*int ile = tworzenieTrojek(lista.length);
    for (int i=0; i<ile; i++){
    if(listaTrójek[i].x==0)
    for(int k=0; k<lista.length; k++)
        pomocnicza[k] = lista[k];
        scal(listaTrójek[i].x, listaTrójek[i].y, listaTrójek[i].z);
        }
        //for(int k=0; k<lista.length; k++) System.out.println(listaTrójek);
        //System.out.println();
    */
        }

    static Trojka[] listaTrójek = new Trojka[100];
    static int pomocnicza[] = new int[lista.length];

    static int tworzenieTrojek(int n) {
         n = 16;
        int numer = 0;
        int tr1, tr2, tr3, pzakres;
        for (int zakres = 2; zakres < n + 1; zakres *= 2) {
            for (int x = 0; x<n; x+=zakres) {
                tr1 = x;
                tr2 = x + (zakres - 1) / 2;
                /* pzakres=zakres;
                 while (tr2>n-1){
                 pzakres=pzakres/2; tr2=x+(pzakres-1)/2;}*/

                tr3=x+zakres-1;
                if(tr3>n-1) tr3=n-1;
                listaTrójek[numer++] = new Trojka(tr1, tr2, tr3);
                System.out.println(tr1 + " " + tr2 + " " + tr3);
            }
            }


            return numer;
        }
    }
    class Trojka {
    int x, y, z;
    public Trojka(int x, int y, int z){
        this.x =x;
        this.y=y;
        this.z=z;
    }
    }

    /*
    int ile = utworzTrojki
    for (int i=0; i<ile; i++){
    if(listaTrojek[i].x++0)
    for(int k=0; k<lista.length; k++)
        pomocnicza[k] = lista[k]
        scal(listTrojek[i].x, listaTrojek[i].y, lista
     */

