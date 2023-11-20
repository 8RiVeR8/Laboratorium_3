package UI;
import Logic.OpinionService;
import Model.opinionType;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Interaction implements InteractionInterface{
    static Scanner scanner = new Scanner(System.in);
    private final OpinionService opinionService;
    private final String pythonWay;

    public Interaction(OpinionService opinionService, String pythonWay) {
        this.opinionService = opinionService;
        this.pythonWay = pythonWay;
    }

    @Override
    public void startInteraction() throws SQLException, ClassNotFoundException {
        int choice;
        do {
            displayMenu();
            System.out.print("Choose option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Odczyt dodatkowego znaku nowej linii
            processChoice(choice);
        } while (choice != 6);

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
    public void processChoice(int choice) throws SQLException, ClassNotFoundException {
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

    @Override
    public LocalDate setDate(){
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

        String id = String.valueOf(setId());

        System.out.println("Set start of the period to create trend line for:");
        LocalDate start = setDate();

        System.out.println("Set end of the period to create trend line for:");
        LocalDate end = setDate();

        OpinionService.trendAnalyze(id, String.valueOf(start), String.valueOf(end), pythonWay);
    }


}
