package application;



class Menu {
    String name;
    double price;
    static Menu[] foodOptions = FileHandler.readMenuFromFile(null);

    public Menu(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}