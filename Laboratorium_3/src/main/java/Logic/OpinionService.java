package Logic;
import Model.Person;
import Model.opinionType;
import UI.Interaction;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import static UI.Interaction.setOpinion;

public class OpinionService {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Person> person = new ArrayList<>();
    public static void addOpinion(int id, LocalDate date, opinionType type, int weight, String opinion){
        person.add(new Person(id, date, type, weight, opinion, setIndex(id)));
    }

    public static int setIndex(int id){
        return (int) person.stream()
                .filter(Person -> Person.getId() == id)
                .count()+1;
    }

    public void deleteOpinion(){
    }
    public static void showPerson(int id){
        person.stream()
                .filter(Person -> Person.getId() == id)
                .forEach(Person ->{
                    System.out.println(Person.getDate());
                    System.out.println("Id: " + Person.getId() + " Type: " + Person.getType() + " Weight: " + Person.getWeight() + " Number of opinion: " + setIndex(id));
                    System.out.println("Opinion:\n" + Person.getComment());
                    System.out.println();
                });
    }

    public void showAll(){

    }

}
