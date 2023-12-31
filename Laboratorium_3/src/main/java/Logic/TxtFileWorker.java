package Logic;
import Model.Person;
import Model.opinionType;
import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * TxtFileWorker provides methods to read and write Person objects to a text file.
 * It uses a ';' delimited format for storage.

 * Methods:
 * - getTxt(String fileName): Reads and parses a text file, returning a list of Person objects.
 * - insertOpinion(ArrayList<Person> personList, String fileName): Writes a list of Person objects to a text file.
 *   If the file exists, its content is replaced; otherwise, a new file is created.

 * Note: Date formatting ensures consistent representation.

 * Example Usage:
 * ArrayList<Person> persons = TxtFileWorker.getTxt("input.txt");
 * // Modify persons list...
 * TxtFileWorker.insertOpinion(persons, "output.txt");
 */

public class TxtFileWorker {
    static ArrayList<Person> getTxt(String fileName)    {
        ArrayList<Person> persons = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                int id = Integer.parseInt(parts[0]);
                LocalDate date = LocalDate.parse(parts[1]);
                int number = Integer.parseInt(parts[2]);
                opinionType type = opinionType.valueOf(parts[3]);
                int weight = Integer.parseInt(parts[4]);
                String comment = parts[5];

                Person person = new Person(id, date, type, weight, comment, number);
                persons.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return persons;
    }

    public static void insertOpinion(ArrayList<Person> personList, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))) {
            for (Person person : personList) {
                String line = person.getId() + ";" +
                        person.getDate() + ";" +
                        person.getOpinionNumber() + ";" +
                        person.getType() + ";" +
                        person.getWeight() + ";" +
                        person.getComment();

                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
