package Logic;
import Model.Person;
import Model.opinionType;
import java.time.LocalDate;
import java.util.ArrayList;
import static Logic.TxtFileWorker.*;

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
