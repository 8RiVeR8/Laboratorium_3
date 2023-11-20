package UI;

import Model.opinionType;

import java.sql.SQLException;
import java.time.LocalDate;

public interface InteractionInterface {
    void startInteraction() throws SQLException, ClassNotFoundException;
    void displayMenu();
    void processChoice(int choice) throws SQLException, ClassNotFoundException;
    opinionType setOpinionType();
    int setWeight();
    int setId();
    int setNumber();
    int tryCatch();
    LocalDate setDate();
    String setOpinion();
    void showOpinion();
}
