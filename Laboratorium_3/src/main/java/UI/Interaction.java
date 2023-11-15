package UI;

import Logic.OpinionService;
import Model.opinionType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static Logic.OpinionService.*;

public class Interaction {
    OpinionService Opinion = new OpinionService();
    static Scanner scanner = new Scanner(System.in);

    public void startInteraction() {
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

    private void processChoice(int choice) {
        switch (choice) {
            case 1:
                addPerson();
                break;
            case 2:
                deleteOpinions();
                break;
            case 3:
                showOpinion();
                break;
            case 4:
                Opinion.showAll();
                break;
            case 5:
                System.out.println("Interaction completed. Thank you!");
                break;
            default:
                System.out.println("Incorrect choice. Please choose again.");
        }
    }

    public static opinionType setOpinionType() {
        System.out.println("Enter opinion type \"p\" - POSITIVE  or \"n\" - NEGATIVE");
        String opinionTypes = scanner.nextLine();
        //scanner.nextLine();
        if (opinionTypes.toLowerCase().equals("p")) {
            return opinionType.POSITIVE;
        } else {
            if (opinionTypes.toLowerCase().equals("n")){
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
        int weight = scanner.nextInt();
        //scanner.nextLine();
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
        int id;
        id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public static int setNumber(){
        System.out.println("Enter number of opinion you want to delete (starts from 1) ");
        int number = scanner.nextInt();
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
        scanner.nextLine();
        String opinion = scanner.nextLine();
        return opinion;
    }

    public void addPerson(){
        addOpinion(setId(), setDate(), setOpinionType(), setWeight(), setOpinion());
    }

    public void showOpinion(){
        showPerson(setId());
    }

    public void deleteOpinions(){
        deleteOpinion(setId(), setNumber());
    }


}
