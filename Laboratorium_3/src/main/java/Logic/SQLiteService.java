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
}
