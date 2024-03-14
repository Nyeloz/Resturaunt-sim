package application;

import java.util.ArrayList;

public class UserNPC {
    private String name;
    private int age;
    protected ArrayList<Menu> likes;
    protected ArrayList<Menu> dislikes;
    private int lovemeter;

    // Constructor with all parameters
    public UserNPC(String name, int age, ArrayList<Menu> likes, ArrayList<Menu> dislikes, int lovemeter) {
        this.name = name;
        this.age = age;
        this.likes = likes;
        this.dislikes = dislikes;
        this.lovemeter = lovemeter;
    }

    // Empty constructor
    public UserNPC() {
        // Initialize lists
        likes = new ArrayList<>();
        dislikes = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Menu> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Menu> likes) {
        this.likes = likes;
    }

    public ArrayList<Menu> getDislikes() {
        return dislikes;
    }

    public void setDislikes(ArrayList<Menu> dislikes) {
        this.dislikes = dislikes;
    }

    public int getLovemeter() {
        return lovemeter;
    }

    public void setLovemeter(int lovemeter) {
        this.lovemeter = lovemeter;
    }
}
