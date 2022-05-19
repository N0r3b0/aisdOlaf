public class test
{
    public static void main(String[] args) {
        int[] lista = {1, 2, 3, 4, 5};
        int[] temp = lista;
        // lista[2] = 0;
        System.out.println(temp[2]);
        int x = 5;
        int z = x;
        x = 6;
        System.out.println(z);
        test2 t2 = new test2();
        System.out.println(t2);
    }
}
class test2
{

}
