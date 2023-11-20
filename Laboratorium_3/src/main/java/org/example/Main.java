package org.example;
import Logic.OpinionService;
import UI.Interaction;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String dbWay = args[0];
        String dbFeedback = args[1];
        String pythonWay = args[2];

        OpinionService Feedback = new OpinionService(dbWay, dbFeedback);
        Interaction interaction = new Interaction(Feedback, pythonWay);
        interaction.startInteraction();
    }
}