package Logic;
import Model.Person;
import Model.opinionType;
import java.time.LocalDate;
import java.util.ArrayList;

import static Logic.SQLiteService.getDatabase;

public class OpinionService {
    static ArrayList<Person> person;

    public OpinionService(String dbWay, String dbFeedback){
        person = getDatabase(dbWay, dbFeedback);
    }
    public static void addOpinion(int id, LocalDate date, opinionType type, int weight, String opinion){
        Person persons = new Person(id, date, type, weight, opinion, setIndex(id));
        person.add(persons);
    }

    public static int setIndex(int id){
        return (int) person.stream()
                .filter(Person -> Person.getId() == id)
                .count()+1;
    }

    public static void deleteOpinion(int id, int number){
        person.removeIf(Person -> Person.getId() == id && Person.getOpinionNumber() == number);
    }
    public static void showPerson(int id){
        person.stream()
                .filter(Person -> Person.getId() == id)
                .forEach(Person ->{
                    System.out.println(Person.getDate());
                    System.out.println("Id: " + Person.getId() + " Type: " + Person.getType() + " Weight: " + Person.getWeight() + " Number of opinion: " + Person.getOpinionNumber());
                    System.out.println("Opinion:\n" + Person.getComment());
                    System.out.println();
                });
    }

    public static void showAll(){
        person
                .forEach(Person ->{
                    System.out.println(Person.getDate());
                    System.out.println("Id: " + Person.getId() + " Type: " + Person.getType() + " Weight: " + Person.getWeight() + " Number of opinion: " + Person.getOpinionNumber());
                    System.out.println("Opinion:\n" + Person.getComment());
                    System.out.println();
                });
    }

}
