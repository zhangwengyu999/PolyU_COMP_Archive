public class Lab3Main {
    public static void main(String[] args) {

        Hero hero1 = new Hero("OO", 60, true);
        Hero hero2 = new Hero();
        Hero hero3 = new Hero();
        hero1.greeting(hero2);
        hero1.greeting(hero3);
        hero2.greeting(hero1);
        hero2.greeting(hero3);
        hero3.greeting(hero1);
        hero3.greeting(hero2);
    }
}
