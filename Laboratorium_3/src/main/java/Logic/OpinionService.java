package Logic;
import Model.Person;
import Model.opinionType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static Logic.SQLiteService.*;

public class OpinionService implements OpinionServiceInterface{
    static ArrayList<Person> person;
    private static String dbWay;
    private static String dbFeedback;

    public OpinionService(String dbWay, String dbFeedback){
        OpinionService.dbWay = dbWay;
        OpinionService.dbFeedback = dbFeedback.toLowerCase();
        person = getDatabase(dbWay, dbFeedback);
    }
    @Override
    public void addOpinion(int id, LocalDate date, opinionType type, int weight, String opinion) throws SQLException, ClassNotFoundException {
        Person persons = new Person(id, date, type, weight, opinion, setIndex(id));
        person.add(persons);
        insertOpinion(persons, dbWay, dbFeedback);
    }

    @Override
    public int setIndex(int id){
        return (int) person.stream()
                .filter(Person -> Person.getId() == id)
                .count()+1;
    }

    @Override
    public void deleteOpinion(int id, int number) throws SQLException, ClassNotFoundException {
        person.removeIf(Person -> Person.getId() == id && Person.getOpinionNumber() == number);
        delOpinion(id, number, dbWay, dbFeedback);
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

}
