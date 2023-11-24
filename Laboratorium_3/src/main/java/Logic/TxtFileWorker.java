package Logic;
import Model.Person;
import Model.opinionType;
import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class TxtFileWorker {
    static ArrayList<Person> getTxt(String fileName)    {
        ArrayList<Person> persons = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Podziel linię na części, używając odpowiedniego separatora
                String[] parts = line.split(";"); // Załóżmy, że dane są oddzielone średnikami

                // Parsuj dane i twórz obiekt Person
                int id = Integer.parseInt(parts[0]);
                LocalDate date = LocalDate.parse(parts[1]);
                int number = Integer.parseInt(parts[2]);
                opinionType type = opinionType.valueOf(parts[3]); // Zakładam, że Type to enum
                int weight = Integer.parseInt(parts[4]);
                String comment = parts[5];

                // Dodaj obiekt Person do listy
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
                // Tworzenie linii tekstu z danymi obiektu Person
                String line = person.getId() + ";" +
                        person.getDate() + ";" +
                        person.getOpinionNumber() + ";" +
                        person.getType() + ";" +
                        person.getWeight() + ";" +
                        person.getComment();

                // Zapisz linię do pliku
                bw.write(line);
                bw.newLine(); // Dodaj nową linię po każdym obiekcie Person
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
