package Logic;

import Model.Person;
import Model.opinionType;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SQLiteService {
    private static Connection connect(String dbWay) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:" + dbWay);
    }

    static ArrayList<Person> getDatabase(String dbWay, String dbFeedback)    {
        ArrayList<Person> person = new ArrayList<>();

        try (Connection connection = connect(dbWay);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + dbFeedback.toLowerCase());
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next())    {
                person.add(new Person(resultSet.getInt("id"),
                        convertDate(resultSet.getString("data")),
                        opinionType.valueOf(resultSet.getString("type")),
                        resultSet.getInt("weight"),
                        resultSet.getString("opinion"),
                        resultSet.getInt("number")));
            }

        }   catch (SQLException | ClassNotFoundException e)  {
            e.printStackTrace();
        }

        return person;
    }

    public static LocalDate convertDate(String data) {
        LocalDate output = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            output = LocalDate.parse(data, formatter);

        } catch (DateTimeParseException e) {
            System.out.println("Parsing error");
        }
        return output;
    }

    static void delOpinion (int id, int opinionNumber, String dbWay, String dbFeedback) throws SQLException, ClassNotFoundException {
        Connection junction = connect(dbWay);
        PreparedStatement delStatement = junction.prepareStatement("DELETE FROM " + dbFeedback + " WHERE id = " + id + " AND number = " + opinionNumber);
        delStatement.executeUpdate();
    }

    static void insertOpinion(Person person, String dbWay, String Feedback) throws SQLException, ClassNotFoundException {
        Connection junction = connect(dbWay);
        PreparedStatement addStatement = junction.prepareStatement(" INSERT INTO " + Feedback + "(id, data, type, weight, opinion, number) VALUES (?, ?, ?, ?, ?, ?)");
        addStatement.setInt(1, person.getId());
        addStatement.setString(2, String.valueOf(person.getDate()));
        addStatement.setString(3, String.valueOf(person.getType()));
        addStatement.setInt(4, person.getWeight());
        addStatement.setString(5, person.getComment());
        addStatement.setInt(6, person.getOpinionNumber());

        addStatement.executeUpdate();
    }
}
