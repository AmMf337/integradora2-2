package ui;

import model.*;

import java.util.ArrayList;
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
                "3. add order\n" +
                "4. \n" +
                "5. \n" +
                "0. Exit. ");
        option = validateIntegerInput();
        return option;
    }

    public void executeOption(int option) {
        String msj = "";
        ArrayList<String> nameProducts = new ArrayList<>();
        ArrayList<Integer> quantityProducts = new ArrayList<>();
        switch (option) {
            case 1:
                case1("", "", 0, 0, 0, 0);
                break;

            case 2:
                case2("", 0);
                break;

            case 3:
                case3("", nameProducts, quantityProducts, 0, false);
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

    public void case3(String nameBuyer, ArrayList<String> nameProducts, ArrayList<Integer> quantityProducts,
            double totalprice, boolean thereIsProduct) {
        int option = 0;
        int quantityAvalible = 0;
        int quantityOrder = 0;
        String name = "";
        System.out.println("type the name of the client");
        nameBuyer = reader.next();
        do {
            System.out.println("type the names of the product");
            name = reader.next();
            if (controller.searchProductPosition(name) != -1) {
                quantityAvalible = controller.searchProductQuantity(name);
                if (quantityAvalible > 0) {
                    System.out.println("the avalible quantity of the product is " + quantityAvalible);
                    System.out.println("type the products quantity order");
                    quantityOrder = reader.nextInt();
                    if (quantityAvalible - quantityOrder >= 0) {
                        controller.setNewQuantityProduct(name, quantityOrder);
                        totalprice += controller.calculateTotalPriceByProduct(name, quantityOrder);
                        nameProducts.add(name);
                        quantityProducts.add(quantityOrder);
                        thereIsProduct = true;
                        do {
                            System.out.println("want to add another product \n" +
                                    "1. yes\n" +
                                    "2. no");
                            option = reader.nextInt();
                            if (option == 2) {
                                controller.addOrder(nameBuyer, nameProducts, quantityProducts, totalprice);
                                System.out.println("order complete");
                            } else if (option != 1 && option != 2) {
                                System.out.println("invalid option");
                            } else {

                            }
                        } while (option != 1 && option != 2);
                    } else {
                        do {
                            System.out.println("there is not enough quantity of this product for this order\n" +
                                    "want to make a correction\n" +
                                    "1. yes\n" +
                                    "2. no");
                            option = reader.nextInt();
                            if (option == 2) {
                                if (thereIsProduct == true) {
                                    controller.addOrder(name, nameProducts, quantityProducts, totalprice);
                                    System.out.println("order complete");
                                } else {
                                    System.out.println("there arent product order denied");
                                }
                            } else if (option != 1) {
                                System.out.println("invalid option");
                            } else {

                            }
                        } while (option != 1 && option != 2);

                    }

                } else {
                    do {
                        System.out.println("this product is out of stock\n" +
                                "want to make a correction\n" +
                                "1. yes\n" +
                                "2. no");
                        option = reader.nextInt();
                        if (option == 2) {
                            if (thereIsProduct == true) {
                                controller.addOrder(name, nameProducts, quantityProducts, totalprice);
                                System.out.println("order complete");
                            } else {
                                System.out.println("there arent product order denied");
                            }
                        } else if (option != 1) {
                            System.out.println("invalid option");
                        } else {

                        }
                    } while (option != 1 && option != 2);

                }

            } else {
                do {
                    System.out.println("this product doesnt exist\n" +
                            "want to make a correction\n" +
                            "1. yes\n" +
                            "2. no");
                    option = reader.nextInt();
                    if (option == 2) {
                        if (thereIsProduct == true) {
                            controller.addOrder(name, nameProducts, quantityProducts, totalprice);
                            System.out.println("order complete");
                        } else {
                            System.out.println("there arent product order denied");
                        }
                    } else if (option != 1) {
                        System.out.println("invalid option");
                    } else {

                    }
                } while (option != 1 && option != 2);
            }

        } while (option != 2);

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
