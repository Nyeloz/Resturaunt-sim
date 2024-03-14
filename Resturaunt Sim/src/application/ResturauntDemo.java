package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JFrame;
import java.util.Random;

public class ResturauntDemo {

	public static void main(String[] args) { 
		Random ran = new Random();
		
		NPC1.likes.add(Menu.foodOptions[ran.nextInt(Menu.foodOptions.length)]);
		NPC1.likes.add(Menu.foodOptions[ran.nextInt(Menu.foodOptions.length)]);
		NPC1.likes.add(Menu.foodOptions[ran.nextInt(Menu.foodOptions.length)]);
		
		NPC1.dislikes.add(Menu.foodOptions[ran.nextInt(Menu.foodOptions.length)]);
		NPC1.dislikes.add(Menu.foodOptions[ran.nextInt(Menu.foodOptions.length)]);
		NPC1.dislikes.add(Menu.foodOptions[ran.nextInt(Menu.foodOptions.length)]);

	       
	        
	   
	    
	      // Checking if read was correct
	       for (int i = 0; i < Menu.foodOptions.length; i++) {
	            System.out.println("Name: " + Menu.foodOptions[i].getName() + " price: " + Menu.foodOptions[i].getPrice());
	        }

	        // Writing to a file FileHandler.writeMenuToFile("D:\\Java final\\writedFile.txt", foodOptions); 
	       SwingUtilities.invokeLater(() -> new GUI());
	    }






	
		
		
		 
		
	


	
	
	

	

	
}
	

