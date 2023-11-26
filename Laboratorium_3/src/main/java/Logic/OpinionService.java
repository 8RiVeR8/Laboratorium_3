package Logic;
import Model.Person;
import Model.opinionType;
import java.time.LocalDate;
import java.util.ArrayList;
import static Logic.TxtFileWorker.*;

/**
 * OpinionService manages Person objects, facilitating addition, deletion,
 * and display of opinions. It uses TxtFileWorker for data persistence,
 * with key methods such as addOpinion(), deleteOpinion(), showPerson(),
 * showAll(), and trendAnalyze().

 * Methods:
 * - addOpinion(int id, LocalDate date, opinionType type, int weight, String opinion):
 *   Adds a new opinion based on user input.
 * - setIndex(int id): Generates a unique opinion number for a given user ID.
 * - deleteOpinion(int id, int number): Removes a specific opinion based on user input.
 * - showPerson(int id): Displays all opinions for a specific user ID.
 * - showAll(): Displays all stored opinions.
 * - getOpinionValue(opinionType type): Assigns numerical values to opinion types (Positive, Negative).
 * - trendAnalyze(int id, LocalDate start, LocalDate finish): Analyzes opinion trends
 *   for a specified user ID within a given time period.
 */

public class OpinionService implements OpinionServiceInterface{
    static ArrayList<Person> person;
    private static String TxtName;

    public OpinionService(String TxtName){
        OpinionService.TxtName = TxtName;
        person = getTxt(TxtName);
    }

    @Override
    public void endProgram(){
        TxtFileWorker.insertOpinion(person, TxtName);
    }
    @Override
    public void addOpinion(int id, LocalDate date, opinionType type, int weight, String opinion) {
        Person persons = new Person(id, date, type, weight, opinion, setIndex(id));
        person.add(persons);
    }

    @Override
    public int setIndex(int id){
        return (int) person.stream()
                .filter(Person -> Person.getId() == id)
                .count()+1;
    }

    @Override
    public void deleteOpinion(int id, int number) {
        person.removeIf(Person -> Person.getId() == id && Person.getOpinionNumber() == number);
    }
    @Override
    public void showPerson(int id){
        person.stream()
                .filter(Person -> Person.getId() == id)
                .forEach(Person ->{
                    System.out.println(Person.getDate());
                    System.out.println("Id: " + Person.getId() + " Type: " + Person.getType() + " Weight: " + Person.getWeight() + " Number of opinion: " + Person.getOpinionNumber());
                    System.out.println("Opinion:\n" + Person.getComment());
                    System.out.println();
                });
    }

    @Override
    public void showAll(){
        person
                .forEach(Person ->{
                    System.out.println(Person.getDate());
                    System.out.println("Id: " + Person.getId() + " Type: " + Person.getType() + " Weight: " + Person.getWeight() + " Number of opinion: " + Person.getOpinionNumber());
                    System.out.println("Opinion:\n" + Person.getComment());
                    System.out.println();
                });
    }

    public static double getOpinionValue(opinionType type){
        if (type == opinionType.POSITIVE) {
            return 1.0;
        } else if (type == opinionType.NEGATIVE) {
            return -1.0;
        } else {
            return 0.0;
        }
    }

    public static void trendAnalyze(int id, LocalDate start, LocalDate finish) {
        double trend;
        trend = person.stream()
                .filter(Person -> Person.getId() == id && Person.getDate().isAfter(start) && Person.getDate().isBefore(finish))
                .mapToDouble(Person -> getOpinionValue(Person.getType())* Person.getWeight())
                .sum();
        System.out.println("Trend for person: " + id + " between " + start + " and " + finish + ": " + trend);
    }

}
