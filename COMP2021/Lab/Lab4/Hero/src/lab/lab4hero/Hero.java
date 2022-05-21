package lab.lab4hero;

import java.util.Random;

public class Hero {
    private String name;
    private int age;
    private boolean gender; // true for Male, false for Female
    private String superPower;
    private int greetingCount;
    private static int heroNumCount;
    private final int id;

    public Hero (String inName, int inAge, boolean inGender){
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

        id = ++heroNumCount;
    }
    public Hero (){
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

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public static void greeting (Hero[] hero){
        int count = 0;
        int onlyHero = 0;
        for (int i = 0; i < hero.length; i++){
            if (hero[i] == null){break;}
            else {
                count++;
                onlyHero = i;
            }
        }
        if (count > 1){System.out.println("Hello to all you "+count);}
        else if (count == 1){System.out.println("Hello to "+ hero[onlyHero].getName());}
    }
}

