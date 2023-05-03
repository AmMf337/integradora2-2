package ui;
import model.*;
import java.util.Scanner;
public class Main {
    private Scanner reader;

    public Main() {
        reader = new Scanner(System.in);

    }

    public static void main(String[] args) {


        Main main = new Main();

        int option = 0;

        do{

            option = main.getOptionShowMenu();
            main.executeOption(option);

        }while(option != 0);

        main.getReader().close();

    }

    public int getOptionShowMenu(){
        int option = 0;
        System.out.println(
                "1. \n" +
                        "2. \n" +
                        "3. \n" +
                        "4. \n" +
                        "5. \n" +
                        "0. Exit. ");
        option =  validateIntegerInput();
        return option;
    }

    public void executeOption(int option){

        String msj = "";
        switch(option){
            case 1:

                System.out.println("");
                break;

            case 2:
                System.out.println(msj);
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

    public Scanner getReader(){
        return reader;
    }

    public int validateIntegerInput(){
        int option = 0;

        if(reader.hasNextInt()){
            option = reader.nextInt();
        }
        else{
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }

    public double validateDoubleInput(){
        double option = 0;

        if(reader.hasNextDouble()){
            option = reader.nextDouble();
        }
        else{
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }



}

