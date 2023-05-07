package ui;

import model.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
                "4. search product\n" +
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
                case4();
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
        String input = "";
        // Input validation
        boolean validInput = false;
        do {
            System.out.println("Enter the name of the product");
            name = reader.next();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty");
            } else {
                validInput = true;
            }
        } while (!validInput);

        validInput = false;
        do {
            System.out.println("Enter the description of the product");
            description = reader.next();
            if (description.isEmpty()) {
                System.out.println("Description cannot be empty");
            } else {
                validInput = true;
            }
        } while (!validInput);

        validInput = false;
        do {
            System.out.println("Enter the price of the product");
            input = reader.next();
            try {
                price = Double.parseDouble(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid price");
            }
        } while (!validInput);

        validInput = false;
        do {
            System.out.println("Enter the initial quantity of the product");
            input = reader.next();
            try {
                quantity = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity");
            }
        } while (!validInput);

        validInput = false;
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
            input = reader.next();
            try {
                category = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("invalid option");
            }
            if (category < 1 && category > 8) {
                System.out.println("invalid option");
            } else {
                validInput = true;
            }
        } while (!validInput);

        // Add product to controller
        System.out.println(controller.addProduct(name, description, price, quantity, category, quantityOfSales));
    }

    public void case2(String name, int quantity) {
        String input = "";
        boolean validInput = false;
        System.out.println("Enter the name of the product:");
        name = reader.next();
        do {
            System.out.println("Enter the quantity to increase:");
            input = reader.next();
            try {
                quantity = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("invalid option");
            }
        } while (!validInput);

        // Input validation
        if (quantity < 1) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        System.out.println(controller.increaseProductQuantity(name, quantity));
    }

    public void case3(String nameBuyer, ArrayList<String> nameProducts, ArrayList<Integer> quantityProducts,
                      double totalprice, boolean thereIsProduct) {
        int option = 0;
        int quantityAvalible = 0;
        int quantityOrder = 0;
        String name = "";
        System.out.println("Type the name of the client");
        nameBuyer = reader.next();
        do {
            System.out.println("Type the names of the product");
            name = reader.next();
            if (controller.searchProductPosition(name) != -1) {
                quantityAvalible = controller.searchProductQuantity(name);
                if (quantityAvalible > 0) {
                    System.out.println("The available quantity of the product is " + quantityAvalible);
                    System.out.println("Type the products quantity order");
                    try {
                        quantityOrder = Integer.parseInt(reader.next());
                        if (quantityAvalible - quantityOrder >= 0) {
                            controller.setNewQuantityProduct(name, quantityOrder);
                            totalprice += controller.calculateTotalPriceByProduct(name, quantityOrder);
                            nameProducts.add(name);
                            quantityProducts.add(quantityOrder);
                            thereIsProduct = true;
                            do {
                                System.out.println("Want to add another product?\n" +
                                        "1. Yes\n" +
                                        "2. No");
                                option = Integer.parseInt(reader.next());
                                if (option == 2) {
                                    controller.addOrder(nameBuyer, nameProducts, quantityProducts, totalprice);
                                    System.out.println("Order complete");
                                } else if (option != 1 && option != 2) {
                                    System.out.println("Invalid option");
                                }
                            } while (option != 1 && option != 2);
                        } else {
                            do {
                                System.out.println("There is not enough quantity of this product for this order.\n" +
                                        "Want to make a correction?\n" +
                                        "1. Yes\n" +
                                        "2. No");
                                option = Integer.parseInt(reader.next());
                                if (option == 2) {
                                    if (thereIsProduct == true) {
                                        controller.addOrder(name, nameProducts, quantityProducts, totalprice);
                                        System.out.println("Order complete");
                                    } else {
                                        System.out.println("There are no products. Order denied.");
                                    }
                                } else if (option != 1) {
                                    System.out.println("Invalid option");
                                }
                            } while (option != 1 && option != 2);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input format. Please enter a valid number.");
                    }
                } else {
                    do {
                        System.out.println("This product is out of stock.\n" +
                                "Want to make a correction?\n" +
                                "1. Yes\n" +
                                "2. No");
                        try {
                            option = Integer.parseInt(reader.next());
                            if (option == 2) {
                                if (thereIsProduct == true) {
                                    controller.addOrder(name, nameProducts, quantityProducts, totalprice);
                                    System.out.println("Order complete");
                                } else {
                                    System.out.println("There are no products. Order denied.");
                                }
                            } else if (option != 1) {
                                System.out.println("Invalid option");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input format. Please enter a valid number.");
                        }
                    } while (option != 1 && option != 2);
                }
            } else {
                do {
                    System.out.println("This product does not exist.\n" +
                            "Want to make a correction?\n" +
                            "1. Yes\n" +
                            "2. No");
                    try {
                        option = Integer.parseInt(reader.next());
                        if (option == 2) {
                            if (thereIsProduct == true) {
                                controller.addOrder(name, nameProducts, quantityProducts, totalprice);
                                System.out.println("order complete");
                            } else {
                                System.out.println("there arent product order denied");
                            }
                        } else if (option != 1) {
                            System.out.println("invalid option");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input format. Please enter a valid number.");
                    }
                } while (option != 1 && option != 2);
            }
        } while (option != 2);
    }

    public void case4() {
        int option = 0;
        String msj = "";
        System.out.println(
                "How do you want to search the product?\n" +
                        "1.Search by name\n" +
                        "2.Search by category\n" +
                        "3.Search by price\n" +
                        "4.Search by number of sales\n" +
                        "5.Search by range of price\n" +
                        "6.Search by range of number of sales\n" +
                        "7.Search by alphabetic range\n");
        try {
            option = reader.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("The typed value must be an integer");
            return;
        }
        if (option == 1) {
            System.out.println("Type the name of the product");
            String productName = reader.next();
            msj = controller.searchProductByName(productName);
            System.out.println(msj);
        } else if (option == 2) {
            System.out.println(
                    "Choose a category for search the product:\n" +
                            "1.Books\n" +
                            "2.Electronics\n" +
                            "3.Cloth and accessories\n" +
                            "4.Food and drinks\n" +
                            "5.Stationers\n" +
                            "6.Sports\n" +
                            "7.Beauty and personal care\n" +
                            "8.Toy and games\n");
            int category = 0;
            try {
                category = reader.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("The typed value must be an integer");
                return;
            }
            msj = controller.searchProduct(option, "", controller.getCategory(category), 0, 0);
            System.out.println(msj);
        } else if (option == 3) {
            System.out.println("Type the price of the product");
            double priceProduct = 0;
            try {
                priceProduct = reader.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("The typed value must be an number");
                return;
            }
            msj = controller.searchByProductPrice(priceProduct);
        } else if (option == 4) {
            System.out.println("Type the number of sales");
            int numberOfsales = 0;
            try {
                numberOfsales = reader.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("The typed value must be an integer");
                return;
            }
            msj = controller.searchProduct(option, "", null, 0, numberOfsales);
            System.out.println(msj);
        } else if (option == 5) {

        } else if (option == 6) {

        } else if (option == 7) {

        }
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
