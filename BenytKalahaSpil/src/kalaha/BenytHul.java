package kalaha;

public class BenytHul
{
    public static void main(String[] args)
    {
        Hul testHul = new Hul(420, 69);
        System.out.println(testHul.toString());
        Maal testMaal = new Maal(420, 69); 

        System.out.println(testMaal.point());
        System.out.println(testMaal.getAntalKugler());
    }
}
