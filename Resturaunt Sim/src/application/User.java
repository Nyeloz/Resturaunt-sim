package application;

import java.util.ArrayList;

public class User extends UserNPC implements Money {

	static String name;
	int age;
	static int money = 300;
	User(String name, int age, ArrayList<Menu> dislikes, ArrayList<Menu> likes){
		super(name, age, likes, dislikes, age);
		
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public  int getMoney() {
		// TODO Auto-generated method stub
		return money;
	}
	

}
