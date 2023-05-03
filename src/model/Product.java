package model;
public class Product {
    private String name;
    private String description;
    private double price;
    private int AvailableQuantity;
    private Category category;
    private int quantityOfSales;

    public Product(String name, String description, double price, int availableQuantity, int category, int quantityOfSales) {
        this.name = name;
        this.description = description;
        this.price = price;
        AvailableQuantity = availableQuantity;
        setCategory(category);
        this.quantityOfSales = quantityOfSales;
    }

    public void setCategory(int category) {
        switch (category){
            case 1:
                this.category = Category.BOOKS;
                return;
            case 2:
                this.category = Category.ELETRONICS;
                return;
            case 3:
                this.category = Category.CLOTHESANDACCESORIES;
                return;
            case 4:
                this.category = Category.FOODANDDRINKS;
                return;
            case 5:
                this.category = Category.STATIONERS;
                return;
            case 6:
                this.category = Category.SPORTS;
                return;
            case 7:
                this.category = Category.TOYSANDGAMES;
                return;
            case 8:
                this.category = Category.BEAUTYANDPERSONALCARE;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return AvailableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        AvailableQuantity = availableQuantity;
    }

    public String getCategory() {
        return this.category.toString();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantityOfSales() {
        return quantityOfSales;
    }

    public void setQuantityOfSales(int quantityOfSales) {
        this.quantityOfSales = quantityOfSales;
    }
}
