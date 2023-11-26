package Model;
import java.time.LocalDate;

/**
 * Represents an individual's opinion, encapsulating key details such as ID, date, type, weight, comment,
 * and a unique opinion number. Essential methods include getId(), getWeight(), getOpinionNumber(),
 * getComment(), getDate(), and getType(). This class serves as a data model for storing and retrieving
 * individual opinions.
 */

public class Person {
    private int id;
    private LocalDate date;
    opinionType type;
    private int weight;
    public int opinionNumber;
    private String comment;

    public Person(int id, LocalDate date, opinionType type, int weight, String comment, int opinionNumber){
        this.id = id;
        this.date = date;
        this.type = type;
        this.weight = weight;
        this.comment = comment;
        this.opinionNumber = opinionNumber;
    }

    public int getId() {
        return id;
    }
    public int getWeight() {
        return weight;
    }
    public int getOpinionNumber() {
        return opinionNumber;
    }
    public String getComment() {
        return comment;
    }
    public LocalDate getDate() {
        return date;
    }
    public opinionType getType() {
        return type;
    }
}
