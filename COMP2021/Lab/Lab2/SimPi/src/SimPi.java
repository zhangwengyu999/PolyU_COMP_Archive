// OOP_LAB2
// Made by Mike_Zhang(21098431d)
import java.util.*;

public class SimPi {
    public static void main(String[] args){
        Random random = new Random();
        int hitCount = 0; // the number of hit
        int n = 100000; // repeat time

        for(int i = 0; i <= n; i++){
           double x = random.nextDouble();
           double y = random.nextDouble();
           double distance = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
           if (distance<1){
               hitCount++; // if hit then count
           }
        }
        double pi = 4.0 * hitCount/n; // estimate the pi

        System.out.println(pi); // output
    }
}
// OOP_LAB2
// Made by Mike_Zhang(21098431d)