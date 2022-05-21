import java.util.Random;

public class Hero {
    String name;
    int age;
    boolean gender;
    String superPower;
    int greetingCount;

    public Hero(String name, int age, boolean gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        //init superPower
        if (isMale()) {
            if (age < 40)
                superPower = "TimeTravel";
            else
                superPower = "Invulnerability";
        } else {
            if (age < 40)
                superPower = "Precognition";
            else
                superPower = "Healing";
        }
    }

    public Hero() {
        Random random = new Random();
        name = (char) (random.nextInt(26) + 'A') + "" + ((char) (random.nextInt(26) + 'A'));
        age = random.nextInt(85) + 5;
        gender = random.nextBoolean();
        //init superPower
        if (isMale()) {
            if (age < 40)
                superPower = "TimeTravel";
            else
                superPower = "Invulnerability";
        } else {
            if (age < 40)
                superPower = "Precognition";
            else
                superPower = "Healing";
        }
    }

    public boolean isMale() {
        return gender;// Assume male is represented by True
    }

    public void greeting(Hero anotherHero) {
        if (!(anotherHero == null || this.equals(anotherHero))) {
            System.out.println("Greeting-" + greetingCount + " from " + "Hero-" + name + ": Hi " + "Hero-" + anotherHero.name + ", I'm " + "Hero-" + name + ", my super power is " + superPower);
            greetingCount++;
        }
    }

}
