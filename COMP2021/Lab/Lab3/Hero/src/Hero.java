import java.util.Random;

public class Hero {
    private String name;
    private int age;
    private boolean gender; // true for Male, false for Female
    private String superPower;
    private int greetingCount;

    Hero (String inName, int inAge, boolean inGender){
        name = inName;
        age = inAge;
        gender = inGender;
        if (inGender){
            if (inAge < 40){superPower = "_TimeTravel_";}
            else{superPower ="_Invulnerablility_";}
        }
        else{
            if (inAge < 40){superPower = "_Precognition_";}
            else{superPower ="_Healing_";}

        }
    }
    Hero (){
        this(((char)(new Random().nextInt(26) + 'A')) + "" + ((char)(new Random().nextInt(26) + 'A')) + "",
                (new Random().nextInt(85))+5,
                new Random().nextInt(2) == 1);
    }

    public String superPower(){
        return this.superPower;
    }

    public void greeting(Hero hero){
        if (hero != this && hero != null){
            System.out.println("Greeting-" + this.greetingCount + " from Hero-" + this.name + " : Hi Hero-" + hero.name + ", I'm " + this.name + ", my superpower is " + this.superPower() + ".");
            this.greetingCount++;
        }
    }

    public static void main(String[] args){
        Hero H1 = new Hero("AB", 18, false);
        Hero H2 = new Hero("CD", 19, true);
        Hero H3 = new Hero("EF", 50, true);
        Hero h1 = new Hero();
        Hero h2 = new Hero();
        Hero h3 = new Hero();
        h1.greeting(H2);
        h1.greeting(H3);
        h2.greeting(H1);
        h2.greeting(H3);
        h3.greeting(H1);
        h3.greeting(H2);
    }
}
