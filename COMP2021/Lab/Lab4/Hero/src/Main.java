import lab.lab4hero.*;

public class Main {
    public static void main(String[] args){
        Hero H1 = new Hero("AB", 18, false);
        System.out.println(H1.getId());
        Hero H2 = new Hero("CD", 19, true);
        System.out.println(H2.getId());
        Hero H3 = new Hero("EF", 50, true);
        System.out.println(H3.getId());
        Hero h1 = new Hero();
        System.out.println(h1.getId());
        Hero h2 = new Hero();
        System.out.println(h2.getId());
        Hero h3 = new Hero();
        System.out.println(h3.getId());
        h1.greeting(H2);
        h1.greeting(H3);
        h2.greeting(H1);
        h2.greeting(H3);
        h3.greeting(H1);
        h3.greeting(H2);

        Hero[] heroArr1 = {new Hero()};

        Hero[] heroArr5 = {new Hero(), new Hero(), new Hero(), new Hero(), new Hero()};

        Hero.greeting(heroArr1);

    }
}
