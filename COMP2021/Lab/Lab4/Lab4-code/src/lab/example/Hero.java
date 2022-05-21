package lab.example;

import java.util.Random;

public class Hero {
    public static final boolean Male=true;
    public static final boolean Female=false;

    enum SuperPower{
        TimeTravel, Invulnerability, Precognition,Healing
    }

    static int nbrHeroCreated;

    private final int id;
    private String name;
    private int age;
    private boolean gender;
    private SuperPower superPower;
    private int greetingCount;

    {
        id=nbrHeroCreated++;
    }

    public Hero(String name, int age, boolean gender) {
        init(name, age, gender);
    }

    public Hero() {
        Random random = new Random();
        String name = (char) (random.nextInt(26) + 'A') + "" + ((char) (random.nextInt(26) + 'A'));
        int age = random.nextInt(85) + 5;
        boolean gender = random.nextBoolean();
        init(name, age, gender);
    }

    void init(String name, int age, boolean gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
        //init superPower
        if (isMale()) {
            if (age < 40)
                superPower = SuperPower.TimeTravel;
            else
                superPower = SuperPower.Invulnerability;
        } else {
            if (age < 40)
                superPower = SuperPower.Precognition;
            else
                superPower = SuperPower.Healing;
        }
    }

    public String getName(){
        return "lab.example.Hero-"+name+"-"+id;
    }

    public boolean isMale() {
        return gender==Male;// Assume male is represented by True
    }

    public void greeting(Hero anotherHero) {
        if (!(anotherHero == null || this.equals(anotherHero))) {
            System.out.println("Greeting-" + greetingCount + " from " + getName() + ": Hi " + anotherHero.getName() + ", I'm " + getName() + ", my super power is " + superPower);
            greetingCount++;
        }
    }

    public void greeting(Hero[] heroes){
        int xx=0;
        for (Hero hero:heroes){
            if(hero !=null) xx++;
        }
        if(xx>1)
            System.out.println("Hello to all you "+xx);
        else if(xx==1){
            for (Hero hero: heroes){
                if(hero !=null) greeting(hero);
            }
        }
    }

}