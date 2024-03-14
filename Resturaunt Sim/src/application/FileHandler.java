package application;


import java.io.*;

public class FileHandler extends Menu{

    public FileHandler(String name, double price) {
		super(name, price);
		
	}

	public static Menu[] readMenuFromFile(String filePath) {
    	filePath = "C:\\Users\\thatg\\menuObj.txt";
       Menu[] foodOptions = new Menu[30];
        try (BufferedReader r = new BufferedReader(new FileReader(filePath))) {
            // Read file logic goes BufferedReader r = new BufferedReader(new FileReader(fp));
    		String line;
    		int count = 0;
    		  while ((line = r.readLine()) != null) {
                   // System.out.println(line);
    		   
    		  String[] parts = line.split(" - \\$");
    			  
    			 Double hodler = Double.parseDouble(parts[1].replace(",", ""));
    			 Menu newFood = new Menu(parts[0], hodler);
                 foodOptions[count] = newFood;
                 count++;
    		  }
        }
    		  catch (IOException e) {
            System.out.println("File not read successfully");
            e.printStackTrace();
        }
        return foodOptions;
        
    }

    public static void writeMenuToFile(String filePath, Menu[] foodOptions)
    {
    	filePath = "C:\\Users\\thatg\\writFile.txt";
    		
        try (BufferedWriter w = new BufferedWriter(new FileWriter(filePath))) {
        	
			
			BufferedWriter w1 = new BufferedWriter(new FileWriter(filePath));
			for(int i = 0; i < foodOptions.length;i++) {
				w1.write("Name: " + foodOptions[i].getName() + " price: " + foodOptions[i].getPrice());;
			}
			
        } catch (IOException e) {
            System.out.println("File not written successfully");
            e.printStackTrace();
        }
        
    }
}