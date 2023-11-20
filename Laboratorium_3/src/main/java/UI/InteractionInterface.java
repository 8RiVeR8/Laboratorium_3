package UI;

import Model.opinionType;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public interface InteractionInterface {
    void startInteraction() throws SQLException, ClassNotFoundException, IOException, InterruptedException;
    void displayMenu();
    void processChoice(int choice) throws SQLException, ClassNotFoundException, IOException, InterruptedException;
    opinionType setOpinionType();
    int setWeight();
    int setId();
    int setNumber();
    int tryCatch();
    LocalDate setDate();
    String setOpinion();
    void showOpinion();
    void cleanScreen();
}
