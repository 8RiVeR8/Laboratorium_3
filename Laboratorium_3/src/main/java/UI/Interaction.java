package UI;
import Logic.OpinionService;
import Model.opinionType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import static Logic.OpinionService.*;

public class Interaction {
    static Scanner scanner = new Scanner(System.in);

    public void startInteraction() throws SQLException, ClassNotFoundException {
        int choice;
        do {
            displayMenu();
            System.out.print("Choose option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Odczyt dodatkowego znaku nowej linii
            processChoice(choice);
        } while (choice != 5);

        scanner.close();
    }

    private void displayMenu() {
        System.out.println("----- Menu -----");
        System.out.println("1. Add opinion about worker");
        System.out.println("2. Delete opinion about worker");
        System.out.println("3. Show specific person");
        System.out.println("4. Show all");
        System.out.println("5. Quit");
    }

    private void processChoice(int choice) throws SQLException, ClassNotFoundException {
        switch (choice) {
            case 1 -> addOpinion(setId(), setDate(), setOpinionType(), setWeight(), setOpinion());
            case 2 -> OpinionService.deleteOpinion(setId(), setNumber());
            case 3 -> showOpinion();
            case 4 -> showAll();
            case 5 -> System.out.println("Interaction completed. Thank you!");
            default -> System.out.println("Incorrect choice. Please choose again.");
        }
    }

    public static opinionType setOpinionType() {
        System.out.println("Enter opinion type \"p\" - POSITIVE  or \"n\" - NEGATIVE");
        String opinionTypes = scanner.nextLine();
        //scanner.nextLine();
        if (opinionTypes.equalsIgnoreCase("p")) {
            return opinionType.POSITIVE;
        } else {
            if (opinionTypes.equalsIgnoreCase("n")){
                return opinionType.NEGATIVE;
            }else{
                System.out.println("Incorrect opinion");
                setOpinionType();
            }
        }
        return opinionType.POSITIVE;
    }

    public static int setWeight(){
        System.out.println("Please enter weight of your opinion (1-5)");
        int weight = tryCatch();
        if(weight>=1 && weight<=5){
            return weight;
        }else{
            System.out.println("Incorrect weight!");
            setWeight();
        }

        return weight;
    }

    public static int setId(){
        System.out.println("Please enter user id");
        return tryCatch();
    }

    public static int setNumber(){
        System.out.println("Enter number of opinion you want to delete (starts from 1) ");
        return tryCatch();
    }

    public static int tryCatch(){
        int number = 0;
        try{
            number = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Incorrect data. Enter again: ");
            scanner.nextLine();
            tryCatch();
        }
        return number;
    }


    public static LocalDate setDate(){
        System.out.println("Please enter your date (yyyy-MM-dd)");
        String inputData = scanner.nextLine();
        //scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(inputData, formatter);

        } catch (Exception e) {
            System.out.println("Wrong date format.");
            e.printStackTrace();
        }
        return parsedDate;
    }

    public static String setOpinion(){
        System.out.println("Please enter opinion about worker: ");
        return scanner.nextLine();
    }

    public void showOpinion(){
        showPerson(setId());
    }

}
