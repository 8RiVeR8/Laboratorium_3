package UI;
import Logic.OpinionService;
import Model.opinionType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction implements InteractionInterface{
    static Scanner scanner = new Scanner(System.in);
    private final OpinionService opinionService;

    public Interaction(OpinionService opinionService) {
        this.opinionService = opinionService;
    }

    @Override
    public void startInteraction() {
        int choice;
        do {
            displayMenu();
            System.out.print("Choose option: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            processChoice(choice);
        } while (choice != 6);

        opinionService.endProgram();
        scanner.close();
    }

    @Override
    public void displayMenu() {
        System.out.println("----- Menu -----");
        System.out.println("1. Add opinion about worker");
        System.out.println("2. Delete opinion about worker");
        System.out.println("3. Show specific person");
        System.out.println("4. Show all");
        System.out.println("5. Trend line");
        System.out.println("6. Quit");
    }

    @Override
    public void processChoice(int choice) {
        switch (choice) {
            case 1 -> {opinionService.addOpinion(setId(), setDate(), setOpinionType(), setWeight(), setOpinion()); cleanScreen();}
            case 2 -> {opinionService.deleteOpinion(setId(), setNumber()); cleanScreen();}
            case 3 -> {showOpinion(); cleanScreen();}
            case 4 -> {opinionService.showAll(); cleanScreen();}
            case 5 -> getTrend();
            case 6 -> System.out.println("Interaction completed. Thank you!");
            default -> System.out.println("Incorrect choice. Please choose again.");
        }
    }

    @Override
    public opinionType setOpinionType() {
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

    @Override
    public int setWeight(){
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

    @Override
    public int setId(){
        System.out.println("Please enter user id");
        return tryCatch();
    }

    @Override
    public int setNumber(){
        System.out.println("Enter number of opinion you want to delete (starts from 1) ");
        return tryCatch();
    }

    @Override
    public int tryCatch(){
        int number = 0;
        boolean validInput = false;

        while (!validInput){
            try{
                number = scanner.nextInt();
                scanner.nextLine();
                validInput = true;
            }catch (InputMismatchException e){
                System.out.println("Incorrect data. Enter again: ");
                scanner.nextLine();
            }
        }
        return number;
    }

    @Override
    public LocalDate setDate(){

        LocalDate parsedDate = null;
        boolean isCorrect = false;
        String inputData;

        while(!isCorrect){
            try {
                System.out.println("Please enter your date (yyyy-MM-dd)");
                inputData = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                parsedDate = LocalDate.parse(inputData, formatter);
                isCorrect = true;

            } catch (Exception e) {
                System.out.println("Wrong date format. Try again: ");
            }
        }
        return parsedDate;
    }

    @Override
    public String setOpinion(){
        System.out.println("Please enter opinion about worker: ");
        return scanner.nextLine();
    }

    @Override
    public void showOpinion(){
        opinionService.showPerson(setId());
    }

    @Override
    public void cleanScreen() {
        System.out.println("Press enter to continue");
        scanner.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
    }

    public void getTrend() {
        System.out.println("Set start of the period to create trend line for:");
        LocalDate start = setDate();

        System.out.println("Set end of the period to create trend line for:");
        LocalDate end = setDate();

        OpinionService.trendAnalyze(setId(), start, end);
    }

}
