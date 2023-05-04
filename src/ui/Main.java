package ui;

import model.*;

import java.util.Scanner;

public class Main {
    private Scanner reader;
    private Controller controller;

    public Main() {
        reader = new Scanner(System.in);
        controller = new Controller();
    }

    public static void main(String[] args) {

        Main main = new Main();

        int option = 0;

        do {

            option = main.getOptionShowMenu();
            main.executeOption(option);

        } while (option != 0);

        main.getReader().close();

    }

    public int getOptionShowMenu() {
        int option = 0;
        System.out.println("welcome to mercado libre \n" +
                "1. add product\n" +
                "2. increase product\n" +
                "3. \n" +
                "4. \n" +
                "5. \n" +
                "0. Exit. ");
        option = validateIntegerInput();
        return option;
    }

    public void executeOption(int option) {
        String msj = "";
        // product atributes
        String name = "";
        String description = "";
        double price = 0;
        int quantity = 0;
        int category = 0;
        int quantityOfSales = 0;
        switch (option) {
            case 1:
                case1(name, description, price, quantity, category, quantityOfSales);
                break;

            case 2:
                case2(name, quantity);
                break;

            case 3:
                System.out.println(msj);
                break;
            case 4:

                System.out.println("");
                break;
            case 5:

                System.out.println(msj);
                break;
            case 0:
                System.out.println("Exit program.");
                break;

            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    public void case1(String name, String description, double price, int quantity, int category,
            int quantityOfSales) {
        System.out.println("type the name of the product");
        name = reader.next();
        int position = controller.searchProductPosition(name);
        if (position > -1) {
            System.out.println("the product is already created. \n" +
                    "if you want to increase the quantity of that product select 'increase quantity product'");
        } else {
            System.out.println("type the description of that product");
            description = reader.next();

            System.out.println("type the price of the product");
            price = reader.nextInt();

            System.out.println("type the initial quantity of products");
            quantity = reader.nextInt();
            do {
                System.out.println("select the category of the product \n" +
                        "1. BOOKS\n" +
                        "2. ELETRONICS\n" +
                        "3. CLOTHESANDACCESORIES\n" +
                        "4. FOODANDDRINKS\n" +
                        "5. STATIONERS\n" +
                        "6. SPORTS\n" +
                        "7. BEAUTYANDPERSONALCARE\n" +
                        "8. TOYSANDGAMES\n");
                category = reader.nextInt();
                if (category < 1 || category > 8) {
                    System.out.println("invalid option");
                }

            } while (category < 1 || category > 8);
            System.out.println(
                    controller.addProduct(name, description, price, quantity, category, quantityOfSales));
        }

    }

    public void case2(String name, int quantity) {
        System.out.println("type the name of the product");
        name = reader.next();
        System.out.println("write the quantity to increase");
        quantity = reader.nextInt();
        System.out.println(controller.increaseProductQuantity(name, quantity));
    }

    public Scanner getReader() {
        return reader;
    }

    public int validateIntegerInput() {
        int option = 0;

        if (reader.hasNextInt()) {
            option = reader.nextInt();
        } else {
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }

    public double validateDoubleInput() {
        double option = 0;

        if (reader.hasNextDouble()) {
            option = reader.nextDouble();
        } else {
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }

}
