package application;

import java.util.ArrayList;
import java.util.Random;
import application.Menu;
public class NPC1 implements GetLove{
    static String name;
    static int age;
    static int loveMeter = 3;
    
    static ArrayList<Menu> likes = new ArrayList<>();
    static ArrayList<Menu> dislikes = new ArrayList<>();
    String[] names = {
    	    "John",
    	    "Alice",
    	    "Bob",
    	    "Emily",
    	    "Michael",
    	    "Sophia",
    	    "David",
    	    "Emma",
    	    "James",
    	    "Olivia"
    	};
    public NPC1() {
        this.name = names[new Random().nextInt(names.length)] ;
        this.age = generateRandomAge();
    }

    private int generateRandomAge() {
        return new Random().nextInt(50) + 18; // Random age between 18 and 68
    }

    public static String getName() {
        return name;
    }

    public static int getAge() {
        return age;
    }

	@Override
	public int getLove() {
		// TODO Auto-generated method stub
		return this.loveMeter;
	}

	
}

